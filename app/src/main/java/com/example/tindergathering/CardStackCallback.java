package com.example.tindergathering;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class CardStackCallback extends DiffUtil.Callback {

    private List<ItemModel> ancien, nouveau;

    public CardStackCallback(List<ItemModel> ancien, List<ItemModel> nouveau) {
        this.ancien = ancien;
        this.nouveau = nouveau;
    }

    @Override
    public int getOldListSize() {
        return ancien.size();
    }

    @Override
    public int getNewListSize() {
        return nouveau.size();
    }

    @Override
    public boolean areItemsTheSame(int ancienItemPosition, int newItemPosition) {
        return ancien.get(ancienItemPosition).getImage() == nouveau.get(newItemPosition).getImage();
    }

    @Override
    public boolean areContentsTheSame(int ancienItemPosition, int newItemPosition) {
        return ancien.get(ancienItemPosition) == nouveau.get(newItemPosition);
    }
}
