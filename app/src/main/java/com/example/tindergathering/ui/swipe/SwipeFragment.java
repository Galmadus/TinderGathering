package com.example.tindergathering.ui.swipe;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;

import com.example.tindergathering.CardStackAdapter;
import com.example.tindergathering.CardStackCallback;
import com.example.tindergathering.ItemModel;
import com.example.tindergathering.ManageFragments;
import com.example.tindergathering.R;
import com.example.tindergathering.ui.matchs.MatchsFragment;
import com.example.tindergathering.ui.other_profile.OtherProfileFragment;
import com.example.tindergathering.ui.user.User;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SwipeFragment extends Fragment {

    private static final String TAG = SwipeFragment.class.getSimpleName();
    private CardStackLayoutManager manager;
    private CardStackAdapter adapter;
    private List<ItemModel> items = new ArrayList<>();
    private int current_item;
    private Context context = this.getContext();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_swipe, container, false);

        //Si l'on provient de la page profil
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String receivedPseudo = bundle.getString("name", "John");
            items.add(new ItemModel(R.drawable.sample4, receivedPseudo, "19", "Reims","Commander, Standard"));
        }


        init(root);
        return root;
    }

    private void init(View root) {
        CardStackView cardStackView = root.findViewById(R.id.card_stack_view);
        manager = new CardStackLayoutManager(getContext(), new CardStackListener() {

            @Override
            public void onCardDragging(Direction direction, float ratio) {
                Log.d(TAG, "onCardDragging: d=" + direction.name() + " ratio=" + ratio);
            }

            @Override
            public void onCardSwiped(Direction direction) {
                Log.d(TAG, "onCardSwiped: p=" + manager.getTopPosition() + " d=" + direction);
                ItemModel current = items.get(current_item);
                if (direction == Direction.Right) {
                    Toast.makeText(getContext(), "Matched : "+current.getName(), Toast.LENGTH_SHORT).show();
                    // TODO Send User swiped to API
                }
                if (direction == Direction.Left){
                    Toast.makeText(getContext(), "Discarded : "+current.getName(), Toast.LENGTH_SHORT).show();
                }



                if (direction == Direction.Top){
                    Toast.makeText(getContext(), "View profile", Toast.LENGTH_SHORT).show();
                    // Set data to pass
                    Bundle bundle = new Bundle();
                    bundle.putString("name", current.getName());  // Key, value
                    bundle.putString("id", "45");
                    new ManageFragments().goToWithParams(SwipeFragment.this, new OtherProfileFragment(),bundle);
                }
                /* A ajouter si l'on met une action dans la direction top et bottom (intéressé, non intéréssé)
                if (direction == Direction.Bottom){
                    Toast.makeText(getContext(), "Matched", Toast.LENGTH_SHORT).show();
                }*/

                // Paginating
                if (manager.getTopPosition() == adapter.getItemCount() - 5){
                    paginate();
                }

            }

            @Override
            public void onCardRewound() {
                Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
            }

            @Override
            public void onCardCanceled() {
                Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
            }

            @Override
            public void onCardAppeared(View view, int position) {
                TextView tv = view.findViewById(R.id.item_name);
                Log.d(TAG, "onCardAppeared: " + position + ", name: " + tv.getText());
            }

            @Override
            public void onCardDisappeared(View view, int position) {
                TextView tv = view.findViewById(R.id.item_name);
                current_item = position;
                Log.d(TAG, "onCardAppeared: " + position + ", name: " + tv.getText());
            }
        });
        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        ArrayList<Direction> directions = new ArrayList<Direction>();
        directions.add(Direction.Top);
        directions.add(Direction.Left);
        directions.add(Direction.Right);
        manager.setDirections(directions);
        manager.setSwipeableMethod(SwipeableMethod.Manual);
        manager.setOverlayInterpolator(new LinearInterpolator());
        adapter = new CardStackAdapter(addList());
        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(adapter);
        cardStackView.setItemAnimator(new DefaultItemAnimator());
    }

    private void paginate() {
        List<ItemModel> ancien = adapter.getItems();
        List<ItemModel> nouveau = new ArrayList<>(addList());
        CardStackCallback callback = new CardStackCallback(ancien, nouveau);
        DiffUtil.DiffResult hasil = DiffUtil.calculateDiff(callback);
        adapter.setItems(nouveau);
        hasil.dispatchUpdatesTo(adapter);
    }

    private List<ItemModel> addList() {
        User u;
        // ArrayList<User> usersSwipe = accesLocal.selectAllUserExceptUserInParamSQLite(new User()).toString();
        for (int i=0; i<20; i++){
            u = new User();
            items.add(new ItemModel(randomNameDrawable(), u.getUsername(), u.getAge()+"", u.getVille(),"Pioneer, Commander, Standard"));
        }
        items.add(new ItemModel(R.drawable.sample1, "Antonin", "24", "Reims","Pioneer, Commander, Standard"));
        items.add(new ItemModel(R.drawable.sample2, "Clément", "20", "Épernay","Commander, Standard"));
        items.add(new ItemModel(R.drawable.sample3, "Hugo", "27", "Reims","Brawl"));
        items.add(new ItemModel(R.drawable.sample4, "Jérémy", "19", "Reims","Commander, Standard"));
        return items;
    }

    public int randomNameDrawable(){
        List<Integer>list = new ArrayList<>();
        list.add(R.drawable.sample1);
        list.add(R.drawable.sample2);
        list.add(R.drawable.sample3);
        list.add(R.drawable.sample4);
        list.add(R.drawable.sample5);
        list.add(R.drawable.sample6);
        list.add(R.drawable.sample7);
        list.add(R.drawable.sample8);
        list.add(R.drawable.sample9);
        list.add(R.drawable.sample10);
        list.add(R.drawable.sample11);
        list.add(R.drawable.sample12);
        list.add(R.drawable.sample13);
        list.add(R.drawable.sample14);
        list.add(R.drawable.sample15);
        list.add(R.drawable.sample16);
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

}