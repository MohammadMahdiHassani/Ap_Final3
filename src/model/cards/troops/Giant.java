package model.cards.troops;

import model.cards.CellValue;
import model.cards.levelEnums.GiantLevel;
import model.cards.troops.enums.Speed;
import model.cards.troops.enums.Target;
import javafx.scene.image.Image;

import java.io.Serializable;


public class Giant extends Troop implements Serializable {

    private GiantLevel level ;

    public Giant(GiantLevel level){
        super(CellValue.GIANT ,level.getHitPoint(), level.getDamage() ,Speed.SLOW ,1.5f , Target.GROUND , 5 , 1,0);
        this.level = level ;
    }

    }
