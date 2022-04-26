package com.example.foodtrucktracker;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FollowAdapter extends RecyclerView.Adapter<FollowAdapter.ViewHolder> {

    private Context context;
    private List<Follow> follows;

    public FollowAdapter(Context context, List<Follow> follows) {
        this.context = context;
        this.follows = follows;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_truck, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Follow follow = follows.get(position);
        holder.bind(follow);

    }

    @Override
    public int getItemCount() {
        return follows.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        follows.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Follow> list) {
        follows.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        private TextView tvFoodTruckName;
        private TextView tvOpeningHours;
        private TextView tvTruckType;
        private TextView tvLocation;
        private ImageView ivTruckImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvFoodTruckName = itemView.findViewById(R.id.tvFoodTruckName);
            tvOpeningHours = itemView.findViewById(R.id.tvOpeningHours);
            tvTruckType = itemView.findViewById(R.id.tvTruckType);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            ivTruckImage = itemView.findViewById(R.id.ivTruckImage);
        }

        public void bind(Follow follow) {
            // Can you bind the data as i am not getting how to set truck details from list of follow objects
            //bind data to view
            //     tvFoodTruckName.setText(follow.getTruck());
//            tvOpeningHours.setText(truck.getOpenHours());
//            tvTruckType.setText(truck.getTruckType());
//            tvLocation.setText(truck.getTruckLocation());
//            ParseFile image = truck.getImage();
//            if ( image != null) {
//                Glide.with(context).load(image.getUrl()).into(ivTruckImage);
//            }

        }


    }
}

