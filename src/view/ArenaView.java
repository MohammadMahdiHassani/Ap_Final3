package view;

import controller.ArenaController;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.GameElement;
import model.cards.CellValue;
import model.cards.troops.BabyDragon;
import model.cards.troops.Troop;
import model.game.ArenaModel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import static model.cards.CellValue.*;

public class ArenaView extends Group {
    public final static double CELL_WIDTH = 26.0;
    private static Label timeLabel;
    private Label crown1;
    private Label crown2;
    private int rowCount;
    private int columnCount;
    private int countTime;
    private ProgressBar elixirProgress;
    private ImageView[][] cellView;
    private ImageView[][] componentView;

    public ArenaView() {

    }

    private void initializeGrid() {
        elixirProgress = new ArenaController().getElixirProgress();
        crown1 = new Label("0");
        crown2 = new Label("0");
        timeLabel = new Label("3:00");
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
        timeLabel.setLayoutX((CELL_WIDTH * 17) + 6);
        timeLabel.setLayoutY(CELL_WIDTH * 0);
        timeLabel.setTextFill(Color.ANTIQUEWHITE);
        timeLabel.setFont(Font.font("adobe heiti std R", 18));

        crown2.setLayoutX(((CELL_WIDTH) * 18) + 10);
        crown2.setLayoutY(((CELL_WIDTH) * 8) + 5);
        crown1.setLayoutX(((CELL_WIDTH) * 18) + 10);
        crown1.setLayoutY(((CELL_WIDTH) * 12) + 5);
        crown2.setFont(Font.font("adobe heiti std R", 18));
        crown1.setFont(Font.font("adobe heiti std R", 18));
        crown1.setTextFill(Color.BLUE);
        crown2.setTextFill(Color.RED);
        this.getChildren().add(timeLabel);
        this.getChildren().add(crown1);
        this.getChildren().add(crown2);


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
                }else if (cellValues[i][j] == CellValue.HROAD) {
                    cellView[i][j].setImage(HROAD.getThumbnailImage());
                } else if (cellValues[i][j] == BRIDGE) {
                    cellView[i][j].setImage(BRIDGE.getThumbnailImage());
                } else if (cellValues[i][j] == CellValue.RIVER) {
                    cellView[i][j].setImage(RIVER.getThumbnailImage());
                } else if (cellValues[i][j] == CellValue.STONE) {
                    cellView[i][j].setImage(STONE.getThumbnailImage());
                } else if (cellValues[i][j] == CellValue.SHRUB) {
//                    cellView[i][j].setImage(SHRUB.getThumbnailImage());
                } else if (cellValues[i][j] == TREE) {
//                    cellView[i][j].setImage(TREE.getThumbnailImage());
                } else if (cellValues[i][j] == CellValue.FENCE) {
//                    cellView[i][j].setImage(FENCE.getThumbnailImage());
                } else if (cellValues[i][j] == CellValue.HOME) {
                    cellView[i][j].setImage(HOME.getThumbnailImage());
                } else if (cellValues[i][j] == CellValue.TIME) {
                    cellView[i][j].setImage(TIME.getThumbnailImage());
                } else if (cellValues[i][j] == B_CROWN) {
                    cellView[i][j].setImage(B_CROWN.getThumbnailImage());
                } else if (cellValues[i][j] == R_CROWN) {
                    cellView[i][j].setImage(R_CROWN.getThumbnailImage());
                } else if (cellValues[i][j] == POINT) {
                    cellView[i][j].setImage(POINT.getThumbnailImage());
                }
            }
        }
    }

    public void decreaseTime() {

        String[] time = timeLabel.getText().split(":");
        int second = Integer.parseInt(time[1]);
        int min = Integer.parseInt(time[0]);
        if (min != 0 || second != 0) {
            if (second == 0) {
                min--;
                second = 59;
            }
            second--;
            String newTime = min + ":" + second;
            if (second < 10) {
                newTime = min + ":0" + second;
            }
            if (min == 0 && second == 0) {
                //final condition
            }
            if (min == 0) {
                timeLabel.setTextFill(Color.RED);
            }
            timeLabel.setText(newTime);
        }
    }


    public void update(ArenaModel model) {
        countTime++;
        if (countTime % 2 == 0) {
            decreaseTime();
            countTime = 0;
        }
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
                    componentView[i][j].setImage(GIANT.getActionImage());
                } else if (cellValues[i][j].getValue() == ARCHER) {
                    componentView[i][j].setImage(ARCHER.getThumbnailImage());
                } else if (cellValues[i][j].getValue() == CANNON) {
                    componentView[i][j].setImage(CANNON.getThumbnailImage());
                } else if (cellValues[i][j].getValue() == BARBERIAN) {
                    componentView[i][j].setImage(BARBERIAN.getActionImage());
                } else if (cellValues[i][j].getValue() == FIREBALL) {
                    componentView[i][j].setImage(FIREBALL.getActionImage());
                } else if (cellValues[i][j].getValue() == WIZARD) {
                    componentView[i][j].setImage(WIZARD.getActionImage());
                } else if (cellValues[i][j].getValue() == MINI_PEKA) {
                    componentView[i][j].setImage(MINI_PEKA.getActionImage());
                } else if (cellValues[i][j].getValue() == VALKYRIE) {
                    componentView[i][j].setImage(VALKYRIE.getActionImage());
                } else if (cellValues[i][j].getValue() == RAGE) {
                    componentView[i][j].setImage(RAGE.getActionImage());
                } else if (cellValues[i][j].getValue() == BABY_DRAGON) {
                    componentView[i][j].setImage(BABY_DRAGON.getActionImage());
                }  else if (cellValues[i][j].getValue() == INFERNO) {
                    componentView[i][j].setImage(INFERNO.getActionImage());
                } else if (cellValues[i][j].getValue() == ARROWS) {
                    componentView[i][j].setImage(ARROWS.getActionImage());
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

    public void setTimeLabelText(String text) {
        this.timeLabel.setText(text);
    }

    public static Label getTimeLabel() {
        return timeLabel;
    }

    public void shootCircles(Point2D starting_point, Point2D ending_point, int radius, Color color) {

        Circle circle = new Circle(starting_point.getX()*CELL_WIDTH + CELL_WIDTH/2 , starting_point.getY()*CELL_WIDTH + CELL_WIDTH/2, radius);
        circle.setFill(color);
        this.getChildren().add(circle);
        Duration duration =  Duration.millis(500);
        TranslateTransition transition = new TranslateTransition(duration , circle);
        transition.setToX(ending_point.subtract(starting_point).getX()*CELL_WIDTH);
        transition.setToY(ending_point.subtract(starting_point).getY()*CELL_WIDTH);

        FadeTransition fade = new FadeTransition(Duration.millis(100) , circle);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);

        SequentialTransition sequentialTransition = new SequentialTransition(transition , fade);
        sequentialTransition.play();

    }
    public void scaleCircle(Point2D starting_point , int range, Color color){
//        Circle circle = new Circle(starting_point.getX()*CELL_WIDTH + CELL_WIDTH/2 , starting_point.getY()*CELL_WIDTH + CELL_WIDTH/2, 1);
//        circle.setStroke(color);
//        this.getChildren().add(circle);
//
//        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500) , circle);
//        scaleTransition.setByX(range * CELL_WIDTH);
//        scaleTransition.setByY(range * CELL_WIDTH);
//
//        FadeTransition fade = new FadeTransition(Duration.millis(100) , circle);
//        fade.setFromValue(1.0);
//        fade.setToValue(0.0);
//
//        SequentialTransition sequentialTransition = new SequentialTransition(scaleTransition , fade);
//        sequentialTransition.play();


    }
}