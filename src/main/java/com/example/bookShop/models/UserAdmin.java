package com.example.bookShop.models;

public class UserAdmin extends User {
    public UserAdmin(String login, String password) {
        super(login, password);
    }
    public UserAdmin(int id, String login, String password) {
        super(id, login, password);
    }
    public UserAdmin(int id, String login, String password, double balance) {
        super(id, login, password, balance);
    }
}
