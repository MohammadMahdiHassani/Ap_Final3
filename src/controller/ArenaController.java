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

    private int count;

    private ArrayList<Card> reminderCard;

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


        System.out.println("MouseEvent setting currPoint to (" + x + "," + y + ")");
        mouseEvent.consume();
        if (listArmy.getSelectionModel().getSelectedItem() != null) {
            if (listArmy.getSelectionModel().getSelectedItem().getCost() <= (elixirProgress.getProgress() * 10)) {
                model.setCurrPoint(new Point2D(x, y));
                elixirProgress.setProgress(elixirProgress.getProgress() - ((double) listArmy.getSelectionModel().getSelectedItem().getCost() / 10));
                removeFromListArmy(listArmy.getSelectionModel().getSelectedIndex());

            }

        }
    }

    public void removeFromListArmy(int index) {
        Random random = new Random();
        count = 0;
        listArmy.getItems().add(nextCard);

        int indexRandom = random.nextInt(reminderCard.size());
        nextCard = reminderCard.get(indexRandom);
        reminderCard.remove(indexRandom);
        reminderCard.add(listArmy.getItems().get(index));
        nextCardImage.setImage(nextCard.getValue().getThumbnailImage());
        listArmy.getItems().remove(index);
        listArmy.getSelectionModel().select(null);

        count = 1;
    }

    private void initializeListArmy() {
        count = 1;
        reminderCard = DataHandler.getUserData().getPlayerDeck();
        System.out.println("size:" + reminderCard.size());
        ArrayList<Card> initFourCard = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(reminderCard.size());
            initFourCard.add(reminderCard.get(index));
            reminderCard.remove(index);
        }
        listArmy.setItems(FXCollections.observableArrayList(initFourCard));
        int index = random.nextInt(reminderCard.size());
        nextCard = reminderCard.get(index);
        reminderCard.remove(index);
        nextCardImage.setImage(nextCard.getValue().getThumbnailImage());


        listArmy.setCellFactory(
                new Callback<ListView<Card>, ListCell<Card>>() {
                    @Override
                    public ListCell<Card> call(ListView<Card> param) {
                        return new ArmyCellFactory();
                    }
                }
        );
        listArmy.getSelectionModel().select(null);
        listArmy.getSelectionModel().selectedItemProperty()
                .addListener(
                        new ChangeListener<Card>() {
                            @Override
                            public void changed(ObservableValue<? extends Card> observable, Card oldValue, Card newValue) {

                                if (count == 1) {
                                    Card newCard = CardFactory.makeCard(newValue.getValue(), DataHandler.getLevel());
                                    model.setCurrCard(newCard);
                                }


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