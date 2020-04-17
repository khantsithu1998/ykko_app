package com.ykko.app.ui.confirm_order;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ykko.app.R;
import com.ykko.app.data.model.Order;

import java.util.ArrayList;
import java.util.List;

public class ConfirmOrderFragment extends Fragment {

    private ConfirmOrderViewModel mViewModel;
    FirebaseDatabase database;
    Order newOrder = new Order();
    long orderPostId = 1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel =
                ViewModelProviders.of(this).get(ConfirmOrderViewModel.class);
        View root = inflater.inflate(R.layout.fragment_confirm_order, container, false);
        Button confirmTableBtn = root.findViewById(R.id.confirm_table_btn);
        database = FirebaseDatabase.getInstance();
        DatabaseReference orderPostsRef = database.getReference("orderPosts").child("posts");
        ValueEventListener orderPostListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                if(dataSnapshot.exists()){
                    orderPostId = dataSnapshot.getChildrenCount() +  1;
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("", "loadPost:onCancelled", databaseError.toException());
            }
        };
        orderPostsRef.addValueEventListener(orderPostListener);

        confirmTableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTable();
                Navigation.findNavController(v).navigate(R.id.nav_reserved);
            }
        });

        TextView confirmName = root.findViewById(R.id.confirm_name);
        TextView confirmPhNo = root.findViewById(R.id.confirm_phNo);
        TextView confirmDate = root.findViewById(R.id.confirm_date);
        TextView confirmBranch = root.findViewById(R.id.confirm_branch);
        TextView confirmTownship = root.findViewById(R.id.confirm_township);
        TextView confirmNoOfPersons = root.findViewById(R.id.confirm_noOfPerson);
        TextView confirmFoodOne = root.findViewById(R.id.confirm_food1);
        TextView confirmFoodTwo= root.findViewById(R.id.confirm_food2);
        TextView confirmDes = root.findViewById(R.id.confirm_des);

        newOrder = getArguments().getParcelable("newOrderKey");
        confirmName.setText(newOrder.name);
        confirmPhNo.setText(newOrder.phNo);
        confirmDate.setText(newOrder.date);
        confirmBranch.setText(newOrder.branch);
        confirmTownship.setText(newOrder.township);
        confirmNoOfPersons.setText(Integer.toString(newOrder.numberOfPersons));
        confirmFoodOne.setText(newOrder.food1);
        confirmFoodTwo.setText(newOrder.food2);
        confirmDes.setText(newOrder.description);
        return root;
    }

    public void saveTable(){
        DatabaseReference orderPostsRef = database.getReference("orderPosts");
        String postID = String.valueOf(orderPostId);
        orderPostsRef.child("posts").child(postID).setValue(newOrder);
        Toast.makeText(getActivity(),"Confirm Order Successful",Toast.LENGTH_SHORT).show();
    }

}
