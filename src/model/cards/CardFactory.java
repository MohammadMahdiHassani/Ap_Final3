package model.cards;


import model.cards.buildings.Cannon;
import model.cards.buildings.Inferno;
import model.cards.levelEnums.*;
import model.cards.spells.Arrows;
import model.cards.spells.FireBall;
import model.cards.spells.Rage;
import model.cards.troops.*;

public class CardFactory {
    public static Card makeCard(CellValue card, model.cards.levelEnums.Level level) {
        switch (card) {
            case ARCHER:
                ArcherLevel archerLevel = (ArcherLevel) LevelAdaptor.adoptLevel(card, level);
                return new Archer(archerLevel);


            case GIANT:
                GiantLevel giantLevel = (GiantLevel) LevelAdaptor.adoptLevel(card, level);
                return new Giant(giantLevel);

            case WIZARD:
                WizardLevel wizardLevel = (WizardLevel) LevelAdaptor.adoptLevel(card, level);
                return new Wizard(wizardLevel);

            case BARBERIAN:
                BarbarianLevel barbarianLevel = (BarbarianLevel) LevelAdaptor.adoptLevel(card, level);
                return new Barbarian(barbarianLevel);

            case RAGE:
                RageLevel rageLevel = (RageLevel) LevelAdaptor.adoptLevel(card, level);
                return new Rage(rageLevel);

            case CANNON:
                CannonLevel cannonLevel = (CannonLevel) LevelAdaptor.adoptLevel(card, level);
                return new Cannon(cannonLevel);

            case MINI_PEKA:
                PekkaLevel pekkaLevel = (PekkaLevel) LevelAdaptor.adoptLevel(card, level);
                return new MiniPeka(pekkaLevel);

            case ARROWS:
                ArrowsLevel arrowsLevel = (ArrowsLevel) LevelAdaptor.adoptLevel(card, level);
                return new Arrows(arrowsLevel);

            case FIREBALL:
                FireBallLevel fireBallLevel = (FireBallLevel) LevelAdaptor.adoptLevel(card, level);
                return new FireBall(fireBallLevel);

            case BABY_DRAGON:
                BabyDragonLevel babyDragonLevel = (BabyDragonLevel) LevelAdaptor.adoptLevel(card, level);
                return new BabyDragon(babyDragonLevel);

            case INFERNO:
                InfernoLevel infernoLevel = (InfernoLevel) LevelAdaptor.adoptLevel(card, level);
                return new Inferno(infernoLevel);

            case VALKYRIE:
                ValkyrieLevel valkyrieLevel = (ValkyrieLevel) LevelAdaptor.adoptLevel(card, level);
                return new Valkyrie(valkyrieLevel);


        }
        return null;
    }
}
