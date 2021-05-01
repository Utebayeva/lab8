package com.example.bookShop.models;

public class UserJust extends User{
    public UserJust(String login, String password) {
        super(login, password);
    }
    public UserJust(int id, String login, String password) {
        super(id, login, password);
    }
    public UserJust(int id, String login, String password, double balance) {
        super(id, login, password, balance);
    }
}
