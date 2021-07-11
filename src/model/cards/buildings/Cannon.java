package model.cards.buildings;

import javafx.scene.image.Image;
import model.cards.CellValue;
import model.cards.levelEnums.CannonLevels;
import model.cards.troops.enums.Target;

public class Cannon extends Building{

    public Cannon(CannonLevels level) {
        super(CellValue.CANNON ,  6, 5, Target.GROUND, 0.8f, 30 , level.getHitPoint(), level.getDamage());
    }
}
