package controller;

import DataBase.DataHandler;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import model.GameElement;
import model.cards.Card;
import model.cards.CardFactory;
import model.cards.levelEnums.KingTowerLevel;
import model.game.ArenaModel;
import model.towers.KingTower;
import view.ArenaView;

import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ArenaController implements EventHandler<MouseEvent> {
    final private static double FRAMES_PER_SECOND = 2.0;

    @FXML
    private ListView<Card> listArmy;
    @FXML
    private ArenaView arenaView;

    public ImageView getNextCard() {
        return nextCardImage;
    }

    public Card nextCard;

    @FXML
    private ImageView nextCardImage;

    @FXML
    private ProgressBar elixirProgress;
    private ArenaModel model;
    private int countTime;


    private Timer timer;

    public ArenaController() {
        model = ArenaModel.getModel();
        arenaView = new ArenaView();
    }

    public void initialize() {
        initializeListArmy();
        arenaView.setBackgroundCell(model);

        startTimer();
    }

    private void startTimer() {
        this.timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        update();

                    }
                });
            }
        };
        long frameTimeInMilliseconds = (long) (1000.0 / FRAMES_PER_SECOND);
        this.timer.schedule(task, 0, frameTimeInMilliseconds);
    }

    public void increaseElixir() {
        countTime++;

        if (countTime % 2 == 0) {
            if (elixirProgress.getProgress() < 1) {
                elixirProgress.setProgress(elixirProgress.getProgress() + 0.1);
            }
        }
    }

    private void update() {

        increaseElixir();
        model.move();
        arenaView.update(model);


    }

    public double getBoardWidth() {
        return arenaView.CELL_WIDTH * this.arenaView.getColumnCount();
    }

    public double getBoardHeight() {
        return arenaView.CELL_WIDTH * this.arenaView.getRowCount();
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        Point2D point = new Point2D(mouseEvent.getX(), mouseEvent.getY());
        System.out.println("inside MouseEven handle method");
        int x = (int) (Math.floor(point.getX()) / arenaView.CELL_WIDTH);
        int y = (int) (Math.floor(point.getY()) / arenaView.CELL_WIDTH);

        model.setCurrPoint(new Point2D(x, y));
        System.out.println("MouseEvent setting currPoint to (" + x + "," + y + ")");
        mouseEvent.consume();
        if (listArmy.getSelectionModel().getSelectedItem() != null) {
            if (elixirProgress.getProgress() * 10 >= listArmy.getSelectionModel().getSelectedItem().getCost()) {
                listArmy.getItems().remove(listArmy.getSelectionModel().getSelectedItem());
                listArmy.getSelectionModel().select(null);
            }
        }
    }

    private void initializeListArmy() {
        listArmy.setItems(model.getDeck());
        ArrayList<Card> playerDeck = DataHandler.getUserData().getPlayerDeck();
        ArrayList<Card> savedCard = new ArrayList<>();
        int flag = 0;
        for (int i = 0; i < playerDeck.size(); i++) {
            flag = 0;
            for (int j = 0; j < model.getDeck().size(); j++) {
                if (playerDeck.get(i).getValue().toString().equals(model.getDeck().get(j).getValue())) {
                    flag = 1;
                }
            }
            if (flag == 0) {
                savedCard.add(playerDeck.get(i));
            }
        }
        Random random = new Random();
        nextCard = savedCard.get(random.nextInt(savedCard.size()));
        nextCardImage.setImage(nextCard.getValue().getThumbnailImage());
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
//                                System.out.println("currCard was set to " + newValue.getValue());
                                Card newCard = CardFactory.makeCard(newValue.getValue(), DataHandler.getLevel());
                                model.setCurrCard(newCard) ;
//                                ArrayList<Card> newCards = new ArrayList<>();
//                                for (int i = 0; i < listArmy.getItems().size(); i++)
//                                {
//                                    if (!listArmy.getItems().get(i).getValue().toString().equals(newValue.getValue()))
//                                    {
//                                        newCards.add(listArmy.getItems().get(i));
//                                    }
//                                }
//                                newCards.add(CardFactory.makeCard(nextCard.getValue(),DataHandler.getLevel()));
//                                listArmy.setItems(FXCollections.observableArrayList(newCards));


                            }
                        }
                );

    }

    public double getPrefHeightList() {
        return listArmy.getPrefHeight();
    }

    void cellViewInit() {

    }

    public ProgressBar getElixirProgress() {
        return elixirProgress;
    }


}