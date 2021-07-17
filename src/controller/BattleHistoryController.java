package controller;

import DataBase.DataHandler;
import com.sun.jdi.IntegerValue;
import javafx.beans.InvalidationListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

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

    public void initialize()
    {
        TroopyCounter.setText(String.valueOf(DataHandler.getUserData().getTroopy()));
        XPprogressSlider.setProgress((1.0 * DataHandler.getUserData().getXP()) / 2500);
        xp.setText(DataHandler.getUserData().getXP()+"");
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
