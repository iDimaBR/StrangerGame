package panel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StartPanel extends JPanel{

    public final int screenWidth = 1280;
    public final int screenHeight = 720;

    private BufferedImage title = ImageIO.read(new File("src/assets/panel/title.png"));
    private ImageIcon startImage = new ImageIcon(new File("src/assets/panel/start_button.png").getPath());
    private JButton startButton = new JButton(startImage);

    JFrame frame;

    public StartPanel(JFrame frame) throws IOException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setVisible(true);
        this.setLayout(null);
        this.frame = frame;

        this.startButton.setBounds(0, 0, 300, 50);
        this.startButton.setLocation(screenHeight/2 + 100, screenWidth/2 - 100);
        this.startButton.setLayout(null);
        this.startButton.setBorderPainted(false);
        this.startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            @Override
            public void mousePressed(MouseEvent e) {
                GamePanel game = new GamePanel(frame);
                game.setVisible(true);
                frame.add(game);
                frame.pack();
                setVisible(false);
            }
        });
        add(startButton);
    }


    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        final Graphics2D g2 = (Graphics2D) graphics;
        g2.drawImage(title, 150, 0, null);
        g2.setColor(Color.black);
    }
}
