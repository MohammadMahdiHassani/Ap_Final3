package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.cards.Army;
import model.game.ArenaModel;
import view.ArenaView;

import java.util.Timer;
import java.util.TimerTask;

public class ArenaController {
    final private static double FRAMES_PER_SECOND = 5.0;

    @FXML
    private ListView<Army> listArmy;
    @FXML
    private ArenaView arenaView;
    private ArenaModel model ;

    private Timer timer;

    public ArenaController(){
        model = ArenaModel.getModel();
    }
    public void initialize()
    {
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


        arenaView.update(model);


    }
    public double getBoardWidth() {
        return arenaView.CELL_WIDTH * this.arenaView.getColumnCount();
    }

    public double getBoardHeight() {
        return arenaView.CELL_WIDTH * this.arenaView.getRowCount();
    }
}