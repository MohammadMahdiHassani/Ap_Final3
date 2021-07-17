package model.game;

import DataBase.DataHandler;
import DataBase.UserData;
import controller.MenuController;
import javafx.geometry.Point2D;
import model.GameElement;
import model.cards.Card;
import model.cards.CardFactory;
import model.cards.CellValue;
import model.cards.levelEnums.*;
import model.towers.ArcherTower;
import model.towers.KingTower;
import model.towers.Tower;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameData {
    private UserData userData ;
    ArrayList<GameElement> playerDeck;
    public ArrayList<GameElement> botDeck;
    ArrayList<GameElement> botGenesis;
    public ArrayList<GameElement> boardElements;
    Botlevel botlevel;
    Point2D leftBridge;
    Point2D rightBridge;
    Level gameLevel ;
    int botScore ;
    int playerScore ;
    boolean playerWon ;

    public GameData() {

        userData = DataHandler.getUserData() ;
        botlevel = userData.getBotlevel();
        gameLevel = DataHandler.getLevel() ;
        initboardElements();
        initPlayerDeck();
        initBotDeck();
        addingTowersTodecks();
        botScore = 0 ;
        playerScore = 0 ;
        botGenesis = new ArrayList<>() ;

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
        if (botlevel == null)
        {
            botlevel = Botlevel.RANDOME;
        }
        switch (botlevel) {
            case RANDOME:
                initRandomBotDeck();
            case MEDIUM:
                initMediumBotDeck();
            case HARD:
                initHardBotDeck();
        }


    }

    private void initHardBotDeck() {

        botGenesis.add(CardFactory.makeCard(CellValue.BABY_DRAGON , gameLevel));
        botGenesis.add(CardFactory.makeCard(CellValue.GIANT , gameLevel));
        botGenesis.add(CardFactory.makeCard(CellValue.RAGE , gameLevel));
        botGenesis.add(CardFactory.makeCard(CellValue.CANNON , gameLevel));
        botGenesis.add(CardFactory.makeCard(CellValue.INFERNO , gameLevel));
        botGenesis.add(CardFactory.makeCard(CellValue.FIREBALL , gameLevel));
        botGenesis.add(CardFactory.makeCard(CellValue.WIZARD , gameLevel));
        botGenesis.add(CardFactory.makeCard(CellValue.ARCHER , gameLevel));

    }

    private void initMediumBotDeck() {
    }

    private void initRandomBotDeck() {

        ArrayList<Card> deck = userData.getPlayerDeck();
        for (int i = 0; i < deck.size(); i++) {
            botGenesis.add(CardFactory.makeCard(deck.get(i).getValue(), DataHandler.getLevel()));

        }
    }

    private void initPlayerDeck() {
        playerDeck = new ArrayList<>();

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

    public ArrayList<GameElement> getBotDeck() {
        return botDeck;
    }

    public int getBotScore() {
        return botScore;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void xpDealer(int x){
        userData.addXP(x);
    }

    public boolean isPlayerWon() {
        return playerWon;
    }

    public void saveToHistory(){
        String Winner ;
        if(isPlayerWon())
            Winner = userData.getUserName();
        else
            Winner = "bot" ;
        userData.addToHistory(userData.getUserName(), "Bot" , Winner);
    }

    public void saveData() {
        DataHandler.saveToFile(userData);
    }
}
