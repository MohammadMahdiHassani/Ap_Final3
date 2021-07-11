package model.cards.spells;


import javafx.scene.image.Image;
import model.cards.Card;
import model.cards.CellValue;

public abstract class Spell extends Card {
    private int radius ;
    private float duration ;
    public Spell(CellValue value, int cost , int radius , float duration ) {
        super(value, cost);
        this.radius = radius ;
    }
}
