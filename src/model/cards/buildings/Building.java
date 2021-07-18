package model.cards.buildings;

import model.cards.Card;
import model.cards.CellValue;
import model.cards.troops.attributes.Target;

import java.io.Serializable;

public abstract class Building extends Card implements Serializable {
    private float hitSpeed ;
    private Target target ;
    private int lifeTime ;
    private int hitPoint ;
    private int damage ;
    private boolean isTimerStarted ;
    public Building(CellValue value , int cost, int range , Target target , float hitSpeed , int lifeTime , int hitPoint , int damage) {
        super(value,range , cost);
        this.hitSpeed = hitSpeed ;
        this.lifeTime = lifeTime ;
        this.target = target ;
        this.hitPoint = hitPoint ;
        this.damage = damage ;
        isTimerStarted = false ;

    }
    private float hitSpeedCounter = 0.8f;
    public boolean isAllowedToHit(){
        if(hitSpeedCounter < hitSpeed) {
            hitSpeedCounter += 0.8f;
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
    public void startTimer(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    isTimerStarted = true ;
                    Thread.sleep(lifeTime* 1000L);
                    System.out.println(getValue() + " is sleeping ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                killCard();
            }
        }).start() ;
    }


    public Target getTarget() {
        return target;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isTimerStarted() {
        return isTimerStarted;
    }

    public void setHitSpeed(float hitSpeed) {
        this.hitSpeed = hitSpeed;
    }

    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    public synchronized void setDamage(int damage) {
        this.damage = damage;
    }

    public float getHitSpeed() {
        return hitSpeed;
    }

    public int getHitPoint() {
        return hitPoint;
    }
}
