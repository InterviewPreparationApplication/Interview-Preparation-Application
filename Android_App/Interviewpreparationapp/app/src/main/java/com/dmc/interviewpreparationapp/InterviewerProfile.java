package com.dmc.interviewpreparationapp;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

import HelperClasses.Interviewerhelper;
import HelperClasses.UserHelperClass;

public class InterviewerProfile extends AppCompatActivity {

    TextView ifirstname, ilastname, iemail, iaddress, iqualification,iworkposition;

  //  String interfirstname, interlastname, interemail, interaddress, interqualification,interworkposition;

    private DatabaseReference reference;
    private String userId;
    FirebaseAuth Fauthprofile;
    public static Interviewerhelper LoggedinUser = new Interviewerhelper();

    String interviewerfirstname,interviewerlastname,intervieweraddress,interviewerQualification,interviewerworkposition,intervieweremail;

    Button editbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interviewer_profile);

        ifirstname=findViewById(R.id.ifirstname);
        ilastname=findViewById(R.id.ilastname);
        iemail=findViewById(R.id.iemail);
        iaddress=findViewById(R.id.iaddress);
        iqualification=findViewById(R.id.iqualification);
        iworkposition=findViewById(R.id.iworkposition);
        editbt=findViewById(R.id.editbt);
        getSupportActionBar().setTitle("Profile");

        Fauthprofile=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=Fauthprofile.getCurrentUser();

        if(firebaseUser==null)
        {
            Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_SHORT).show();
        }else {
            showUserProfile(firebaseUser);
        }
        reference = FirebaseDatabase.getInstance().getReference("Interviewer");
    }


    private void showUserProfile(FirebaseUser firebaseUser) {
        String userId=firebaseUser.getUid();
        DatabaseReference reference1=FirebaseDatabase.getInstance().getReference("Interviwer");
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    String name = user.getDisplayName();
                    Object tempp = snapshot.getChildren();
                    Map<String, List<Interviewerhelper>> value = (Map<String, List<Interviewerhelper>>) snapshot.getValue();

                    Interviewerhelper uh = snapshot.getValue(Interviewerhelper.class);
                    SharedPreferences SP = getApplicationContext().getSharedPreferences("LoginUser", Context.MODE_PRIVATE);

                    for (Map.Entry<String, List<Interviewerhelper>> entry : value.entrySet()) {

                        Map<String, Interviewerhelper> TempValue = (Map<String, Interviewerhelper>) entry.getValue();
                        String currentemail = String.valueOf(TempValue.get("intervieweremail"));
                        String Temp2 = entry.getKey();

                        if (currentemail.equalsIgnoreCase(SP.getString("UserID", ""))) {
                            Interviewerhelper TempObj = new Interviewerhelper();

                            Object interviewerfirstname = TempValue.get("interviewerfirstname");
                            Object interviewerlastname = TempValue.get("interviewerlastname");
                            Object intervieweraddress = TempValue.get("intervieweraddress");
                            Object interviewerdob = TempValue.get("interviewerdob");
                            Object interviewerage = TempValue.get("interviewerage");
                            Object interviewermobno = TempValue.get("interviewermobno");
                            Object interviewerQualification = TempValue.get("interviewerQualification");
                            Object interviewerworkposition = TempValue.get("interviewerworkposition");
                            Object intervieweremail=TempValue.get("intervieweremail");
                            Object interPw=TempValue.get("interPw");
                            TempObj.setInterviewerfirstname(interviewerfirstname.toString());
                            TempObj.setInterviewerlastname(interviewerlastname.toString());
                            TempObj.setIntervieweraddress(intervieweraddress.toString());
                            TempObj.setInterviewerdob(interviewerdob.toString());
                            TempObj.setInterviewerage(interviewerage.toString());
                            TempObj.setInterviewermobno(interviewermobno.toString());
                            TempObj.setInterviewerQualification(interviewerQualification.toString());
                            TempObj.setInterviewerworkposition(interviewerworkposition.toString());
                            TempObj.setIntervieweremail(intervieweremail.toString());
                            TempObj.setInterPw(interPw.toString());

                            LoggedinUser = TempObj;
                            ifirstname.setText("First Name: " + interviewerfirstname.toString());
                            ilastname.setText("Last Name: " +interviewerlastname.toString());
                            iemail.setText("email: " +intervieweremail.toString());
                            iaddress.setText("Address: " +intervieweraddress.toString());
                            iqualification.setText("Qualification: " +interviewerQualification.toString());
                            iworkposition.setText("Workposition: " +interviewerworkposition.toString());

                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        editbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit();
            }
        });

    }
    public void edit ()
    {
        Intent intent = new Intent(this, InterviwerEditprofileActivity.class);
        intent.putExtra("firstname", interviewerfirstname);
        intent.putExtra("lastname", interviewerlastname);
        intent.putExtra("email", intervieweremail);
        intent.putExtra("address", intervieweraddress);
        intent.putExtra("qualification", interviewerQualification);
        intent.putExtra("workposition",interviewerworkposition);
        startActivity(intent);

    }
}