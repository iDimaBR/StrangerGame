package model;

import javax.sound.sampled.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Entity {

    public int x, y;
    public int speed;

    public BufferedImage actualImage;
    public BufferedImage up, down, right, left, stop;
    public String direction;

    public Rectangle area;
    public boolean hitting = false;

    public void playSound(String soundFile){
        File lol = new File("src/assets/sounds/" + soundFile);
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(lol));
            clip.start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }



}
