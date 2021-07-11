package model.cards.buildings;

import javafx.scene.image.Image;
import model.cards.Card;
import model.cards.CellValue;
import model.cards.troops.enums.Target;

public abstract class Building extends Card {
    private float hitSpeed ;
    private Target target ;
    private int rang ;
    private int lifeTime ;
    public Building(CellValue value , int cost, int rang , Target target , float hitSpeed , int lifeTime) {
        super(value, cost);
        this.hitSpeed = hitSpeed ;
        this.lifeTime = lifeTime ;
        this.target = target ;
        this.rang = rang ;
    }
}
