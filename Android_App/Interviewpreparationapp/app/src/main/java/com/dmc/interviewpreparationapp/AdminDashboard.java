package com.dmc.interviewpreparationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import java.util.zip.Inflater;

public class AdminDashboard extends AppCompatActivity {



    CardView candidateinfoid,interviwerinfoid,reportsid,addinterviwerid,deletecandidateid,deleteinterviwerid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        getSupportActionBar().setTitle("Admin Panel");

        candidateinfoid=findViewById(R.id.candidateinfoid);
        interviwerinfoid=findViewById(R.id.interviwerinfoid);
      //  reportsid=findViewById(R.id.reportsid);
        addinterviwerid=findViewById(R.id.addinterviwerid);
        deletecandidateid=findViewById(R.id.deletecandidateid);
        deleteinterviwerid=findViewById(R.id.deleteinterviwerid);


        candidateinfoid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Admincandidateinfo.class));
            }
        });

        interviwerinfoid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Admininterviewerinfo.class));
            }
        });

      /*  reportsid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Adminreports.class));
            }
        });*/

        addinterviwerid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Adminaddinterviewer.class));
            }
        });

        deletecandidateid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Admindeletecandidate.class));
            }
        });

        deleteinterviwerid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Admindeleteinterviwer.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.adminmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.adminlogout)
        {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, Adminlogin.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}