package com.example.a8_temp;


import java.io.Serializable;

public class UserInfo implements Serializable {

    String userName;
    String token;

    public UserInfo(String userName, String token) {
        this.userName = userName;
        this.token = token;
    }
    // to firebase getValue to map it into a object
    public UserInfo(){

    }

    public String getUserName() {
        return userName;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
