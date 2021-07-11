package model;

import javafx.scene.image.Image;
import model.cards.CellValue;

public abstract class GameElement {
    private CellValue value ;

    public GameElement(CellValue value) {
        this.value = value ;
    }

    public CellValue getValue() {
        return value;
    }
}
