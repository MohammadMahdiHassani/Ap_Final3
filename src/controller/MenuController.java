package controller;

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

public class MenuController {

    @FXML
    private ImageView gameHistory;

    @FXML
    private ImageView battleDeck;

    @FXML
    private ImageView mainPage;

    @FXML
    private ImageView profilePage;

    @FXML
    private ProgressBar XPprogressSlider;

    @FXML
    private Label TroopyCounter;

    @FXML
    private ImageView battleButton;

    @FXML
    private ImageView button2v2;

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
        if(event.getSource() == profilePage){
            return "../view/Profile.fxml";
        }
        else if(event.getSource() == gameHistory){
            return "../view/BattleHistory.fxml";
        }
        else if(event.getSource() == battleDeck){
            return "../view/Deck.fxml";
        }
        else
            return "";
    }


}
