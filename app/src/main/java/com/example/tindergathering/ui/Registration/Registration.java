package com.example.tindergathering.ui.Registration;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.example.tindergathering.R;
import com.example.tindergathering.ui.Network;

import java.io.IOException;


public class Registration {
    private Context context;
    private String email;
    private String pseudo;
    private String password;
    private String name;
    private String firstname;

    private static final String TAG = "Registration";

    public Registration(Context context, String email, String pseudo, String password, String name, String firstname ) {
        this.context = context;
        this.email = email;
        this.pseudo = pseudo;
        this.password = password;
        this.name = name;
        this.firstname = firstname;
    }

    //Send registration to API
    public boolean register(){
        new AsyncTask<Void,String,String>(){

            Network network = new Network(context);
            @Override
            protected String doInBackground(Void... voids) {
                Network network = new Network(context);
                String url = context.getResources().getString(R.string.url_start) + "test.json";
                try {
                    // API Request
                    String result = network.getRequest(url);

                    // Save the new value of Authentification Token
                    SharedPreferences sharedPref = context.getSharedPreferences("com.example.tindergathering", Context.MODE_PRIVATE);
                    sharedPref.edit().putString("AuthToken", result).apply();

                    Log.v(TAG, sharedPref.getString("AuthToken", null) );

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

        }.execute();
        return true;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
