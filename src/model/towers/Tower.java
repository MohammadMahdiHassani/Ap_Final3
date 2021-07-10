package model.towers;

public abstract class Tower {
    private float hitSpeed ;
    private int rang ;
    private int damage ;
    private int hitPoint ;

    public Tower(float hitSpeed, int rang, int damage, int hitPoint) {
        this.hitSpeed = hitSpeed;
        this.rang = rang;
        this.damage = damage;
        this.hitPoint = hitPoint;
    }
}
