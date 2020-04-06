package com.skepseis.model.request;


import com.skepseis.model.helper.AES;

import javax.persistence.Transient;

public class AddVolunteerRequest {
    private String username;
    private String name;
    private String password;
    private String dateOfBirth;
    private String nic;
    private String email;
    private String mobile;
    private String gender;

    @Transient
    private final String SECRET_KEY = "sthvl@sk";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
