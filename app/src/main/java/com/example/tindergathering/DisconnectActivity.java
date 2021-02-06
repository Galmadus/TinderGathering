package com.example.tindergathering;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DisconnectActivity extends AppCompatActivity {

    private Context context = this;
    private static AccesLocal accesLocal;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disconnect);
        try{
            accesLocal = new AccesLocal(getApplicationContext());
        }catch (Exception e){
            Log.v("DisconnectActivity", e.toString());
        }
        BottomNavigationView navView = findViewById(R.id.nav_view_disconnect);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_login_disconnect, R.id.navigation_registration_disconnect)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_disconnect);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        // Ajouter un logo à la nav à gauche du titre de la page
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.app_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }
}