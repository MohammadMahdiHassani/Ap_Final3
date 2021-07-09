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
import javafx.util.Callback;
import model.cards.Army;
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
    private ListView<Army> mainTroop;

    @FXML
    private ListView<Army> allTroop;

    private final ObservableList<Army> troops =
            FXCollections.observableArrayList();

    public void initialize()
    {
        troops.add(new Army("/view/photos/archers.png","archer"));
        troops.add(new Army("/view/photos/barbarians.png","barbarians"));
        troops.add(new Army("/view/photos/baby_dragon.png","baby_dragon"));
        troops.add(new Army("/view/photos/giant.png","giant"));
        troops.add(new Army("/view/photos/mini_pekka.png","mini_pekka"));
        troops.add(new Army("/view/photos/arrows.png","arrows"));
        troops.add(new Army("/view/photos/rage.png","rage"));
        troops.add(new Army("/view/photos/wizard.png","wizard"));

        mainTroop.setItems(troops);


        mainTroop.setCellFactory(
                new Callback<ListView<Army>, ListCell<Army>>() {
                    @Override
                    public ListCell<Army> call(ListView<Army> param) {
                       return new ArmyCellFactory();
                    }
                }
        );
    }

    @FXML
    void actionHandler(MouseEvent event) {

    }

}
