package model;

import handler.KeyboardHandler;
import panel.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
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
            actualImage = down;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){

        if (keyboard.up) direction = "up";
        else if (keyboard.down) direction = "down";
        else if (keyboard.left) direction = "left";
        else if (keyboard.right) direction = "right";
        else direction = "";

        hitting = false;
        panel.hitbox.check(this);
        move();
    }

    public void draw(Graphics2D g2){
        switch(direction){
            case "up":
                actualImage = up;
                break;
            case "down":
                actualImage = down;
                break;
            case "right":
                actualImage = right;
                break;
            case "left":
                actualImage = left;
                break;
        }

        g2.drawImage(actualImage, screenX, screenY, panel.tileSize, panel.tileSize, null);
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
                default:
                    break;
            }
        }
    }
}
