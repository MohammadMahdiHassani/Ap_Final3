import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.GameElement;
import model.cards.CardFactory;
import model.cards.CellValue;
import model.cards.levelEnums.Level;
import model.cards.levelEnums.KingTowerLevel;
import model.towers.KingTower;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase extends Application {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/test1";
    private static final String password = "Mehr_12345";
    private static final String user = "admins";
    private static Statement st ;
    private static Connection con ;

//    private DataBase()  {
//        try {
//            con = DriverManager.getConnection(url , user , password);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        try {
//            st = con.createStatement();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane i = new Pane() ;
        Canvas canvas = new Canvas() ;
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        i.getChildren().add(canvas) ;
        Circle circle = new Circle(30 ,30 , 30);
        circle.setFill(Color.ORANGE);
        i.getChildren().add(circle);
        Duration duration = Duration.millis(2500);
        //Create new translate transition
        TranslateTransition transition = new TranslateTransition(duration, circle);
        //Move in X axis by +200
        transition.setByX(200);
        //Move in Y axis by +100
        transition.setByY(100);
        //Go back to previous position after 2.5 seconds
        transition.setAutoReverse(true);
        //Repeat animation twice
        transition.setCycleCount(2);
        transition.play();
        stage.setScene(new Scene(i , 500, 05));
//        i.getChildren().remove(circle) ;
        stage.show() ;
    }

    public static void main(String [] args) throws SQLException {
        launch(args) ;
    }
}
