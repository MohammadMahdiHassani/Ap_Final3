package model.game;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import model.cards.Army;
import model.cards.Card;
import model.cards.CellValue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ArenaModel {

    private static ArenaModel arenaModel = null ;
    private int rowCount;
    private int columnCount;
    private CellValue[][] cellValues;
    private Point2D currPoint ;
    private Card currCard ;
    private Card previousCard ;
    private ArrayList<Card> playedCards ;

    private ArenaModel()
    {
        rowCount = 21;
        columnCount = 19;
        cellValues = new CellValue[rowCount][columnCount];
        playedCards = new ArrayList<>() ;
    }
    public static ArenaModel getModel(){
        if(arenaModel == null){
            arenaModel = new ArenaModel();
            return arenaModel ;
        }
        else
            return arenaModel ;
    }
    public CellValue[][] getCellValues(String address) throws FileNotFoundException {

        rowCount = 21;
        columnCount = 19;
        cellValues = new CellValue[rowCount][columnCount];
        File file = new File(address);
        Scanner scanner = new Scanner(file);
        int row = 0;
        int column = 0;
        while (scanner.hasNextLine()) {
            Scanner scannerLine = new Scanner(scanner.nextLine());
            column = 0;
            while (scannerLine.hasNext()) {
                String value = scannerLine.next();

                switch (value) {
                    case "g":
                        cellValues[row][column] = CellValue.GRASS;
                        break;
                    case "r":
                        cellValues[row][column] = CellValue.ROAD;
                        break;
                    case "s":
                        cellValues[row][column] = CellValue.STONE;
                        break;
                    case "h":
                        cellValues[row][column] = CellValue.SHRUB;
                        break;
                    case "v":
                        cellValues[row][column] = CellValue.RIVER;
                        break;
                    case "f":
                        cellValues[row][column] = CellValue.FENCE;
                        break;
                    case "k":
                        cellValues[row][column] = CellValue.KINGTOWER;
                        break;
                    case "a":
                        cellValues[row][column] = CellValue.ARCHERTOWER;
                        break;
                    case "t":
                        cellValues[row][column] = CellValue.TREE;
                        break;
                    case "m":
                        cellValues[row][column] = CellValue.HOME;
                        break;
                    default:
                        cellValues[row][column] = CellValue.EMPTY;
                }
                column++;

            }
            row++;
        }
        return cellValues;
    }
    public void move(){
        if(previousCard == null)
            return ;
        if(currCard.getValue() == CellValue.GIANT){

        }


    }





    public void setCurrPoint(Point2D point2D){
        currPoint = point2D ;
    }

    public void setCurrCard(Card currCard) {
        previousCard = currCard ;
        this.currCard = currCard;
    }
    public ObservableList<Card> getDeck(){
        return FXCollections.observableArrayList(playedCards) ;
    }
}