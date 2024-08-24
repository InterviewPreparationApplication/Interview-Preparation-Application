package com.dmc.interviewpreparationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import HelperClasses.Interviewerhelper;
import HelperClasses.UserHelperClass;

public class InterviwerinfodataAdmin extends AppCompatActivity {
    TextView ifirstname, ilastname, iemail, iaddress, iqualification,iworkposition;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interviwerinfodata_admin);

        ifirstname = findViewById(R.id.ifirstname);
        ilastname = findViewById(R.id.ilastname);
        iemail = findViewById(R.id.iemail);
        iaddress = findViewById(R.id.iaddress);
        iqualification = findViewById(R.id.iqualification);
        iworkposition=findViewById(R.id.iworkposition);


        databaseReference = FirebaseDatabase.getInstance().getReference("Interviwer");
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("iuser")) {
            Interviewerhelper userData = intent.getParcelableExtra("iuser");
            Log.e("sunbeam001","userdata: " +userData);
            if (userData != null) {
                // Access user data properties
                //there is some data name inconsistency following dont do on name
                ifirstname.setText("Firstname: " +userData.getInterviewerfirstname());
                ilastname.setText("Lastname: " +userData.getInterviewerlastname());
                iemail.setText("email:" +userData.getIntervieweraddress());
                iaddress.setText("Address: " +userData.getInterviewerdob());
                iqualification.setText("qualification: " +userData.getInterviewerage());
                iworkposition.setText("Work Position: " +userData.getInterviewermobno());
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