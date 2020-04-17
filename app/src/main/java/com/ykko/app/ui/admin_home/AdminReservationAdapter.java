package com.ykko.app.ui.admin_home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ykko.app.R;
import com.ykko.app.data.model.Order;

import java.util.List;

public class AdminReservationAdapter extends RecyclerView.Adapter<AdminReservationAdapter.MyViewHolder> {
    private List<Order> posts;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView confirmTownshipTextView;
        public Button confirmBtn;

        public MyViewHolder(View itemView) {
            super(itemView);
            confirmTownshipTextView = itemView.findViewById(R.id.admin_confirm_township);
            confirmBtn = itemView.findViewById(R.id.confirm_table_btn);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdminReservationAdapter(List<Order> orderPosts) {
        posts = orderPosts;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdminReservationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                                    int viewType) {
        // create a new view\
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View orderPostView = inflater.inflate(R.layout.admin_reservation_posts_row,parent,false);

        AdminReservationAdapter.MyViewHolder viewHolder  = new AdminReservationAdapter.MyViewHolder(orderPostView);

//        viewHolder.reserveBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Navigation.findNavController(v).navigate(R.id.nav_table_reservation);
//            }
//        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        TextView priceTextView = holder.confirmTownshipTextView;
        Button confirmBtn = holder.confirmBtn;

        priceTextView.setText(posts.get(position).township);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference orderPostsRef = database.getReference("orderPosts").child("posts");
                orderPostsRef.child(String.valueOf(position)).removeValue();
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


}
