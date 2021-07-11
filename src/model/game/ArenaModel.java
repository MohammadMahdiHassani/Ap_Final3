package model.game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import model.GameElement;
import model.cards.Card;
import model.cards.CellValue;
import model.cards.levelEnums.ArcherTowerLevel;
import model.cards.levelEnums.KingTowerLevel;
import model.towers.ArcherTower;
import model.towers.KingTower;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ArenaModel {

    private static ArenaModel arenaModel = null ;
    private int rowCount;
    private int columnCount;
    private CellValue[][] BackGroundCellValues;
    private GameElement[][] cellValues ;
    private Point2D currPoint ;
    private Card currCard ;
    private GameData gameData ;


    private ArenaModel()
    {
        rowCount = 20;
        columnCount = 19;
        BackGroundCellValues = new CellValue[rowCount][columnCount];
        initCellValue("map.txt");
        gameData = new GameData();


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

        for(int i=0 ; i<rowCount ; i++){
            for(int j=0 ; j<columnCount ; j++){
                if(cellValues[i][j] != null && cellValues[i][j] instanceof Card){
                    if(j>3){
                        Card m = (Card) cellValues[i][j];
                        cellValues[i][j] = null ;
                        cellValues[i][j-1] = m ;
                    }

                }
            }

        }
        if(!checkForPlayerMove()) {
            return ;
        }
        setCurrPointComponentValue(currPoint , currCard);
    }
    private boolean checkForPlayerMove(){
        if(currPoint == null)
            return false;
        if(gameData.playedCards.contains(currCard))
            return false;
        if(!(currPoint.getY()>=11 && currPoint.getY()<=17))
            return false ;
        if(getCurrPointComponentValue() != null)
            return false ;
        return true ;
    }



    public void setCurrPoint(Point2D point2D){
        currPoint = point2D ;
    }
    private CellValue getCurrPointBackgroundValue(){
        return  BackGroundCellValues[(int) currPoint.getY()][(int) currPoint.getX()];
    }
    private GameElement getCurrPointComponentValue(){
        return  cellValues[(int) currPoint.getY()][(int) currPoint.getX()];
    }
    private void setCurrPointComponentValue(Point2D point , GameElement element){
          cellValues[(int) currPoint.getY()][(int) currPoint.getX()] = element;
          gameData.playedCards.add(currCard) ;
          currPoint = null ;
          currCard = null ;
    }
    private void setCurrPointValue(CellValue value){

    }
    public void setCurrCard(Card currCard) {
        this.currCard = currCard;
    }
    public ObservableList<Card> getDeck(){
        return FXCollections.observableArrayList(gameData.playerDeck) ;
    }


}