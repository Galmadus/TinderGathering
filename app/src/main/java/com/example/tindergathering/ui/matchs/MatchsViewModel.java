package com.example.tindergathering.ui.matchs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MatchsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MatchsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is matchs fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}