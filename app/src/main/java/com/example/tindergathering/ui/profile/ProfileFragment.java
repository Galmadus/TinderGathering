package com.example.tindergathering.ui.profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tindergathering.DisconnectActivity;
import com.example.tindergathering.AccesLocal;
import com.example.tindergathering.ManageFragments;
import com.example.tindergathering.R;
import com.example.tindergathering.ui.edit_profile_activity.EditProfileActivity;
import com.example.tindergathering.ui.matchs.MatchsFragment;
import com.example.tindergathering.ui.user.User;

import java.text.ParseException;

public class ProfileFragment extends Fragment {

    private ProfileViewModel ProfileViewModel;
    public AccesLocal accesLocal;

    @SuppressLint("ResourceAsColor")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        accesLocal = new AccesLocal(this.getContext());
        try {
            User user = accesLocal.selectUserSQLite(1);
            final TextView textViewDescription = root.findViewById(R.id.biography);
            textViewDescription.setText(user.getDescription());
            final TextView textViewUsername = root.findViewById(R.id.pseudo);
            textViewUsername.setText(user.getDisplayName());
            final TextView textViewCity = root.findViewById(R.id.city);
            textViewCity.setText(user.getCity());
            final TextView textViewGender = root.findViewById(R.id.gender);
            textViewGender.setText(user.getGender());
            final TextView textViewAge = root.findViewById(R.id.age);
            textViewAge.setText(String.valueOf(user.getAge())+" ans");
            final ImageView imageViewPicture = root.findViewById(R.id.profile_picture);
            imageViewPicture.setImageResource(user.getPicture());
            final Button go_matchs = root.findViewById(R.id.go_matchs);
            go_matchs.setText(accesLocal.getMatchCount()+" MATCHS");
                    //acces local get matchs count
            if(!user.getFormats().contains("Commander")){
                TextView textViewCommander = root.findViewById(R.id.format_commander);
                textViewCommander.setBackgroundColor(R.color.colorPrimary);
            }
            if(!user.getFormats().contains("Standard")){
                TextView textViewCommander = root.findViewById(R.id.format_standard);
                textViewCommander.setBackgroundColor(R.color.colorPrimary);
            }
            if(!user.getFormats().contains("Pioneer")){
                TextView textViewCommander = root.findViewById(R.id.format_pioneer);
                textViewCommander.setBackgroundColor(R.color.colorPrimary);
            }
            if(!user.getFormats().contains("Brawl")){
                TextView textViewCommander = root.findViewById(R.id.format_brawl);
                textViewCommander.setBackgroundColor(R.color.colorPrimary);
            }
            if(!user.getFormats().contains("Vintage")){
                TextView textViewCommander = root.findViewById(R.id.format_vintage);
                textViewCommander.setBackgroundColor(R.color.colorPrimary);
            }
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