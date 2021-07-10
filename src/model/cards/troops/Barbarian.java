package model.cards.troops;

import javafx.scene.image.Image;

import model.cards.levelEnums.BarbarianLevel;
import model.cards.troops.enums.Speed;
import model.cards.troops.enums.Target;

public class Barbarian extends Troop{
    private BarbarianLevel level;
    private static final Image image = new Image("/view/photos/giant.png");

    public Barbarian(BarbarianLevel level) {
        super(image, level.getHitPoint(), level.getDamage(), Speed.MEDIUM, 1.5f, Target.GROUND, 5, 4);
        this.level = level ;
    }

}
