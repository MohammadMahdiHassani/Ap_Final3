package model.cards.troops;

import model.cards.CellValue;
import model.cards.levelEnums.PekkaLevel;
import model.cards.troops.attributes.Speed;
import model.cards.troops.attributes.Target;

import java.io.Serializable;

public class MiniPeka extends Troop implements Serializable {
    private PekkaLevel pekkaLevel;
    public MiniPeka(PekkaLevel pekkaLevel) {
        super(CellValue.MINI_PEKA,pekkaLevel.getHitPoint(),pekkaLevel.getDamage(),Speed.FAST,1.8f,Target.GROUND,4,1,0);
        this.pekkaLevel = pekkaLevel;
    }
}
