package com.example.foodtrucktracker.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodtrucktracker.R;
import com.example.foodtrucktracker.Truck;
import com.example.foodtrucktracker.TrucksAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class TimelineFragment extends Fragment {
    public static final String TAG = "TimelineFragment";
    private RecyclerView rvTrucks;
    protected TrucksAdapter adapter;
    protected List<Truck> allTrucks;

    public TimelineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timeline, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Lookup the swipe container view
        rvTrucks = view.findViewById(R.id.rvTrucks);

        allTrucks = new ArrayList<>();
        //To use the Recycler view:
        // 1. Create layout for a row in the list
        // 2. Create the adapter
        adapter = new TrucksAdapter(getContext(), allTrucks);
        // 3. Create the data source
        // 4. Set the adapter on the RV
        rvTrucks.setAdapter(adapter);
        // 5. Set the layout manager on the RV
        rvTrucks.setLayoutManager(new LinearLayoutManager(getContext()));
        queryTrucks();
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
                    Log.i(TAG, "Truck " + truck.getTruckDescription() + " username = " + truck.getUser().getUsername());
                }

                allTrucks.addAll(trucks);
                adapter.notifyDataSetChanged();
            }
        });
    }
}