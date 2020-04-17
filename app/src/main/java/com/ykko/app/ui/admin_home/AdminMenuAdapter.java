package com.ykko.app.ui.admin_home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ykko.app.R;
import com.ykko.app.data.model.Feedback;
import com.ykko.app.data.model.Menu;

import java.util.List;

public class AdminMenuAdapter extends RecyclerView.Adapter<AdminMenuAdapter.MyViewHolder> {
    private List<Menu> posts;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView foodStickNameTextView;
        public TextView foodStickTypeTextView;
        public TextView priceTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            foodStickNameTextView = itemView.findViewById(R.id.admin_food_stick_name);
            foodStickTypeTextView = itemView.findViewById(R.id.admin_food_stick_type);
            priceTextView = itemView.findViewById(R.id.admin_food_stick_price);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdminMenuAdapter(List<Menu> menuPosts) {
        posts = menuPosts;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdminMenuAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        // create a new view\
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View menuPostView = inflater.inflate(R.layout.admin_foodmenu_posts_row,parent,false);

        AdminMenuAdapter.MyViewHolder viewHolder  = new AdminMenuAdapter.MyViewHolder(menuPostView);

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
        TextView foodStickNameTextView = holder.foodStickNameTextView;
        TextView foodStickTypeTextView = holder.foodStickTypeTextView;
        TextView priceTextView = holder.priceTextView;

        foodStickNameTextView.setText(posts.get(position).foodStickName);
        foodStickTypeTextView.setText(posts.get(position).foodStickType);
        priceTextView.setText(posts.get(position).price);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


}
