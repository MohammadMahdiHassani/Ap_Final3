package model.cards.spells;


import javafx.scene.image.Image;
import model.cards.Card;
import model.cards.CellValue;

public abstract class Spell extends Card {

    public Spell(CellValue value, int cost , int radius ) {
        super(value,radius , cost);
    }
}
