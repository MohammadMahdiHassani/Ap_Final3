package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.util.Callback;
import model.cards.Army;


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
    private ListView<Army> mainArmy;

    @FXML
    private ListView<Army> allArmy;

    private ObservableList<Army> mainArmies =
            FXCollections.observableArrayList();

    private ObservableList<Army> allArmies =
            FXCollections.observableArrayList();

    public void initialize() {
        mainArmies.add(new Army("/view/photos/archers.png", "archer"));
        mainArmies.add(new Army("/view/photos/barbarians.png", "barbarians"));
        mainArmies.add(new Army("/view/photos/baby_dragon.png", "baby_dragon"));
        mainArmies.add(new Army("/view/photos/giant.png", "giant"));
        mainArmies.add(new Army("/view/photos/mini_pekka.png", "mini_pekka"));
        mainArmies.add(new Army("/view/photos/arrows.png", "arrows"));
        mainArmies.add(new Army("/view/photos/rage.png", "rage"));
        mainArmies.add(new Army("/view/photos/wizard.png", "wizard"));

        allArmies.add(new Army("view/photos/valkyrie.png", "valkyrie"));
        allArmies.add(new Army("view/photos/fire_fireball.png", "fire_fireball"));
        allArmies.add(new Army("view/photos/chaos_cannon.png", "chaos_cannon"));
        allArmies.add(new Army("view/photos/inferno_tower.png", "inferno_tower"));

        allArmy.setItems(allArmies);
        mainArmy.setItems(mainArmies);

        mainArmy.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Army>() {
                    @Override
                    public synchronized void changed(ObservableValue<? extends Army> observable, Army oldValue, Army newValue) {
                        allArmy.getItems().add(newValue);
                        mainArmy.getItems().remove(newValue);
                        //System.out.println(mainArmies.size());
                    }
                }
        );

        allArmy.getSelectionModel().selectedItemProperty()
                .addListener(
                        new ChangeListener<Army>() {
                            @Override
                            public synchronized void changed(ObservableValue<? extends Army> observable, Army oldValue, Army newValue) {
                                mainArmy.getItems().add(newValue);
                                allArmy.getItems().remove(newValue);

                            }
                        }
                );


        mainArmy.setCellFactory(
                new Callback<ListView<Army>, ListCell<Army>>() {
                    @Override
                    public ListCell<Army> call(ListView<Army> param) {
                        return new ArmyCellFactory();
                    }
                }
        );

        allArmy.setCellFactory(
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
