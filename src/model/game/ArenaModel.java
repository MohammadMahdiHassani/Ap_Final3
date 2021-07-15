package model.game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import model.GameElement;
import model.cards.Card;
import model.cards.CellValue;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ArenaModel {

     static ArenaModel arenaModel = null ;
     int rowCount;
     int columnCount;
     CellValue[][] BackGroundCellValues;
     GameElement[][] cellValues ;
     GameData gameData ;
     GameLogic logic ;
     HashMap<GameElement , ArrayList<Point2D>> vectorMap ;

    private ArenaModel() {
        rowCount = 21;
        columnCount = 19;
        BackGroundCellValues = new CellValue[rowCount][columnCount];
        gameData = new GameData();
        logic = new GameLogic();
        vectorMap = new HashMap<>() ;

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
        readMap("map.txt");
        return BackGroundCellValues ;
    }
    private void readMap(String address) {
        rowCount = 21;
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
                        if(column < 10)
                            gameData.setLeftBridge(new Point2D(column , row)) ;
                        else
                            gameData.setRightBridge(new Point2D(column , row));
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
                        break;
                    case "a":
                        BackGroundCellValues[row][column] = CellValue.EMPTY;
                        break;
                    case "t":
                        BackGroundCellValues[row][column] = CellValue.TREE;
                        break;
                    case "m":
                        BackGroundCellValues[row][column] = CellValue.HOME;
                        break;
                    case "l":
                        BackGroundCellValues[row][column] = CellValue.TIME;
                        break;
                    case "b":
                        BackGroundCellValues[row][column] = CellValue.B_CROWN;
                        break;
                    case "d" :
                        BackGroundCellValues[row][column] = CellValue.R_CROWN;
                        break;
                    case "p":
                        BackGroundCellValues[row][column] = CellValue.POINT;
                        break;
                    default:
                        BackGroundCellValues[row][column] = CellValue.EMPTY;
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
        logic.preprocessLogic();
        logic.executeLogic() ;
    }

    public GameLogic getLogic() {
        return logic;
    }

    public void setCurrPoint(Point2D point2D){
        logic.setCurrPoint(point2D);
    }
    public ObservableList<Card> getDeck(){

        ArrayList<Card> arr = new ArrayList<>() ;
        for(GameElement i : gameData.playerDeck){
            if(i instanceof Card)
                arr.add((Card) i);
        }
        return FXCollections.observableArrayList(arr) ;
    }
    public void setCurrCard(Card currCard) {
        logic.setCurrCard(currCard);
    }

    public HashMap<GameElement, ArrayList<Point2D>> getVectorMap() {
        return vectorMap;
    }

    public boolean isBot(GameElement ele){
        return gameData.botDeck.contains(ele);
    }

}