package view;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.GameElement;
import model.cards.CellValue;
import model.game.ArenaModel;

import java.io.FileNotFoundException;

import static model.cards.CellValue.*;

public class ArenaView extends Group {
    public final static double CELL_WIDTH = 26.0;
    private Label timeLabel;
    private int rowCount;
    private int columnCount;
    private ImageView[][] cellView;
    private ImageView[][] componentView;

    public ArenaView() {
    }

    private void initializeGrid() {
        timeLabel = new Label("3:45");
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
        timeLabel.setLayoutX((CELL_WIDTH * 17) + 6 );
        timeLabel.setLayoutY(CELL_WIDTH * 0);
        timeLabel.setTextFill(Color.ANTIQUEWHITE);
        timeLabel.setFont(Font.font("adobe heiti std R",18));
        this.getChildren().add(timeLabel);


    }

    public void setBackgroundCell(ArenaModel model) {
        CellValue[][] cellValues = null;
        cellValues = model.getBackGroundCellValues();

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (cellValues[i][j] == GRASS) {
                    cellView[i][j].setImage(GRASS.getThumbnailImage());
                } else if (cellValues[i][j] == CellValue.ROAD) {
                    cellView[i][j].setImage(ROAD.getThumbnailImage());
                } else if (cellValues[i][j] == CellValue.RIVER) {
                    cellView[i][j].setImage(RIVER.getThumbnailImage());
                } else if (cellValues[i][j] == CellValue.STONE) {
                    cellView[i][j].setImage(STONE.getThumbnailImage());
                } else if (cellValues[i][j] == CellValue.SHRUB) {
                    cellView[i][j].setImage(SHRUB.getThumbnailImage());
                } else if (cellValues[i][j] == TREE) {
                    cellView[i][j].setImage(TREE.getThumbnailImage());
                } else if (cellValues[i][j] == CellValue.FENCE) {
                    cellView[i][j].setImage(FENCE.getThumbnailImage());
                } else if (cellValues[i][j] == CellValue.HOME) {
                    cellView[i][j].setImage(HOME.getThumbnailImage());
                } else if (cellValues[i][j] == CellValue.TIME) {
                    cellView[i][j].setImage(TIME.getThumbnailImage());
                }
            }
        }
    }

    public void update(ArenaModel model) {
        GameElement[][] cellValues = null;
        cellValues = model.getCellValues();

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (cellValues[i][j] == null) {
                    componentView[i][j].setImage(null);
                } else if (cellValues[i][j].getValue() == CellValue.KINGTOWER) {
                    componentView[i][j].setImage(KINGTOWER.getThumbnailImage());
                } else if (cellValues[i][j].getValue() == CellValue.ARCHERTOWER) {
                    componentView[i][j].setImage(ARROWS.getThumbnailImage());
                } else if (cellValues[i][j].getValue() == GIANT) {
                    componentView[i][j].setImage(GIANT.getThumbnailImage());
                } else if (cellValues[i][j].getValue() == ARCHER) {
                    componentView[i][j].setImage(ARCHER.getThumbnailImage());
                } else if (cellValues[i][j].getValue() == CANNON) {
                    componentView[i][j].setImage(CANNON.getThumbnailImage());
                } else if (cellValues[i][j].getValue() == BARBERIAN) {
                    componentView[i][j].setImage(BARBERIAN.getActionImage());
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