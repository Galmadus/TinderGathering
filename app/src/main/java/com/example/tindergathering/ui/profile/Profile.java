package com.example.tindergathering.ui.profile;

import com.example.tindergathering.ui.user.User;

public class Profile {
    private User user;

    public Profile() {
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
