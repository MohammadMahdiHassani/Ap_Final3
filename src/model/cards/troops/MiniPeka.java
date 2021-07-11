package model.cards.troops;

import model.cards.CellValue;
import model.cards.levelEnums.PekkaLevel;
import model.cards.troops.enums.Speed;
import model.cards.troops.enums.Target;

public class MiniPeka extends Troop{
    public MiniPeka(PekkaLevel pekkaLevel) {
        super(CellValue.MINI_PEKA,pekkaLevel.getHitPoint(),pekkaLevel.getDamage(),Speed.FAST,1.8f,Target.GROUND,4,1,0);
    }
}
