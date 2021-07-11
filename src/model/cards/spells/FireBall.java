package model.cards.spells;

import javafx.scene.image.Image;
import model.cards.CellValue;

public class FireBall extends Spell {
    public FireBall(int cost, int radius) {
        super(CellValue.FIREBALL  ,  cost, radius);
    }
}
