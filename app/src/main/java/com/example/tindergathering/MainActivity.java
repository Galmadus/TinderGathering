package com.example.tindergathering;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.example.tindergathering.ui.user.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class MainActivity extends AppCompatActivity {

    private Context context = this;
    public AccesLocal accesLocal;


    public AccesLocal getAccesLocal(){
        return this.accesLocal;
    }
    public SQLiteDatabase getDB(){
        return this.accesLocal.getDB();
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        new AsyncTask<Void,String,String>(){
//
//            Network network = new Network(context);
//            @Override
//            protected String doInBackground(Void... voids) {
//                String url = "https://localhost/users";
//                String testJson = "{ \"username\": \"hugo\",  \"password\": \"error\" }";
//                try {
//                    // API Request
//                    network.postRequest(url, testJson);
//
//                    String s = "mc";
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//
//        }.execute();
        setContentView(R.layout.activity_main);
        try{
            accesLocal = new AccesLocal(getApplicationContext());
            Log.v("MainActivity", "numberRow " + accesLocal.getUsersCount());
            User u = new User();
//            u = new User("Adminus", "123", new Date("01-01-1970"), "Homme", "adminus@mail.com", 1, "John", "Does", "Admirable et affectueux", "Reims");
            u.setUsername("Adminus");
            u.setFirstName("Admi");
            u.setName("Nus");
            u.setEmail("adminus@mail.com");
            u.setGender("Homme");
            u.setPicture(2131165367);
            u.setDescription("Admirable et affectueux");
            // if BD not init with user data

            if(accesLocal.getUsersCount()==0){
                accesLocal.insertUserSQLite(u);
                for (int i=0; i<50; i++){
                    u = new User();
                    accesLocal.insertUserSQLite(u);
                }
            }

            Log.v("MainActivity", "numberRow " + accesLocal.getUsersCount());
        }catch (Exception e){
            Log.v("MainActivity", e.toString());
        }
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_swipe, R.id.navigation_profile, R.id.navigation_matchs)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.app_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

}