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

    public Character(GamePanel panel, KeyboardHandler keyboard) {
        this.panel = panel;
        this.keyboard = keyboard;

        setDefaulValues();
        loadImages();
    }

    public void setDefaulValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "up";
    }

    public void loadImages(){
        try {
            up = ImageIO.read(new File("src/assets/up.png"));
            down = ImageIO.read(new File("src/assets/down.png"));
            right = ImageIO.read(new File("src/assets/right.png"));
            left = ImageIO.read(new File("src/assets/left.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){

        if(keyboard.up) {
            y -= speed;
            direction = "up";
        }


        if(keyboard.down) {
            y += speed;
            direction = "down";
        }

        if(keyboard.left) {
            x -= speed;
            direction = "left";
        }

        if(keyboard.right) {
            x += speed;
            direction = "right";
        }
    }

    public void draw(Graphics2D g2){
        //g2.setColor(Color.PINK);
        //g2.fillRect(x, y, panel.tileSize, panel.tileSize);
        //g2.dispose();

        BufferedImage image;

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
                image = up;
                break;
        }

        g2.drawImage(image, x, y, panel.tileSize, panel.tileSize, null);
    }
}
