package model.cards;

import javafx.scene.image.Image;

public abstract class Card {
    private Image image ;
    private int cost ;

    public Card(Image image, int cost) {
        this.image = image;
        this.cost = cost;
    }

    public Image getImage() {
        return image;
    }

    public int getCost() {
        return cost;
    }
}
