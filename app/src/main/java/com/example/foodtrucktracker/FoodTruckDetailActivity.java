package com.example.foodtrucktracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import org.parceler.Parcels;

public class FoodTruckDetailActivity extends AppCompatActivity {

    private TextView tvFoodTruckName;
    private TextView tvOpeningHours;
    private TextView tvTruckType;
    private TextView tvLocation;
    private ImageView ivTruckImage;
    private TextView tvTruckDescription;

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


        Truck truck = Parcels.unwrap(getIntent().getParcelableExtra("truck"));
        tvFoodTruckName.setText(truck.getTruckName());
        tvOpeningHours.setText(truck.getOpenHours());
        tvTruckType.setText(truck.getTruckType());
        tvTruckDescription.setText(truck.getTruckDescription());
        ParseFile image = truck.getImage();
        if ( image != null) {
            Glide.with(this.getApplicationContext()).load(image.getUrl()).into(ivTruckImage);
        }
    }
}