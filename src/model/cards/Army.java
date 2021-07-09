package model.cards;

import javafx.scene.image.Image;

public class Army {
    private Image image;
    private String name;

    public Army(String image, String name)
    {
        this.image = new Image(image);
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = new Image(image);
    }
}

