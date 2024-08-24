package com.dmc.interviewpreparationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Map;

import HelperClasses.Feedbackhelper;
import HelperClasses.Interviewerhelper;
import HelperClasses.UserHelperClass;

public class Interviewerdashboardfeedback extends AppCompatActivity {

    Button feedbacksubmitbt;

    EditText feedbackcandidateid,feedbacktext;

    DatabaseReference databaseReference;

    Object firstname;
    public static Interviewerhelper LoggedinUser = new Interviewerhelper();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interviewerdashboardfeedback);

        feedbacksubmitbt = findViewById(R.id.feedbacksubmitbt);
        feedbackcandidateid = findViewById(R.id.feedbackcandidateid);
        feedbacktext = findViewById(R.id.feedbacktext);

        //        databaseReference=FirebaseDatabase.getInstance().getReference("Interviwer");
        databaseReference = FirebaseDatabase.getInstance().getReference("Feedback");

        getSupportActionBar().setTitle("Feedback");

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        FirebaseDatabase database = FirebaseDatabase.getInstance();


/*
        feedbacksubmitbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentUser != null) {
                    // Get the currently logged-in user's ID and nam
                    // String currentUserName = currentUser.getDisplayName();
                    Interviewerhelper interviewerhelper=new Interviewerhelper();
                    String firstname=interviewerhelper.getInterviewerfirstname();
                    String candid = feedbackcandidateid.getText().toString();
                    String feedb = feedbacktext.getText().toString();

                    Feedbackhelper feedbackhelper = new Feedbackhelper(firstname, feedb);
                    databaseReference.child(candid).setValue(feedbackhelper);

                }

            }
        });
       */




        DatabaseReference reference1=FirebaseDatabase.getInstance().getReference("Interviwer");
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    String name = user.getDisplayName();
                    Object tempp = snapshot.getChildren();
                    Map<String, List<Interviewerhelper>> value = (Map<String, List<Interviewerhelper>>) snapshot.getValue();

//                    UserHelperClass uh = snapshot.getValue(UserHelperClass.class);
                    SharedPreferences SP = getApplicationContext().getSharedPreferences("LoginUser", Context.MODE_PRIVATE);

                    for (Map.Entry<String, List<Interviewerhelper>> entry : value.entrySet()) {

                        Map<String, Interviewerhelper> TempValue = (Map<String, Interviewerhelper>) entry.getValue();
                        String currentemail = String.valueOf(TempValue.get("intervieweremail"));
                        String Temp2 = entry.getKey();


                        if (currentemail.toString().equalsIgnoreCase(SP.getString("UserID", ""))) {
                            Interviewerhelper TempObj = new Interviewerhelper();

                            firstname = TempValue.get("interviewerfirstname");
                            Log.e("sunbeam01","firstname"+firstname);
                            TempObj.setInterviewerfirstname(firstname.toString());

                            LoggedinUser = TempObj;

                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        feedbacksubmitbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentUser != null) {
                    // Get the currently logged-in user's ID and nam
                    // String currentUserName = currentUser.getDisplayName();
                    Interviewerhelper interviewerhelper=new Interviewerhelper();
                    String candid = feedbackcandidateid.getText().toString();
                    String feedb = feedbacktext.getText().toString();

                    Feedbackhelper feedbackhelper = new Feedbackhelper(firstname, feedb);
                    databaseReference.child(candid).setValue(feedbackhelper);

                    feedbackcandidateid.setText("");
                    feedbacktext.setText("");
                }

            }
        });
    }
}