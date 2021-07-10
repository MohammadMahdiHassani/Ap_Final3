package model.cards;


import model.cards.levelEnums.ArcherLevel;
import model.cards.levelEnums.GiantLevel;
import model.cards.levelEnums.LevelAdaptor;
import model.cards.troops.Archer;
import model.cards.troops.Giant;

public class CardFactory {
    public static Card makeCard(CellValue card, model.cards.Level level) {
        switch (card) {
            case ARCHER:
                ArcherLevel archerLevel = (ArcherLevel) LevelAdaptor.adoptLevel(CellValue.ARCHERTOWER, level) ;
                return new Archer(archerLevel);
            case GIANT:
                GiantLevel giantLevel = (GiantLevel) LevelAdaptor.adoptLevel(card, level);
                return new Giant(giantLevel);
            case WIZARD:
            case BARBERIAN:
            case MINI_PEKA:
            case ARROWS:
            case RAGE:
            case FIREBALL:
        }
        return null;
    }
}
