package com.example.tindergathering.ui.other_profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tindergathering.R;
import com.example.tindergathering.ui.edit_profile.EditProfileFragment;
import com.example.tindergathering.ui.matchs.MatchsFragment;
import com.example.tindergathering.ui.swipe.SwipeFragment;

public class OtherProfileFragment extends Fragment {

    private OtherProfileViewModel OtherProfileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        OtherProfileViewModel =
                ViewModelProviders.of(this).get(OtherProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_other_profile, container, false);
        final TextView textView = root.findViewById(R.id.pseudo);
        OtherProfileViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

/*
        Button goSwipe = (Button) root.findViewById(R.id.go_swipe);
        goSwipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                SwipeFragment swipeFragment = new SwipeFragment();
                fragmentTransaction.replace(R.id.layout_swipe, swipeFragment, "Swipe");
                fragmentTransaction.commit();
            }
        });

*/
        return root;
    }
}