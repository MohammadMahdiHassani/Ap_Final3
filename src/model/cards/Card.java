package model.cards;

import javafx.scene.image.Image;
import model.GameElement;

import java.io.Serializable;

public abstract class Card extends GameElement implements Serializable {
    private int cost ;

    public Card(CellValue value ,int cost) {
        super(value) ;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
}
