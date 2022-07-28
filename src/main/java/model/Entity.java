package model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int x, y;
    public int speed;

    public BufferedImage up, down, right, left, stop;
    public String direction;

    public Rectangle area;
    public boolean hitting = false;



}
