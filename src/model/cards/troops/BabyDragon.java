package model.cards.troops;

import model.cards.CellValue;
import model.cards.levelEnums.BabyDragonLevel;
import model.cards.troops.attributes.Speed;
import model.cards.troops.attributes.Target;

public class BabyDragon extends Troop{
    private BabyDragonLevel babyDragonLevel;
    public BabyDragon(BabyDragonLevel babyDragonLevel) {
        super(CellValue.BABY_DRAGON,babyDragonLevel.getHitPoint(),babyDragonLevel.getDamage(),Speed.FAST,1.8f,Target.ANY,5,1,3);
        this.babyDragonLevel = babyDragonLevel;
    }
}