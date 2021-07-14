package network;

import DataBase.UserData;
import model.cards.Card;

import java.io.Serializable;

public class Request implements Serializable {
    private String type;
    private Card card;
    private UserData userData;

    private double x;
    private double y;

}