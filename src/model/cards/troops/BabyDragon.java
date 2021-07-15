package model.cards.troops;

import model.cards.CellValue;
import model.cards.levelEnums.BabyDragonLevel;
import model.cards.troops.attributes.Speed;
import model.cards.troops.attributes.Target;

import java.io.Serializable;

public class BabyDragon extends Troop implements Serializable {
    private BabyDragonLevel babyDragonLevel;
    public BabyDragon(BabyDragonLevel babyDragonLevel) {
        super(CellValue.BABY_DRAGON,babyDragonLevel.getHitPoint(),babyDragonLevel.getDamage(),Speed.FAST,1.8f,Target.ANY,5,1,3 , true );
        this.babyDragonLevel = babyDragonLevel;
    }
}