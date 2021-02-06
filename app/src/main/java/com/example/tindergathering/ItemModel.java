package com.example.tindergathering;

import com.example.tindergathering.ui.user.User;

public class ItemModel {
    private User user;

    public ItemModel(User u) {
        this.user = u;
    }

    public User getUser() {
        return user;
    }
}
