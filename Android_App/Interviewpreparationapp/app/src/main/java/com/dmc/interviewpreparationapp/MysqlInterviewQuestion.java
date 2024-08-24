package com.dmc.interviewpreparationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import Adapters.JavaQuestionAdapter;
import HelperClasses.Javademoquestionhelper;


public class MysqlInterviewQuestion extends AppCompatActivity {
    RecyclerView recyclerViewmysql;

    DatabaseReference databaseReference;

    JavaQuestionAdapter javaQuestionAdapter;

    ArrayList<Javademoquestionhelper> javaqalist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysql_interview_question);

        recyclerViewmysql = findViewById(R.id.recyclerViewmysql);
        databaseReference = FirebaseDatabase.getInstance().getReference("DemoInterviewQuestion").child("Mysql");
        //recyclerView.setHasFixedSize(true);
        recyclerViewmysql.setLayoutManager(new LinearLayoutManager(this));
        javaqalist=new ArrayList<>();

        getSupportActionBar().setTitle("Mysql Question");

        FirebaseRecyclerOptions<Javademoquestionhelper> options
                = new FirebaseRecyclerOptions.Builder<Javademoquestionhelper>()
                .setQuery(databaseReference, Javademoquestionhelper.class)
                .build();
        javaQuestionAdapter = new JavaQuestionAdapter(options);
        recyclerViewmysql.setAdapter(javaQuestionAdapter);
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