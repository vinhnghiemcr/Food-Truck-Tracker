package com.example.foodtrucktracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.parceler.Parcels;

public class FoodTruckDetailActivity extends AppCompatActivity {
    public static final String TAG = "FoodTruckDetailActivity";
    private TextView tvFoodTruckName;
    private TextView tvOpeningHours;
    private TextView tvTruckType;
    private TextView tvLocation;
    private ImageView ivTruckImage;
    private TextView tvTruckDescription;
    private ImageView ivFavoriteBtn;
    private Context context;

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


        Truck truck = Parcels.unwrap(getIntent().getParcelableExtra("truck"));
        tvFoodTruckName.setText(truck.getTruckName());
        tvOpeningHours.setText(truck.getOpenHours());
        tvTruckType.setText(truck.getTruckType());
        tvTruckDescription.setText(truck.getTruckDescription());
        ParseFile image = truck.getImage();
        if ( image != null) {
            Glide.with(this.getApplicationContext()).load(image.getUrl()).into(ivTruckImage);
        }

        ivFavoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                followTruck(v, ParseUser.getCurrentUser(), truck);
            }
        });


    }



    protected void followTruck(View v, ParseUser user, Truck truck) {
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
}