package com.example.foodtrucktracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class FoodTruckDetailActivity extends AppCompatActivity {
    public static final String TAG = "FoodTruckDetailActivity";
    protected List<Truck> allTrucks;
    protected ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_truck_detail);

        RecyclerView rvPhotos = findViewById(R.id.rvPhotos);
        allTrucks = new ArrayList<>();
        queryTrucks();
        adapter = new ImageAdapter(getApplicationContext(), allTrucks);
        rvPhotos.setAdapter(adapter);
        // 5. Set the layout manager on the RV
        rvPhotos.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));

    }

    protected void queryTrucks() {
        // Specify which class to query
        ParseQuery<Truck> query = ParseQuery.getQuery(Truck.class);
        query.include(Truck.KEY_USER);
        query.setLimit(20);
        query.addDescendingOrder(Truck.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Truck>() {
            @Override
            public void done(List<Truck> trucks, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting trucks", e);
                    return;
                }
                for (Truck truck : trucks) {
                    Log.i(TAG, "Truck " + truck.getTruckName() + " username = " + truck.getUser().getUsername());
                }

                allTrucks.addAll(trucks);
                adapter.notifyDataSetChanged();
            }
        });
    }
}