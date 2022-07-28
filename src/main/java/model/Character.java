package model;

import handler.KeyboardHandler;
import panel.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Character extends Entity{

    GamePanel panel;
    KeyboardHandler keyboard;

    public final int screenX;
    public final int screenY;

    public Character(GamePanel panel, KeyboardHandler keyboard) {
        this.panel = panel;
        this.keyboard = keyboard;

        screenX = panel.screenWidth/2 - (panel.tileSize/2);
        screenY = panel.screenHeight/2 - (panel.tileSize/2);

        area = new Rectangle(8, 16, 32, 32);

        setDefaulValues();
        loadImages();
    }

    public void setDefaulValues(){
        x = panel.tileSize * 7;
        y = panel.tileSize * 5;
        speed = 4;
        direction = "";
    }

    public void loadImages(){
        try {
            stop = ImageIO.read(new File("src/assets/down.png"));
            up = ImageIO.read(new File("src/assets/up.png"));
            down = ImageIO.read(new File("src/assets/down.png"));
            right = ImageIO.read(new File("src/assets/right.png"));
            left = ImageIO.read(new File("src/assets/left.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){

        if (keyboard.up) direction = "up";
        else if (keyboard.down) direction = "down";
        else if (keyboard.left) direction = "left";
        else if (keyboard.right) direction = "right";

        hitting = false;
        panel.hitbox.check(this);
        move();
    }

    public void draw(Graphics2D g2){
        //g2.setColor(Color.PINK);
        //g2.fillRect(x, y, panel.tileSize, panel.tileSize);
        //g2.dispose();

        BufferedImage image = null;

        switch(direction){
            case "up":
                image = up;
                break;
            case "down":
                image = down;
                break;
            case "right":
                image = right;
                break;
            case "left":
                image = left;
                break;
            default:
                image = stop;
                break;
        }

        g2.drawImage(image, screenX, screenY, panel.tileSize, panel.tileSize, null);
    }

    private void move(){
        if(!hitting) {
            switch (direction) {
                case "up":
                    y -= speed;
                    break;
                case "down":
                    y += speed;
                    break;
                case "right":
                    x += speed;
                    break;
                case "left":
                    x -= speed;
                    break;
            }
        }
    }
}
