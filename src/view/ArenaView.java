package view;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.cards.CellValue;
import model.game.ArenaModel;

import java.io.FileNotFoundException;

import static model.cards.CellValue.*;

public class ArenaView extends Group {
    public final static double CELL_WIDTH = 30.0;
    private int rowCount;
    private int columnCount;
    private ImageView[][] cellView;

    public ArenaView() {
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

    public void update(ArenaModel model ) {
        CellValue[][] cellValues = null;
        try {
            cellValues = model.getCellValues("map.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (cellValues[i][j] == GRASS) {
                        cellView[i][j].setImage(GRASS.getThumbnailImage());
                }
                else if (cellValues[i][j] == CellValue.ROAD) {
                    cellView[i][j].setImage(ROAD.getThumbnailImage());
                }
                else if (cellValues[i][j] == CellValue.RIVER) {
                    cellView[i][j].setImage(RIVER.getThumbnailImage());
                }
                else if (cellValues[i][j] == CellValue.STONE) {
                    cellView[i][j].setImage(STONE.getThumbnailImage());
                }
                else if (cellValues[i][j] == CellValue.SHRUB) {
                    cellView[i][j].setImage(SHRUB.getThumbnailImage());
                }
                else if (cellValues[i][j] == CellValue.FENCE) {
                    cellView[i][j].setImage(FENCE.getThumbnailImage());
                }
                else if (cellValues[i][j] == CellValue.HOME) {
                    cellView[i][j].setImage(HOME.getThumbnailImage());
                }
                else if (cellValues[i][j] == CellValue.KINGTOWER) {
                    cellView[i][j].setImage(KINGTOWER.getThumbnailImage());
                }
                else if (cellValues[i][j] == CellValue.ARCHERTOWER) {
                    cellView[i][j].setImage(ARROWS.getThumbnailImage());
                }
                else if (cellValues[i][j] == CellValue.TREE) {
                    cellView[i][j].setImage(TREE.getThumbnailImage());
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