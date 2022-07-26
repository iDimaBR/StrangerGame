import panel.GamePanel;

import javax.swing.*;

public class StrangerGame {

    public static void main(String[] args){

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("StrangerGame");

        GamePanel panel = new GamePanel();
        frame.add(panel);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel.startThread();
    }
}
