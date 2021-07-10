package model.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ArenaModel {
    public enum CellValue {
        GRASS, KING, ARCHER, STONE, RIVER, ROAD, FENCE, SHRUB, TREE,HOME, EMPTY
    }
    private static ArenaModel arenaModel = null ;
    private GameData gameData ;
    private GameLogic logic ;
    private int rowCount;
    private int columnCount;
    private CellValue[][] cellValues;

    private ArenaModel()
    {
        rowCount = 21;
        columnCount = 19;
        cellValues = new CellValue[rowCount][columnCount];
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
                        cellValues[row][column] = CellValue.KING;
                        break;
                    case "a":
                        cellValues[row][column] = CellValue.ARCHER;
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

}