package com.example.foodtrucktracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import com.bumptech.glide.Glide;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class FoodTruckDetailActivity extends AppCompatActivity {
    public static final String TAG = "FoodTruckDetailActivity";
    private TextView tvFoodTruckName;
    private TextView tvOpeningHours;
    private TextView tvTruckType;
    private TextView tvLocation;
    private ImageView ivTruckImage;
    private TextView tvTruckDescription;
    private ImageView ivFavoriteBtn;
    private ImageView ivAddPhotos;
    private ImageView ivAddReviews;
    private Context context;
    AlertDialog dialogAddPhotos;
    AlertDialog dialogAddReviews;
    AlertDialog dialog;
    private RecyclerView rvPhotos;
    private ImageAdapter imageAdapter;
    private List<Image> allPhotos;
    private RecyclerView rvReviews;
    protected ReviewAdapter reviewAdapter;
    protected List<Review> allReviews;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_truck_detail);


        tvFoodTruckName = findViewById(R.id.tvFoodTruckName);
        tvOpeningHours = findViewById(R.id.tvOpeningHours);
        tvTruckType = findViewById(R.id.tvTruckType);
//        tvLocation = findViewById(R.id.tvLocation);
        ivTruckImage = findViewById(R.id.ivTruckImage);
        tvTruckDescription = findViewById(R.id.tvTruckDescription);
        ivFavoriteBtn = findViewById(R.id.ivFavoriteBtn);

        ivAddPhotos = findViewById(R.id.ivAddPhotos);

        // opens dialog on click of add photos
        ivAddPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(FoodTruckDetailActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_add_photos, null);
                mBuilder.setView(mView);
                dialog = mBuilder.create();
                dialog.show();
                ImageView closeTaskImage = mView.findViewById(R.id.closeTaskImage);
                closeTaskImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });

        ivAddReviews = findViewById(R.id.ivAddReviews);
        //opens dialog on click of add reviews
        ivAddReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(FoodTruckDetailActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_add_review, null);
                mBuilder.setView(mView);
                dialogAddReviews = mBuilder.create();
                dialogAddReviews.show();

                ImageView ivCloseReviewDialog = mView.findViewById(R.id.ivCloseReviewDialog);
                ivCloseReviewDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogAddReviews.dismiss();
                    }
                });
            }
        });

        Truck truck = Parcels.unwrap(getIntent().getParcelableExtra("truck"));
        tvFoodTruckName.setText(truck.getTruckName());
        tvOpeningHours.setText(truck.getOpenHours());
        tvTruckType.setText(truck.getTruckType());
        tvTruckDescription.setText(truck.getTruckDescription());
        ParseFile image = truck.getImage();
        if ( image != null) {
            Glide.with(this.getApplicationContext()).load(image.getUrl()).into(ivTruckImage);
        }
        //set ivFavoriteBtn
        renderFollowIcon(truck);


        ivFavoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleFollowBtnClick(truck);
            }
        });

        //---Display images of the truck----
        // Lookup the swipe container view
        rvPhotos = findViewById(R.id.rvPhotos);
        allPhotos = new ArrayList<>();
        //To use the Recycler view:
        // 1. Create layout for a row in the list
        // 2. Create the adapter
        imageAdapter = new ImageAdapter(this, allPhotos);
        // 3. Create the data source
        // 4. Set the adapter on the RV
        rvPhotos.setAdapter(imageAdapter);
        // 5. Set the layout manager on the RV
        rvPhotos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        queryPhotos(truck);

        //---Display reviews of the truck---
        // Lookup the swipe container view
        rvReviews = findViewById(R.id.rvReviews);
        allReviews = new ArrayList<>();
        //To use the Recycler view:
        // 1. Create layout for a row in the list
        // 2. Create the adapter
        reviewAdapter = new ReviewAdapter(this, allReviews);
        // 3. Create the data source
        // 4. Set the adapter on the RV
        rvReviews.setAdapter(reviewAdapter);
        // 5. Set the layout manager on the RV
        rvReviews.setLayoutManager(new LinearLayoutManager(this.context));
        queryReviews(truck);

    }

    private void renderFollowIcon(Truck truck){

        // Specify which class to query
        ParseQuery<Follow> query = ParseQuery.getQuery(Follow.class);
        query.include(Follow.KEY_TRUCK);
        query.include(Follow.KEY_USER);
        query.whereEqualTo(Follow.KEY_TRUCK, truck);
        query.whereEqualTo(Follow.KEY_USER, ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<Follow>() {
            @Override
            public void done(List<Follow> follows, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting follows", e);
                    return;
                }
                if (follows.size() != 0) {
                    ivFavoriteBtn.setImageResource(R.drawable.like);
                } else {
                    ivFavoriteBtn.setImageResource(R.drawable.unlike);
                }
            }
        });
    }

    private void queryReviews(Truck truck) {
        // Specify which class to query
        ParseQuery<Review> query = ParseQuery.getQuery(Review.class);
        query.include(Review.KEY_TRUCK);
        query.whereEqualTo(Review.KEY_TRUCK, truck);
        query.include(Review.KEY_USER);
        query.setLimit(20);
        query.addDescendingOrder(Image.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Review>() {
            @Override
            public void done(List<Review> reviews, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting reviews", e);
                    return;
                }
                for (Review review : reviews) {
                    Log.i(TAG, "Review " + review.getComment()+ " username = " + review.getUser().getUsername());
                }

                allReviews.addAll(reviews);
                reviewAdapter.notifyDataSetChanged();
            }
        });
    }

    protected void handleFollowBtnClick(Truck truck) {
        // Specify which class to query
        ParseQuery<Follow> query = ParseQuery.getQuery(Follow.class);
        query.include(Follow.KEY_TRUCK);
        query.include(Follow.KEY_USER);
        query.whereEqualTo(Follow.KEY_TRUCK, truck);
        query.whereEqualTo(Follow.KEY_USER, ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<Follow>() {
            @Override
            public void done(List<Follow> follows, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting follows", e);
                    return;
                }
                if (follows.size() != 0) {
                    unfollowTruck(ParseUser.getCurrentUser(), truck);
                    ivFavoriteBtn.setImageResource(R.drawable.unlike);
                } else {
                    followTruck(ParseUser.getCurrentUser(), truck);
                    ivFavoriteBtn.setImageResource(R.drawable.like);
                }
            }
        });
    }

    protected void followTruck(ParseUser user, Truck truck) {
        Follow follow = new Follow();
        follow.setUser(user);
        follow.setTruck(truck);
        follow.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Error while saving", e);
                    Toast.makeText(context, "Error while saving!", Toast.LENGTH_SHORT).show();
                }
                Log.i(TAG, "Follow save was successful!");
            }
        });

    }

    protected void unfollowTruck(ParseUser user, Truck truck) {

        ParseQuery<Follow> query = ParseQuery.getQuery(Follow.class);
        query.include(Follow.KEY_USER);
        query.whereEqualTo(Follow.KEY_USER, user);
        query.include(Follow.KEY_TRUCK);
        query.whereEqualTo(Follow.KEY_TRUCK, truck);
        query.getFirstInBackground (new GetCallback<Follow>() {
            @Override
            public void done(Follow follow, ParseException e) {
                // TODO Auto-generated method stub
                try {
                    follow.delete();
                    follow.saveInBackground();
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }

            }
        });

    }

    protected void queryPhotos(Truck truck) {
        // Specify which class to query
        ParseQuery<Image> query = ParseQuery.getQuery(Image.class);
        query.include(Image.KEY_USER);
        query.include(Image.KEY_TRUCK);
        query.whereEqualTo(Image.KEY_TRUCK, truck);
        query.setLimit(20);
        query.addDescendingOrder(Image.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Image>() {
            @Override
            public void done(List<Image> images, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting trucks", e);
                    return;
                }
                for (Image image : images) {
                    Log.i(TAG, "Truck " + image.getTruck()+ " username = " + image.getUser().getUsername());
                }

                allPhotos.addAll(images);
                imageAdapter.notifyDataSetChanged();
            }
        });
    }
}