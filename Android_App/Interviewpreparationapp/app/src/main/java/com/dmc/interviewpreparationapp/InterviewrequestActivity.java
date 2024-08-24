package com.dmc.interviewpreparationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Adapters.Feedbackadapteruser;
import Adapters.InterviewerlistAdapter;
import Adapters.Interviewrequestadapter;
import HelperClasses.Feedbackhelper;
import HelperClasses.Interviewerrecivedreqhelper;
import HelperClasses.Interviewrequesthelper;
import HelperClasses.UserHelperClass;

public class InterviewrequestActivity extends AppCompatActivity{

    RecyclerView interviewreqid;

    String table1Key;
    DataSnapshot table2Snapshot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interviewrequest);
        interviewreqid=findViewById(R.id.interviewreqid);

        getSupportActionBar().setTitle("Request");



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table1Ref = database.getReference("Interviwer");
        DatabaseReference table2Ref = database.getReference("Interviewrequest");


        List<Interviewerrecivedreqhelper> dataList = new ArrayList<>();


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
                            //    Toast.makeText(getApplicationContext(), "inside if", Toast.LENGTH_SHORT).show();
                                String  tab2name=table2Snapshot.child("firstname").getValue(String.class);
                                dataList.add(new Interviewerrecivedreqhelper(tab2name));
                                Log.e("sunbeam001","data:"+dataList);
                                break;

                            }

                        }
                        /*if(dataList.isEmpty() || !table2Snapshot.exists())
                        {
                          //  Toast.makeText(getApplicationContext(), "inside else", Toast.LENGTH_SHORT).show();
                            String  tab2name=table2Snapshot.child("interviewername").getValue(String.class);
                            String  tab2feedback=table2Snapshot.child("feedback").getValue(String.class);
                            dataList.add(new Interviewerrecivedreqhelper(""));
                        }*/


                        Interviewrequestadapter adapter = new Interviewrequestadapter(dataList,getApplicationContext());
                        Log.e("sunbeam001","adapter:"+adapter);
                        interviewreqid.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        interviewreqid.setAdapter(adapter);
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


    }



}