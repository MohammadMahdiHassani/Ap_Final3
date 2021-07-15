package network;

import javafx.geometry.Point2D;
import model.cards.Card;

import java.io.IOException;
import java.io.ObjectInputStream;

public class TransferDataReceive implements Runnable{
    private ObjectInputStream objectInputStream;
    private String card;
    private boolean isReceive;
    private double x;
    private double y;

    public TransferDataReceive(ObjectInputStream objectInputStream)
    {
        this.objectInputStream = objectInputStream;
    }

    public void setReceive(boolean receive) {
        isReceive = receive;
    }

    public boolean isReceive() {
        return isReceive;
    }

    @Override
    public void run() {
        while (true)
        {
            try {
                Request request = (Request) objectInputStream.readObject();
                x = request.getX();
                y = request.getY();
                card = request.getCard();
                isReceive = true;
                break;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }


    public String getCard() {
        return card;
    }
}