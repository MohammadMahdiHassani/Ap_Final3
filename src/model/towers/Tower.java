package model.towers;

import javafx.geometry.Point2D;
import model.GameElement;
import model.cards.CellValue;

public abstract class Tower extends GameElement {
    private float hitSpeed ;

    private int damage ;
    private int hitPoint ;

    public Tower(CellValue value , Point2D point ,  float hitSpeed, int rang, int damage, int hitPoint) {
        super(value , rang) ;
        this.hitSpeed = hitSpeed;
        this.damage = damage;
        this.hitPoint = hitPoint;
        this.setPoint(point);
    }

    private int hitSpeedCounter = 0;
    public boolean isAllowedToHit(){
        if(hitSpeedCounter < hitSpeed) {
            hitSpeedCounter += 0.3;
            return false;
        }
        hitSpeedCounter = 0 ;
        return true ;
    }
    public void decreaseHitPoint(int hit){
        hitPoint -= hit;
        if(hitPoint < 0){
            this.killCard();
        }
    }
}
