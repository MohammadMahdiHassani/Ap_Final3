package model.cards.troops;

import model.cards.CellValue;
import model.cards.levelEnums.ValkyrieLevel;
import model.cards.troops.enums.Speed;
import model.cards.troops.enums.Target;

import java.io.Serializable;

public class Valkyrie extends Troop implements Serializable {
    private ValkyrieLevel valkyrieLevel;

    public Valkyrie(ValkyrieLevel valkyrieLevel) {
        super(CellValue.VALKYRIE,valkyrieLevel.getHitPoint(), valkyrieLevel.getDamage(), Speed.MEDIUM, 1.5f, Target.GROUND, 4, 1, 0);
        System.out.println("ali");
        this.valkyrieLevel = valkyrieLevel;
    }
}