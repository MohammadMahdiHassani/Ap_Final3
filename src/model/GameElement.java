package model;

import javafx.scene.image.Image;

public abstract class GameElement {
    private Image image ;

    public GameElement(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
}
