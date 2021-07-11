package model.cards;

import javafx.scene.image.Image;
import model.GameElement;

public abstract class Card extends GameElement {
    private int cost ;

    public Card(CellValue value ,int cost) {
        super(value) ;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
}
