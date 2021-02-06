package com.example.tindergathering.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tindergathering.DisconnectActivity;
import com.example.tindergathering.AccesLocal;
import com.example.tindergathering.ManageFragments;
import com.example.tindergathering.R;
import com.example.tindergathering.ui.edit_profile.EditProfileFragment;
import com.example.tindergathering.ui.edit_profile_activity.EditProfileActivity;
import com.example.tindergathering.ui.matchs.MatchsFragment;
import com.example.tindergathering.ui.user.User;

import java.text.ParseException;

public class ProfileFragment extends Fragment {

    private ProfileViewModel ProfileViewModel;
    public AccesLocal accesLocal;

    public AccesLocal getAccesLocal(){
        return this.accesLocal;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        final TextView textView = root.findViewById(R.id.pseudo);
//        ProfileViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        accesLocal = new AccesLocal(this.getContext());
        try {
            User user = accesLocal.selectUserSQLite(1);
            final TextView textViewDescription = root.findViewById(R.id.biography);
            textViewDescription.setText(user.getDescription());
            final TextView textViewUsername = root.findViewById(R.id.pseudo);
            textViewUsername.setText(user.getUsername());
            final TextView textViewCity = root.findViewById(R.id.city);
            textViewCity.setText(user.getCity());
            final TextView textViewGender = root.findViewById(R.id.gender);
            textViewGender.setText(user.getGender());
            final TextView textViewAge = root.findViewById(R.id.age);
            textViewAge.setText(String.valueOf(user.getAge()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Button goEdit = (Button) root.findViewById(R.id.go_edit_profile);
        goEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

        Button goMatchs = (Button) root.findViewById(R.id.go_matchs);
        goMatchs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ManageFragments().goTo(ProfileFragment.this,new MatchsFragment());
            }
        });

        ImageButton goLogOut = (ImageButton) root.findViewById(R.id.bouton_logout);
        goLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DisconnectActivity.class);
                startActivity(intent);
            }
        });



        return root;
    }
}