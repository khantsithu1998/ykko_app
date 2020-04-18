package com.ykko.app.ui.admin_home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.ykko.app.R;
import com.ykko.app.data.model.Feedback;

import java.util.List;

public class AdminReviewAdapter extends RecyclerView.Adapter<AdminReviewAdapter.MyViewHolder> {
    private List<Feedback> posts;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView reviewerNameTextView;
        public TextView commentTextView;
        public RatingBar ratingBar;

        public MyViewHolder(View itemView) {
            super(itemView);
            reviewerNameTextView = itemView.findViewById(R.id.admin_reviewer_name);
            commentTextView = itemView.findViewById(R.id.admin_reviewer_comment);
            ratingBar = itemView.findViewById(R.id.admin_reviewer_rating);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(v).navigate(R.id.nav_admin_review);
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdminReviewAdapter(List<Feedback> reviewPosts) {
        posts = reviewPosts;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdminReviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        // create a new view\
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View reviewPostView = inflater.inflate(R.layout.admin_review_posts_row,parent,false);

        AdminReviewAdapter.MyViewHolder viewHolder  = new AdminReviewAdapter.MyViewHolder(reviewPostView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        TextView reviewerNameTextView = holder.reviewerNameTextView;
        TextView commentTextView = holder.commentTextView;
        RatingBar ratingBar = holder.ratingBar;

        reviewerNameTextView.setText(posts.get(position).reviewerName);
        commentTextView.setText(posts.get(position).comment);
        ratingBar.setRating(posts.get(position).quality);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


}
