package manager;

import model.Character;
import model.Tile;
import panel.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class TileManager {

    GamePanel panel;
    public Tile[] tile;
    public int mapText[][];

    public TileManager(GamePanel panel) {
        this.panel = panel;

        tile = new Tile[16];
        mapText = new int[panel.maxWorldCol][panel.maxWorldRow];
        getTileImage();
        loadMap();
    }

    public void loadMap(){
         int col = 0;
         int row = 0;

         Path path = Paths.get("src/assets/maps/map-01.txt");
         try (Scanner scanner =  new Scanner(path, StandardCharsets.UTF_8.name())){
             while (scanner.hasNextLine()){
                 while(col < panel.maxWorldCol && row < panel.maxWorldRow){

                     final String line = scanner.nextLine();

                     while(col < panel.maxWorldCol){
                         String numbers[] = line.split(" ");

                         int index = Integer.parseInt(numbers[col]);

                         mapText[col][row] = index;
                         col++;
                     }

                     if(col == panel.maxWorldCol){
                         col = 0;
                         row++;
                     }
                 }
             }
         } catch (IOException ex) {
             throw new RuntimeException(ex);
         }
    }

    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("src/assets/tiles/floor.jpg"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("src/assets/tiles/wall.jpg"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("src/assets/tiles/wall2.jpg"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new File("src/assets/tiles/edge.png"));
            tile[3].collision = true;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(new File("src/assets/tiles/tree.png"));
            tile[4].collision = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){

        final Character player = panel.player;
        int col = 0;
        int row = 0;

        while(col < panel.maxWorldCol && row < panel.maxWorldRow){

            int tileIndex = mapText[col][row];

            int x = col * panel.tileSize;
            int y = row * panel.tileSize;
            int screenX = x - player.x + player.screenX;
            int screenY = y - player.y + player.screenY;

            if(     x + panel.tileSize > player.x - player.screenX &&
                    x - panel.tileSize < player.x + player.screenX &&
                    y + panel.tileSize > player.y - player.screenY &&
                    y - panel.tileSize < player.y + player.screenY){

                g2.drawImage(tile[tileIndex].image, screenX,screenY, panel.tileSize, panel.tileSize, null);
            }
            col++;

            if(col == panel.maxWorldCol){
                col = 0;
                row++;
            }
        }
    }
}
