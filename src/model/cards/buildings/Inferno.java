package model.cards.buildings;

import model.cards.CellValue;
import model.cards.levelEnums.InfernoLevel;
import model.cards.troops.enums.Target;

import java.io.Serializable;

public class Inferno extends Building implements Serializable {
    public InfernoLevel infernoLevel;

    public Inferno(InfernoLevel infernoLevel) {
        super(CellValue.INFERNO, 5, 6, Target.ANY, 0.4f, 40, infernoLevel.getHitPoint(), infernoLevel.getDamage());
        this.infernoLevel = infernoLevel;
    }

}