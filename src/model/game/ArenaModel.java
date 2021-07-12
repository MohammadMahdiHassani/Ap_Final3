package model.game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import model.GameElement;
import model.cards.Card;
import model.cards.CellValue;
import model.cards.levelEnums.ArcherTowerLevel;
import model.cards.levelEnums.Botlevel;
import model.cards.levelEnums.KingTowerLevel;
import model.towers.ArcherTower;
import model.towers.KingTower;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ArenaModel {

     private static ArenaModel arenaModel = null ;
     int rowCount;
     int columnCount;
     CellValue[][] BackGroundCellValues;
     GameElement[][] cellValues ;
     GameData gameData ;
     GameLogic logic ;


    private ArenaModel() {
        rowCount = 20;
        columnCount = 19;
        BackGroundCellValues = new CellValue[rowCount][columnCount];
        initCellValue("map.txt");
        gameData = new GameData();
        logic = new GameLogic() ;

    }
    public static ArenaModel getModel(){
        if(arenaModel == null){
            arenaModel = new ArenaModel();
            return arenaModel ;
        }
        else
            return arenaModel ;
    }
    public CellValue[][] getBackGroundCellValues(){
        initializeBackgroundCellValues("map.txt");
        return BackGroundCellValues ;
    }
    private void initializeBackgroundCellValues(String address) {

        rowCount = 20;
        columnCount = 19;
        BackGroundCellValues = new CellValue[rowCount][columnCount];
        File file = new File(address);
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
                    case "g":
                        BackGroundCellValues[row][column] = CellValue.GRASS;
                        break;
                    case "r":
                        BackGroundCellValues[row][column] = CellValue.ROAD;
                        break;
                    case "s":
                        BackGroundCellValues[row][column] = CellValue.STONE;
                        break;
                    case "h":
                        BackGroundCellValues[row][column] = CellValue.SHRUB;
                        break;
                    case "v":
                        BackGroundCellValues[row][column] = CellValue.RIVER;
                        break;
                    case "f":
                        BackGroundCellValues[row][column] = CellValue.FENCE;
                        break;
                    case "k":
                        BackGroundCellValues[row][column] = CellValue.EMPTY;
//                        cellValues[row][column] = (GameElement) new KingTower(KingTowerLevel.LEVEL_1) ;
                        break;
                    case "a":
                        BackGroundCellValues[row][column] = CellValue.EMPTY;
//                        cellValues[row][column] = new ArcherTower(ArcherTowerLevel.LEVEL_1) ;
                        break;
                    case "t":
                        BackGroundCellValues[row][column] = CellValue.TREE;
                        break;
                    case "m":
                        BackGroundCellValues[row][column] = CellValue.HOME;
                        break;
                    default:
                        BackGroundCellValues[row][column] = CellValue.EMPTY;
                }
                column++;

            }
            row++;
        }

    }
    private void initCellValue(String address){

        rowCount = 21;
        columnCount = 19;
        cellValues = new GameElement[rowCount][columnCount];
        File file = new File(address);
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
//                        BackGroundCellValues[row][column] = CellValue.EMPTY;
                        cellValues[row][column] = (GameElement) new KingTower(KingTowerLevel.LEVEL_1) ;
                        break;
                    case "a":
//                        BackGroundCellValues[row][column] = CellValue.EMPTY;
                        cellValues[row][column] = new ArcherTower(ArcherTowerLevel.LEVEL_1) ;
                        break;

                }
                column++;

            }
            row++;
        }

    }

    public GameElement[][] getCellValues(){
        return cellValues ;
    }
    public void move(){
        logic.executeLogic() ;
    }


    public void setCurrPoint(Point2D point2D){
        logic.setCurrPoint(point2D);
    }
    public ObservableList<Card> getDeck(){
        return FXCollections.observableArrayList(gameData.playerDeck) ;
    }
    public void setCurrCard(Card currCard) {
        logic.setCurrCard(currCard);
    }
    public void setBotlevel(Botlevel botlevel) {
        logic.setBotlevel(botlevel);
    }
}