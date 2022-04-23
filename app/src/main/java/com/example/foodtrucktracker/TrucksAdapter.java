package com.example.foodtrucktracker;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
