package com.example.tindergathering.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.tindergathering.MainActivity;
import com.example.tindergathering.R;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        loginViewModel =
                ViewModelProviders.of(this).get(LoginViewModel.class);
        View root = inflater.inflate(R.layout.fragment_login, container, false);


        Button connect = root.findViewById(R.id.login_button);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();

                TextView pseudoView =  view.getRootView().findViewById(R.id.pseudo);
                String pseudo = pseudoView.getText().toString();
                TextView passwordView =  view.getRootView().findViewById(R.id.pseudo);
                String password = passwordView.getText().toString();

                if(pseudo.equals("") & password.equals("")){
                    Toast.makeText(context, "Merci de remplir les champs", Toast.LENGTH_SHORT).show();
                }else{
                    Login login = new Login(context, pseudo, password);
                    login.connect();
                    login.getCurrentUser();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }

                //TODO change to homeFragment
            }
        });

        return root;
    }
}