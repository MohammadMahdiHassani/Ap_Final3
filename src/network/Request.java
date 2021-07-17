package network;

import DataBase.UserData;
import model.cards.Card;
import model.cards.levelEnums.Level;

import java.io.Serializable;

public class Request implements Serializable {
    private String userName;
    private String card;
    private double x;
    private double y;
    private Level level;
    public Request(String card,Level level,String userName, double x, double y)
    {
        this.card = card;
        this.level = level;
        this.userName = userName;
        this.x = x;
        this.y = y;
    }

    public String getCard() {
        return card;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Level getLevel() {
        return level;
    }
}