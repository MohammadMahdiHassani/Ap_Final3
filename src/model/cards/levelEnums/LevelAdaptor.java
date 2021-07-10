package model.cards.levelEnums;

import model.cards.CardNum;
import model.cards.Level;


public class LevelAdaptor {
    public static Object adoptLevel(CardNum card , Level level){

        switch (card){
            case ARCHER:
                for(ArcherLevel i : ArcherLevel.values()){
                    if(i.toString().equals(level.toString()))
                        return (ArcherLevel) i ;
                }

            case GIANT:
                for(GiantLevel i : GiantLevel.values()){
                    if(i.toString().equals(level.toString()))
                        return (GiantLevel) i ;
                }

            case WIZARD:
            case BARBERIAN:
            case MINI_PEKA:
            case ARROWS:
            case RAGE:
            case FIREBALL:
        }
    return null ;
    }


}
