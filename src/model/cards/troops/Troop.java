package model.cards.troops;
import model.cards.CellValue;
import model.cards.troops.attributes.Speed;
import model.cards.troops.attributes.Target;
import model.cards.Card;


public abstract class Troop extends Card {


    private int hitPoint ;
    private int damage ;
    private Speed speed ;
    private float hitSpeed ;
    private Target target ;
    private int count ;


    public Troop(CellValue value , int hitPoint, int damage, Speed speed, float hitSpeed, Target target, int cost, int count,int range ) {
        super(value ,range, cost);
        this.hitPoint = hitPoint;
        this.damage = damage;
        this.speed = speed;
        this.hitSpeed = hitSpeed;
        this.target = target;
        this.count = count;
    }

    private float hitSpeedCounter = 0;
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


}
