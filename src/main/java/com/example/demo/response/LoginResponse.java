package com.example.demo.response;

import com.example.demo.model.User;

public class LoginResponse {
    private boolean dbdata;
    private User user;
    private String token;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
