package com.example.tindergathering.ui.swipe;

import com.example.tindergathering.ui.user.User;

public class Swipe {
    private User user;

    public Swipe() {
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
