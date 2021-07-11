package model.cards.spells;

import javafx.scene.image.Image;
import model.cards.CellValue;
import model.cards.levelEnums.RageLevel;

public class Rage extends Spell {

    public Rage(RageLevel level) {
        super(CellValue.RAGE , 3, 5 , level.getDuration());
    }
}
