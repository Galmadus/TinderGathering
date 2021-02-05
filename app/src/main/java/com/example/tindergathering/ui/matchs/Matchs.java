package com.example.tindergathering.ui.matchs;

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

public class Matchs {
    private User user;
    private Context context;
    private ArrayList<User> userList;

    private static final String TAG = "Matchs";

    public Matchs(Context context) {
        this.context = context;
        this.userList = new ArrayList<>();
    }
    public Matchs(Context context, ArrayList userList) {
        this.context = context;
        this.userList = userList;
    }

    public boolean addUser(User user){
        new AsyncTask<Void,String,String>(){

            Network network = new Network(context);
            @Override
            protected String doInBackground(Void... voids) {
                //reset value AuthToken
                SharedPreferences sharedPref = context.getSharedPreferences("com.example.tindergathering", Context.MODE_PRIVATE);
                sharedPref.edit().putString("AuthToken", null).apply();

                Network network = new Network(context);
                String url = context.getResources().getString(R.string.url_start) + "matchs";
                try {
                    // API Request
                    String result = network.getRequest(url);
                    result = "{" +
                            ""+
                            "}";
                    Toast.makeText(context, "result : "+result, Toast.LENGTH_SHORT).show();
                    // Read result
                    JSONObject obj = new JSONObject(result);

                    // Get values
                    String token = obj.getString("token");
                    Log.v(TAG, sharedPref.getString("AuthToken", null) );

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

        }.execute();
        return this.userList.add(user);
    }
    public boolean removeUser(User user){
        return this.userList.remove(user);
    }
}
