package com.example.tindergathering.ui.user;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.tindergathering.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class User {
    private String username;
    private String password;
    private Date birthday;
    private String sexe;
    private String email;

    public User() {}

    public User(Context context) {
        this.context = context;
    }

    private Context context;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String findResource(){
        return context.getResources().getString(R.string.url_start);
    }

    public void testJson() throws IOException {
        String s = findResource() + "test.json";
        URL url = new URL(s);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//            readStream(in);
            int d = 1;
        } finally {
            urlConnection.disconnect();
        }
    }
}

