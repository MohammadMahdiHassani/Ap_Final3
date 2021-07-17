package controller;

import DataBase.DataHandler;
import DataBase.UserData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import network.Client;
import network.TransferDataReceive;
import network.TransferDataSend;

import java.io.IOException;

public class MenuController {
    @FXML
    private Label xp;
    @FXML
    private ImageView gameHistory;
    @FXML
    private ImageView button1v1;
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

    public static TransferDataReceive transferDataReceive;
    public static TransferDataSend transferDataSend;

    public static boolean isOnServer;

    private UserData userData;
    public static Media media;
    public static MediaPlayer mediaPlayer;

    public void initialize() {

        if (LoginController.count == 1) {

            media = new Media(getClass().getResource("/sound/menu.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(20);
            mediaPlayer.play();
        }
        LoginController.count++;
        isOnServer = false;
        TroopyCounter.setText(String.valueOf(DataHandler.getUserData().getTroopy()));
        XPprogressSlider.setProgress((1.0 * DataHandler.getUserData().getXP()) / 2500);
        xp.setText(DataHandler.getUserData().getXP() + "");
    }

    @FXML
    void actionHandler(MouseEvent event) throws IOException, InterruptedException {
        if (event.getSource() == button1v1) {
            mediaPlayer.stop();
            LoginController.sound.playMain("ATTACK_BUTTON");
            isOnServer = true;
            Client client = new Client();

            client.Connect(button1v1);
            transferDataSend = new TransferDataSend(client.getObjectOutputStream());
            transferDataReceive = new TransferDataReceive(client.getObjectInputStream());

            Parent root = FXMLLoader.load(getClass().getResource("../view/Loading.fxml"));
            Stage stage = (Stage) mainPage.getScene().getWindow();
            Stage stage1 = (Stage) mainPage.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
            new Thread(transferDataReceive).start();
            while (true)
            {
                if (transferDataReceive.isReceive())
                {
                    break;
                }
                Thread.sleep(100);
            }

            transferDataReceive.setReceive(false);
            LoginController.sound.playMain("GOTOARENA");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Arena.fxml"));
            fxmlLoader.load();
            root = fxmlLoader.getRoot();
            ArenaController arenaController = fxmlLoader.getController();
            root.setOnMouseClicked(arenaController);
            stage1.setScene(new Scene(root, arenaController.getBoardWidth(), arenaController.getBoardHeight() + arenaController.getPrefHeightList() + arenaController.getElixirProgress().getPrefHeight() + 5));
            stage1.show();
            root.setOnMouseClicked(arenaController);
            stage1.show();

        } else if ((event.getSource() == profilePage || (event.getSource() == battleDeck) || event.getSource() == gameHistory)) {
            LoginController.sound.playMain("CLICK");
            String fxmlAddress = getFxml(event);
            Parent root = FXMLLoader.load(getClass().getResource(fxmlAddress));
            Stage stage = (Stage) mainPage.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        }
        else if ((event.getSource() == battleButton))
        {
            mediaPlayer.stop();
            LoginController.sound.playMain("ATTACK_BUTTON");
            String fxmlAddress = getFxml(event);
            Parent root = FXMLLoader.load(getClass().getResource(fxmlAddress));
            Stage stage = (Stage) mainPage.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        }
    }

    private String getFxml(MouseEvent event) {
        if (event.getSource() == profilePage) {
            return "../view/Profile.fxml";
        } else if (event.getSource() == gameHistory) {
            return "../view/BattleHistory.fxml";
        } else if (event.getSource() == battleDeck) {
            return "../view/Deck.fxml";
        } else if (event.getSource() == battleButton) {
            return "../view/ChooseBot.fxml";
        } else if (event.getSource() == button1v1) {
            return "../view/Loading.fxml";
        } else {
            return "";
        }
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
