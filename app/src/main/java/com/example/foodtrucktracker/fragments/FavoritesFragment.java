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
import com.example.foodtrucktracker.FollowAdapter;
import com.example.foodtrucktracker.Image;
import com.example.foodtrucktracker.ImageAdapter;
import com.example.foodtrucktracker.R;
import com.example.foodtrucktracker.TrucksAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {
    public static final String TAG = "FavoritesFragment";
    private List<Follow> allFollows;
    private RecyclerView rvfavorites;
    private FollowAdapter followsAdapter;
    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvfavorites = view.findViewById(R.id.rvFavorites);
        allFollows = new ArrayList<>();
        queryFollows(ParseUser.getCurrentUser());

        followsAdapter = new FollowAdapter(getContext(), allFollows);
    }

    protected void queryFollows(ParseUser user) {
        // Specify which class to query
        ParseQuery<Follow> query = ParseQuery.getQuery(Follow.class);
        query.whereEqualTo(Follow.KEY_USER, user);
        query.setLimit(20);
        query.addDescendingOrder(Image.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Follow>() {
            @Override
            public void done(List<Follow> follows, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting trucks", e);
                    return;
                }
                for (Follow follow : follows) {
                    Log.i(TAG, "Truck " + follow.getTruck()+ " username = " + follow.getUser().getUsername());
                }

                allFollows.addAll(follows);
                followsAdapter.notifyDataSetChanged();
            }
        });
    }
}