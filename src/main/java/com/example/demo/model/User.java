package com.example.demo.model;

import com.example.demo.AES;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.TABLE;

@Entity
@Table(name = "user_account_details")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"secretKey"})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy=TABLE)
    private int userid;

    private String username;
    private String password;
    private String usertype;
    private String image;

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

    public String getRealPassword() {
        if(password!=null) {
            return AES.decrypt(password, SECRET_KEY);
        }
        return null;
    }

    public void setPassword(String password) {
        this.password = AES.encrypt(password, SECRET_KEY);
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return String.format(
                "{" +
                        "\"id\"=%d," +
                        "\"username\"=\"%s\"," +
                        "\"password\"=\"%s\"," +
                        "\"image\"=\"%s\"}",
                userid, username, password, image);
    }
}
