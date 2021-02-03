package com.example.tindergathering.ui.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.example.tindergathering.R;
import com.example.tindergathering.ui.Network;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Login {

    private Context context;
    private String pseudo;
    private String password;

    private static final String TAG = "Login";

    public Login(Context context, String pseudo, String password) {
        this.context = context;
        this.pseudo = pseudo;
        this.password = password;
    }

    //Send registration to API
    public boolean connect(){
        boolean connected = false;
        new AsyncTask<Void,String,String>(){

            Network network = new Network(context);
            @Override
            protected String doInBackground(Void... voids) {
                Network network = new Network(context);
                String url = context.getResources().getString(R.string.url_start) + "login_check";
                try {
                    // API Request
                    String result = network.getRequest(url);

                    // Read result
                    JSONObject obj = new JSONObject(result);

                    // Get values
                    String token = obj.getString("token");

                    // Save the new value of Authentification Token
                    SharedPreferences sharedPref = context.getSharedPreferences("com.example.tindergathering", Context.MODE_PRIVATE);
                    sharedPref.edit().putString("AuthToken", token).apply();
                    Log.v(TAG, sharedPref.getString("AuthToken", null) );

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

        }.execute();
        SharedPreferences sharedPref = context.getSharedPreferences("com.example.tindergathering", Context.MODE_PRIVATE);
        connected = sharedPref.getString("AuthToken", null) == null;
        return connected;
    }
}
