package controller;

import DataBase.DataHandler;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;
import javafx.util.Callback;
import javafx.util.Duration;
import model.GameElement;
import model.cards.Card;
import model.cards.CardFactory;
import model.cards.CellValue;
import model.cards.levelEnums.KingTowerLevel;
import model.cards.levelEnums.Level;
import model.cards.spells.Spell;
import model.cards.troops.Troop;
import model.game.ArenaModel;
import model.game.GameData;
import model.towers.KingTower;
import model.towers.Tower;
import network.Request;
import network.TransferDataReceive;
import network.TransferDataSend;
import view.ArenaView;

import javafx.scene.input.MouseEvent;

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
    private ArenaModel model;
    private int countTime;


    private Timer timer;

    public ArenaController() {
        model = ArenaModel.getModel();
        arenaView = new ArenaView();
    }

    public void initialize() {
        new Thread(MenuController.transferDataReceive).start();
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
        }else if (card.equals("BARBERIAN")) {
            card1 = CardFactory.makeCard(CellValue.BARBERIAN, level);
        }else if (card.equals("BABY_DRAGON")) {
            card1 = CardFactory.makeCard(CellValue.BABY_DRAGON, level);
        }else if (card.equals("WIZARD")) {
            card1 = CardFactory.makeCard(CellValue.WIZARD, level);
        }else if (card.equals("VALKYRIE")) {
            card1 = CardFactory.makeCard(CellValue.VALKYRIE, level);
        }else if (card.equals("CANNON")) {
            card1 = CardFactory.makeCard(CellValue.CANNON, level);
        }else if (card.equals("INFERNO")) {
            card1 = CardFactory.makeCard(CellValue.INFERNO, level);
        }else if (card.equals("MINI_PEKA")) {
            card1 = CardFactory.makeCard(CellValue.MINI_PEKA, level);
        }else if (card.equals("RAGE")) {
            card1 = CardFactory.makeCard(CellValue.RAGE, level);
        }else if (card.equals("ARROWS")) {
            card1 = CardFactory.makeCard(CellValue.ARROWS, level);
        }else if (card.equals("FIREBALL")) {
            card1 = CardFactory.makeCard(CellValue.FIREBALL, level);
        }

        card1.setPoint(point2D);
        model.getLogic().data.botDeck.add(card1);
        model.getLogic().data.boardElements.add(card1);
}

    private void update() {
        increaseElixir();
        if (MenuController.isOnServer) {
            if (MenuController.transferDataReceive.isReceive()) {
                MenuController.transferDataReceive.setReceive(false);
                updateByOther();
                new Thread(MenuController.transferDataReceive).start();
            }
        }
        model.move();
        arenaView.update(model);
        processAnimations(model.getVectorMap());
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
                    MenuController.transferDataSend.setRequest(new Request(listArmy.getSelectionModel().getSelectedItem().getValue().toString(),DataHandler.getLevel(), x, y));
                    new Thread(MenuController.transferDataSend).start();
                }
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

    private void processAnimations(HashMap<GameElement , ArrayList<Point2D>> animationMap){
        if(animationMap.size() == 0)
            return ;
        for(GameElement i : animationMap.keySet()){
            if(!(i instanceof Spell)){

                Point2D starting_point = i.getPoint();
                Color color ;
                if(i instanceof Tower)
                    color = Color.ORANGE;
                else if(model.isBot(i))
                    color = Color.RED ;
                else
                    color = Color.BLUE ;

                int radius = 0;
                if(i instanceof Tower){
                    if(i instanceof KingTower)
                        radius = 5 ;
                    else
                        radius = 4 ;
                }
                else
                    radius = 3 ;


                for(Point2D ending_point : animationMap.get(i)){
                    arenaView.drawCircle(starting_point , ending_point , radius ,  color );
                }

            }
        }
    }


}