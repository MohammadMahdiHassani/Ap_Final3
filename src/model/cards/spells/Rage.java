package model.cards.spells;

import javafx.scene.image.Image;
import model.cards.CellValue;

public class Rage extends Spell {

    public Rage(int cost, int radius) {
        super(CellValue.RAGE , cost, radius);
    }
}
