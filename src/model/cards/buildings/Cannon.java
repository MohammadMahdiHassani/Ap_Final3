package model.cards.buildings;

import model.cards.CellValue;
import model.cards.levelEnums.CannonLevel;
import model.cards.troops.enums.Target;

import java.io.Serializable;

public class Cannon extends Building implements Serializable {

    public Cannon(CannonLevel level) {
        super(CellValue.CANNON ,  6, 5, Target.GROUND, 0.8f, 30 , level.getHitPoint(), level.getDamage());
    }
}
