package com.example.tindergathering.ui.Registration;

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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tindergathering.R;
import com.example.tindergathering.ui.login.LoginFragment;

public class RegistrationFragment extends Fragment {

    private RegistrationViewModel registrationViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        registrationViewModel =
                ViewModelProviders.of(this).get(RegistrationViewModel.class);
        View root = inflater.inflate(R.layout.fragment_registration, container, false);
        // final TextView textView = root.findViewById(R.id.text_registration);

        Button valider = root.findViewById(R.id.registration_button);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Toast.makeText(view.getContext().getApplicationContext(), "Tapes pas trop fort stp...", Toast.LENGTH_SHORT).show();

                TextView pseudoView =  view.getRootView().findViewById(R.id.pseudo);
                String pseudo = pseudoView.getText().toString();

                TextView mailView =  view.getRootView().findViewById(R.id.pseudo);
                String mail = pseudoView.getText().toString();

                TextView passwordView =  view.getRootView().findViewById(R.id.pseudo);
                String password = pseudoView.getText().toString();

                TextView nameView =  view.getRootView().findViewById(R.id.pseudo);
                String name = pseudoView.getText().toString();

                TextView firstnameView =  view.getRootView().findViewById(R.id.pseudo);
                String firstname = pseudoView.getText().toString();

                Registration registration = new Registration(context, mail, pseudo, password, name, firstname);
                Boolean registered = registration.register();

                String textToastRegistered = registered ? "Inscription terminée":"L'inscription a échoué !";
                Toast.makeText(view.getContext().getApplicationContext(), textToastRegistered, Toast.LENGTH_SHORT).show();

                //redirection
                // Intent intent = new Intent(context, LoginFragment.class);
                // startActivity(intent);
            }
        });
        return root;
    }

}