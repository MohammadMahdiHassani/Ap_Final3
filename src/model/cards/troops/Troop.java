package model.cards.troops;
import model.cards.CellValue;
import model.cards.troops.attributes.Speed;
import model.cards.troops.attributes.Target;
import model.cards.Card;

import java.io.Serializable;


public abstract class Troop extends Card implements Serializable {


    private int hitPoint ;
    private int damage ;
    private int initHit;
    private Speed speed ;
    private float hitSpeed ;
    private Target target ;
    private int count ;
    private boolean areaSplash ;
    private boolean isSpawned ;


    public int getInitHit() {
        return initHit;
    }

    public Troop(CellValue value , int hitPoint, int damage, Speed speed, float hitSpeed, Target target, int cost, int count, int range , boolean areaSplash ) {
        super(value ,range, cost);
        this.hitPoint = hitPoint;
        this.damage = damage;
        this.speed = speed;
        this.hitSpeed = hitSpeed;
        this.target = target;
        this.count = count;
        this.areaSplash = areaSplash ;
        this.isSpawned = false ;
        initHit = hitPoint;
    }

    private float hitSpeedCounter = 0.8f;
    public boolean isAllowedToHit(){
        if(hitSpeedCounter < hitSpeed) {
            hitSpeedCounter += 0.1;
            return false;
        }
        hitSpeedCounter = 0.8f ;
        return true ;
    }
    public void decreaseHitPoint(int hit){
        hitPoint -= hit;
        if(hitPoint < 0){
            this.killCard();
        }
    }



    public int getHitPoint() {
        return hitPoint;
    }

    public int getDamage() {
        return damage;
    }

    public Speed getSpeed() {
        return speed;
    }

    public float getHitSpeed() {
        return hitSpeed;
    }

    public Target getTarget() {
        return target;
    }

    public int getCount() {
        return count;
    }

    public boolean isAreaSplash() {
        return areaSplash;
    }

    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setHitSpeed(float hitSpeed) {
        this.hitSpeed = hitSpeed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    public boolean isSpawned() {
        if(!isSpawned){
            isSpawned = true ;
            return false ;}
        else
            return isSpawned;
    }
}
