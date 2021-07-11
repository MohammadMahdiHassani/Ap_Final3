package model.cards;


import model.cards.buildings.Cannon;
import model.cards.levelEnums.*;
import model.cards.spells.Rage;
import model.cards.troops.Archer;
import model.cards.troops.Barbarian;
import model.cards.troops.Giant;

public class CardFactory {
    public static Card makeCard(CellValue card, model.cards.Level level) {
        switch (card) {
            case ARCHER:
                ArcherLevel archerLevel = (ArcherLevel) LevelAdaptor.adoptLevel(CellValue.ARCHER, level) ;
                return new Archer(archerLevel);
            case GIANT:
                GiantLevel giantLevel = (GiantLevel) LevelAdaptor.adoptLevel(card, level);
                return new Giant(giantLevel);
            case WIZARD:

            case BARBERIAN:
                BarbarianLevel barbarianLevel = (BarbarianLevel) LevelAdaptor.adoptLevel(card, level);
                return new Barbarian(barbarianLevel);
            case RAGE:
                RageLevel rageLevel = (RageLevel) LevelAdaptor.adoptLevel(card, level);
                return new Rage(rageLevel);

            case CANNON:
                CannonLevels cannonLevel = (CannonLevels) LevelAdaptor.adoptLevel(card, level);
                return new Cannon(cannonLevel);

            case MINI_PEKA:
            case ARROWS:
            case FIREBALL:
        }
        return null;
    }
}
