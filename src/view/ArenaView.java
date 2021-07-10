package view;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ArenaView extends Group {
    public final static double CELL_WIDTH = 30.0;
    public int rowCount;
    public int columnCount;
    public ImageView [][] cellView;
    public Image grass;
    public Image river;
    public Image bridge;
    public Image stone;
    public Image road;
    public Image roadNW;
    public Image roadNE;
    public Image roadSW;
    public Image roadSE;
    public Image someTree;
    public Image oneTree;
    public Image shrub;
    public Image myKing;
    public Image myArcher;
    public Image otherKing;
    public Image otherArcher;

    public ArenaView()
    {
        bridge = new Image(getClass().getResourceAsStream("/view/photos/valkyrie.png"));
        System.out.println("ali");
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

    public void update()
    {
        for (int i = 0 ; i < rowCount; i++)
        {
            for (int j = 0 ; j < columnCount; j++)
            {
                cellView[i][j].setImage(bridge);

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