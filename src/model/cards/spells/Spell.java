package model.cards.spells;


import javafx.scene.image.Image;
import model.cards.Card;
import model.cards.CellValue;

import java.io.Serializable;

public abstract class Spell extends Card implements Serializable {
    private int radius ;

    public Spell(CellValue value, int cost , int radius ) {
        super(value, cost);
        this.radius = radius ;

    }
}
