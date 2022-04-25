package com.example.foodtrucktracker;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Truck.class);
        ParseObject.registerSubclass(Review.class);
        ParseObject.registerSubclass(Dish.class);
        ParseObject.registerSubclass(Follow.class);

        // set applicationId, and server server based on the values in the back4app settings.
        // any network interceptors must be added with the Configuration Builder given this syntax
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("yrxTdoZqgiGr0K25XDwtMYNJjDsVocZcx2Wc1K5w")
                .clientKey("V6QqeDddpQYn6IFOpzuuiHm1LZonS1n2WiePvuds")
                .server("https://parseapi.back4app.com")
                .build());
    }

}
