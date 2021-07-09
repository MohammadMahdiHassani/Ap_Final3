package model.cards.troops;

import javafx.scene.image.Image;
import model.cards.troops.enums.Speed;
import model.cards.troops.enums.Target;

public class Archer extends Troop {

    private Level level;
    private static final Image image = new Image("/view/photos/giant.png");

    public Archer(Level level) {
        super(image, level.hitPoint, level.damage, Speed.SLOW, 1.5f, Target.GROUND, 5, 1);
    }

    private enum Level {
        LEVEL_1(126, 2000),
        LEVEL_2(138, 2200),
        LEVEL_3(152, 2420),
        LEVEL_4(167, 2660),
        LEVEL_5(183, 2920);

        private int damage;
        private int hitPoint;

        private Level(int damage, int hitPoint) {
            this.damage = damage;
            this.hitPoint = hitPoint;
        }

    }
}
