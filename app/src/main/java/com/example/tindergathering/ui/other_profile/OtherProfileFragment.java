package com.example.tindergathering.ui.other_profile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.tindergathering.AccesLocal;
import com.example.tindergathering.ManageFragments;
import com.example.tindergathering.R;
import com.example.tindergathering.ui.swipe.SwipeFragment;
import com.example.tindergathering.ui.user.User;

import java.text.ParseException;

public class OtherProfileFragment extends Fragment {

    private OtherProfileViewModel OtherProfileViewModel;
    public AccesLocal accesLocal;
    public int receivedId;
    public AccesLocal getAccesLocal(){
        return this.accesLocal;
    }

    @SuppressLint("ResourceAsColor")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OtherProfileViewModel = ViewModelProviders.of(this).get(OtherProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_other_profile, container, false);
        final Bundle bundleRecieved = this.getArguments();
        User user = null;
        if (bundleRecieved != null) {
            receivedId = bundleRecieved.getInt("id");
            accesLocal = new AccesLocal(this.getContext());
            try {
                user = accesLocal.selectUserSQLite(receivedId);
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
        }

        ImageButton goSwipe = (ImageButton) root.findViewById(R.id.go_swipe);
        final User finalUser = user;
        goSwipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", receivedId);
                new ManageFragments().goToWithParams(OtherProfileFragment.this, new SwipeFragment(),bundle);
            }
        });
        return root;
    }
}