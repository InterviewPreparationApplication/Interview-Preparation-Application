package com.dmc.interviewpreparationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

//import Adapters.Feedbackadapteruser;
import Adapters.Feedbackadapteruser;
import Adapters.JavaQuestionAdapter;
import HelperClasses.Feedbackhelper;
import HelperClasses.Feedbacktabledata;
import HelperClasses.Interviewerhelper;
import HelperClasses.Javademoquestionhelper;
import HelperClasses.UserHelperClass;

public class FeedbacklistActivityUser extends AppCompatActivity {

    RecyclerView feedbackrecyclerview;
//    List<Feedbacktabledata> dataList=new ArrayList<>(); // Populate this list with the fetched data
    String table1Key;
    DataSnapshot table2Snapshot;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbacklist_user);
        feedbackrecyclerview = findViewById(R.id.feedbackrecyclerview);
        getSupportActionBar().setTitle("Feedbacks");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table1Ref = database.getReference("Users");
        DatabaseReference table2Ref = database.getReference("Feedback");


        List<Feedbackhelper> dataList = new ArrayList<>();
        FeedbacklistActivityUser activity = this;

       table1Ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {
                Log.e("sunbeam01", "snapshot1" + dataSnapshot1);
                table2Ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot2) {
                        for (DataSnapshot table1Snapshot : dataSnapshot1.getChildren()) {
                             table1Key = table1Snapshot.getKey();
                             table2Snapshot = dataSnapshot2.child(table1Key);
                            if (table2Snapshot.exists()) {
                            //    Toast.makeText(activity, "inside if", Toast.LENGTH_SHORT).show();
                                String  tab2name=table2Snapshot.child("interviewername").getValue(String.class);
                                String  tab2feedback=table2Snapshot.child("feedback").getValue(String.class);
                                dataList.add(new Feedbackhelper(tab2name, tab2feedback));
                                Log.e("sunbeam001","data:"+dataList);
                                break;

                            }
                        }
                        if(dataList.isEmpty() || !table2Snapshot.exists())
                        {
                            //Toast.makeText(activity, "inside else", Toast.LENGTH_SHORT).show();
                            String  tab2name=table2Snapshot.child("interviewername").getValue(String.class);
                            String  tab2feedback=table2Snapshot.child("feedback").getValue(String.class);
                            dataList.add(new Feedbackhelper("", ""));
                        }


                        Feedbackadapteruser adapter = new Feedbackadapteruser(dataList,activity);
                        Log.e("sunbeam001","adapter:"+adapter);
                        feedbackrecyclerview.setLayoutManager(new LinearLayoutManager(activity));
                        feedbackrecyclerview.setAdapter(adapter);
                       // adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle errors
                    }
                });


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
            }
        });

        /*feedbackrecyclerview.setLayoutManager(new GridLayoutManager(this,1));
        List<Feedbackhelper> dataList = new ArrayList<>();
        Feedbackadapteruser adapter = new Feedbackadapteruser(dataList,this);
        feedbackrecyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();*/


    }

}