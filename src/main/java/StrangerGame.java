import panel.GamePanel;
import panel.StartPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class StrangerGame {

    public static JFrame frame = null;

    public static void main(String[] args){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("StrangerGame");
        loadIcon(frame);

        try {
            final StartPanel startPanel = new StartPanel(frame);
            frame.add(startPanel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void loadIcon(JFrame frame){
        try {
            frame.setIconImage(ImageIO.read(new File("src/assets/panel/icon.jpg")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
