package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.util.Callback;
import model.cards.troops.Troop;

public class DeckController {

    @FXML
    private ImageView gameHistory;

    @FXML
    private ImageView battleDeck;

    @FXML
    private ImageView mainPage;

    @FXML
    private ProgressBar XPprogressSlider;

    @FXML
    private Label TroopyCounter;

    @FXML
    private ListView<Troop> mainTroop;

    @FXML
    private ListView<Troop> allTroop;

    private final ObservableList<Troop> troops =
            FXCollections.observableArrayList();

    public void initialize()
    {
        troops.add(new Troop("/view/photos/archers.png","archer"));
        troops.add(new Troop("/view/photos/barbarians.png","barbarians"));
        troops.add(new Troop("/view/photos/baby_dragon.png","baby_dragon"));
        troops.add(new Troop("/view/photos/giant.png","giant"));
        troops.add(new Troop("/view/photos/mini_pekka.png","mini_pekka"));
        troops.add(new Troop("/view/photos/arrows.png","arrows"));
        troops.add(new Troop("/view/photos/rage.png","rage"));
        troops.add(new Troop("/view/photos/wizard.png","wizard"));

        mainTroop.setItems(troops);


        mainTroop.setCellFactory(
                new Callback<ListView<Troop>, ListCell<Troop>>() {
                    @Override
                    public ListCell<Troop> call(ListView<Troop> param) {
                       return new TroopCellFactory();
                    }
                }
        );
    }

    @FXML
    void actionHandler(MouseEvent event) {

    }

}
