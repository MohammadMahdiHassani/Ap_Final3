package model.cards.buildings;

import javafx.scene.image.Image;
import model.cards.troops.enums.Target;

public class Cannon extends Building{

    public Cannon(Image image, int cost, int rang, Target target, float hitSpeed, int lifeTime) {
        super(image, cost, rang, target, hitSpeed, lifeTime);
    }
}
