package model.cards.troops;

import model.cards.levelEnums.GiantLevel;
import model.cards.troops.enums.Speed;
import model.cards.troops.enums.Target;
import javafx.scene.image.Image;


public class Giant extends Troop {

    private GiantLevel level ;
    private static final Image image = new Image("") ;

    public Giant(GiantLevel level){
        super(image ,level.getHitPoint(), level.getDamage() ,Speed.SLOW ,1.5f , Target.GROUND , 5 , 1);
        this.level = level ;
    }

    }
