package view;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.ArenaModel;

import java.io.FileNotFoundException;

public class ArenaView extends Group {
    public final static double CELL_WIDTH = 30.0;
    private int rowCount;
    private int columnCount;
    private ImageView[][] cellView;
    private Image grass;
    private Image river;
    private Image stone;
    private Image road;
    private Image tree;
    private Image shrub;
    private Image king;
    private Image archer;
    private Image home;
    private Image fence;
    private ArenaModel arenaModel;


    public ArenaView() {
        arenaModel = new ArenaModel();
        grass = new Image(getClass().getResourceAsStream("/view/photos/BackGround_Arena/grass.png"));
        river = new Image(getClass().getResourceAsStream("/view/photos/BackGround_Arena/river.png"));
        stone = new Image(getClass().getResourceAsStream("/view/photos/BackGround_Arena/stone.png"));
        road = new Image(getClass().getResourceAsStream("/view/photos/BackGround_Arena/road.png"));
        tree = new Image(getClass().getResourceAsStream("/view/photos/BackGround_Arena/tree.png"));
        shrub = new Image(getClass().getResourceAsStream("/view/photos/BackGround_Arena/shrub.png"));
        king = new Image(getClass().getResourceAsStream("/view/photos/BackGround_Arena/king_tower.png"));
        archer = new Image(getClass().getResourceAsStream("/view/photos/BackGround_Arena/archer_tower.png"));
        home = new Image(getClass().getResourceAsStream("/view/photos/BackGround_Arena/home.png"));
        fence = new Image(getClass().getResourceAsStream("/view/photos/BackGround_Arena/fence.png"));
    }

    private void initializeGrid() {
        cellView = new ImageView[rowCount][columnCount];
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                ImageView imageView = new ImageView();
                imageView.setX(column * CELL_WIDTH);
                imageView.setY(row * CELL_WIDTH);
                imageView.setFitWidth(CELL_WIDTH);
                imageView.setFitHeight(CELL_WIDTH);
                cellView[row][column] = imageView;
                this.getChildren().add(imageView);

            }
        }
    }

    public void update() {
        ArenaModel.CellValue[][] cellValues = null;
        try {
            System.out.println("salam");
            cellValues = arenaModel.getCellValues("map.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (cellValues[i][j] == ArenaModel.CellValue.GRASS) {
                    cellView[i][j].setImage(grass);
                }
                else if (cellValues[i][j] == ArenaModel.CellValue.ROAD) {
                    cellView[i][j].setImage(road);
                }
                else if (cellValues[i][j] == ArenaModel.CellValue.RIVER) {
                    cellView[i][j].setImage(river);
                }
                else if (cellValues[i][j] == ArenaModel.CellValue.STONE) {
                    cellView[i][j].setImage(stone);
                }
                else if (cellValues[i][j] == ArenaModel.CellValue.SHRUB) {
                    cellView[i][j].setImage(shrub);
                }
                else if (cellValues[i][j] == ArenaModel.CellValue.FENCE) {
                    cellView[i][j].setImage(fence);
                }
                else if (cellValues[i][j] == ArenaModel.CellValue.HOME) {
                    cellView[i][j].setImage(home);
                }
                else if (cellValues[i][j] == ArenaModel.CellValue.KING) {
                    cellView[i][j].setImage(king);
                }
                else if (cellValues[i][j] == ArenaModel.CellValue.ARCHER) {
                    cellView[i][j].setImage(archer);
                }
                else if (cellValues[i][j] == ArenaModel.CellValue.TREE) {
                    cellView[i][j].setImage(tree);
                }

            }
        }
    }

    public int getRowCount() {
        return this.rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
        this.initializeGrid();
    }

    public int getColumnCount() {
        return this.columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
        this.initializeGrid();
    }

}