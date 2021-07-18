package controller;

import DataBase.DataHandler;
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
import model.cards.Card;
import model.cards.CardFactory;

import java.io.IOException;
import java.util.ArrayList;

public class ProfileController {
    @FXML
    private Label xp;
    @FXML
    private ImageView profilePage;
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
    private ListView<Card> mainArmyListView;

    private final ObservableList<Card> mainArmies =
            FXCollections.observableArrayList();

    @FXML
    void initialize() {
        TroopyCounter.setText(String.valueOf(DataHandler.getUserData().getTroopy()));
        XPprogressSlider.setProgress(DataHandler.getUserData().getXP() / 2500);
        xp.setText(DataHandler.getUserData().getXP() + "");
        ArrayList<Card> playerDeck = DataHandler.getUserData().getPlayerDeck();
        if (playerDeck != null) {
            for (int i = 0; i < playerDeck.size(); i++) {
                mainArmies.add(CardFactory.makeCard(playerDeck.get(i).getValue(), DataHandler.getLevel()));
            }
        }
        mainArmyListView.setItems(mainArmies);
        mainArmyListView.setCellFactory(
                new Callback<ListView<Card>, ListCell<Card>>() {
                    @Override
                    public ListCell<Card> call(ListView<Card> param) {
                        return new ArmyCellFactory();
                    }
                }
        );
    }


    @FXML
    void actionHandler(MouseEvent event) throws IOException {
        LoginController.sound.playMain("CLICK");
        String fxmlAddress = getFxml(event);
        Parent root = FXMLLoader.load(getClass().getResource(fxmlAddress));
        Stage stage = (Stage) mainPage.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();


    }

    private String getFxml(MouseEvent event) {
        if (event.getSource() == mainPage) {
            return "../view/Menu.fxml";
        } else if (event.getSource() == gameHistory) {
            return "../view/BattleHistory.fxml";
        } else if (event.getSource() == battleDeck) {
            return "../view/Deck.fxml";
        } else
            return "";
    }
}
