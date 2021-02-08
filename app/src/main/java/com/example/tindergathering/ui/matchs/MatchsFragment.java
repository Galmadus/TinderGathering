package com.example.tindergathering.ui.matchs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.tindergathering.AccesLocal;
import com.example.tindergathering.ManageFragments;
import com.example.tindergathering.MatchsListAdapter;
import com.example.tindergathering.R;
import com.example.tindergathering.ui.other_profile.OtherProfileFragment;
import com.example.tindergathering.ui.user.User;

import java.text.ParseException;
import java.util.ArrayList;

public class MatchsFragment extends Fragment {

    private MatchsViewModel matchsViewModel;
    private ListView list;
    private ArrayList<User> arrayList = null;

    // Instance créée lorsqu'on arrive sur la page des matchs
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        matchsViewModel =
                ViewModelProviders.of(this).get(MatchsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_matchs, container, false);
        list = root.findViewById(R.id.list);
        final AccesLocal accesLocal = new AccesLocal(this.getContext());
        try {
            arrayList = accesLocal.selectMatchFromUserSQLite(1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ImageButton button_search = root.findViewById(R.id.search_button);
        final TextView search = root.findViewById(R.id.search);
        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    arrayList = accesLocal.selectMatchByNameUserSQLite(search.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                MatchsListAdapter matchsList = new MatchsListAdapter(getActivity(), arrayList);
                list.setAdapter(matchsList);

            }
        });
        MatchsListAdapter matchsList = new MatchsListAdapter(getActivity(), arrayList);
        list.setAdapter(matchsList);
        final Context context = this.getContext();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", arrayList.get(position).getId());
                new ManageFragments().goToWithParams(MatchsFragment.this, new OtherProfileFragment(),bundle);
            }
        });
        return root;
    }
}