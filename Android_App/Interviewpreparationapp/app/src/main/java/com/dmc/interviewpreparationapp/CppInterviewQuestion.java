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

public class CppInterviewQuestion extends AppCompatActivity {

    RecyclerView recyclerViewcpp;

    DatabaseReference databaseReference;

    JavaQuestionAdapter javaQuestionAdapter;

    ArrayList<Javademoquestionhelper> javaqalist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpp_interview_question);

        recyclerViewcpp = findViewById(R.id.recyclerViewcpp);
        databaseReference = FirebaseDatabase.getInstance().getReference("DemoInterviewQuestion").child("Cpp");
        //recyclerView.setHasFixedSize(true);
        recyclerViewcpp.setLayoutManager(new LinearLayoutManager(this));
        javaqalist=new ArrayList<>();

        getSupportActionBar().setTitle("Cpp Question");

        FirebaseRecyclerOptions<Javademoquestionhelper> options
                = new FirebaseRecyclerOptions.Builder<Javademoquestionhelper>()
                .setQuery(databaseReference, Javademoquestionhelper.class)
                .build();
        javaQuestionAdapter = new JavaQuestionAdapter(options);
        recyclerViewcpp.setAdapter(javaQuestionAdapter);
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
