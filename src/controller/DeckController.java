package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.cards.Army;
import model.cards.CellValue;

import java.io.IOException;


public class DeckController {

    @FXML
    private ImageView profilePage;
    @FXML
    private ImageView doneIcon;
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
    private Army mainLastValue;
    private Army allLastValue;

    public void initialize() {
        mainArmies.add(new Army("/view/photos/archers.png", CellValue.ARCHERTOWER));
        mainArmies.add(new Army("/view/photos/barbarians.png", CellValue.BARBERIAN));
        mainArmies.add(new Army("/view/photos/baby_dragon.png", CellValue.BABY_DRAGON));
        mainArmies.add(new Army("/view/photos/giant.png",  CellValue.GIANT));
        mainArmies.add(new Army("/view/photos/mini_pekka.png", CellValue.MINI_PEKA));
        mainArmies.add(new Army("/view/photos/arrows.png", CellValue.ARROWS));
        mainArmies.add(new Army("/view/photos/rage.png", CellValue.RAGE));
        mainArmies.add(new Army("/view/photos/wizard.png", CellValue.WIZARD));

        allArmies.add(new Army("view/photos/valkyrie.png", CellValue.VALKYRIE));
        allArmies.add(new Army("view/photos/fire_fireball.png", CellValue.FIREBALL));
        allArmies.add(new Army("view/photos/chaos_cannon.png", CellValue.CANNON));
        allArmies.add(new Army("view/photos/inferno_tower.png", CellValue.INFERNO));

        allArmy.setItems(allArmies);
        mainArmy.setItems(mainArmies);
        mainArmy.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Army>() {
                    @Override
                    public void changed(ObservableValue<? extends Army> observable, Army oldValue, Army newValue) {
                        allArmy.getItems().add(newValue);
                        mainArmy.getItems().remove(oldValue);
                        mainLastValue = newValue;
                    }
                }
        );


        allArmy.getSelectionModel().selectedItemProperty()
                .addListener(
                        new ChangeListener<Army>() {
                            @Override
                            public void changed(ObservableValue<? extends Army> observable, Army oldValue, Army newValue) {
                                mainArmy.getItems().add(newValue);
                                allArmy.getItems().remove(oldValue);
                                allLastValue = newValue;
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
    void endClick(MouseEvent event) {
        int mainLastIndex = -1;
        int allLastIndex = -1;
        if (mainLastValue != null) {
            for (int i = 0; i < mainArmy.getItems().size(); i++) {
                if (mainArmy.getItems().get(i).getName().equals(mainLastValue.getName())) {
                    mainLastIndex = i;
                    break;
                }
            }
        }
        if (allLastValue != null) {
            for (int i = 0; i < allArmy.getItems().size(); i++) {
                if (allArmy.getItems().get(i).getName().equals(allLastValue.getName())) {
                    allLastIndex = i;
                    break;
                }
            }
        }
        if (allArmy.getItems().size() > allLastIndex && allLastIndex >= 0) {
            allArmy.getItems().remove(allLastIndex);
            mainArmy.getItems().remove(mainArmy.getItems().size() - 1);
        }
        if (mainArmy.getItems().size() > mainLastIndex && mainLastIndex >= 0) {
            mainArmy.getItems().remove(mainLastIndex);
            allArmy.getItems().remove(allArmy.getItems().size() - 1);
        }
    }

    @FXML
    void actionHandler(MouseEvent event) throws IOException {
        String fxmlAddress = getFxml(event);
        Parent root = FXMLLoader.load(getClass().getResource(fxmlAddress));
        Stage stage = (Stage) mainPage.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();


    }
    private String getFxml(MouseEvent event){
        if(event.getSource() == mainPage){
            return "../view/Menu.fxml";
        }
        else if(event.getSource() == gameHistory){
            return "../view/BattleHistory.fxml";
        }
        else if(event.getSource() == profilePage){
            return "../view/Profile.fxml";
        }
        else
            return "";
    }


}
