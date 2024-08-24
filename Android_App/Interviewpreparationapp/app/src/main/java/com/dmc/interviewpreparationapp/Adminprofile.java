package com.dmc.interviewpreparationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Adminprofile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminprofile);

        getSupportActionBar().setTitle("Profile");
    }
}