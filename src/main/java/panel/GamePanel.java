package panel;

import handler.Hitbox;
import handler.KeyboardHandler;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import manager.TileManager;
import model.Character;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {

    JFrame frame;

    static final int originalTileSize = 16;
    static final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    public Hitbox hitbox = new Hitbox(this);

    Thread thread;
    int FPS = 60;

    public TileManager tileManager = new TileManager(this);
    final KeyboardHandler keyboard = new KeyboardHandler();
    public Character player = new Character(this, keyboard);

    // texts

    Text textFPS;

    public GamePanel(JFrame frame) {
        this.frame = frame;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyboard);
        this.setFocusable(true);
        this.setVisible(true);

        this.thread = new Thread(this);
        this.thread.start();
    }

    public void loadScreen(){
        textFPS = new Text("FPS: 0");
        textFPS.setFont(Font.font("Arial"));
        textFPS.setFill(javafx.scene.paint.Color.WHITE);
        textFPS.setX(0);
        textFPS.setY(20);
    }

    @Override
    public void run() {

        final double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;

        while(thread.isAlive()) {

            currentTime = System.nanoTime();
            timer += (currentTime - lastTime);
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                //textFPS.setText("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){
        player.update();
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        final Graphics2D g2 = (Graphics2D) graphics;
        tileManager.draw(g2);
        player.draw(g2);
    }

    public void returnToStart() {
        try {
            StartPanel game = new StartPanel(frame);
            game.setVisible(true);
            frame.add(game);
            frame.pack();
            setVisible(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
