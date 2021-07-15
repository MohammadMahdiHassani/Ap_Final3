package network;

import DataBase.UserData;
import view.ArenaView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClientDetail {
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private UserDataServer userDataServer;
    private int crown;
    private int id;
    public ClientDetail(ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream, UserDataServer userData,int id)
    {
        this.objectOutputStream = objectOutputStream;
        this.objectInputStream = objectInputStream;
        this.userDataServer = userData;
        this.id = id;
    }

    public UserDataServer getUserDataServer() {
        return userDataServer;
    }

    public int getCrown() {
        return crown;
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public int getId() {
        return id;
    }
}