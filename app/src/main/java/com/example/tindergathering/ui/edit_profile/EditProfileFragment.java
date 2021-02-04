package com.example.tindergathering.ui.edit_profile;

import android.content.Context;
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

import com.example.tindergathering.R;

public class EditProfileFragment extends Fragment {

    private EditProfileViewModel editProfileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        editProfileViewModel =
                ViewModelProviders.of(this).get(EditProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        // final TextView textView = root.findViewById(R.id.text_registration);

        Button valider = root.findViewById(R.id.edit_profile_button);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Toast.makeText(view.getContext().getApplicationContext(), "Tapes pas trop fort stp...", Toast.LENGTH_SHORT).show();

                TextView pseudoView =  view.getRootView().findViewById(R.id.pseudo);
                String pseudo = pseudoView.getText().toString();

                TextView mailView =  view.getRootView().findViewById(R.id.pseudo);
                String mail = mailView.getText().toString();

                TextView passwordView =  view.getRootView().findViewById(R.id.pseudo);
                String password = passwordView.getText().toString();

                TextView nameView =  view.getRootView().findViewById(R.id.pseudo);
                String name = nameView.getText().toString();

                TextView firstnameView =  view.getRootView().findViewById(R.id.pseudo);
                String firstname = firstnameView.getText().toString();

                TextView birthdayView =  view.getRootView().findViewById(R.id.pseudo);
                String birthday = birthdayView.getText().toString();


                if(pseudo.equals("") & mail.equals("") & password.equals("") & name.equals("") & firstname.equals("") & birthday.equals("")){
                    Toast.makeText(context, "Merci de remplir les champs", Toast.LENGTH_SHORT).show();
                }else{
                    EditProfile registration = new EditProfile(context, mail, pseudo, password, name, firstname);
                    Boolean registered = registration.register();
                    String textToastRegistered = registered ? "Inscription terminée":"L'inscription a échoué !";
                    Toast.makeText(view.getContext().getApplicationContext(), textToastRegistered, Toast.LENGTH_SHORT).show();
                }



                //redirection
                // Intent intent = new Intent(context, LoginFragment.class);
                // startActivity(intent);
            }
        });
        return root;
    }

}