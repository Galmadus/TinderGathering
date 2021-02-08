package com.example.tindergathering.ui.login;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    // Methode de connexion de l'utilisateur
    // Appel d'api sur la route /login_check, permettant d'identifier un user et renvoyer un jwtToken
    // Ajoute le JwtToken dans les shared Preferences
    // Return true si l'utilisteur est connecte, false sinon
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

//                    Toast.makeText(context, "result : "+result, Toast.LENGTH_SHORT).show();
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
        connected = sharedPref.getString("AuthToken", null) == null;
        return connected;
    }

    // Envoie une requete vers la route users/current
    public void getCurrentUser() {
        new AsyncTask<Void,String,String>(){
            Network network = new Network(context);
            @Override
            protected String doInBackground(Void... voids) {
                //reset value AuthToken
                SharedPreferences sharedPref = context.getSharedPreferences("com.example.tindergathering", Context.MODE_PRIVATE);
                sharedPref.edit().putString("AuthToken", null).apply();

                Network network = new Network(context);
                String url = context.getResources().getString(R.string.url_start) + "users/current";
                // Exemple d'une reponse de l'api au format json
//                String jsonCurrentUser = "{ " +
//                        "\"username\": \"" + sharedPref.getString("username", null) + "\"," +
//                        " \"password\": \"" + sharedPref.getString("password", null) + "\"," +
//                        " \"gender\": \"" + "homme" + "\"," +
//                        " \"id\": \"" + sharedPref.getInt("IdUser", 0) + "\"," +
//                        " \"email\": \"" + sharedPref.getString("password", null) + "\"," +
//                        " \"birthday\": \"" + sharedPref.getString("username", null) + "\"," +
//                        " \"description\": \"" + sharedPref.getString("password", null) + "\"," +
//                        " \"picture\": \"" + sharedPref.getString("password", null) + "\"," +
//                        " \"myFriends\": \"" + sharedPref.getString("password", null) + "\"," +
//                        " \"formats\": \"" + sharedPref.getString("password", null) + "\","
//                        + "\" }";
                try {
                    // API Request
                    String result = network.getRequest(url);

//                    Toast.makeText(context, "result : "+result, Toast.LENGTH_SHORT).show();
                    // Read result
                    JSONObject obj = new JSONObject(result);

                    String dateJson = obj.getString("birthday");
                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                    Date date = format.parse(dateJson);

                    User user = new User();
                    user.setBirthday(date);
                    user.setUsername(obj.getString("username"));
                    user.setPassword(obj.getString("password"));
                    user.setGender(obj.getString("gender"));
                    user.setEmail(obj.getString("email"));
                    user.setId(obj.getInt("id"));

//                    boolean alreadyExist = user.selectUserSQLite(obj.getInt("id"));
//                    if(alreadyExist){
//                        user.updateUserSQLite();
//                    } else {
//                        user.insertUserSQLite();
//                    }


                } catch (IOException | JSONException | ParseException e) {
                    e.printStackTrace();
                }
                return null;
            }

        }.execute();
    }
}
