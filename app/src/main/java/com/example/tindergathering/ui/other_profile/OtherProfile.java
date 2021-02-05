package com.example.tindergathering.ui.other_profile;

import com.example.tindergathering.ui.user.User;

public class OtherProfile {
    private User user;

    public OtherProfile() {
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
