package model.cards.spells;

import model.cards.CellValue;
import model.cards.levelEnums.ArrowsLevel;

import java.io.Serializable;

public class Arrows extends Spell implements Serializable {
    private int damage;
    public Arrows(ArrowsLevel arrowsLevel) {
        super(CellValue.ARROWS,3,4);
        damage = arrowsLevel.getDamage();
    }

    public int getDamage() {
        return damage;
    }
}
