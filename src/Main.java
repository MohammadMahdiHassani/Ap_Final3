import controller.ArenaController;
import javafx.application.Application;
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
        //LoginController loginController = new LoginController();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/Login.fxml"));

        loader.load();
//test
        Parent root = loader.getRoot();

//        System.out.println(arenaController.getPrefHeightList());
        stage.setScene(new Scene(root));
        //test comment
        stage.setTitle("Clash Royal");
        stage.show();
    }
}
