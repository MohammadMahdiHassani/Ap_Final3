package network;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import DataBase.GameHistory;
import model.cards.CellValue;
import model.cards.Card;
import model.cards.levelEnums.Botlevel;
import model.cards.levelEnums.Level;

public class UserDataServer implements Serializable {
    private final String userName ;
    private final String password ;
    private int XP ;
    private int troopy ;
    private final ArrayList<GameHistory> histories ;
    private  ArrayList<String> playerDeck;
    private Botlevel botlevel ;
    private Level level;

    UserDataServer(String userName , String password){
        this.userName = userName ;
        this.password = password ;
        troopy = 0 ;
        XP = 0 ;
        histories = new ArrayList<>() ;
        playerDeck = new ArrayList<>() ;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getXP() {
        return XP;
    }

    public int getTroopy() {
        return troopy;
    }

    public ArrayList<GameHistory> getHistories() {
        return histories;
    }

    public ArrayList<String> getPlayerDeck() {
        return playerDeck;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public void setTroopy(int troopy) {
        this.troopy = troopy;
    }

    public void setPlayerDeck(ArrayList<String> playerDeck) {
        this.playerDeck = playerDeck;
    }

    public void setBotlevel(Botlevel botlevel) {
        this.botlevel = botlevel;
    }

    public Botlevel getBotlevel() {
        return botlevel;
    }
}
