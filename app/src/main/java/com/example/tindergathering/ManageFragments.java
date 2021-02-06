package com.example.tindergathering;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ManageFragments extends Fragment {
    public void goTo(Fragment ancien, Fragment nouveau){
        FragmentManager fm = ancien.getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(ancien.getId(),nouveau);
        ft.commit();
        ft = fm.beginTransaction();
        ft.remove(ancien);
        ft.commit();
    }
    public void goToWithParams(Fragment ancien, Fragment nouveau, Bundle bundle){
        FragmentManager fm = ancien.getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        nouveau.setArguments(bundle);
        ft.add(ancien.getId(),nouveau);
        ft.commit();
        ft = fm.beginTransaction();
        ft.remove(ancien);
        ft.commit();
    }
}
