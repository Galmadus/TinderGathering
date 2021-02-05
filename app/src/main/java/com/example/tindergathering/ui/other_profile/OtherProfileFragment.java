package com.example.tindergathering.ui.other_profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.tindergathering.R;
import com.example.tindergathering.ui.swipe.SwipeFragment;

public class OtherProfileFragment extends Fragment {

    private OtherProfileViewModel OtherProfileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OtherProfileViewModel = ViewModelProviders.of(this).get(OtherProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_other_profile, container, false);
        final TextView pseudoTextView = root.findViewById(R.id.pseudo);


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String receivedPseudo = bundle.getString("name", "John");
            pseudoTextView.setText(receivedPseudo);
            //String receivedId = bundle.getString("id", "null");
        }

        ImageButton goSwipe = (ImageButton) root.findViewById(R.id.go_swipe);
        goSwipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwipeFragment fragment = new SwipeFragment(); //Your Fragment
                Bundle bundle = new Bundle();
                bundle.putString("name", "JUL");
                fragment.setArguments(bundle);
                // Pass data to other Fragment
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.layout_other_profile, fragment)
                        .commit();
            }
        });


        return root;
    }
}