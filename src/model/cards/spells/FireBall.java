package model.cards.spells;

import model.cards.CellValue;
import model.cards.levelEnums.ArrowsLevel;
import model.cards.levelEnums.FireBallLevel;

import java.io.Serializable;

public class FireBall extends Spell implements Serializable {
    private int damage;
    public FireBall(FireBallLevel fireBallLevel) {
        super(CellValue.FIREBALL,3,4);
        damage = fireBallLevel.getDamage();
    }

    public int getDamage() {
        return damage;
    }
}
