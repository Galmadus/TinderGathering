package com.example.tindergathering.ui.matchs;

import com.example.tindergathering.ui.user.User;

public class Matchs {
    private User user;

    public Matchs() {
        this.user = null;
    }

    User findUser(){
        return new User();
    }
    boolean acceptUser(User user){
        return false;
    }
    boolean refuseUser(User user){
        return false;
    }
}
