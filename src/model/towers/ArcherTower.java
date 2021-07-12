package model.towers;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import model.cards.CellValue.*;
import model.cards.CellValue;
import model.cards.levelEnums.ArcherTowerLevel;

public class ArcherTower extends Tower {
    public ArcherTower(ArcherTowerLevel level , Point2D point) {
        super(CellValue.ARCHERTOWER , point ,0.8f , 7, level.getDamage(), level.getHitPoint());
    }
}
