package com.example.demo;

import com.example.demo.model.User;

public class LoginResponse {
    private boolean dbdata;
    private User user;

    public boolean isDbdata() {
        return dbdata;
    }

    public void setDbdata(boolean dbdata) {
        this.dbdata = dbdata;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
