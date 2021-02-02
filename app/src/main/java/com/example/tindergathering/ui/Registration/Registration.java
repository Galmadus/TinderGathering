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

    public boolean register(){


        return true;
    }

    public boolean authTokenValid(){

        //SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        //String authToken = getResources().getString(R.string.url_start);
        // Request API with
        return false;
    }

    public void saveAuthToken(String token){
        //SharedPreferences sharedPref = context.getActivity().getPreferences(Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = sharedPref.edit();
        //editor.putString(getString(R.string.url_start), token);
        //editor.apply();
    }
}
