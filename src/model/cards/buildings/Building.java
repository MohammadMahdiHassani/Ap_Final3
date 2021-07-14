package model.cards.buildings;

import javafx.scene.image.Image;
import model.cards.Card;
import model.cards.CellValue;
import model.cards.troops.enums.Target;

import java.io.Serializable;

public abstract class Building extends Card implements Serializable {
    private float hitSpeed ;
    private Target target ;
    private int range ;
    private int lifeTime ;
    private int hitPoint ;
    private int damage ;
    public Building(CellValue value , int cost, int range , Target target , float hitSpeed , int lifeTime , int hitPoint , int damage) {
        super(value, cost);
        this.hitSpeed = hitSpeed ;
        this.lifeTime = lifeTime ;
        this.target = target ;
        this.range = range ;
        this.hitPoint = hitPoint ;
        this.damage = damage ;
    }
}
