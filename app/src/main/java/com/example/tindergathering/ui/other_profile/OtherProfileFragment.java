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

import com.example.tindergathering.AccesLocal;
import com.example.tindergathering.ManageFragments;
import com.example.tindergathering.R;
import com.example.tindergathering.ui.swipe.SwipeFragment;
import com.example.tindergathering.ui.user.User;

import java.text.ParseException;

public class OtherProfileFragment extends Fragment {

    private OtherProfileViewModel OtherProfileViewModel;
    public AccesLocal accesLocal;

    public AccesLocal getAccesLocal(){
        return this.accesLocal;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OtherProfileViewModel = ViewModelProviders.of(this).get(OtherProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_other_profile, container, false);

        final TextView pseudoTextView = root.findViewById(R.id.pseudo);
        final Bundle bundleRecieved = this.getArguments();
        User user = null;

        if (bundleRecieved != null) {
            int receivedId = Integer.parseInt(bundleRecieved.getString("id"));
            accesLocal = new AccesLocal(this.getContext());
            try {
                user = accesLocal.selectUserSQLite(1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            pseudoTextView.setText(user.getUsername());
            
        }

        ImageButton goSwipe = (ImageButton) root.findViewById(R.id.go_swipe);
        final User finalUser = user;
        goSwipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("name", finalUser.getUsername());
                new ManageFragments().goToWithParams(OtherProfileFragment.this, new SwipeFragment(),bundle);
            }
        });
        return root;
    }
}