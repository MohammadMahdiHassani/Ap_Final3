package model.cards;

import javafx.scene.image.Image;

public class Army {
    private Image image;
    private CardNum name;

    public Army(String image, CardNum name)
    {
        this.image = new Image(image);
        this.name = name;
    }

    public CardNum getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = new Image(image);
    }
}

