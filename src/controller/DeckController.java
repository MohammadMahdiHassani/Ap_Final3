package controller;

import DataBase.DataHandler;
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
import model.cards.*;
import model.cards.Level;
import model.game.ArenaModel;

import java.io.IOException;
import java.util.ArrayList;


public class DeckController {
    @FXML
    private Label xp;
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
    private ListView<Card> mainArmy;

    @FXML
    private ListView<Card> allArmy;

    private ObservableList<Card> mainArmies =
            FXCollections.observableArrayList();

    private ObservableList<Card> allArmies =
            FXCollections.observableArrayList();
    private Card mainLastValue;
    private Card allLastValue;

    public void initialize() {
        TroopyCounter.setText(String.valueOf(DataHandler.getUserData().getTroopy()));
        XPprogressSlider.setProgress(DataHandler.getUserData().getXP()/1000);
        xp.setText(DataHandler.getUserData().getXP()+"");
        mainArmies.add(CardFactory.makeCard(CellValue.ARCHERTOWER , Level.LEVEL_1));
        mainArmies.add(CardFactory.makeCard(CellValue.BARBERIAN , Level.LEVEL_1));
//        mainArmies.add(CardFactory.makeCard(CellValue.BABY_DRAGON , Level.LEVEL_1));
        mainArmies.add(CardFactory.makeCard(CellValue.GIANT , Level.LEVEL_1));
//        mainArmies.add(CardFactory.makeCard(CellValue.MINI_PEKA , Level.LEVEL_1));
//        mainArmies.add(CardFactory.makeCard(CellValue.ARROWS , Level.LEVEL_1));
        mainArmies.add(CardFactory.makeCard(CellValue.RAGE , Level.LEVEL_1));
//        mainArmies.add(CardFactory.makeCard(CellValue.WIZARD , Level.LEVEL_1));

//        allArmies.add(CardFactory.makeCard(CellValue.VALKYRIE , Level.LEVEL_1));
//        allArmies.add(CardFactory.makeCard(CellValue.FIREBALL , Level.LEVEL_1));
        allArmies.add(CardFactory.makeCard(CellValue.CANNON , Level.LEVEL_1));
//        allArmies.add(CardFactory.makeCard(CellValue.INFERNO , Level.LEVEL_1));

        allArmy.setItems(allArmies);
        mainArmy.setItems(mainArmies);
        mainArmy.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Card>() {
                    @Override
                    public void changed(ObservableValue<? extends Card> observable, Card oldValue, Card newValue) {
                        allArmy.getItems().add(newValue);
                        mainArmy.getItems().remove(oldValue);
                        mainLastValue = newValue;
                    }
                }
        );


        allArmy.getSelectionModel().selectedItemProperty()
                .addListener(
                        new ChangeListener<Card>() {
                            @Override
                            public void changed(ObservableValue<? extends Card> observable, Card oldValue, Card newValue) {
                                mainArmy.getItems().add(newValue);
                                allArmy.getItems().remove(oldValue);
                                allLastValue = newValue;
                            }
                        }
                );
        mainArmy.setCellFactory(
                new Callback<ListView<Card>, ListCell<Card>>() {
                    @Override
                    public ListCell<Card> call(ListView<Card> param) {
                        return new ArmyCellFactory();
                    }
                }
        );

        allArmy.setCellFactory(
                new Callback<ListView<Card>, ListCell<Card>>() {
                    @Override
                    public ListCell<Card> call(ListView<Card> param) {
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
                if (mainArmy.getItems().get(i).getValue() == mainLastValue.getValue()) {
                    mainLastIndex = i;
                    break;
                }
            }
        }
        if (allLastValue != null) {
            for (int i = 0; i < allArmy.getItems().size(); i++) {
                if (allArmy.getItems().get(i).getValue() == allLastValue.getValue()) {
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
        DataHandler.getUserData().setPlayerDeck(new ArrayList(allArmy.getItems()));
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
