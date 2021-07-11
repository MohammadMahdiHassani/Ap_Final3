package model.cards.troops;

import javafx.scene.image.Image;
import model.cards.CellValue;
import model.cards.Level;
import model.cards.levelEnums.ArcherLevel;
import model.cards.troops.enums.Speed;
import model.cards.troops.enums.Target;

public class Archer extends Troop {

    private ArcherLevel level;
    private static final Image image = new Image("/view/photos/giant.png");

    public Archer(ArcherLevel level) {
        super(CellValue.ARCHER, level.getHitPoint(), level.getDamage(), Speed.MEDIUM, 1.2f, Target.ANY, 5, 1);
    }

}
