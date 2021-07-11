package model.cards.spells;

import model.cards.CellValue;
import model.cards.levelEnums.ArrowsLevel;
import model.cards.levelEnums.FireBallLevel;

public class FireBall extends Spell{
    private int damage;
    public FireBall(FireBallLevel fireBallLevel) {
        super(CellValue.ARROWS,3,4);
        damage = fireBallLevel.getDamage();
    }

    public int getDamage() {
        return damage;
    }
}
