package handler;

import model.Entity;
import panel.GamePanel;

public class Hitbox {

    GamePanel panel;

    public Hitbox(GamePanel panel) {
        this.panel = panel;
    }

    public void check(Entity entity){
        int entityLeftX = entity.x + entity.area.x;
        int entityRightX = entity.x + entity.area.x + entity.area.width;
        int entityTopY = entity.y + entity.area.y;
        int entityBottomY = entity.y + entity.area.y + entity.area.height;

        int entityLeftCol = entityLeftX/panel.tileSize;
        int entityRightCol = entityRightX/panel.tileSize;
        int entityTopRow = entityTopY/panel.tileSize;
        int entityBottomRow = entityBottomY/panel.tileSize;

        int tile1, tile2;

        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopY - entity.speed)/panel.tileSize;
                tile1 = panel.tileManager.mapText[entityLeftCol][entityTopRow];
                tile2 = panel.tileManager.mapText[entityRightCol][entityTopRow];

                if(panel.tileManager.tile[tile1].collision || panel.tileManager.tile[tile2].collision)
                    entity.hitting = true;
                break;
            case "down":
                entityBottomRow = (entityBottomY + entity.speed)/panel.tileSize;
                tile1 = panel.tileManager.mapText[entityLeftCol][entityBottomRow];
                tile2 = panel.tileManager.mapText[entityRightCol][entityBottomRow];

                if(panel.tileManager.tile[tile1].collision || panel.tileManager.tile[tile2].collision)
                    entity.hitting = true;
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.speed)/panel.tileSize;
                tile1 = panel.tileManager.mapText[entityLeftCol][entityTopRow];
                tile2 = panel.tileManager.mapText[entityRightCol][entityBottomRow];

                if(panel.tileManager.tile[tile1].collision || panel.tileManager.tile[tile2].collision)
                    entity.hitting = true;
                break;
            case "right":
                entityRightCol = (entityRightX + entity.speed)/panel.tileSize;
                tile1 = panel.tileManager.mapText[entityLeftCol][entityTopRow];
                tile2 = panel.tileManager.mapText[entityRightCol][entityBottomRow];

                if(panel.tileManager.tile[tile1].collision || panel.tileManager.tile[tile2].collision)
                    entity.hitting = true;
                break;
        }
    }
}
