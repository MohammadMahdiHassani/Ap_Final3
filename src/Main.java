import controller.ArenaController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/Arena.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        ArenaController arenaController = loader.getController();
//        Parent root  = FXMLLoader.load(getClass().getResource("view/Arena.fxml"));
        stage.setScene(new Scene(root,arenaController.getBoardWidth(),arenaController.getBoardHeight()+200));
//        stage.setResizable(false);//test comment
        root.setOnMouseClicked(arenaController);
        stage.setTitle("Clash Royal");
        stage.show();


    }
}
