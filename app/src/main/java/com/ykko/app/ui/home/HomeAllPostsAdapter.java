package com.ykko.app.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.ykko.app.R;
import com.ykko.app.data.model.AllPost;
import java.util.List;

public class HomeAllPostsAdapter extends RecyclerView.Adapter<HomeAllPostsAdapter.MyViewHolder> {
    private List<AllPost> posts;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView priceTextView;
        public TextView foodNameTextView;
        public TextView foodTypeTextView;
        public TextView foodSizeTextView;

        public Button reserveBtn;

        public MyViewHolder(View itemView) {
            super(itemView);
            priceTextView = itemView.findViewById(R.id.food_price);
            foodNameTextView = itemView.findViewById(R.id.food_name);
            foodTypeTextView = itemView.findViewById(R.id.food_type);
            foodSizeTextView = itemView.findViewById(R.id.food_size);
            reserveBtn = itemView.findViewById(R.id.reserve_btn);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public HomeAllPostsAdapter(List<AllPost> allPosts) {
        posts = allPosts;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public HomeAllPostsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view\
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View allPostView = inflater.inflate(R.layout.visitor_all_posts_row,parent,false);

        MyViewHolder viewHolder  = new MyViewHolder(allPostView);

        viewHolder.reserveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_table_reservation);
            }
        });
        return viewHolder ;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        TextView priceTextView = holder.priceTextView;
        TextView foodNameTextView = holder.foodNameTextView;
        TextView foodTypeTextView = holder.foodTypeTextView;
        TextView foodSizeTextView = holder.foodSizeTextView;

        priceTextView.setText(posts.get(position).price);
        foodNameTextView.setText(posts.get(position).foodName);
        foodTypeTextView.setText(posts.get(position).foodType);
        foodSizeTextView.setText(posts.get(position).foodSize);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return posts.size();
    }
}
