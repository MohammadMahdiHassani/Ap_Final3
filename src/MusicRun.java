import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class MusicRun extends Application {

    public static void main(String[] args) {
        launch(args) ;
    }

    @Override
    public void start(Stage stage) throws Exception {

        Button myButton = new Button("Press me for sound!");
        Media buzzer = new Media(getClass().getResource("/view/photos/579703__brylie__bco-2021-07-07(1).wav").toExternalForm());
        MediaPlayer player = new MediaPlayer(buzzer);
        myButton.setOnAction(event -> {
            if(player.getStatus() != MediaPlayer.Status.PLAYING){
                player.play();
            }
        });
        Group group = new Group() ;
        group.getChildren().add(myButton);
        stage.setScene(new Scene(group));
        stage.show();
    }
}
