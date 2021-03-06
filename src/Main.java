import controller.ArenaController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.cards.Card;
import model.cards.CardFactory;
import model.cards.CellValue;
import model.cards.levelEnums.Level;

import java.util.ArrayList;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //LoginController loginController = new LoginController();
        // ;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/Login.fxml"));

        loader.load();
//test
        Parent root = loader.getRoot();
//        ArenaController arenaController = loader.getController();
//        root.setOnMouseClicked(arenaController);

        stage.setScene(new Scene(root ));
        //test comment
        stage.setTitle("Clash Royal");
        stage.show();

    }
}
