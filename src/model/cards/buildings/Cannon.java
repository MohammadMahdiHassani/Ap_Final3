package model.cards.buildings;

import model.cards.CellValue;
import model.cards.levelEnums.CannonLevel;
import model.cards.troops.attributes.Target;

public class Cannon extends Building{

    public Cannon(CannonLevel level) {
        super(CellValue.CANNON ,  6, 5, Target.GROUND, 0.8f, 30 , level.getHitPoint(), level.getDamage());
    }
}
