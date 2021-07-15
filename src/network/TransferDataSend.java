package network;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class TransferDataSend implements Runnable{
    private ObjectOutputStream objectOutputStream;
    private Request request;
    private String card;
    private double x;
    private double y;

    public TransferDataSend(ObjectOutputStream objectOutputStream)
    {
        this.objectOutputStream = objectOutputStream;

    }
    @Override
    public void run() {
        try {
            objectOutputStream.writeObject(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public double getX() {
        return x;
    }

    public String getCard() {
        return card;
    }

    public double getY() {
        return y;
    }

}