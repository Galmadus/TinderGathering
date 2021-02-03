package com.example.tindergathering.ui.edit_profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EditProfileViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EditProfileViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is edit profile fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}