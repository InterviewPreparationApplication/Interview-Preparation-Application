package com.dmc.interviewpreparationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import HelperClasses.Interviewerhelper;

public class Adminaddinterviewer extends AppCompatActivity {



    EditText interviewerfirstname,interviewerlastname,intervieweraddress,interviewerdob,interviewerage,interviewermobno,intervieweremail,interPw,interviewerQualification,interviewerworkposition;

    TextView intervieweralreadyaccount;

    Button intervieweraddbt;

    FirebaseAuth ifAuth;

    FirebaseUser iuser;
    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminaddinterviewer);

        getSupportActionBar().setTitle("Add Interviewer");

        interviewerfirstname=findViewById(R.id.interviewerfirstname);
        interviewerlastname=findViewById(R.id.interviewerlastname);
        intervieweraddress=findViewById(R.id.intervieweraddress);
        interviewerdob=findViewById(R.id.interviewerdob);
        interviewerage=findViewById(R.id.interviewerage);
        interviewermobno=findViewById(R.id.interviewermobno);
        intervieweremail=findViewById(R.id.intervieweremail);
        interPw=findViewById(R.id.interPw);
        interviewerQualification=findViewById(R.id.interviewerQualification);
        interviewerworkposition=findViewById(R.id.interviewerworkposition);
        intervieweraddbt=findViewById(R.id.intervieweraddbt);
        ifAuth = FirebaseAuth.getInstance();
        iuser=ifAuth.getCurrentUser();


        intervieweraddbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Interviwer");
                String ifname = interviewerfirstname.getText().toString().trim();
                String ilname = interviewerlastname.getText().toString().trim();
                String iaddr = intervieweraddress.getText().toString().trim();
                String idob = interviewerdob.getText().toString().trim();
                String iage = interviewerage.getText().toString().trim();
                String imobno = interviewermobno.getText().toString().trim();
                String iemailid = intervieweremail.getText().toString().trim();
                String ipw = interPw.getText().toString().trim();
                String iqulification = interviewerQualification.getText().toString().trim();
                String iworkposition = interviewerworkposition.getText().toString();
                //String fml = interviewerRegisterbt.getText().toString();


                if (TextUtils.isEmpty(ifname)) {
                    //Toast.makeText(Registrationpage.this, "first name required", Toast.LENGTH_SHORT).show();
                    interviewerfirstname.setError("first name required");
                    return;
                }
                if (TextUtils.isEmpty(ilname)) {
                    //Toast.makeText(Registrationpage.this, "last name required", Toast.LENGTH_SHORT).show();
                    interviewerlastname.setError("last name required");
                    return;
                }
                if (TextUtils.isEmpty(iaddr)) {
                    // Toast.makeText(Registrationpage.this, " mobile number required", Toast.LENGTH_SHORT).show();
                    intervieweraddress.setError(" address is required");
                    return;
                }
                if (TextUtils.isEmpty(idob)) {
                    // Toast.makeText(Registrationpage.this, " address required", Toast.LENGTH_SHORT).show();
                    interviewerdob.setError(" dob required");
                    return;
                }
                if (TextUtils.isEmpty(iage)) {
                    //Toast.makeText(Registrationpage.this, "date of birth required", Toast.LENGTH_SHORT).show();
                    interviewerage.setError("age required");
                    return;
                }
                if (TextUtils.isEmpty(imobno)) {
                    //  Toast.makeText(Registrationpage.this, " qualification required", Toast.LENGTH_SHORT).show();
                    interviewermobno.setError("mobile no is required");
                    return;
                }
                if (TextUtils.isEmpty(iemailid)) {
                    //Toast.makeText(Registrationpage.this, "emailid required", Toast.LENGTH_SHORT).show();
                    intervieweremail.setError("emailid required");
                    return;
                }
                if (TextUtils.isEmpty(ipw)) {
                    //Toast.makeText(Registrationpage.this, "password required", Toast.LENGTH_SHORT).show();
                    interPw.setError("password required");
                    return;
                }
                if (TextUtils.isEmpty(iqulification)) {
                    //Toast.makeText(Registrationpage.this, "repassword required", Toast.LENGTH_SHORT).show();
                    interviewerQualification.setError("qualification required");
                    return;
                }
                if(TextUtils.isEmpty(iworkposition))
                {
                    interviewerworkposition.setError("work position is required");
                    return;
                }
                else {
                    Interviewerhelper interviewerhelper = new Interviewerhelper(ifname, ilname, iaddr, idob, iage, imobno, iqulification, iworkposition, iemailid, ipw);

                    reference.child(imobno).setValue(interviewerhelper);
/*
                    Toast.makeText(Adminaddinterviewer.this, "User Register successfully", Toast.LENGTH_SHORT).show();*/
                }
                ifAuth.createUserWithEmailAndPassword(iemailid,ipw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            Toast.makeText(Adminaddinterviewer.this, "Interviwer Added Succesfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else
                        {
                            Log.e("ch",task +"");
                            Toast.makeText(Adminaddinterviewer.this, "Faild authentication", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });
    }
}