package model.towers;

import javafx.scene.image.Image;
import model.cards.CellValue;
import model.cards.levelEnums.KingTowerLevel;

public class KingTower extends Tower{
    public KingTower(KingTowerLevel level) {
        super(CellValue.KINGTOWER,1, 7 , level.getDamage(), level.getHitPoint());
    }
}
