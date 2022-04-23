package com.example.foodtrucktracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class TrucksAdapter extends RecyclerView.Adapter<TrucksAdapter.ViewHolder> {

    private Context context;
    private List<Truck> trucks;

    public TrucksAdapter(Context context, List<Truck> trucks) {
        this.context = context;
        this.trucks = trucks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_truck, parent, false);
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

        public void bind(Truck truck) {

            //bind data to view
            tvFoodTruckName.setText(truck.getTruckName());
            tvOpeningHours.setText(truck.getOpenHours());
            tvTruckType.setText(truck.getTruckType());
            tvLocation.setText(truck.getTruckLocation());
            ParseFile image = truck.getImage();
            if ( image != null) {
                Glide.with(context).load(image.getUrl()).into(ivTruckImage);
            }
        }
    }
}
