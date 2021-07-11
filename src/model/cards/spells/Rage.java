package model.cards.spells;

import javafx.scene.image.Image;
import model.cards.CellValue;
import model.cards.levelEnums.RageLevel;

public class Rage extends Spell {
    private float duration;
    public Rage(RageLevel level) {
        super(CellValue.RAGE , 3, 5);
        duration = level.getDuration();
    }

    public float getDuration() {
        return duration;
    }
}
