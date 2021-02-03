package com.example.tindergathering.ui.Registration;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.tindergathering.R;
import com.example.tindergathering.ui.Network;

import java.io.IOException;

import static android.provider.Settings.Global.getString;


public class Registration {
    private String email;
    private String password;
    private Context context;

    private static final String TAG = "Registration";

    public Registration(Context context, String email, String password){
        this.context = context;
        this.email = email;
        this.password = password;
    }

    //Send email, password to API
    public boolean register(){
        new AsyncTask<Void,String,String>(){

            Network network = new Network(context);
            @Override
            protected String doInBackground(Void... voids) {
                Network network = new Network(context);
                String url = context.getResources().getString(R.string.url_start) + "test.json";
                try {
                    // API Request
                    String result = network.downloadUrl(url);
                    Log.v(TAG, result.toString());

                    //this.context.getSharedPreferences("AuthToken", Context.MODE_PRIVATE);

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
}
