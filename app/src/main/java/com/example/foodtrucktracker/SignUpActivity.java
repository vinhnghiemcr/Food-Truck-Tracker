package com.example.foodtrucktracker;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {
    public static final String TAG = "SignUpActivity";
    EditText etEmail, etPassword, etConfirmPassword;
    Button btnSignUpAsOwner,btnSignUpAsUser;
    TextView alreadyHaveAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);

        btnSignUpAsOwner = findViewById(R.id.btnSignUpAsOwner);
        btnSignUpAsUser = findViewById(R.id.btnSignUpAsUser);

        alreadyHaveAccount = findViewById(R.id.tvAlreadyHaveAccount);

        btnSignUpAsOwner.setOnClickListener(view -> {


        });

        btnSignUpAsUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String password2 = etConfirmPassword.getText().toString();
                if (password == password2) {
                    signUpUser(username, password);
                } else {
                    Toast.makeText(SignUpActivity.this, "Password must match!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLoginActivity();
            }
        });

    }
    private void goLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    private void goMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void signUpUser(String username, String password) {
        Log.i(TAG, "Attempting to sign up user" + username);

        // Create the ParseUser
        ParseUser user = new ParseUser();
        // Set core properties
        user.setUsername(username);
        user.setPassword(password);

        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(SignUpActivity.this, "Sign Up success!", Toast.LENGTH_SHORT).show();
                        goMainActivity();

                } else {
                    Log.e(TAG, "Issue with sign up", e);
                    Toast.makeText(SignUpActivity.this, "Issue with sign up!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
}