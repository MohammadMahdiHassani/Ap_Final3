package model.cards;

import javafx.scene.image.Image;
import model.GameElement;

import java.io.Serializable;

public abstract class Card extends GameElement {
    private int cost ;

    public Card(CellValue value ,int  range ,int cost) {
        super(value , range) ;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

}
