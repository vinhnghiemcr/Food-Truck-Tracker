package com.example.foodtrucktracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private Context context;
    private List<Truck> trucks;

    public ImageAdapter(Context context, List<Truck> trucks) {
        this.context = context;
        this.trucks = trucks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Truck truck = trucks.get(position);
        holder.bind(truck);

    }

    @Override
    public int getItemCount() {
        return trucks.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        trucks.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Truck> list) {
        trucks.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        private TextView tv_name;
        private ImageView ivImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            ivImage = itemView.findViewById(R.id.iv_image);
        }

        public void bind(Truck truck) {

            //bind data to view
            tv_name.setText("George");
            ParseFile image = truck.getImage();
            if ( image != null) {
                Glide.with(context).load(image.getUrl()).into(ivImage);
            }
        }


    }
}
