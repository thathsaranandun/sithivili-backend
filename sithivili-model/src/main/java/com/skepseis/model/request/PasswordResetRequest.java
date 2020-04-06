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
        if(password != null) {
            this.password = AES.encrypt(password, SECRET_KEY);
        }else {
            this.password = null;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
