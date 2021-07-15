package network;

import DataBase.DataHandler;
import DataBase.UserData;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private Socket socket;
    private UserData userData;
    private ImageView button1v1;

    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    private UserDataServer userDataServer;
    public void createUserData()
    {
        userDataServer = new UserDataServer(DataHandler.getUserData().getUserName(),DataHandler.getUserData().getPassword());
        userDataServer.setBotlevel(DataHandler.getUserData().getBotlevel());
        userDataServer.setTroopy(DataHandler.getUserData().getTroopy());
        userDataServer.setXP(DataHandler.getUserData().getXP());
        userDataServer.setLevel(DataHandler.getLevel());
        ArrayList<String> cards = new ArrayList<>();
        for (int i = 0 ;i < DataHandler.getUserData().getPlayerDeck().size(); i++)
        {
            if (DataHandler.getUserData().getPlayerDeck().get(i).getValue().toString().equals("GIANT"))
            {
                cards.add("GIANT");
            }
            else if (DataHandler.getUserData().getPlayerDeck().get(i).getValue().toString().equals("ARCHER"))
            {
                cards.add("ARCHER");
            }else if (DataHandler.getUserData().getPlayerDeck().get(i).getValue().toString().equals("BARBERIAN"))
            {
                cards.add("BARBERAIN");
            }else if (DataHandler.getUserData().getPlayerDeck().get(i).getValue().toString().equals("WIZARD"))
            {
                cards.add("WIZARD");
            }else if (DataHandler.getUserData().getPlayerDeck().get(i).getValue().toString().equals("VALKYRIE"))
            {
                cards.add("VALKYRIE");
            }else if (DataHandler.getUserData().getPlayerDeck().get(i).getValue().toString().equals("BABY_DRAGON"))
            {
                cards.add("BABY_DRAGON");
            }else if (DataHandler.getUserData().getPlayerDeck().get(i).getValue().toString().equals("MINI_PEKA"))
            {
                cards.add("MINI_PEKA");
            }else if (DataHandler.getUserData().getPlayerDeck().get(i).getValue().toString().equals("FIREBALL"))
            {
                cards.add("FIREBALL");
            }else if (DataHandler.getUserData().getPlayerDeck().get(i).getValue().toString().equals("ARROWS"))
            {
                cards.add("ARROWS");
            }else if (DataHandler.getUserData().getPlayerDeck().get(i).getValue().toString().equals("RAGE"))
            {
                cards.add("RAGE");
            }else if (DataHandler.getUserData().getPlayerDeck().get(i).getValue().toString().equals("INFERNO"))
            {
                cards.add("INFERNO");
            }else if (DataHandler.getUserData().getPlayerDeck().get(i).getValue().toString().equals("CANNON"))
            {
                cards.add("CANNON");
            }
        }
        userDataServer.setPlayerDeck(cards);
    }
    public void Connect(ImageView button1v1)
    {

        createUserData();
        this.button1v1 = button1v1;
        userData = DataHandler.getUserData();
        try {
            socket = new Socket("localhost",9000);
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
            this.objectOutputStream.writeObject(userDataServer);
            //Thread.sleep(3000);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }
}