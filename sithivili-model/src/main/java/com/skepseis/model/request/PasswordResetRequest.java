package com.skepseis.model.request;

import com.skepseis.model.helper.AES;
import lombok.Data;

import javax.persistence.Transient;

public class PasswordResetRequest {
    String username;
    String password;

    @Transient
    private final String SECRET_KEY = "sthvl@sk";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password != null) {
            this.password = AES.encrypt(password, SECRET_KEY);
        }else {
            this.password = null;
        }
    }
}
