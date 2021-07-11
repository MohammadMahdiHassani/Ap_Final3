package model.towers;

import javafx.scene.image.Image;
import model.cards.CellValue.*;
import model.cards.CellValue;

public class PrincessTower extends Tower {
    public PrincessTower(float hitSpeed, int rang, int damage, int hitPoint) {
        super(CellValue.ARCHERTOWER ,hitSpeed, rang, damage, hitPoint);
    }
}
