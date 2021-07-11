package model.cards.buildings;

import javafx.scene.image.Image;
import model.cards.CellValue;
import model.cards.troops.enums.Target;

public class Cannon extends Building{

    public Cannon(int cost, int rang, Target target, float hitSpeed, int lifeTime) {
        super(CellValue.CANNON ,  cost, rang, target, hitSpeed, lifeTime);
    }
}
