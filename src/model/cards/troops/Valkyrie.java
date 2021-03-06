package model.cards.troops;

import model.cards.CellValue;
import model.cards.levelEnums.ValkyrieLevel;
import model.cards.troops.attributes.Speed;
import model.cards.troops.attributes.Target;

import java.io.Serializable;

public class Valkyrie extends Troop implements Serializable {
    private ValkyrieLevel valkyrieLevel;

    public Valkyrie(ValkyrieLevel valkyrieLevel) {
        super(CellValue.VALKYRIE,valkyrieLevel.getHitPoint(), valkyrieLevel.getDamage(), Speed.MEDIUM, 1.5f, Target.GROUND, 4, 1, 0 , true);
        System.out.println("ali");
        this.valkyrieLevel = valkyrieLevel;
    }
}