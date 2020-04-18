package com.ykko.app.ui.admin_food_menu;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ykko.app.R;
import com.ykko.app.data.model.Menu;
import com.ykko.app.ui.admin_home.AdminMenuAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdminFoodMenuFragment extends Fragment {

    private AdminFoodMenuViewModel mViewModel;

    private List<Menu> menuPosts = new ArrayList<>();
    private List<String> keys = new ArrayList<>();

    private RecyclerView foodMenuPostsView;
    private RecyclerView.Adapter foodMenuPostsViewAdapter;
    private RecyclerView.LayoutManager foodMenuPostsViewLayoutManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(AdminFoodMenuViewModel.class);
        View root = inflater.inflate(R.layout.fragment_admin_food_menu, container, false);

        foodMenuPostsView = root.findViewById(R.id.admin_food_list_posts_view);
        foodMenuPostsView.setHasFixedSize(true);
        foodMenuPostsViewLayoutManager = new LinearLayoutManager(getActivity());
        foodMenuPostsView.setLayoutManager(foodMenuPostsViewLayoutManager);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference foodMenuPostsRef = database.getReference("menuPosts").child("posts");
        ValueEventListener menuPostListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                keys.clear();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Menu post = keyNode.getValue(Menu.class);
                    menuPosts.add(post);
                }

                foodMenuPostsViewAdapter = new AdminMenuAdapter(menuPosts);
                foodMenuPostsView.setAdapter(foodMenuPostsViewAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("", "loadPost:onCancelled", databaseError.toException());
            }
        };
        foodMenuPostsRef.addValueEventListener(menuPostListener);
        return root;
    }


}
