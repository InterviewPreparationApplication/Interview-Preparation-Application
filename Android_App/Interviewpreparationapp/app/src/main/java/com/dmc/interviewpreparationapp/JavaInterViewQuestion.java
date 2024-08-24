package com.dmc.interviewpreparationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Adapters.JavaQuestionAdapter;
import HelperClasses.Javademoquestionhelper;
import HelperClasses.UserHelperClass;

public class JavaInterViewQuestion extends AppCompatActivity {

    RecyclerView recyclerView;

    DatabaseReference databaseReference;

    JavaQuestionAdapter javaQuestionAdapter;

    ArrayList<Javademoquestionhelper> javaqalist;

    Object question,answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_inter_view_question);
        recyclerView = findViewById(R.id.recyclerView);
        databaseReference = FirebaseDatabase.getInstance().getReference("DemoInterviewQuestion").child("Java");
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
         javaqalist=new ArrayList<>();

        getSupportActionBar().setTitle("Java Question");

        FirebaseRecyclerOptions<Javademoquestionhelper> options
                = new FirebaseRecyclerOptions.Builder<Javademoquestionhelper>()
                .setQuery(databaseReference, Javademoquestionhelper.class)
                .build();
        javaQuestionAdapter = new JavaQuestionAdapter(options);
        recyclerView.setAdapter(javaQuestionAdapter);
        Log.e("datashow",options + "");

    }


    @Override
    protected void onStart()
    {
        super.onStart();
        javaQuestionAdapter.startListening();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        javaQuestionAdapter.stopListening();
    }

}