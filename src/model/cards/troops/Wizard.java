package model.cards.troops;

import model.cards.CellValue;
import model.cards.levelEnums.WizardLevel;
import model.cards.troops.enums.Speed;
import model.cards.troops.enums.Target;

public class Wizard extends Troop{

    public Wizard(WizardLevel wizardLevel) {
        super(CellValue.WIZARD,wizardLevel.getHitPoint(),wizardLevel.getDamage(),Speed.MEDIUM,1.7f,Target.ANY,5,1,5);
    }
}