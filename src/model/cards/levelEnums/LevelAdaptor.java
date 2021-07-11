package model.cards.levelEnums;

import model.cards.CellValue;
import model.cards.Level;


public class LevelAdaptor {
    public static Object adoptLevel(CellValue card , Level level){

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

            case BARBERIAN:
                for(BarbarianLevel i : BarbarianLevel.values()){
                    if(i.toString().equals(level.toString()))
                        return (BarbarianLevel) i ;
                }
            case WIZARD:
                for(ArcherLevel i : ArcherLevel.values()){
                    if(i.toString().equals(level.toString()))
                        return (ArcherLevel) i ;
                }
            case MINI_PEKA:
                for(ArcherLevel i : ArcherLevel.values()){
                    if(i.toString().equals(level.toString()))
                        return (ArcherLevel) i ;
                }
            case ARROWS:
                for(ArcherLevel i : ArcherLevel.values()){
                    if(i.toString().equals(level.toString()))
                        return (ArcherLevel) i ;
                }
            case RAGE:
                for(RageLevel i : RageLevel.values()){
                    if(i.toString().equals(level.toString()))
                        return (RageLevel) i ;
                }
            case FIREBALL:
                for(ArcherLevel i : ArcherLevel.values()){
                    if(i.toString().equals(level.toString()))
                        return (ArcherLevel) i ;
                }
            case CANNON :
                for(CannonLevel i : CannonLevel.values()){
                    if(i.toString().equals(level.toString()))
                        return (CannonLevel) i ;
                }
        }
    return null ;
    }


}
