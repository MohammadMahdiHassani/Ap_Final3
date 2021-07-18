package controller;

import DataBase.DataHandler;
import DataBase.UserData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {


    @FXML
    private TextField UserField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Hyperlink SignUpLink;

    @FXML
    private Button LoginButton;
    @FXML
    private Label errorMessage;

    public static MediaPlayer mediaPlayer;
    public static Media media;

    public static Sound sound;

    public static int count;

    public void initialize() {
        count = 0;
        sound = new Sound();
        media = new Media(getClass().getResource("/sound/login.wav").toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(10);
        mediaPlayer.play();
    }

    @FXML
    private void actionHandler(ActionEvent event) throws IOException {
        if (event.getSource() == LoginButton) {


            UserData userData = DataHandler.getUser(UserField.getText(), PasswordField.getText());
            if (userData == null)  {
                errorMessage.setVisible(true);
            } else {
                mediaPlayer.stop();
                count++;
                Parent root = FXMLLoader.load(getClass().getResource("../view/Menu.fxml"));
                Stage stage = (Stage) SignUpLink.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();
            }
        } else if (event.getSource() == SignUpLink) {

            Parent root = FXMLLoader.load(getClass().getResource("../view/SignUp.fxml"));
            Stage stage = (Stage) SignUpLink.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();

        }
    }
}
