package controller;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.cards.Card;
import model.cards.troops.Troop;

public class ArmyCellFactory extends ListCell<Card> {
    private HBox hBox = new HBox(8.0);
    private ImageView imageView = new ImageView();

    public ArmyCellFactory() {
        hBox.setAlignment(Pos.CENTER);

        imageView.setPreserveRatio(true);
        imageView.setFitHeight(75);

        hBox.getChildren().add(imageView);

        setPrefWidth(USE_COMPUTED_SIZE);


    }

    @Override
    protected void updateItem(Card item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null)
        {
            setGraphic(null);
        }
        else{
            if (item.getValue() != null) {
                imageView.setImage((item.getValue().getThumbnailImage()));
                setGraphic(hBox);
            }
        }


    }
}

