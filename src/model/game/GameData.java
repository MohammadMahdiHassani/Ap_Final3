package model.game;

import DataBase.DataHandler;
import DataBase.UserData;

public class GameData {
    private UserData userData ;

    public GameData() {
        userData = DataHandler.getUserData();
    }
}
