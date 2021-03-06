package model.game;

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

     int rowCount;
     int columnCount;
     CellValue[][] BackGroundCellValues;
     GameElement[][] cellValues ;
     GameData gameData ;
     GameLogic logic ;
     HashMap<GameElement , ArrayList<Point2D>> vectorMap ;

    public ArenaModel() {
        rowCount = 21;
        columnCount = 19;
        BackGroundCellValues = new CellValue[rowCount][columnCount];
        gameData = new GameData();
        logic = new GameLogic(this);
        vectorMap = new HashMap<>() ;

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
                    case "-":
                        BackGroundCellValues[row][column] = CellValue.HROAD;
                        break;
                    case "g":
                        BackGroundCellValues[row][column] = CellValue.GRASS;
                        break;
                    case "r":
                        BackGroundCellValues[row][column] = CellValue.ROAD;

                        break;
                    case "s":
                        BackGroundCellValues[row][column] = CellValue.STONE;
                        break;
                    case "g1":
                        BackGroundCellValues[row][column] = CellValue.GRASS2;
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
                    case "a":
                    case "a2":
                    case "k2":
                        BackGroundCellValues[row][column] = CellValue.ROAD;
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
                        BackGroundCellValues[row][column] = CellValue.BRIDGE;
                        if(column < 10)
                            gameData.setLeftBridge(new Point2D(column , row)) ;
                        else
                            gameData.setRightBridge(new Point2D(column , row));
                        break;
                    case "d" :
                        BackGroundCellValues[row][column] = CellValue.R_CROWN;
                        break;
                    case "g2":
                        BackGroundCellValues[row][column] = CellValue.GRASS3;
                        break;
                    case "*":
                        BackGroundCellValues[row][column] = CellValue.B_CROWN;
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
        logic.executeLogic() ;
    }

    public boolean isBot(GameElement ele){
        return gameData.botDeck.contains(ele);
    }

    public boolean checkForEndCondition(){
        return logic.isKingDead() ;
    }

    public void setCurrPoint(Point2D point2D){
        logic.setCurrPoint(point2D);
    }
    public void setCurrCard(Card currCard) {
        logic.setCurrCard(currCard);
    }
    public HashMap<GameElement, ArrayList<Point2D>> getVectorMap() {
        return vectorMap;
    }
    public GameLogic getLogic() {
        return logic;
    }

    public GameData getGameData() {
        return gameData ;
    }
}