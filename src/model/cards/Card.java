package model.cards;

import javafx.scene.image.Image;
import model.GameElement;

public abstract class Card extends GameElement {
    private int cost ;

    public Card(Image image, int cost) {
        super(image) ;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
}
