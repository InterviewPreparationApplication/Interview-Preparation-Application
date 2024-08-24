package com.dmc.interviewpreparationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Adapters.Candidateinfoadapter;
import Adapters.Interviewerinfoadapter;
import HelperClasses.Interviewerhelper;
import HelperClasses.UserHelperClass;

public class Admininterviewerinfo extends AppCompatActivity {

    TextView ifirstname, ilastname, iemail, iaddress, iqualification,iworkposition;

    RecyclerView interviewerinforecyclerview;
    private Interviewerinfoadapter adapter;
    private List<Interviewerhelper> mData;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admininterviewerinfo);
        getSupportActionBar().setTitle("Interviwer information");

        interviewerinforecyclerview=findViewById(R.id.interviewerinforecyclerview);
        interviewerinforecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mData = new ArrayList<>();
        adapter = new Interviewerinfoadapter(mData,this);
        interviewerinforecyclerview.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Interviwer");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mData.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Interviewerhelper value = snapshot.getValue(Interviewerhelper.class);
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