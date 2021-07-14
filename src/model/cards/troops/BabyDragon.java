package model.cards.troops;

import model.cards.CellValue;
import model.cards.levelEnums.BabyDragonLevel;
import model.cards.levelEnums.BarbarianLevel;
import model.cards.levelEnums.WizardLevel;
import model.cards.troops.enums.Speed;
import model.cards.troops.enums.Target;

import java.io.Serializable;

public class BabyDragon extends Troop implements Serializable {
    private BabyDragonLevel babyDragonLevel;
    public BabyDragon(BabyDragonLevel babyDragonLevel) {
        super(CellValue.BABY_DRAGON,babyDragonLevel.getHitPoint(),babyDragonLevel.getDamage(),Speed.FAST,1.8f,Target.ANY,5,1,3);
        this.babyDragonLevel = babyDragonLevel;
    }
}