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
        loader.setLocation(getClass().getResource("view/Login1.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        stage.setScene(new Scene(root));
        stage.setResizable(false);//test comment
        stage.setTitle("Clash Royal");
        stage.show();


    }
}
