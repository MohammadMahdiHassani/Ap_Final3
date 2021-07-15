package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClientHandler implements Runnable {
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private ClientDetail clientDetail;

    private Server server;

    public ClientHandler(ClientDetail clientDetail, Server server) {
        this.objectOutputStream = clientDetail.getObjectOutputStream();
        this.objectInputStream = clientDetail.getObjectInputStream();
        this.clientDetail = clientDetail;
        this.server = server;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Request request = (Request) objectInputStream.readObject();
                System.out.println(request.getCard() + " was chosen by " + clientDetail.getUserDataServer().getUserName());
                Request request1 = changeDirection(request);
                if (clientDetail.getId() == 0) {
                    server.sendRequest(1, request1);
                } else if (clientDetail.getId() == 1) {
                    server.sendRequest(0, request1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public Request changeDirection(Request request) {
        double x = request.getX();
        double y = request.getY();


        if (x > 10) {
            x = x - 2 * Math.abs(x - 10);
        } else {
            x = x + 2 * Math.abs(x - 10);
        }
        if (y > 11) {
            y = y - 2 * Math.abs(x - 11);
        } else {
            y = y + 2 * Math.abs(y - 11);
        }

        Request request1 = new Request(request.getCard(), x, y);
        return  request1;

    }
}