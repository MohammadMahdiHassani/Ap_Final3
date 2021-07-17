package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.GameElement;
import model.game.GameData;

import java.io.IOException;

public class EndGameController {

    @FXML
    private ImageView redCrown1;

    @FXML
    private ImageView redCrown3;

    @FXML
    private ImageView redCrown2;

    @FXML
    private ImageView blueCrown3;

    @FXML
    private ImageView blueCrown2;

    @FXML
    private ImageView blueCrown1;

    @FXML
    private Label xpLabel;

    @FXML
    private Label troopyLabel;

    public static int score1;
    public static int score2;
    public static boolean playerWon;

    public void initialize()
    {
        System.out.println("score1: " + score1);
        System.out.println("score2: " + score2);
        if (score2 == 0)
        {
            redCrown3.setImage(null);
            redCrown2.setImage(null);
            redCrown1.setImage(null);
        }
        else if (score2 == 1)
        {
            redCrown3.setImage(null);
            redCrown2.setImage(null);
        }
        else if (score2 == 2)
        {
            redCrown3.setImage(null);
        }
        if (score1 == 0)
        {
            blueCrown3.setImage(null);
            blueCrown2.setImage(null);
            blueCrown1.setImage(null);
        }
        if (score1 == 1)
        {
            blueCrown3.setImage(null);
            blueCrown2.setImage(null);
        }
        else if (score1 == 2)
        {
            blueCrown3.setImage(null);
        }
        if (playerWon)
        {
            xpLabel.setText("+700");
            troopyLabel.setText("+30");
            LoginController.sound.playMain("WIN");
        }
        else {
            xpLabel.setText("+200");
            troopyLabel.setText("0");
            LoginController.sound.playMain("DEATH");
        }
    }
    @FXML
    void back(MouseEvent event) throws IOException {
        LoginController.sound.playMain("CLICK");
        LoginController.count = 1;
        Parent root = FXMLLoader.load(getClass().getResource("../view/Menu.fxml"));
        Stage stage = (Stage) blueCrown1.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

}
