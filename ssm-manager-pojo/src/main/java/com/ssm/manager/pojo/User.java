package com.ssm.manager.pojo;

public class User {

    int id;

    String nick;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nick='" + nick + '\'' +
                '}';
    }
}
