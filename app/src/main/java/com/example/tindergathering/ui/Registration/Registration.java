package com.example.tindergathering.ui.Registration;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import androidx.fragment.app.FragmentTransaction;

import com.example.tindergathering.R;
import com.example.tindergathering.ui.Network;
import com.example.tindergathering.ui.login.LoginFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    //Envoie une requete de creation de compte
    //
    public boolean register(final String mail, final String pseudo, final String password, final String name, final String firstName){
        new AsyncTask<Void,String,String>(){

            Network network = new Network(context);
            @Override
            protected String doInBackground(Void... voids) {
                Network network = new Network(context);
                String url = context.getResources().getString(R.string.url_start) + "users";
                try {
                    String jsonParam = "{\n" +
                            "  \"mail\":" + mail +",\n" +
                            "  \"username\":" + pseudo +",\n" +
                            "  \"password\":" + password +",\n" +
                            "  \"name\":" + name +",\n" +
                            "  \"firstName\":" + firstName +"\n" +
                            "}";
                    // API Request
                    String result = network.postRequest(url, jsonParam);
//                    result = "{\n" +
//                            "  \"@context\": \"string\",\n" +
//                            "  \"@id\": \"string\",\n" +
//                            "  \"@type\": \"string\",\n" +
//                            "  \"id\": 1,\n" +
//                            "  \"mail\": \"felix.felice@gmail.com\",\n"
//                            "  \"username\": \"username\",\n"
//                            "  \"password\": \"password\",\n"
//                            "  \"name\": \"Felix\",\n"
//                            "  \"firstName\": \"Felice\"\n"
//                            "}";
                    // Read result
                    JSONObject obj = new JSONObject(result);
                    // Get values
                    String username = obj.getString("username");

                    Log.v(TAG, "username :"+username );

                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                    Log.v(TAG, e.toString() );
                }
                return null;
            }

        }.execute();
        //check if API call succeed
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
