package com.example.tindergathering.ui.Registration;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tindergathering.R;

public class RegistrationFragment extends Fragment {

    private RegistrationViewModel registrationViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        registrationViewModel =
                ViewModelProviders.of(this).get(RegistrationViewModel.class);
        View root = inflater.inflate(R.layout.fragment_registration, container, false);
        // final TextView textView = root.findViewById(R.id.text_registration);
        return root;
    }
}