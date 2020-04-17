package com.ykko.app.ui.admin_home;

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
import com.ykko.app.data.model.Order;
import com.ykko.app.data.model.PopularPost;
import com.ykko.app.ui.home.HomePopularPostsAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdminHomeFragment extends Fragment {

    private AdminHomeViewModel mViewModel;
    private List<Order> orderPosts = new ArrayList<>();
    private List<String> keys = new ArrayList<>();

    private RecyclerView orderPostsView;
    private RecyclerView.Adapter orderPostsViewAdapter;
    private RecyclerView.LayoutManager orderPostsViewLayoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel =
                ViewModelProviders.of(this).get(AdminHomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_admin_home, container, false);
        orderPostsView = root.findViewById(R.id.order_posts_view);
        orderPostsView.setHasFixedSize(true);
        orderPostsViewLayoutManager = new LinearLayoutManager(getActivity());
        orderPostsView.setLayoutManager(orderPostsViewLayoutManager);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference orderPostsRef = database.getReference("orderPosts").child("posts");

        ValueEventListener orderPostListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                keys.clear();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Order post = keyNode.getValue(Order.class);
                    orderPosts.add(post);
                }

                orderPostsViewAdapter = new AdminReservationAdapter(orderPosts);
                orderPostsView.setAdapter(orderPostsViewAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("", "loadPost:onCancelled", databaseError.toException());
            }
        };

        orderPostsRef.addValueEventListener(orderPostListener);
        return root;
    }



}
