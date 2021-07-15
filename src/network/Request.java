package network;

import DataBase.UserData;
import model.cards.Card;

import java.io.Serializable;

public class Request implements Serializable {
    private String type;
    private String card;
    private double x;
    private double y;
    public Request(String card, double x, double y)
    {
        this.card = card;
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
}