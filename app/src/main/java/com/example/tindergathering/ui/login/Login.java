package com.example.tindergathering.ui.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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

    public String getPseudo() {
        return pseudo;
    }

    public String getPassword() {
        return password;
    }

    //Send registration to API
    public boolean connect(){
        boolean connected = false;

        SharedPreferences sharedPref = context.getSharedPreferences("com.example.tindergathering", Context.MODE_PRIVATE);
        sharedPref.edit().putString("username", getPseudo()).apply();
        sharedPref.edit().putString("password", getPassword()).apply();

        new AsyncTask<Void,String,String>(){
            Network network = new Network(context);
            @Override
            protected String doInBackground(Void... voids) {
                //reset value AuthToken
                SharedPreferences sharedPref = context.getSharedPreferences("com.example.tindergathering", Context.MODE_PRIVATE);
                sharedPref.edit().putString("AuthToken", null).apply();

                Network network = new Network(context);
                String url = context.getResources().getString(R.string.url_start) + "login_check";
                String jsonIdentifiant = "{ \"username\": \"" + sharedPref.getString("username", null) + "\",  \"password\": \"" + sharedPref.getString("password", null) + "\" }";
                try {
                    // API Request
                    String result = network.getRequest(url);

                    Toast.makeText(context, "result : "+result, Toast.LENGTH_SHORT).show();
                    // Read result
                    JSONObject obj = new JSONObject(result);

                    // Get values
                    String token = obj.getString("token");

                    // Save the new value of Authentification Token
                    sharedPref.edit().putString("AuthToken", token).apply();
                    sharedPref.edit().putInt("IdUser", 1).apply();
                    Log.v(TAG, sharedPref.getString("AuthToken", null) );

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

        }.execute();
//        SharedPreferences sharedPref = context.getSharedPreferences("com.example.tindergathering", Context.MODE_PRIVATE);
        connected = sharedPref.getString("AuthToken", null) == null;
        return connected;
    }
}
