package network;

import DataBase.UserData;
import view.ArenaView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClientDetail {
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private UserData userData;
    private int crown;
    private ArenaView arenaView;
    public ClientDetail(ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream, UserData userData)
    {
        this.objectOutputStream = objectOutputStream;
        this.objectInputStream = objectInputStream;
        this.userData = userData;

    }


}