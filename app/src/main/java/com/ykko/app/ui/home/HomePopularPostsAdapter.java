package com.ykko.app.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ykko.app.R;
import com.ykko.app.data.model.PopularPost;

import java.util.List;

public class HomePopularPostsAdapter extends RecyclerView.Adapter<HomePopularPostsAdapter.MyViewHolder> {
    private List<PopularPost> posts;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public TextView foodNameTextView;
        public RatingBar ratingBar;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.popular_food_price);
            foodNameTextView = itemView.findViewById(R.id.popular_food_name);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public HomePopularPostsAdapter( List<PopularPost> popularPosts) {
        posts = popularPosts;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public HomePopularPostsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        // create a new view\
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View allPostView = inflater.inflate(R.layout.visitor_popular_posts_row,parent,false);

        MyViewHolder viewHolder  = new MyViewHolder(allPostView);
        return viewHolder ;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        TextView textView = holder.textView;
        TextView foodNameTextView = holder.foodNameTextView;
        RatingBar ratingBar = holder.ratingBar;

        textView.setText(posts.get(position).price);
        foodNameTextView.setText(posts.get(position).foodName);
        ratingBar.setNumStars(posts.get(position).quality);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return posts.size();
    }
}
