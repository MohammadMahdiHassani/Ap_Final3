package network;

import DataBase.DataHandler;
import DataBase.UserData;
import model.cards.Card;
import model.cards.CardFactory;
import model.cards.CellValue;
import model.cards.levelEnums.Level;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket serverSocket;
    private ArrayList<ClientDetail> clientDetails;

//    public void createUserData(UserDataServer userDataServer) {
//        userData = new UserData(userDataServer.getUserName(), userDataServer.getPassword());
//        userData.setBotlevel(userDataServer.getBotlevel());
//        userData.setTroopy(userDataServer.getTroopy());
//        userData.setXP(userDataServer.getXP());
//        ArrayList<Card> deck = new ArrayList<>();
//        for (int i = 0; i < userDataServer.getPlayerDeck().size(); i++) {
//            if (userDataServer.getPlayerDeck().get(i).equals("GIANT")) {
//                deck.add(CardFactory.makeCard(CellValue.GIANT, userDataServer.getLevel()));
//            }
////            } else if (userDataServer.getPlayerDeck().get(i).equals("ARCHER")) {
////                deck.add(CardFactory.makeCard(CellValue.ARCHER, userDataServer.getLevel()));
////            } else if (userDataServer.getPlayerDeck().get(i).equals("BARBERIAN")) {
////                deck.add(CardFactory.makeCard(CellValue.BARBERIAN, userDataServer.getLevel()));
////            } else if (userDataServer.getPlayerDeck().get(i).equals("WIZARD")) {
////                deck.add(CardFactory.makeCard(CellValue.WIZARD, userDataServer.getLevel()));
////            } else if (userDataServer.getPlayerDeck().get(i).equals("VALKYRIE")) {
////                deck.add(CardFactory.makeCard(CellValue.VALKYRIE, userDataServer.getLevel()));
////            } else if (userDataServer.getPlayerDeck().get(i).equals("BABY_DRAGON")) {
////                deck.add(CardFactory.makeCard(CellValue.BABY_DRAGON, userDataServer.getLevel()));
////            } else if (userDataServer.getPlayerDeck().get(i).equals("MINI_PEKA")) {
////                deck.add(CardFactory.makeCard(CellValue.MINI_PEKA, userDataServer.getLevel()));
////            } else if (userDataServer.getPlayerDeck().get(i).equals("CANNON")) {
////                deck.add(CardFactory.makeCard(CellValue.CANNON, userDataServer.getLevel()));
////            } else if (userDataServer.getPlayerDeck().get(i).equals("RAGE")) {
////                deck.add(CardFactory.makeCard(CellValue.RAGE, userDataServer.getLevel()));
////            } else if (userDataServer.getPlayerDeck().get(i).equals("ARROWS")) {
////                deck.add(CardFactory.makeCard(CellValue.ARROWS, userDataServer.getLevel()));
////            } else if (userDataServer.getPlayerDeck().get(i).equals("INFERNO")) {
////                deck.add(CardFactory.makeCard(CellValue.INFERNO, userDataServer.getLevel()));
////            } else if (userDataServer.getPlayerDeck().get(i).equals("FIREBALL")) {
////                deck.add(CardFactory.makeCard(CellValue.FIREBALL, userDataServer.getLevel()));
////            }
//        }
//        userData.setPlayerDeck(deck);
//        for (int i = 0; i < userData.getPlayerDeck().size(); i++) {
//            System.out.println(userData.getPlayerDeck().get(i).getValue().toString());
//        }
//    }

    public void connect() {
        clientDetails = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(9000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 2; i++) {
            try {
                Socket socket = serverSocket.accept();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                UserDataServer userDataServer = (UserDataServer) objectInputStream.readObject();
                ClientDetail clientDetail = new ClientDetail(objectOutputStream, objectInputStream, userDataServer, i);
                clientDetails.add(clientDetail);
                System.out.println(userDataServer.getUserName() + " connected");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0 ; i < 2; i ++)
        {
            Thread thread = new Thread(new ClientHandler(clientDetails.get(i),this));
            thread.start();
        }
        //System.out.println("finish connection");
    }
    public void sendRequest(int id,Request request)
    {
        try {
            clientDetails.get(id).getObjectOutputStream().writeObject(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(request.getCard() + " was sent to "+ clientDetails.get(id).getUserDataServer().getUserName());
    }

}