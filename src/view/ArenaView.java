package view;

import controller.ArenaController;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.GameElement;
import model.cards.CellValue;
import model.game.ArenaModel;

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
    //private ImageView[][] assetView;
    private ImageView[][] cellView;
    private ImageView[][] componentView;
    public static ProgressBar[][] healthBar;
    public static ImageView[][] assetView;
    public static ImageView[][] assetView2;

    public ArenaView() {

    }

    private void initializeGrid() {
        elixirProgress = new ArenaController().getElixirProgress();
        crown1 = new Label("0");
        crown2 = new Label("0");
        timeLabel = new Label("3:00");
        cellView = new ImageView[rowCount][columnCount];
        componentView = new ImageView[rowCount][columnCount];
        assetView = new ImageView[rowCount][columnCount];
        assetView2 = new ImageView[rowCount][columnCount];
        healthBar = new ProgressBar[rowCount][columnCount];
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                ImageView imageView = new ImageView();
                ImageView imageView1 = new ImageView();
                ImageView imageView2 = new ImageView();
                ImageView imageView3 = new ImageView();
                ProgressBar progressBar = null;
                if (row == 2 && (column == 2 || column == 9 || column == columnCount - 3)) {
                    progressBar = new ProgressBar();
                    progressBar.setProgress(1);
                    progressBar.setStyle("-fx-accent: red");
                    progressBar.setLayoutX(column * CELL_WIDTH);
                    progressBar.setLayoutY(row * CELL_WIDTH - 7);
                    progressBar.setPrefSize(CELL_WIDTH, 9);
                }

                if (row == rowCount - 3 && (column == 2 || column == 9 || column == columnCount - 3)) {
                    progressBar = new ProgressBar();
                    progressBar.setProgress(1);
                    progressBar.setLayoutX(column * CELL_WIDTH);
                    progressBar.setLayoutY(row * CELL_WIDTH - 7);
                    progressBar.setPrefSize(CELL_WIDTH, 9);
                }

                imageView.setX(column * CELL_WIDTH);
                imageView.setY(row * CELL_WIDTH);
                imageView.setFitWidth(CELL_WIDTH);
                imageView.setFitHeight(CELL_WIDTH);
                imageView1.setX(column * CELL_WIDTH);
                imageView1.setY(row * CELL_WIDTH);
                imageView1.setFitWidth(CELL_WIDTH);
                imageView1.setFitHeight(CELL_WIDTH);

                imageView3.setX(column * CELL_WIDTH);
                imageView3.setY(row * CELL_WIDTH);
                imageView3.setFitWidth(CELL_WIDTH);
                imageView3.setFitHeight(CELL_WIDTH);

                imageView2.setX(column * CELL_WIDTH);
                imageView2.setY(row * CELL_WIDTH);
                imageView2.setFitWidth(CELL_WIDTH);
                imageView2.setFitHeight(CELL_WIDTH);

                cellView[row][column] = imageView;
                componentView[row][column] = imageView1;
                assetView[row][column] = imageView2;
                if (progressBar != null) {
                    healthBar[row][column] = progressBar;
                }
                assetView2[row][column] = imageView3;
                this.getChildren().add(imageView);
                this.getChildren().add(imageView2);
                this.getChildren().add(imageView1);
                this.getChildren().add(imageView3);
                if (progressBar != null) {
                    this.getChildren().add(progressBar);
                }


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

    public void setAsset() {

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (i == 2 && (j == 2 || j == 9 || j == columnCount - 3)) {
                    healthBar[i][j].setProgress(3);
                }

                if (i == rowCount - 3 && (j == 2 || j == 9 || j == columnCount - 3)) {
                    healthBar[i][j].setProgress(3);
                }
                if ((i != 9 && i != 8 && i != 11 && i != 12 && i != 10) || (j != columnCount - 1)) {
                    if (j != 0 || i != 10) {
                        if (i == 0) {
                            assetView[i][j].setImage(TREE.getThumbnailImage());
                        }
                        if (i == rowCount - 1) {
                            assetView[i][j].setImage(TREE.getThumbnailImage());
                        }
                        if (j == 0) {
                            assetView[i][j].setImage(TREE.getThumbnailImage());
                        }
                        if (j == columnCount - 1) {
                            assetView[i][j].setImage(TREE.getThumbnailImage());
                        }
                    }
                }
            }
        }
        assetView[2][2].setImage(new Image("/view/photos/tower/damageTower.png"));
        assetView[2][9].setImage(new Image("/view/photos/tower/damageTower.png"));
        assetView[2][columnCount - 3].setImage(new Image("/view/photos/tower/damageTower.png"));
        assetView[rowCount - 3][2].setImage(new Image("/view/photos/tower/damageTower.png"));
        assetView[rowCount - 3][9].setImage(new Image("/view/photos/tower/damageTower.png"));
        assetView[rowCount - 3][columnCount - 3].setImage(new Image("/view/photos/tower/damageTower.png"));


        assetView[10][0].setImage(new Image("/view/photos/background/lHome.png"));
        assetView[10][columnCount - 1].setImage(new Image("/view/photos/background/rHome.png"));
        assetView[10][columnCount - 3].setImage(new Image("/view/photos/background/bridge.png"));
        assetView[10][2].setImage(new Image("/view/photos/background/bridge.png"));
        assetView[rowCount - 2][2].setImage(new Image("/view/photos/background/fence.png"));
        assetView[rowCount - 2][3].setImage(new Image("/view/photos/background/fence.png"));
        assetView[rowCount - 2][columnCount - 2].setImage(new Image("/view/photos/background/fence.png"));
        assetView[rowCount - 2][columnCount - 3].setImage(new Image("/view/photos/background/fence.png"));
        assetView[1][2].setImage(new Image("/view/photos/background/fence.png"));
        assetView[1][3].setImage(new Image("/view/photos/background/fence.png"));
        assetView[1][columnCount - 4].setImage(new Image("/view/photos/background/fence.png"));
        assetView[1][columnCount - 3].setImage(new Image("/view/photos/background/fence.png"));
        assetView[1][9].setImage(new Image("/view/photos/background/clock.png"));
        for (int j = 1; j < columnCount - 1; j++) {
            assetView[rowCount - 2][j].setImage(new Image("/view/photos/background/fence.png"));
            if (j != 9) {
                assetView[1][j].setImage(new Image("/view/photos/background/fence.png"));
            }
        }

        assetView2[2][9].setImage(new Image("/view/photos/tower/botKing.png"));
        assetView2[rowCount - 3][9].setImage(new Image("/view/photos/tower/myKing.png"));

    }

    public void setBackgroundCell(ArenaModel model) {
        CellValue[][] cellValues = null;
        cellValues = model.getBackGroundCellValues();

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (cellValues[i][j] == GRASS) {
                    cellView[i][j].setImage(GRASS.getThumbnailImage());

                } else if (cellValues[i][j] == GRASS2) {
                    cellView[i][j].setImage(GRASS2.getThumbnailImage());

                } else if (cellValues[i][j] == GRASS3) {
                    cellView[i][j].setImage(GRASS3.getThumbnailImage());
                } else if (cellValues[i][j] == CellValue.ROAD) {
                    cellView[i][j].setImage(ROAD.getThumbnailImage());
                } else if (cellValues[i][j] == CellValue.HROAD) {
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
                } else if (cellValues[i][j] == DAMAGE) {
                    cellView[i][j].setImage(DAMAGE.getThumbnailImage());
                }
            }
        }
        setAsset();
    }

    public GameElement getCardWithPoint(int x, int y, ArenaModel model) {
        for (int i = 0; i < model.getGameData().boardElements.size(); i++) {
            if (x == model.getGameData().boardElements.get(i).getPoint().getX()) {
                if (y == model.getGameData().boardElements.get(i).getPoint().getY()) {
                    return model.getGameData().boardElements.get(i);
                }
            }
        }
        return null;
    }

    public void update(ArenaModel model) {

        GameElement[][] cellValues = null;
        cellValues = model.getCellValues();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (cellValues[i][j] == null) {
                    componentView[i][j].setImage(null);
                } else if (cellValues[i][j].getValue() == MYKINGTOWER) {
                    componentView[i][j].setImage(MYKINGTOWER.getMyWalk());
                    // assetView[i][j].setImage(new Image("/view/photos/tower/myKing.png"));
                } else if (cellValues[i][j].getValue() == MYARCHERTOWER) {
                    componentView[i][j].setImage(MYARCHERTOWER.getMyWalk());
                } else if (cellValues[i][j].getValue() == BOTARCHERTOWER) {
                    componentView[i][j].setImage(BOTARCHERTOWER.getMyWalk());
                } else if (cellValues[i][j].getValue() == BOTKINGTOWER) {
                    //assetView[i][j].setImage(new Image("/view/photos/tower/botKing.png"));
                    componentView[i][j].setImage(BOTKINGTOWER.getMyWalk());
                } else if (cellValues[i][j].getValue() == GIANT) {
                    if (model.getLogic().isPlayerElement(getCardWithPoint(j, i, model))) {
                        if (getCardWithPoint(j, i, model).isAttack()) {
                            componentView[i][j].setImage(GIANT.getMyAttack());
                        } else {
                            if (getCardWithPoint(j, i, model).isWalk2()) {
                                componentView[i][j].setImage(GIANT.getMyWalk2());
                            } else {
                                componentView[i][j].setImage(GIANT.getMyWalk());
                            }
                        }
                    } else if (model.getLogic().isBotElement(getCardWithPoint(j, i, model))) {
                        if (getCardWithPoint(j, i, model).isAttack()) {
                            componentView[i][j].setImage(GIANT.getBotAttack());
                        } else {
                            if (getCardWithPoint(j, i, model).isWalk2()) {
                                componentView[i][j].setImage(GIANT.getBotWalk2());
                            } else {
                                componentView[i][j].setImage(GIANT.getBotWalk());
                            }
                        }
                    }

                } else if (cellValues[i][j].getValue() == ARCHER) {
                    if (model.getLogic().isPlayerElement(getCardWithPoint(j, i, model))) {
                        if (getCardWithPoint(j, i, model).isAttack()) {
                            componentView[i][j].setImage(ARCHER.getMyAttack());
                        } else {
                            if (getCardWithPoint(j, i, model).isWalk2()) {
                                componentView[i][j].setImage(ARCHER.getMyWalk2());
                            } else {
                                componentView[i][j].setImage(ARCHER.getMyWalk());
                            }
                        }
                    } else if (model.getLogic().isBotElement(getCardWithPoint(j, i, model))) {
                        if (getCardWithPoint(j, i, model).isAttack()) {
                            componentView[i][j].setImage(ARCHER.getBotAttack());
                        } else {
                            if (getCardWithPoint(j, i, model).isWalk2()) {
                                componentView[i][j].setImage(ARCHER.getBotWalk2());
                            } else {
                                componentView[i][j].setImage(ARCHER.getBotWalk());
                            }
                        }
                    }
                } else if (cellValues[i][j].getValue() == CANNON) {
                    componentView[i][j].setImage(CANNON.getMyWalk());

                } else if (cellValues[i][j].getValue() == BARBERIAN) {
                    if (model.getLogic().isPlayerElement(getCardWithPoint(j, i, model))) {
                        if (getCardWithPoint(j, i, model).isAttack()) {
                            componentView[i][j].setImage(BARBERIAN.getMyAttack());
                        } else {
                            if (getCardWithPoint(j, i, model).isWalk2()) {
                                componentView[i][j].setImage(BARBERIAN.getMyWalk2());
                            } else {
                                componentView[i][j].setImage(BARBERIAN.getMyWalk());
                            }
                        }
                    } else if (model.getLogic().isBotElement(getCardWithPoint(j, i, model))) {
                        if (getCardWithPoint(j, i, model).isAttack()) {
                            componentView[i][j].setImage(BARBERIAN.getBotAttack());
                        } else {
                            if (getCardWithPoint(j, i, model).isWalk2()) {
                                componentView[i][j].setImage(BARBERIAN.getBotWalk2());
                            } else {
                                componentView[i][j].setImage(BARBERIAN.getBotWalk());
                            }
                        }
                    }
                } else if (cellValues[i][j].getValue() == FIREBALL) {
                    componentView[i][j].setImage(FIREBALL.getMyWalk());
                } else if (cellValues[i][j].getValue() == WIZARD) {
                    if (model.getLogic().isPlayerElement(getCardWithPoint(j, i, model))) {
                        if (getCardWithPoint(j, i, model).isAttack()) {
                            componentView[i][j].setImage(WIZARD.getMyAttack());
                        } else {
                            if (getCardWithPoint(j, i, model).isWalk2()) {
                                componentView[i][j].setImage(WIZARD.getMyWalk2());
                            } else {
                                componentView[i][j].setImage(WIZARD.getMyWalk());
                            }
                        }
                    } else if (model.getLogic().isBotElement(getCardWithPoint(j, i, model))) {
                        if (getCardWithPoint(j, i, model).isAttack()) {
                            componentView[i][j].setImage(WIZARD.getBotAttack());
                        } else {
                            if (getCardWithPoint(j, i, model).isWalk2()) {
                                componentView[i][j].setImage(WIZARD.getBotWalk2());
                            } else {
                                componentView[i][j].setImage(WIZARD.getBotWalk());
                            }
                        }
                    }
                } else if (cellValues[i][j].getValue() == MINI_PEKA) {
                    if (model.getLogic().isPlayerElement(getCardWithPoint(j, i, model))) {
                        if (getCardWithPoint(j, i, model).isAttack()) {
                            componentView[i][j].setImage(MINI_PEKA.getMyAttack());
                        } else {
                            if (getCardWithPoint(j, i, model).isWalk2()) {
                                componentView[i][j].setImage(MINI_PEKA.getMyWalk2());
                            } else {
                                componentView[i][j].setImage(MINI_PEKA.getMyWalk());
                            }
                        }
                    } else if (model.getLogic().isBotElement(getCardWithPoint(j, i, model))) {
                        if (getCardWithPoint(j, i, model).isAttack()) {
                            componentView[i][j].setImage(MINI_PEKA.getBotAttack());
                        } else {
                            if (getCardWithPoint(j, i, model).isWalk2()) {
                                componentView[i][j].setImage(MINI_PEKA.getBotWalk2());
                            } else {
                                componentView[i][j].setImage(MINI_PEKA.getBotWalk());
                            }
                        }
                    }
                } else if (cellValues[i][j].getValue() == VALKYRIE) {
                    if (model.getLogic().isPlayerElement(getCardWithPoint(j, i, model))) {
                        if (getCardWithPoint(j, i, model).isAttack()) {
                            componentView[i][j].setImage(VALKYRIE.getMyAttack());
                        } else {
                            if (getCardWithPoint(j, i, model).isWalk2()) {
                                componentView[i][j].setImage(VALKYRIE.getMyWalk2());
                            } else {
                                componentView[i][j].setImage(VALKYRIE.getMyWalk());
                            }
                        }
                    } else if (model.getLogic().isBotElement(getCardWithPoint(j, i, model))) {
                        if (getCardWithPoint(j, i, model).isAttack()) {
                            componentView[i][j].setImage(VALKYRIE.getBotAttack());
                        } else {
                            if (getCardWithPoint(j, i, model).isWalk2()) {
                                componentView[i][j].setImage(VALKYRIE.getBotWalk2());
                            } else {
                                componentView[i][j].setImage(VALKYRIE.getBotWalk());
                            }
                        }
                    }
                } else if (cellValues[i][j].getValue() == RAGE) {
                    componentView[i][j].setImage(RAGE.getMyWalk());
                } else if (cellValues[i][j].getValue() == BABY_DRAGON) {
                    if (model.getLogic().isPlayerElement(getCardWithPoint(j, i, model))) {
                        if (getCardWithPoint(j, i, model).isAttack()) {
                            componentView[i][j].setImage(BABY_DRAGON.getMyAttack());
                        } else {
                            if (getCardWithPoint(j, i, model).isWalk2()) {
                                componentView[i][j].setImage(BABY_DRAGON.getMyWalk2());
                            } else {
                                componentView[i][j].setImage(BABY_DRAGON.getMyWalk());
                            }
                        }
                    } else if (model.getLogic().isBotElement(getCardWithPoint(j, i, model))) {
                        if (getCardWithPoint(j, i, model).isAttack()) {
                            componentView[i][j].setImage(BABY_DRAGON.getBotAttack());
                        } else {
                            if (getCardWithPoint(j, i, model).isWalk2()) {
                                componentView[i][j].setImage(BABY_DRAGON.getBotWalk2());
                            } else {
                                componentView[i][j].setImage(BABY_DRAGON.getBotWalk());
                            }
                        }
                    }

                } else if (cellValues[i][j].getValue() == INFERNO) {
                    componentView[i][j].setImage(INFERNO.getMyWalk());
                } else if (cellValues[i][j].getValue() == ARROWS) {
                    componentView[i][j].setImage(ARROWS.getMyWalk());
                }

            }
        }
    }

    public void shootCircles(Point2D starting_point, Point2D ending_point, double radius, Color color) {

        Circle circle = new Circle(starting_point.getX() * CELL_WIDTH + CELL_WIDTH / 2, starting_point.getY() * CELL_WIDTH + CELL_WIDTH / 2, radius);
        circle.setFill(color);
        this.getChildren().add(circle);
        Duration duration = Duration.millis(500);
        TranslateTransition transition = new TranslateTransition(duration, circle);
        transition.setToX(ending_point.subtract(starting_point).getX() * CELL_WIDTH);
        transition.setToY(ending_point.subtract(starting_point).getY() * CELL_WIDTH);

        FadeTransition fade = new FadeTransition(Duration.millis(100), circle);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);

        SequentialTransition sequentialTransition = new SequentialTransition(transition, fade);
        sequentialTransition.play();

    }

    public void scaleCircle(Point2D starting_point, int range, Color color) {

        Circle circle = new Circle(starting_point.getX() * CELL_WIDTH + CELL_WIDTH / 2, starting_point.getY() * CELL_WIDTH + CELL_WIDTH / 2, CELL_WIDTH / 2);
        circle.setStroke(color);
        this.getChildren().add(circle);
        circle.setStrokeWidth(0.5);
        circle.setFill(Color.TRANSPARENT);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(5000), circle);
        scaleTransition.setByX(range - 1);
        scaleTransition.setByY(range - 1);

        FadeTransition fade = new FadeTransition(Duration.millis(100), circle);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);

        SequentialTransition sequentialTransition = new SequentialTransition(scaleTransition, fade);
        sequentialTransition.play();


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

    public void setTimeLabel(String text, Color color) {
        timeLabel.setTextFill(color);
        timeLabel.setText(text);

    }

    public static Label getTimeLabel() {
        return timeLabel;
    }

    public Label getCrown1() {
        return crown1;
    }

    public Label getCrown2() {
        return crown2;
    }
}