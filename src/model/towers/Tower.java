package model.towers;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import model.GameElement;
import model.cards.CellValue;

public abstract class Tower extends GameElement {
    private float hitSpeed ;
    private int rang ;
    private int damage ;
    private int hitPoint ;

    public Tower(CellValue value , Point2D point ,  float hitSpeed, int rang, int damage, int hitPoint) {
        super(value) ;
        this.hitSpeed = hitSpeed;
        this.rang = rang;
        this.damage = damage;
        this.hitPoint = hitPoint;
        this.setPoint(point);
    }
}
