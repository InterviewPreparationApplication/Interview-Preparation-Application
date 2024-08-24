package com.dmc.interviewpreparationapp;

import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import Adapters.InterviewerlistAdapter;
import Adapters.JavaQuestionAdapter;
import Fragments.ScheduleInterviewFragment;
import HelperClasses.Interviewerhelper;
import HelperClasses.Javademoquestionhelper;
import HelperClasses.UserHelperClass;

public class InterviewerListActivity extends AppCompatActivity implements InterviewerlistAdapter.AddRequestListener {


    RecyclerView recyclerView;
    ArrayList<Interviewerhelper> interviewerlist;

    DatabaseReference databaseReference;

    InterviewerlistAdapter interviewerlistAdapter;

    ImageView backarrowbt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interviewer_list);

        recyclerView=findViewById(R.id.recyclerView);
        backarrowbt=findViewById(R.id.backarrowbt);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        interviewerlist=new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Interviwer");

       // getSupportActionBar().setTitle("Interviwers");

        getSupportActionBar().hide();


        FirebaseRecyclerOptions<Interviewerhelper> options
                = new FirebaseRecyclerOptions.Builder<Interviewerhelper>()
                .setQuery(databaseReference, Interviewerhelper.class)
                .build();

        interviewerlistAdapter = new InterviewerlistAdapter(options,this);
        recyclerView.setAdapter(interviewerlistAdapter);

        //InterviewerlistAdapter adapter = new InterviewerlistAdapter(options, this);
        interviewerlistAdapter.setAddRequestListener(new InterviewerlistAdapter.AddRequestListener() {
            @Override
            public void onAddRequestClicked(Interviewerhelper user) {
             //   Toast.makeText(InterviewerListActivity.this, "Add request sent to "+ user.getInterviewerfirstname(), Toast.LENGTH_SHORT).show();
            }

        });



        backarrowbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScheduleInterviewFragment fragment=new ScheduleInterviewFragment();
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container,fragment).commit();
            }
        });


    }



    @Override
    protected void onStart()
    {
        super.onStart();
        interviewerlistAdapter.startListening();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        interviewerlistAdapter.stopListening();
      //  finish();
    }


    @Override
    public void onAddRequestClicked(Interviewerhelper user) {

    }
}