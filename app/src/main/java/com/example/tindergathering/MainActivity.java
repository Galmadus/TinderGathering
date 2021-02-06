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

import java.text.ParseException;

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
            User u;
            for (int i=0; i<20; i++){
                u = new User();
                try {
                    accesLocal.insertUserSQLite(u);
                } catch (ParseException e) {
                    Log.v("MainActivity", e.toString());
                    e.printStackTrace();
                }
            }
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




        /*Logo dans la navigation à droite, à supprimer si non fonctionnel
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(actionBar.getDisplayOptions()
                | ActionBar.DISPLAY_SHOW_CUSTOM);
        ImageView imageView = new ImageView(actionBar.getThemedContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_END);
        imageView.setImageResource(R.drawable.app_logo);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT, Gravity.RIGHT
                | Gravity.CENTER_VERTICAL);
        imageView.setLayoutParams(layoutParams);
        actionBar.setCustomView(imageView);
        */
        // Ajouter un logo à la nav à gauche du titre de la page

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.app_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

}