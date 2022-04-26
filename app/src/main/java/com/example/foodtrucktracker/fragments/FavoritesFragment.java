package com.example.foodtrucktracker.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodtrucktracker.Follow;

import com.example.foodtrucktracker.R;
import com.example.foodtrucktracker.Truck;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;

import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class FavoritesFragment extends TimelineFragment {
    public static final String TAG = "FavoritesFragment";

    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    protected void queryTrucks() {
        super.queryTrucks();
        List<Truck> favTrucks = new ArrayList<>();
        // Specify which class to query
        ParseQuery<Follow> query = ParseQuery.getQuery(Follow.class);
        query.include(Follow.KEY_USER);
        query.whereEqualTo(Follow.KEY_USER, ParseUser.getCurrentUser());
        query.include(Follow.KEY_TRUCK);
        query.setLimit(20);
        query.findInBackground(new FindCallback<Follow>() {
            @Override
            public void done(List<Follow> follows, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting trucks", e);
                    return;
                }
                for (Follow follow : follows) {
                    Log.i(TAG, "Truck= " + follow.getTruck() + " follower= " + follow.getUser().getUsername());
                    favTrucks.add((Truck) follow.getTruck());

                }
                adapter.clear();
                allTrucks.addAll(favTrucks);
            }
        });
    }


   
}