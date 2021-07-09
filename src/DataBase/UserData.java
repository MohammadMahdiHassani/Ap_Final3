package DataBase;

import java.io.Serializable;
import java.util.ArrayList;
import com.company.model.cards.Card ;

public class UserData implements Serializable {
    private final String userName ;
    private final String password ;
    private int XP ;
    private int troopy ;
    private final ArrayList<GameHistory> histories ;
    private final ArrayList<Card> playerDeck;

    UserData(String userName , String password){
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
}
