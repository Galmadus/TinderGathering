package com.example.tindergathering.ui.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.tindergathering.R;
import com.example.tindergathering.AccesLocal;
import com.example.tindergathering.ui.Network;
import com.example.tindergathering.ui.user.User;

import org.json.JSONException;
import org.json.JSONObject;

public class Profile {
    private User user;
    private Context context;

    public Profile(User user, String description, Context context) {
        this.user = user;
        this.context = context;
    }

    public Profile() {
        this.user = null;
    }

    User findUser(){
        return new User(this.context);
    }
    boolean acceptUser(User user){
        return false;
    }
    boolean refuseUser(User user){
        return false;
    }


    //Send request to API for description and set the value to description
    public void getAndSetDescription(){
        SharedPreferences sharedPref = context.getSharedPreferences("com.example.tindergathering", Context.MODE_PRIVATE);

        new AsyncTask<Void,String,String>(){
            Network network = new Network(context);
            @Override
            protected String doInBackground(Void... voids) {
                //reset value AuthToken
                SharedPreferences sharedPref = context.getSharedPreferences("com.example.tindergathering", Context.MODE_PRIVATE);

                Network network = new Network(context);
                // ADD ROUTE REQUETE SUR LA DESCRIPTION DU JOUEUR
                String url = context.getResources().getString(R.string.url_start) + "/users/current";
                try {
                    // API Request
//                     String result = network.getRequest(url);

                    //On simule temporairement le retour de l'api
                    String result = "{ \"description\": \"Salut c'est moi\" }";

                    Toast.makeText(context, "result : "+result, Toast.LENGTH_SHORT).show();
                    // Read result
                    JSONObject obj = new JSONObject(result);

                    // Get values
                    String description = obj.getString("description");

                    // Save the new value of Description
                    sharedPref.edit().putString("description", description).apply();

                } catch (/*IOException | */JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

        }.execute();
//        SharedPreferences sharedPref = context.getSharedPreferences("com.example.tindergathering", Context.MODE_PRIVATE);
//        this.setDescription(sharedPref.getString("description", null) );
    }

}
