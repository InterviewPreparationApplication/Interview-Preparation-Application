package com.dmc.interviewpreparationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class InterviewerDashBoard extends AppCompatActivity {


    CardView interviewrequestid,givefeedbackid;

    Toolbar interviewertoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interviewer_dash_board);

        interviewrequestid=findViewById(R.id.interviewrequestid);
        givefeedbackid=findViewById(R.id.givefeedbackid);

        getSupportActionBar().setTitle("Interviewer Dashboard");


        interviewrequestid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), InterviewrequestActivity.class));
            }
        });

        givefeedbackid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Interviewerdashboardfeedback.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.interviewerdashboard,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.interviewerdashboardprofileid)
        {
            startActivity(new Intent(getApplicationContext(), InterviewerProfile.class));
        }
        if(item.getItemId()==R.id.interviewerlogout)
        {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, InterviewerLogin.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}