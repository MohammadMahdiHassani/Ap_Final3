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
import model.cards.Card;
import model.cards.CardFactory;
import model.cards.CellValue;
import model.cards.levelEnums.Level;

import java.io.IOException;
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
    private int count;

    public void initialize() {
        count = 0;
        TroopyCounter.setText(String.valueOf(DataHandler.getUserData().getTroopy()));
        XPprogressSlider.setProgress((1.0 * DataHandler.getUserData().getXP()) / 2500);
        xp.setText(DataHandler.getUserData().getXP() + "");
        Level level = DataHandler.getLevel();

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
        allArmies.add(null);


        allArmy.setItems(allArmies);
        mainArmy.setItems(mainArmies);
        mainArmy.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Card>() {
                    @Override
                    public void changed(ObservableValue<? extends Card> observable, Card oldValue, Card newValue) {
                        if (count == 0)
                        {
                            removeFromMainArmy(mainArmy.getSelectionModel().getSelectedIndex());
                            LoginController.sound.playMain("CHOOSE_CART");
                        }
                    }
                }
        );


        allArmy.getSelectionModel().selectedItemProperty()
                .addListener(
                        new ChangeListener<Card>() {
                            @Override
                            public void changed(ObservableValue<? extends Card> observable, Card oldValue, Card newValue) {
                                System.out.println("card");
                                if (count == 0) {
                                    if (mainArmy.getItems().size() < 8) {
                                        System.out.println("ok");
                                        LoginController.sound.playMain("CHOOSE_CART");
                                        removeFromListArmy(allArmy.getSelectionModel().getSelectedIndex());
                                    } else {
                                        LoginController.sound.playMain("NO");

                                    }
                                }


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

    public void removeFromListArmy(int index) {
        count = 1;
        mainArmy.getItems().add(allArmy.getItems().get(index));
        allArmies.remove(index);
        allArmy.setItems(allArmies);
        allArmy.getSelectionModel().select(allArmy.getItems().size() - 1);
        count = 0;
    }

    public void removeFromMainArmy(int index)
    {
        count = 1;
        allArmy.getItems().remove(null);
        allArmy.getItems().add(mainArmy.getItems().get(index));
        allArmy.getItems().add(null);
        allArmy.getSelectionModel().select(allArmy.getItems().size() - 1);
        mainArmies.remove(index);
        mainArmy.setItems(mainArmies);
        mainArmy.getSelectionModel().select(mainArmy.getItems().size() - 1);
        count = 0;
    }

    @FXML
    void endClick(MouseEvent event) {
        LoginController.sound.playMain("CLICK");
//        int mainLastIndex = -1;
//        int allLastIndex = -1;
//        if (mainLastValue != null) {
//            for (int i = 0; i < mainArmy.getItems().size(); i++) {
//                if (mainArmy.getItems().get(i).getValue() == mainLastValue.getValue()) {
//                    mainLastIndex = i;
//                    break;
//                }
//            }
//        }
//        if (allLastValue != null) {
//            for (int i = 0; i < allArmy.getItems().size(); i++) {
//                if (allArmy.getItems().get(i).getValue() == allLastValue.getValue()) {
//                    allLastIndex = i;
//                    break;
//                }
//            }
//        }
//        if (allArmy.getItems().size() > allLastIndex && allLastIndex >= 0) {
//            allArmy.getItems().remove(allLastIndex);
//            mainArmy.getItems().remove(mainArmy.getItems().size() - 1);
//        }
//        if (mainArmy.getItems().size() > mainLastIndex && mainLastIndex >= 0) {
//            mainArmy.getItems().remove(mainLastIndex);
//            allArmy.getItems().remove(allArmy.getItems().size() - 1);
//        }
        DataHandler.getUserData().setPlayerDeck(new ArrayList(mainArmy.getItems()));
    }

    @FXML
    void actionHandler(MouseEvent event) throws IOException {
        DataHandler.getUserData().setPlayerDeck(new ArrayList(mainArmy.getItems()));
        LoginController.sound.playMain("CLICK");
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
