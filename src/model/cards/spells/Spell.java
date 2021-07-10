package model.cards.spells;


import javafx.scene.image.Image;
import model.cards.Card;

public abstract class Spell extends Card {
    private int radius ;
    public Spell(Image image, int cost , int radius) {
        super(image, cost);
        this.radius = radius ;
    }
}
