package DataBase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import model.cards.CellValue;
import model.cards.Card;
import model.cards.Level;

public class UserData implements Serializable {
    private final String userName ;
    private final String password ;
    private int XP ;
    private int troopy ;
    private final ArrayList<GameHistory> histories ;
    private  ArrayList<Card> playerDeck;
    private final HashMap<CellValue, Level> levelMap ;

    UserData(String userName , String password){
        this.userName = userName ;
        this.password = password ;
        troopy = 0 ;
        XP = 0 ;
        histories = new ArrayList<>() ;
        playerDeck = new ArrayList<>() ;
        levelMap = new HashMap<>() ;

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

    public ArrayList<Card> getPlayerDeck() {
        return playerDeck;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public void setTroopy(int troopy) {
        this.troopy = troopy;
    }
}
