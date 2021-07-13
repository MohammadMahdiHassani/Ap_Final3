package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.cards.levelEnums.Botlevel;

import java.io.IOException;

public class ChooseBotController {

    @FXML
    private ImageView backButton;

    @FXML
    private ImageView hardButton;

    @FXML
    private ImageView lowButton;

    @FXML
    private ImageView mediumButton;

    @FXML
    void actionHandler(MouseEvent event) throws IOException {
        if (event.getSource() == backButton) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Menu.fxml"));
            fxmlLoader.load();
            Parent root = fxmlLoader.getRoot();
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Arena.fxml"));
            fxmlLoader.load();
            Parent root = fxmlLoader.getRoot();
            ArenaController arenaController = fxmlLoader.getController();
            Botlevel level = processBotButtons(event);
            arenaController.getModel().setBotlevel(level);
            root.setOnMouseClicked(arenaController);
            Stage stage = (Stage) hardButton.getScene().getWindow();
            root.setOnMouseClicked(arenaController);
            stage.setScene(new Scene(root, arenaController.getBoardWidth(), arenaController.getBoardHeight() + arenaController.getPrefHeightList() + arenaController.getElixirProgress().getPrefHeight() + 5));
            stage.show();
        }
    }

    private Botlevel processBotButtons(MouseEvent event){
        if(event.getSource() == hardButton)
            return Botlevel.HARD ;
        else if (event.getSource() == mediumButton)
            return Botlevel.MEDIUM;
        else
            return Botlevel.RANDOME;
    }

}
