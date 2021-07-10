package model.towers;

import javafx.scene.image.Image;
import model.GameElement;

public abstract class Tower extends GameElement {
    private float hitSpeed ;
    private int rang ;
    private int damage ;
    private int hitPoint ;

    public Tower(Image image , float hitSpeed, int rang, int damage, int hitPoint) {
        super(image) ;
        this.hitSpeed = hitSpeed;
        this.rang = rang;
        this.damage = damage;
        this.hitPoint = hitPoint;
    }
}
