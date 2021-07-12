package model.game;

import javafx.geometry.Point2D;
import model.GameElement;
import model.cards.Card;
import model.cards.CardFactory;
import model.cards.CellValue;
import model.cards.levelEnums.ArcherLevel;
import model.cards.levelEnums.ArcherTowerLevel;
import model.cards.levelEnums.KingTowerLevel;
import model.cards.levelEnums.Level;
import model.towers.ArcherTower;
import model.towers.KingTower;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameData {
     ArrayList<Card> playerDeck ;
     ArrayList<Card> botDeck ;
     ArrayList<GameElement> boardElements;
     Point2D leftBridge ;
     Point2D rightBridge ;

    public GameData() {
        initBotDeck();
        initPlayerDeck();
        initboardElements() ;
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
                            boardElements.add(new KingTower(KingTowerLevel.LEVEL_1 , new Point2D( column , row )));
                            break;
                        case "a":
                            boardElements.add(new ArcherTower(ArcherTowerLevel.LEVEL_1 , new Point2D( column , row )));
                            break;

                    }
                    column++;
                }
                row++;
            }

        }
    private void initBotDeck(){
        botDeck = new ArrayList<>() ;
    }
    private void initPlayerDeck(){
        playerDeck = new ArrayList<>() ;
        playerDeck.add(CardFactory.makeCard(CellValue.GIANT , Level.LEVEL_1) );
        playerDeck.add(CardFactory.makeCard(CellValue.BARBERIAN , Level.LEVEL_1) );
        playerDeck.add(CardFactory.makeCard(CellValue.ARCHER , Level.LEVEL_1) );
        playerDeck.add(CardFactory.makeCard(CellValue.CANNON , Level.LEVEL_1) );

    }
    public void setLeftBridge(Point2D leftBridge) {
        this.leftBridge = leftBridge;
    }
    public void setRightBridge(Point2D rightBridge) {
        this.rightBridge = rightBridge;
    }

}
