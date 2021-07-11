package model.cards.troops;

import model.cards.CellValue;
import model.cards.levelEnums.ValkyrieLevel;
import model.cards.troops.enums.Speed;
import model.cards.troops.enums.Target;

public class Valkyrie extends Troop{

    public Valkyrie(ValkyrieLevel valkyrieLevel) {
        super(CellValue.VALKYRIE,valkyrieLevel.getHitPoint(),valkyrieLevel.getDamage(),Speed.MEDIUM,1.5f,Target.GROUND,4,1,0);
    }
}