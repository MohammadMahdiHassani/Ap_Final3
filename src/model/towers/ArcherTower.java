package model.towers;

import javafx.scene.image.Image;
import model.cards.CellValue.*;
import model.cards.CellValue;
import model.cards.levelEnums.ArcherTowerLevel;

public class ArcherTower extends Tower {
    public ArcherTower(ArcherTowerLevel level) {
        super(CellValue.ARCHERTOWER ,0.8f , 7, level.getDamage(), level.getHitPoint());
    }
}
