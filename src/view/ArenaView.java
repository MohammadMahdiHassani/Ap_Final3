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
    private ImageView[][] componentView;
    private ImageView cannon;
    public ArenaView() {
    }

    private void initializeGrid() {
        cannon = new ImageView("/view/photos/cannon.png");
        cellView = new ImageView[rowCount][columnCount];
        componentView = new ImageView[rowCount][columnCount];
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                ImageView imageView = new ImageView();
                ImageView imageView1 = new ImageView();
                imageView.setX(column * CELL_WIDTH);
                imageView.setY(row * CELL_WIDTH);
                imageView.setFitWidth(CELL_WIDTH);
                imageView.setFitHeight(CELL_WIDTH);
                imageView1.setX(column * CELL_WIDTH);
                imageView1.setY(row * CELL_WIDTH);
                imageView1.setFitWidth(CELL_WIDTH);
                imageView1.setFitHeight(CELL_WIDTH);
                cellView[row][column] = imageView;
                componentView[row][column] = imageView1;
                this.getChildren().add(imageView);
                this.getChildren().add(imageView1);
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
//        ImageView imageView = new ImageView("view/photos/cannon.png");
//        imageView.maxHeight(30);
//        imageView.setFitHeight(30);
//        imageView.setFitWidth(30);
//        imageView.maxWidth(30);
//        imageView.setX(100);
//        imageView.setY(200);
//        this.getChildren().add(imageView);
        componentView[5][10].setImage(cannon.getImage());
        //componentView[5][10].setImage(new Image("/view/photos/cannon.png"));
//        cannon.setX(200);
//        cannon.setY(200);
//        cannon.setFitWidth(30);
//        cannon.setFitHeight(30);
//        this.getChildren().add(cannon);
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