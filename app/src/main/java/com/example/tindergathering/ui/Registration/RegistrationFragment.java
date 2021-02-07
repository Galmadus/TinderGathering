package com.example.tindergathering.ui.Registration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.tindergathering.MainActivity;
import com.example.tindergathering.R;
import com.example.tindergathering.ui.edit_profile_activity.EditProfileActivity;

public class RegistrationFragment extends Fragment {

    private com.example.tindergathering.ui.Registration.RegistrationViewModel registrationViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        registrationViewModel =
                ViewModelProviders.of(this).get(com.example.tindergathering.ui.Registration.RegistrationViewModel.class);
        View root = inflater.inflate(R.layout.fragment_registration, container, false);
        // final TextView textView = root.findViewById(R.id.text_registration);

        Button valider = root.findViewById(R.id.registration_button);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();

                TextView pseudoView =  view.getRootView().findViewById(R.id.pseudo);
                String pseudo = pseudoView.getText().toString();

                TextView mailView =  view.getRootView().findViewById(R.id.mail);
                String mail = mailView.getText().toString();

                TextView passwordView =  view.getRootView().findViewById(R.id.password);
                String password = passwordView.getText().toString();
                TextView passwordConfirmView =  view.getRootView().findViewById(R.id.password_confirm);
                String passwordConfirm = passwordView.getText().toString();

                TextView nameView =  view.getRootView().findViewById(R.id.name);
                String name = nameView.getText().toString();

                TextView firstnameView =  view.getRootView().findViewById(R.id.firstname);
                String firstname = firstnameView.getText().toString();

                DatePicker birthdayView =  view.getRootView().findViewById(R.id.birthdate);
                String birthday = birthdayView.getDayOfMonth()+"/"+birthdayView.getMonth()+"/"+birthdayView.getYear();


                if(pseudo.equals("") & mail.equals("") & password.equals("") & name.equals("") & firstname.equals("") & birthday.equals("")){
                    Toast.makeText(context, "Merci de remplir les champs", Toast.LENGTH_SHORT).show();
                }else{
                    com.example.tindergathering.ui.Registration.Registration registration = new com.example.tindergathering.ui.Registration.Registration(context, mail, pseudo, password, name, firstname);
                    Boolean registered = registration.register();
                    String textToastRegistered = registered ? "Inscription terminée":"L'inscription a échoué !";
                    Toast.makeText(view.getContext().getApplicationContext(), textToastRegistered, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }

                //redirection
                // Intent intent = new Intent(context, LoginFragment.class);
                // startActivity(intent);
            }
        });
        return root;
    }
}