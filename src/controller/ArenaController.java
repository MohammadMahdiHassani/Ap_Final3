package controller;

import DataBase.DataHandler;
import DataBase.GameHistory;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.GameElement;
import model.cards.Card;
import model.cards.CardFactory;
import model.cards.CellValue;
import model.cards.levelEnums.Level;
import model.cards.spells.Rage;
import model.game.ArenaModel;
import model.towers.KingTower;
import model.towers.Tower;
import network.Request;
import network.TransferDataReceive;
import network.TransferDataSend;
import view.ArenaView;

import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.IOException;
import java.util.*;

public class ArenaController implements EventHandler<MouseEvent> {
    final private static double FRAMES_PER_SECOND = 2;

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

    private TransferDataSend transferDataSend;
    private TransferDataReceive transferDataReceive;

    @FXML
    private ImageView nextCardImage;
    @FXML
    private ProgressBar elixirProgress;
    private final ArenaModel model;
    private int countTime;
    private Timer timer;
    private boolean isTimeUp;


    public ArenaController() {
//       ArenaModel.setModel();
        model = new ArenaModel();
        arenaView = new ArenaView();
    }

    public void initialize() {

        new Thread(MenuController.transferDataReceive).start();
        initializeListArmy();
        arenaView.setBackgroundCell(model);
        timer = new Timer();
        startTimer();
        isTimeUp = false;
    }

    private void startTimer() {

        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (!checkEnd())
                            update();
                        else {
                            update();
                            pause();
                            deployEndGameLogic();
                            addGameHistory();
                            LoginController.sound.playMain("END");
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            loadGameOverPage();
                        }
                    }
                });
            }
        };
        long frameTimeInMilliseconds = (long) (1000.0 / FRAMES_PER_SECOND);
        timer.schedule(task, 0, frameTimeInMilliseconds);
    }

    public void addGameHistory()
    {
        String user1 = DataHandler.getUserData().getUserName();
        String user2 = "";
        if (!MenuController.isOnServer)
        {
            user2 = "BOT";
        }
        else {

        }
    }

    private void loadGameOverPage() {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/EndGame.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) elixirProgress.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void deployEndGameLogic() {
        model.getLogic().endGameLogic();
    }

    private void pause() {
        this.timer.cancel();
    }

    private boolean checkEnd() {
        if (isTimeUp || model.checkForEndCondition())
            return true;
        return false;
    }

    private void updateTimer() {
        countTime++;
        if (countTime % 2 == 0) {
            decreaseTime();
            countTime = 0;
        }

    }

    private void decreaseTime() {

        String[] time = ArenaView.getTimeLabel().getText().split(":");
        int second = Integer.parseInt(time[1]);
        int min = Integer.parseInt(time[0]);
        if (min != 0 || second != 0) {
            if (second == 0) {
                min--;
                second = 59;
            }
            second--;
            String newTime = min + ":" + second;
            if (second < 10) {
                newTime = min + ":0" + second;
            }

            if (min == 0) {
                arenaView.setTimeLabel(newTime, Color.RED);
                checkSecond(second);
                return;
            }
            arenaView.setTimeLabel(newTime, Color.WHITE);
        } else {
            isTimeUp = true;
        }
    }

    public void checkSecond(int second) {
        if (second == 10) {
            LoginController.sound.playMain("TEN");
        } else if (second == 9) {
            LoginController.sound.playMain("NINE");
        } else if (second == 8) {
            LoginController.sound.playMain("EIGHT");
        } else if (second == 7) {
            LoginController.sound.playMain("SEVEN");
        } else if (second == 6) {
            LoginController.sound.playMain("SIX");
        } else if (second == 5) {
            LoginController.sound.playMain("FIVE");
        } else if (second == 4) {
            LoginController.sound.playMain("FOUR");
        } else if (second == 3) {
            LoginController.sound.playMain("THREE");
        } else if (second == 2) {
            LoginController.sound.playMain("TWO");
        } else if (second == 1) {
            LoginController.sound.playMain("ONE");
        }
    }

    public void increaseElixir() {

        if (!ArenaView.getTimeLabel().getText().split(":")[0].equals(0)) {
            if (countTime % 4 == 0) {
                if (elixirProgress.getProgress() < 1) {
                    elixirProgress.setProgress(elixirProgress.getProgress() + 0.1);
                }
            }
        }
        else {
            if (countTime % 2 == 0) {
                if (elixirProgress.getProgress() < 1) {
                    elixirProgress.setProgress(elixirProgress.getProgress() + 0.1);
                }
            }
        }
    }

    public void updateByOther() {
        String card = MenuController.transferDataReceive.getCard();
        double x = MenuController.transferDataReceive.getX();
        double y = MenuController.transferDataReceive.getY();
        Level level = MenuController.transferDataReceive.getLevel();
        System.out.println(card + " was received");
        Point2D point2D = new Point2D(x, y);
        Card card1 = null;
        if (card.equals("GIANT")) {
            card1 = CardFactory.makeCard(CellValue.GIANT, level);
        } else if (card.equals("ARCHER")) {
            card1 = CardFactory.makeCard(CellValue.ARCHER, level);
        } else if (card.equals("BARBERIAN")) {
            card1 = CardFactory.makeCard(CellValue.BARBERIAN, level);
        } else if (card.equals("BABY_DRAGON")) {
            card1 = CardFactory.makeCard(CellValue.BABY_DRAGON, level);
        } else if (card.equals("WIZARD")) {
            card1 = CardFactory.makeCard(CellValue.WIZARD, level);
        } else if (card.equals("VALKYRIE")) {
            card1 = CardFactory.makeCard(CellValue.VALKYRIE, level);
        } else if (card.equals("CANNON")) {
            card1 = CardFactory.makeCard(CellValue.CANNON, level);
        } else if (card.equals("INFERNO")) {
            card1 = CardFactory.makeCard(CellValue.INFERNO, level);
        } else if (card.equals("MINI_PEKA")) {
            card1 = CardFactory.makeCard(CellValue.MINI_PEKA, level);
        } else if (card.equals("RAGE")) {
            card1 = CardFactory.makeCard(CellValue.RAGE, level);
        } else if (card.equals("ARROWS")) {
            card1 = CardFactory.makeCard(CellValue.ARROWS, level);
        } else if (card.equals("FIREBALL")) {
            card1 = CardFactory.makeCard(CellValue.FIREBALL, level);
        }

        card1.setPoint(point2D);

        model.getLogic().data.botDeck.add(card1);
        model.getLogic().data.boardElements.add(card1);
        model.getLogic().data.getBotDeck().add(card1);
        sound(card1);
    }

    private void update() {
        updateScore();
        updateTimer();
        increaseElixir();
        serverLogic();
        model.move();
        serverLogic();
        arenaView.update(model);
        serverLogic();
        processAnimations(model.getVectorMap());
    }

    private void updateScore() {
        arenaView.getCrown1().setText(String.valueOf(model.getGameData().getPlayerScore()));
        arenaView.getCrown2().setText(String.valueOf(model.getGameData().getBotScore()));
    }

    private void serverLogic() {
        if (MenuController.isOnServer) {
            if (MenuController.transferDataReceive.isReceive()) {
                MenuController.transferDataReceive.setReceive(false);
                updateByOther();
                new Thread(MenuController.transferDataReceive).start();
            }
        }
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
                if (MenuController.isOnServer) {
                    MenuController.transferDataSend.setRequest(new Request(listArmy.getSelectionModel().getSelectedItem().getValue().toString(), DataHandler.getLevel(),DataHandler.getUserData().getUserName(), x, y));
                    new Thread(MenuController.transferDataSend).start();
                }
                model.setCurrPoint(new Point2D(x, y));
                elixirProgress.setProgress(elixirProgress.getProgress() - ((double) listArmy.getSelectionModel().getSelectedItem().getCost() / 10));
                sound(listArmy.getSelectionModel().getSelectedItem());
                removeFromListArmy(listArmy.getSelectionModel().getSelectedIndex());


            }

        }
    }

    public void sound(GameElement card) {
        if (card.getValue().toString().equals("ARCHER")) {
            LoginController.sound.playMain("ARCHER_CHOOSE");
        } else if (card.getValue().toString().equals("GIANT")) {
            LoginController.sound.playMain("GIANT_CHOOSE");
        } else if (card.getValue().toString().equals("BARBERIAN")) {
            LoginController.sound.playMain("BARBARIAN_CHOOSE");
        } else if (card.getValue().toString().equals("CANNON")) {
            LoginController.sound.playMain("CANNON_CHOOSE");
        } else if (card.getValue().toString().equals("INFERNO")) {
            LoginController.sound.playMain("INFERNO_CHOOSE");
        } else if (card.getValue().toString().equals("VALKYRIE")) {
            LoginController.sound.playMain("VALKYRIE_CHOOSE");
        } else if (card.getValue().toString().equals("MINI_PEKA")) {
            LoginController.sound.playMain("MINIPEKKA_CHOOSE");
        } else if (card.getValue().toString().equals("BABY_DRAGON")) {
            LoginController.sound.playMain("BABYDRAGON_CHOOSE");
        } else if (card.getValue().toString().equals("RAGE")) {
            LoginController.sound.playMain("RAGE");
        } else if (card.getValue().toString().equals("FIREBALL")) {
            LoginController.sound.playMain("FIREBALL_ATTACK");
        } else if (card.getValue().toString().equals("ARROWS")) {
            LoginController.sound.playMain("ARROWS");
        } else if (card.getValue().toString().equals("WIZARD")) {
            LoginController.sound.playMain("WIZARD_CHOOSE");
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
                                    model.setCurrCard(newValue);
                                }


                            }
                        }
                );

    }

    public double getPrefHeightList() {
        return listArmy.getPrefHeight();
    }

    public ProgressBar getElixirProgress() {
        return elixirProgress;
    }

    private void processAnimations(HashMap<GameElement, ArrayList<Point2D>> animationMap) {
        if (animationMap.size() == 0)
            return;
        for (GameElement i : animationMap.keySet()) {

            Point2D starting_point = i.getPoint();
            Color color;
            if (i instanceof Tower)
                color = Color.ORANGE;
            else if (model.isBot(i))
                color = Color.RED;
            else
                color = Color.BLUE;

            int radius = 0;
            if (i instanceof Tower) {
                if (i instanceof KingTower)
                    radius = 5;
                else
                    radius = 4;
            } else
                radius = 3;

            if (i instanceof Rage) {
                arenaView.scaleCircle(animationMap.get(i).get(0), i.getRange(), Color.PURPLE);
                continue;
            }
            for (Point2D ending_point : animationMap.get(i)) {
                arenaView.shootCircles(starting_point, ending_point, radius, color);
            }


        }
    }


}