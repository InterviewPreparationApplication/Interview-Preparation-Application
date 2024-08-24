package com.dmc.interviewpreparationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import HelperClasses.UserHelperClass;

public class Candidateinfodataadmin extends AppCompatActivity {

    TextView firstname, lastname, email, address, qualification;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidateinfodataadmin);

        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        qualification = findViewById(R.id.qualification);


        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("user")) {
            UserHelperClass userData = intent.getParcelableExtra("user");
            Log.e("sunbeam001","userdata: " +userData);
            if (userData != null) {
                // Access user data properties
                firstname.setText("Firstname: " +userData.getFirstname());
                lastname.setText("Lastname: " +userData.getLastname());
                email.setText("email:" +userData.getMobileno());
                address.setText("Address: " +userData.getAddress());
                qualification.setText("qualification: " +userData.getDob());
            } else {
                // Handle case when userData is null
                Toast.makeText(this, "User data is null", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Handle case when intent or "user" extra is missing
            Toast.makeText(this, "Intent or user extra is missing", Toast.LENGTH_SHORT).show();
        }

    }
}