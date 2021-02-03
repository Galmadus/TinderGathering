package com.example.tindergathering.ui.Registration;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.example.tindergathering.R;
import com.example.tindergathering.ui.Network;

import java.io.IOException;

public class Registration {
    private String email;
    private String password;
    private Context context;

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
                try {
                    String url = context.getResources().getString(R.string.url_start) + "test.json";
                    network.downloadUrl(url);
                } catch (IOException e) {
                    Log.v("testJSON error", e.toString());
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
        //this.context.getSharedPreferences("AuthToken", Context.MODE_PRIVATE);
        return true;
    }
}
