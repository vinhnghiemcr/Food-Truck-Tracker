package com.example.foodtrucktracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {
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

        btnSignUpAsOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnSignUpAsUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });







    }
}