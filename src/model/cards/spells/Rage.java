package model.cards.spells;

import javafx.scene.image.Image;
import model.cards.CellValue;
import model.cards.levelEnums.RageLevel;

import java.io.Serializable;

public class Rage extends Spell implements Serializable {
    private float duration;
    public Rage(RageLevel level) {
        super(CellValue.RAGE , 3, 5);
        duration = level.getDuration();
    }

    public float getDuration() {
        return duration;
    }
}
