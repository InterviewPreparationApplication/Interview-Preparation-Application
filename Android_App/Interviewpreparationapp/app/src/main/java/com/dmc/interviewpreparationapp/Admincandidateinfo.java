package com.dmc.interviewpreparationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Adapters.Candidateinfoadapter;
import HelperClasses.Interviewerrecivedreqhelper;
import HelperClasses.UserHelperClass;

public class Admincandidateinfo extends AppCompatActivity {



    RecyclerView candidateinfoadmin;
    private Candidateinfoadapter adapter;
    private List<UserHelperClass> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admincandidateinfo);

        getSupportActionBar().setTitle("Candidate Info");

        candidateinfoadmin=findViewById(R.id.candidateinfoadmin);
        candidateinfoadmin.setLayoutManager(new LinearLayoutManager(this));
        mData = new ArrayList<>();
        adapter = new Candidateinfoadapter(mData,this);
        candidateinfoadmin.setAdapter(adapter);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mData.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UserHelperClass value = snapshot.getValue(UserHelperClass.class);
                    if (value != null) {
                        mData.add(value);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }
}