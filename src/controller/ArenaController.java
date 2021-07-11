package controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
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
        arenaView = new ArenaView() ;
    }
    public void initialize()
    {
        initializeListArmy();
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
    private void initializeListArmy(){
        listArmy.setItems(model.getDeck());
        listArmy.setCellFactory(
                new Callback<ListView<Card>, ListCell<Card>>() {
                    @Override
                    public ListCell<Card> call(ListView<Card> param) {
                        return new ArmyCellFactory();
                    }
                }
        );
        listArmy.getSelectionModel().selectedItemProperty()
                .addListener(
                        new ChangeListener<Card>() {
                            @Override
                            public void changed(ObservableValue<? extends Card> observable, Card oldValue, Card newValue) {
                                model.setCurrCard(newValue);
                            }
                        }
                );

    }

}