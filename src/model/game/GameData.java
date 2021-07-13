package model.game;

import DataBase.DataHandler;
import javafx.geometry.Point2D;
import model.GameElement;
import model.cards.Card;
import model.cards.CardFactory;
import model.cards.levelEnums.*;
import model.towers.ArcherTower;
import model.towers.KingTower;
import model.towers.Tower;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameData {
    ArrayList<GameElement> playerDeck;
    ArrayList<GameElement> botDeck;
    ArrayList<GameElement> boardElements;
    Botlevel botlevel;
    Point2D leftBridge;
    Point2D rightBridge;

    public GameData() {
        botlevel = DataHandler.getUserData().getBotlevel() ;
        initboardElements();
        initPlayerDeck();
        initBotDeck();
        addingTowersTodecks();
    }

    private void initboardElements() {

        boardElements = new ArrayList<>();
        File file = new File("map.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int row = 0;
        int column = 0;
        while (scanner.hasNextLine()) {
            Scanner scannerLine = new Scanner(scanner.nextLine());
            column = 0;
            while (scannerLine.hasNext()) {
                String value = scannerLine.next();

                switch (value) {

                    case "k":
                        boardElements.add(new KingTower(KingTowerLevel.LEVEL_1, new Point2D(column, row)));
                        break;
                    case "a":
                        boardElements.add(new ArcherTower(ArcherTowerLevel.LEVEL_1, new Point2D(column, row)));
                        break;

                }
                column++;
            }
            row++;
        }

    }

    private void initBotDeck() {
        botDeck = new ArrayList<>();

        switch(botlevel){
            case RANDOME:
                initRandomBotDeck() ; 
            case MEDIUM:
                initMediumBotDeck() ;
            case HARD:
                initHardBotDeck() ; 
        }
    }

    private void initHardBotDeck() {
    }

    private void initMediumBotDeck() {
    }

    private void initRandomBotDeck() {

        ArrayList<Card> deck = DataHandler.getUserData().getPlayerDeck();
        for (int i = 0; i < deck.size(); i++) {

            botDeck.add(CardFactory.makeCard(deck.get(i).getValue(), DataHandler.getLevel()));

        }
    }

    private void initPlayerDeck() {
        playerDeck = new ArrayList<>();
        ArrayList<Card> deck = DataHandler.getUserData().getPlayerDeck();
        playerDeck.addAll (deck);
    }

    private void addingTowersTodecks() {
        for (GameElement i : boardElements) {
            if (i instanceof Tower) {
                if (i.getPoint().getY() < 11)
                    botDeck.add(i);
                else
                    playerDeck.add(i);
            }
        }
    }

    public void setLeftBridge(Point2D leftBridge) {
        this.leftBridge = leftBridge;
    }

    public void setRightBridge(Point2D rightBridge) {
        this.rightBridge = rightBridge;
    }

    public void setBotlevel(Botlevel botlevel) {
        this.botlevel = botlevel;
    }
}
