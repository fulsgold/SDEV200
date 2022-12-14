package sample;

import javafx.scene.Scene;

import java.io.FileNotFoundException;

public abstract class User extends Product {

    private int id;
    private String userName;
    private String passWord;

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public abstract Scene SetInfo() throws FileNotFoundException;
}

