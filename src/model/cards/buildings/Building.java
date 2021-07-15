package model.cards.buildings;

import model.cards.Card;
import model.cards.CellValue;
import model.cards.troops.attributes.Target;

public abstract class Building extends Card {
    private float hitSpeed ;
    private Target target ;
    private int lifeTime ;
    private int hitPoint ;
    private int damage ;
    public Building(CellValue value , int cost, int range , Target target , float hitSpeed , int lifeTime , int hitPoint , int damage) {
        super(value,range , cost);
        this.hitSpeed = hitSpeed ;
        this.lifeTime = lifeTime ;
        this.target = target ;
        this.hitPoint = hitPoint ;
        this.damage = damage ;

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


    public Target getTarget() {
        return target;
    }
}
