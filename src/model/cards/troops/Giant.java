package model.cards.troops;

import model.cards.CellValue;
import model.cards.levelEnums.GiantLevel;
import model.cards.troops.attributes.Speed;
import model.cards.troops.attributes.Target;


public class Giant extends Troop {

    private GiantLevel level ;

    public Giant(GiantLevel level){
        super(CellValue.GIANT ,level.getHitPoint(), level.getDamage() ,Speed.SLOW ,1.5f , Target.BUILDING , 5 , 1,0);
        this.level = level ;
    }

    }
