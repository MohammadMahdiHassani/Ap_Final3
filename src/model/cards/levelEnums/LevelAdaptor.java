package model.cards.levelEnums;

import model.cards.CellValue;
import model.cards.levelEnums.Level;
import model.cards.troops.BabyDragon;


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
                for(WizardLevel i : WizardLevel.values()){
                    if(i.toString().equals(level.toString()))
                        return (WizardLevel) i ;
                }
            case MINI_PEKA:
                for(PekkaLevel i : PekkaLevel.values()){
                    if(i.toString().equals(level.toString()))
                        return (PekkaLevel) i ;
                }
            case ARROWS:
                for(ArrowsLevel i : ArrowsLevel.values()){
                    if(i.toString().equals(level.toString()))
                        return (ArrowsLevel) i ;
                }
            case RAGE:
                for(RageLevel i : RageLevel.values()){
                    if(i.toString().equals(level.toString()))
                        return (RageLevel) i ;
                }
            case FIREBALL:
                for(FireBallLevel i : FireBallLevel.values()){
                    if(i.toString().equals(level.toString()))
                        return (FireBallLevel) i ;
                }
            case CANNON :
                for(CannonLevel i : CannonLevel.values()){
                    if(i.toString().equals(level.toString()))
                        return (CannonLevel) i ;
                }
            case BABY_DRAGON:
                for (BabyDragonLevel i : BabyDragonLevel.values())
                {
                    if (i.toString().equals(level.toString()))
                    {
                        return (BabyDragonLevel) i;
                    }
                }
            case INFERNO:
                for (InfernoLevel i : InfernoLevel.values())
                {
                    if (i.toString().equals(level.toString()))
                    {
                        return (InfernoLevel) i;
                    }
                }
            case VALKYRIE:
                for (ValkyrieLevel i : ValkyrieLevel.values())
                {
                    if (i.toString().equals(level.toString()))
                    {
                        return (ValkyrieLevel) i;
                    }
                }

        }
    return null ;
    }


}
