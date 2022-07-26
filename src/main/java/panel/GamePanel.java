package panel;

import handler.KeyboardHandler;
import manager.TileManager;
import model.Character;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    static final int originalTileSize = 16;
    static final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;

    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    int FPS = 60;

    TileManager tileManager = new TileManager(this);
    final KeyboardHandler keyboard = new KeyboardHandler();
    Thread thread;
    Character player = new Character(this, keyboard);

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyboard);
        this.setFocusable(true);
    }

    public void startThread(){
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

        final double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;

        while(thread.isAlive()){

            currentTime = System.nanoTime();
            timer += (currentTime - lastTime);
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        final Graphics2D g2 = (Graphics2D) graphics;
        tileManager.draw(g2);
        player.draw(g2);
    }
}
