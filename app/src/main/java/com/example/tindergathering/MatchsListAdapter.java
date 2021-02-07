package com.example.tindergathering;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tindergathering.ui.user.User;

import java.util.ArrayList;

public class MatchsListAdapter extends ArrayAdapter {
    private ArrayList<User> users;
    private Activity context;

    public MatchsListAdapter(Activity context, ArrayList<User> users) {
        super(context, R.layout.item_list, users);
        this.context = context;
        this.users = users;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null)
            row = inflater.inflate(R.layout.item_list, null, true);
        TextView textViewCountry = (TextView) row.findViewById(R.id.textViewCountry);
        TextView textViewCapital = (TextView) row.findViewById(R.id.textViewCapital);
        ImageView imageFlag = (ImageView) row.findViewById(R.id.imageViewFlag);

        textViewCountry.setText(users.get(position).getDisplayName());
        textViewCapital.setText(users.get(position).getCity());
        imageFlag.setImageResource(users.get(position).getPicture());
        return  row;
    }
}
