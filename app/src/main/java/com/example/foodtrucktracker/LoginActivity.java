package com.example.foodtrucktracker;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private EditText etEmailILA;
    private EditText etPasswordLA;
    private Button btnLoginLA;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //check if the user login already
        if (ParseUser.getCurrentUser() != null) {
//            goMainActivity();
        }

        etEmailILA = findViewById(R.id.etEmailILA);
        etPasswordLA = findViewById(R.id.etPasswordLA);
        btnLoginLA = findViewById(R.id.btnLoginLA);
//        btnSignUp = findViewById(R.id.btnSignUp);

//        btnSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i(TAG, "onClick sign up button");
//                String username = etEmailILA.getText().toString();
//                String password = etPasswordLA.getText().toString();
//                signUpUser(username, password);
//            }
//        });
        btnLoginLA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick login button");
                String username = etEmailILA.getText().toString();
                String password = etPasswordLA.getText().toString();
                loginUser(username, password);
            }
        });
    }

//    private void signUpUser(String username, String password) {
//        Log.i(TAG, "Attempting to sign up user" + username);
//
//        // Create the ParseUser
//        ParseUser user = new ParseUser();
//        // Set core properties
//        user.setUsername(username);
//        user.setPassword(password);
//
//        // Invoke signUpInBackground
//        user.signUpInBackground(new SignUpCallback() {
//            public void done(ParseException e) {
//                if (e == null) {
//                    Toast.makeText(LoginActivity.this, "Sign Up success!", Toast.LENGTH_SHORT).show();
//                    goMainActivity();
//
//                } else {
//                    Log.e(TAG, "Issue with sign up", e);
//                    Toast.makeText(LoginActivity.this, "Issue with sign up!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//            }
//        });
//    }

    private void loginUser(String username, String password) {
        Log.i(TAG, "Attempting to login user " + username);

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with login", e);
                    Toast.makeText(LoginActivity.this, "Issue with login!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Navigate to the main activity if the user has signed in properly
//                goMainActivity();
                Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void goMainActivity() {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//        finish();
//    }
}