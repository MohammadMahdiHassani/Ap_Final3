package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.cards.Army;
import view.ArenaView;

public class ArenaController {
    @FXML
    ListView<Army> listArmy;
    @FXML
    ArenaView arenaView;

    public void initialize()
    {
        arenaView.update();
    }
    public double getBoardWidth() {
        return arenaView.CELL_WIDTH * this.arenaView.getColumnCount();
    }

    public double getBoardHeight() {
        return arenaView.CELL_WIDTH * this.arenaView.getRowCount();
    }
}