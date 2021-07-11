package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
            Stage stage = (Stage) hardButton.getScene().getWindow();
            stage.setScene(new Scene(root, arenaController.getBoardWidth(), arenaController.getBoardHeight() + arenaController.getPrefHeightList()));
            stage.show();
        }
    }


}
