package model.cards.troops;

import model.cards.CellValue;
import model.cards.levelEnums.WizardLevel;
import model.cards.troops.enums.Speed;
import model.cards.troops.enums.Target;

import java.io.Serializable;

public class Wizard extends Troop {
    private WizardLevel wizardLevel;
    public Wizard(WizardLevel wizardLevel) {
        super(CellValue.WIZARD,wizardLevel.getHitPoint(),wizardLevel.getDamage(),Speed.MEDIUM,1.7f,Target.ANY,5,1,5);
        this.wizardLevel = wizardLevel;
    }
}