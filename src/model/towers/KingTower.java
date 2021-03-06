package model.towers;

import javafx.geometry.Point2D;
import model.cards.CellValue;
import model.cards.levelEnums.KingTowerLevel;

public class KingTower extends Tower{
    KingTowerLevel level ;
    public KingTower(KingTowerLevel level , Point2D point , boolean isMy) {
        super(isMy ? CellValue.MYKINGTOWER : CellValue.BOTKINGTOWER, point , 1, 7 , level.getDamage(), level.getHitPoint());
        this.level = level ;
    }
    public int getLevelHitPoint(){
        return level.getHitPoint() ;
    }
}
