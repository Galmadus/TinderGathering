package com.example.tindergathering.ui.Registration;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.tindergathering.R;

public class Registration {
    private String email;
    private String password;
    private Context context;

    public Registration(Context context, String email, String password){
        this.context = context;
        this.email = email;
        this.password = password;
    }

    //Send email, password to API
    public boolean register(){
        //this.context.getSharedPreferences("AuthToken", Context.MODE_PRIVATE);
        return true;
    }
}
