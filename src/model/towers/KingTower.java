package model.towers;

import javafx.scene.image.Image;
import model.cards.CellValue;

public class KingTower extends Tower{
    public KingTower(float hitSpeed, int rang, int damage, int hitPoint) {
        super(CellValue.KINGTOWER,hitSpeed, rang, damage, hitPoint);
    }
}
