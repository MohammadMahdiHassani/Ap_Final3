package model.cards.buildings;

import model.cards.CellValue;
import model.cards.levelEnums.InfernoLevel;
import model.cards.troops.attributes.Target;

public class Inferno extends Building {
    public InfernoLevel infernoLevel;

    public Inferno(InfernoLevel infernoLevel) {
        super(CellValue.INFERNO, 5, 6, Target.ANY, 0.4f, 40, infernoLevel.getHitPoint(), infernoLevel.getDamage());
        this.infernoLevel = infernoLevel;

    }

}