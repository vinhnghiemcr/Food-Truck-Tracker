package com.example.foodtrucktracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private Context context;
    private List<Review> reviews;

    public ReviewAdapter(Context context, List<Review> reviews) {
        this.context = context;
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_review, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.bind(review);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        reviews.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Review> list) {
        reviews.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvUserProfileName;
        private TextView tvUserReview;
        private RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserProfileName = itemView.findViewById(R.id.tvUserProfileName);
            tvUserReview = itemView.findViewById(R.id.tvUserReview);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }

        public void bind(Review review) {
            //bind data to view
            tvUserProfileName.setText(review.getUser().getUsername());
            tvUserReview.setText(review.getComment());
            ratingBar.setRating((float)review.getRating());
        }
    }
}
