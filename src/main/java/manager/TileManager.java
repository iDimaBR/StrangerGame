package manager;

import model.Tile;
import panel.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TileManager {

    GamePanel panel;
    Tile[] tile;

    public TileManager(GamePanel panel) {
        this.panel = panel;

        tile = new Tile[1];
        getTileImage();
    }

    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("src/assets/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        g2.drawImage(tile[0].image, 0,0, 768, 576, null);
    }
}
