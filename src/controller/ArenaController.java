package controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.ListView;
import model.cards.Army;
import model.cards.Card;
import model.game.ArenaModel;
import view.ArenaView;

import javafx.scene.input.MouseEvent;

import java.util.Timer;
import java.util.TimerTask;

public class ArenaController implements EventHandler<MouseEvent> {
    final private static double FRAMES_PER_SECOND = 5.0;

    @FXML
    private ListView<Card> listArmy;
    @FXML
    private ArenaView arenaView;
    private ArenaModel model ;

    private Timer timer;

    public ArenaController(){
        model = ArenaModel.getModel();
    }
    public void initialize()
    {
        listArmy.setItems(model.getDeck());
        listArmy.itemsProperty().addListener(new ChangeListener<ObservableList<Card>>() {
                                                 @Override
                                                 public void changed(ObservableValue<? extends ObservableList<Card>> observableValue, ObservableList<Card> cards, ObservableList<Card> t1) {

                                                 }
                                             });

                startTimer();
    }

    private void startTimer(){
        this.timer = new Timer();
        TimerTask task = new TimerTask(){

            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        update() ;
                    }
                });
            }
        };
        long frameTimeInMilliseconds = (long)(1000.0 / FRAMES_PER_SECOND);
        this.timer.schedule(task, 0, frameTimeInMilliseconds);
    }

    private void update(){

        model.move();
        arenaView.update(model);


    }

    public double getBoardWidth() {
        return arenaView.CELL_WIDTH * this.arenaView.getColumnCount();
    }

    public double getBoardHeight() {
        return arenaView.CELL_WIDTH * this.arenaView.getRowCount() + listArmy.getHeight();
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        Point2D point = new Point2D(mouseEvent.getX(), mouseEvent.getY());
        if(mouseEvent.getY() < getBoardHeight() - listArmy.getHeight()) {
            model.setCurrPoint(new Point2D(Math.floor(point.getX() / arenaView.CELL_WIDTH), Math.floor(point.getY() / arenaView.CELL_WIDTH)));
        }
        else{
            mouseEvent.consume();
        }
    }

}