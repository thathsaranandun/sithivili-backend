package com.skepseis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.skepseis.model.helper.AES;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
    private Boolean loginFlag;
    private Boolean isVerified;

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

    @JsonIgnore
    public String getRealPassword() {
        if(password!=null) {
            return AES.decrypt(password, SECRET_KEY);
        }
        return null;
    }

    public void setPassword(String password) {
        if(password != null) {
            this.password = AES.encrypt(password, SECRET_KEY);
        }else {
            this.password = null;
        }
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

    public Boolean getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(Boolean loginFlag) {
        this.loginFlag = loginFlag;
    }

    public Boolean isVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
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
