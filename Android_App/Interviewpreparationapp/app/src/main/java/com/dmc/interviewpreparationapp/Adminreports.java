package com.dmc.interviewpreparationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Adminreports extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminreports);

        getSupportActionBar().setTitle("Reports");
    }
}