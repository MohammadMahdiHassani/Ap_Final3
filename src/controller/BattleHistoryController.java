package controller;

import DataBase.DataHandler;
import DataBase.GameHistory;
import com.sun.jdi.IntegerValue;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class BattleHistoryController {
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
    private ListView<GameHistory> listGame;

    public void initialize() {
        TroopyCounter.setText(String.valueOf(DataHandler.getUserData().getTroopy()));
        XPprogressSlider.setProgress(DataHandler.getUserData().getXP() / 2500);
        xp.setText(DataHandler.getUserData().getXP() + "");

        ObservableList<GameHistory> gameHistories = FXCollections.observableArrayList();
        ArrayList<GameHistory> gameHistoryArrayList = new ArrayList<>();
//        if (DataHandler.getUserData().getHistories() != null)
//        {
//            gameHistoryArrayList = DataHandler.getUserData().getHistories();
//            for (int i = 0; i < gameHistoryArrayList.size(); i++)
//            {
//                gameHistories.add(gameHistoryArrayList.get(i));
//            }
//        }
        //else {
            gameHistories.add(new GameHistory("ali","saman","ali"));
        //}
        listGame.setItems(gameHistories);
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
        } else if (event.getSource() == profilePage) {
            return "../view/Profile.fxml";
        } else if (event.getSource() == battleDeck) {
            return "../view/Deck.fxml";
        } else
            return "";

    }

}
