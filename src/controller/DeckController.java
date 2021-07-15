package controller;

import DataBase.DataHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.cards.*;
import model.cards.levelEnums.Level;
import model.game.ArenaModel;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class DeckController {
    @FXML
    private Label xp;
    @FXML
    private ImageView profilePage;
    @FXML
    private ImageView doneIcon;
    @FXML
    private ImageView gameHistory;

    @FXML
    private ImageView battleDeck;

    @FXML
    private ImageView mainPage;

    @FXML
    private ProgressBar XPprogressSlider;

    @FXML
    private Label TroopyCounter;

    @FXML
    private ListView<Card> mainArmy;

    @FXML
    private ListView<Card> allArmy;

    private ObservableList<Card> mainArmies =
            FXCollections.observableArrayList();

    private ObservableList<Card> allArmies =
            FXCollections.observableArrayList();
    private Card mainLastValue;
    private Card allLastValue;

    public void initialize() {

        TroopyCounter.setText(String.valueOf(DataHandler.getUserData().getTroopy()));
        XPprogressSlider.setProgress(DataHandler.getUserData().getXP() / 2500);
        xp.setText(DataHandler.getUserData().getXP() + "");
        Level level = null;
        if (Integer.valueOf(xp.getText()) <= 300) {
            level = Level.LEVEL_1;
        } else if (Integer.valueOf(xp.getText()) <= 500) {
            level = Level.LEVEL_2;
        } else if (Integer.valueOf(xp.getText()) <= 900) {
            level = Level.LEVEL_3;
        } else if (Integer.valueOf(xp.getText()) <= 1700) {
            level = Level.LEVEL_4;
        } else if (Integer.valueOf(xp.getText()) > 1700) {
            level = Level.LEVEL_5;
        }

        ArrayList<CellValue> cellValues = new ArrayList<>();
        cellValues.add(CellValue.ARCHER);
        cellValues.add(CellValue.BARBERIAN);
        cellValues.add(CellValue.GIANT);
        cellValues.add(CellValue.VALKYRIE);
        cellValues.add(CellValue.MINI_PEKA);
        cellValues.add(CellValue.WIZARD);
        cellValues.add(CellValue.BABY_DRAGON);
        cellValues.add(CellValue.RAGE);
        cellValues.add(CellValue.ARROWS);
        cellValues.add(CellValue.FIREBALL);
        cellValues.add(CellValue.CANNON);
        cellValues.add(CellValue.INFERNO);


        ArrayList<Card> playerDeck = DataHandler.getUserData().getPlayerDeck();
        if (playerDeck != null) {
            for (int i = 0; i < playerDeck.size(); i++) {
                mainArmies.add(CardFactory.makeCard(playerDeck.get(i).getValue(), level));
                cellValues.remove(playerDeck.get(i).getValue());
            }
        }

        for (int i = 0; i < cellValues.size(); i++) {
            allArmies.add(CardFactory.makeCard(cellValues.get(i), level));

        }


        allArmy.setItems(allArmies);
        mainArmy.setItems(mainArmies);
        mainArmy.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Card>() {
                    @Override
                    public void changed(ObservableValue<? extends Card> observable, Card oldValue, Card newValue) {
                        allArmy.getItems().add(newValue);
                        mainArmy.getItems().remove(oldValue);
                        mainLastValue = newValue;
                    }
                }
        );


        allArmy.getSelectionModel().selectedItemProperty()
                .addListener(
                        new ChangeListener<Card>() {
                            @Override
                            public void changed(ObservableValue<? extends Card> observable, Card oldValue, Card newValue) {
                                mainArmy.getItems().add(newValue);
                                allArmy.getItems().remove(oldValue);
                                allLastValue = newValue;
                            }
                        }
                );
        mainArmy.setCellFactory(
                new Callback<ListView<Card>, ListCell<Card>>() {
                    @Override
                    public ListCell<Card> call(ListView<Card> param) {
                        return new ArmyCellFactory();
                    }
                }
        );

        allArmy.setCellFactory(
                new Callback<ListView<Card>, ListCell<Card>>() {
                    @Override
                    public ListCell<Card> call(ListView<Card> param) {
                        return new ArmyCellFactory();
                    }
                }
        );
    }

    @FXML
    void endClick(MouseEvent event) {
        int mainLastIndex = -1;
        int allLastIndex = -1;
        if (mainLastValue != null) {
            for (int i = 0; i < mainArmy.getItems().size(); i++) {
                if (mainArmy.getItems().get(i).getValue() == mainLastValue.getValue()) {
                    mainLastIndex = i;
                    break;
                }
            }
        }
        if (allLastValue != null) {
            for (int i = 0; i < allArmy.getItems().size(); i++) {
                if (allArmy.getItems().get(i).getValue() == allLastValue.getValue()) {
                    allLastIndex = i;
                    break;
                }
            }
        }
        if (allArmy.getItems().size() > allLastIndex && allLastIndex >= 0) {
            allArmy.getItems().remove(allLastIndex);
            mainArmy.getItems().remove(mainArmy.getItems().size() - 1);
        }
        if (mainArmy.getItems().size() > mainLastIndex && mainLastIndex >= 0) {
            mainArmy.getItems().remove(mainLastIndex);
            allArmy.getItems().remove(allArmy.getItems().size() - 1);
        }
        DataHandler.getUserData().setPlayerDeck(new ArrayList(mainArmy.getItems()));
//        Path path = Paths.get(System.getProperty("user.dir"));
//        File file = new File(path.toString() + "/src/DataBase/Files");
//        File userFile = new File(file + "/" + DataHandler.getUserData().getUserName() + ".ser") ;
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(userFile);
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//            objectOutputStream.writeObject(DataHandler.getUserData());
//            objectOutputStream.close();
//            fileOutputStream.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //DataHandler.saveToFile(DataHandler.getUserData());
    }

    @FXML
    void actionHandler(MouseEvent event) throws IOException {
        String fxmlAddress = getFxml(event);
        Parent root = FXMLLoader.load(getClass().getResource(fxmlAddress));
        Stage stage = (Stage) mainPage.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();


    }

    private String getFxml(MouseEvent event) {
        if (event.getSource() == mainPage) {
            return "../view/Menu.fxml";
        } else if (event.getSource() == gameHistory) {
            return "../view/BattleHistory.fxml";
        } else if (event.getSource() == profilePage) {
            return "../view/Profile.fxml";
        } else
            return "";
    }


}
