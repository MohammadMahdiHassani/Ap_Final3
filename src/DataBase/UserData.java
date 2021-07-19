package DataBase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import model.cards.CellValue;
import model.cards.Card;
import model.cards.levelEnums.ArcherTowerLevel;
import model.cards.levelEnums.Botlevel;
import model.cards.levelEnums.KingTowerLevel;
import model.cards.levelEnums.Level;

public class UserData implements Serializable {
    private final String userName ;
    private final String password ;
    private int XP ;
    private int troopy ;
    private final ArrayList<GameHistory> histories ;
    private  ArrayList<Card> playerDeck;
    private Botlevel botlevel ;


    public UserData(String userName, String password){
        this.userName = userName ;
        this.password = password ;
        troopy = 0 ;
        XP = 0 ;
        histories = new ArrayList<>() ;
        playerDeck = new ArrayList<>() ;
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

    public void setPlayerDeck(ArrayList<Card> playerDeck) {
        this.playerDeck = playerDeck;
    }

    public void setBotlevel(Botlevel botlevel) {
        this.botlevel = botlevel;
    }

    public Botlevel getBotlevel() {
        return botlevel;
    }

    public void addXP(int x){
        XP += x ;
    }

    public void addToHistory(String userName_1 , String userName_2 , String winner){
        histories.add(new GameHistory(userName_1 , userName_2 , winner)) ;
    }
}
