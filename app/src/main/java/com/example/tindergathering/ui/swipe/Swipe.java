package com.example.tindergathering.ui.swipe;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.tindergathering.R;
import com.example.tindergathering.ui.Network;
import com.example.tindergathering.ui.user.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class Swipe {

    private ArrayList<User> userArrayList;
    private Context context;

    public Swipe(Context context) {
        this.context = context;
    }
    private static final String TAG = "Registration";

    // Récupère une liste d'utilisateur pour les swipe
    public void findUsers(){
        new AsyncTask<Void,String,String>(){
            Network network = new Network(context);
            @Override
            protected String doInBackground(Void... voids) {
                Network network = new Network(context);
                String url = context.getResources().getString(R.string.url_start) + "users";
                try {
                    // API Request
                    String result = network.getRequest(url);

                    Toast.makeText(context, "result : "+result, Toast.LENGTH_SHORT).show();
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
    }

    public boolean userSwiped(User user){
        new AsyncTask<Void,String,String>(){
            Network network = new Network(context);
            @Override
            protected String doInBackground(Void... voids) {
                Network network = new Network(context);
                String url = context.getResources().getString(R.string.url_start) + "user_swiped";
                try {
                    // API Request
                    String result = network.getRequest(url);

                    Toast.makeText(context, "result : "+result, Toast.LENGTH_SHORT).show();
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
        return true;
    }

    boolean acceptUser(User user){
        return false;
    }
    boolean refuseUser(User user){
        return false;
    }
}
