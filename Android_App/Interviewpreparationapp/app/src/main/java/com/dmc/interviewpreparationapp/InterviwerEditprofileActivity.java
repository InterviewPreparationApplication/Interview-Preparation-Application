package com.dmc.interviewpreparationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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

import HelperClasses.Interviewerhelper;
import HelperClasses.UserHelperClass;

public class InterviwerEditprofileActivity extends AppCompatActivity {

    EditText editfirstname, editlastname, editemail, editaddress, editqualification,editmno,editworkposition;
    Button editbt;


    public static Interviewerhelper LoggedinUser = new Interviewerhelper();

    Object interviewerfirstname, interviewerlastname, intervieweremail, intervieweraddress, interviewerQualification,interviewerworkposition;

    DatabaseReference reference;

    Interviewerhelper interviewerhelper=new Interviewerhelper();
    Object interviewermobno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interviwer_editprofile);

        reference= FirebaseDatabase.getInstance().getReference("Interviwer");


        editfirstname=findViewById(R.id.editfirstname);
        editlastname=findViewById(R.id.editlastname);
        editemail=findViewById(R.id.editemail);
        editaddress=findViewById(R.id.editaddress);
        editqualification=findViewById(R.id.editqualification);
        editworkposition=findViewById(R.id.editworkposition);
        editbt=findViewById(R.id.editbt);

        getSupportActionBar().hide();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    String name = user.getDisplayName();
                    Object tempp = snapshot.getChildren();
                    Map<String, List<Interviewerhelper>> value = (Map<String, List<Interviewerhelper>>) snapshot.getValue();

                    UserHelperClass uh = snapshot.getValue(UserHelperClass.class);
                    SharedPreferences SP = InterviwerEditprofileActivity.this.getSharedPreferences("LoginUser", Context.MODE_PRIVATE);

                    for (Map.Entry<String, List<Interviewerhelper>> entry : value.entrySet()) {

                        Map<String, Interviewerhelper> TempValue = (Map<String, Interviewerhelper>) entry.getValue();
                        String currentemail = String.valueOf(TempValue.get("intervieweremail"));
                        String Temp2 = entry.getKey();


                        if (currentemail.equalsIgnoreCase(SP.getString("UserID", ""))) {
                            Interviewerhelper TempObj = new Interviewerhelper();

                            interviewerfirstname = TempValue.get("interviewerfirstname");
                            interviewerlastname = TempValue.get("interviewerlastname");
                            intervieweremail = TempValue.get("intervieweremail");
                            intervieweraddress = TempValue.get("intervieweraddress");
                            interviewerQualification = TempValue.get("interviewerQualification");
                            interviewerworkposition=TempValue.get("interviewerworkposition");

                            interviewermobno=TempValue.get("interviewermobno");

                            TempObj.setInterviewerfirstname(interviewerfirstname.toString());
                            TempObj.setInterviewerlastname(interviewerlastname.toString());
                            TempObj.setIntervieweremail(intervieweremail.toString());
                            TempObj.setIntervieweraddress(intervieweraddress.toString());
                            TempObj.setInterviewerQualification(interviewerQualification.toString());
                            TempObj.setIntervieweremail(interviewerworkposition.toString());

                            LoggedinUser = TempObj;
                            editfirstname.setText( interviewerfirstname.toString());
                            editlastname.setText( interviewerlastname.toString());
                            editemail.setText( intervieweremail.toString());
                            editaddress.setText(intervieweraddress.toString());
                            editqualification.setText(interviewerQualification.toString());
                            editworkposition.setText(interviewerworkposition.toString());



                        }

                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        showdata();

        editbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFirstnamechanged() || isLastnamechanged() || isEmailnamechanged() || isAddresschanged() || isQualificationchanged()||isWorkpositionchanged())
                {
                    Toast.makeText(InterviwerEditprofileActivity.this, "save", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(InterviwerEditprofileActivity.this, "no change", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isFirstnamechanged()
    {

        if(!interviewerfirstname.equals(editfirstname.getText().toString()))
        {
            reference.child((String) interviewermobno).child("interviewerfirstname").setValue(editfirstname.getText().toString());
            interviewerfirstname=editfirstname.getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isLastnamechanged()
    {
        if(!interviewerlastname.equals(editlastname.getText().toString()))
        {
            reference.child((String) interviewermobno).child("interviewerlastname").setValue(editlastname.getText().toString());
            interviewerlastname=editlastname.getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isEmailnamechanged()
    {
        if(!intervieweremail.equals(editemail.getText().toString()))
        {
            reference.child((String) interviewermobno).child("intervieweremail").setValue(editemail.getText().toString());
            intervieweremail=editfirstname.getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isAddresschanged()
    {
        if(!intervieweraddress.equals(editaddress.getText().toString()))
        {
            reference.child((String) interviewermobno).child("intervieweraddress").setValue(editaddress.getText().toString());
            intervieweraddress=editaddress.getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isQualificationchanged()
    {
        if(!interviewerQualification.equals(editqualification.getText().toString()))
        {
            reference.child((String) interviewermobno).child("interviewerQualification").setValue(editqualification.getText().toString());
            interviewerQualification=editqualification.getText().toString();
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isWorkpositionchanged()
    {
        if(!interviewerworkposition.equals(editworkposition.getText().toString()))
        {
            reference.child((String) interviewermobno).child("interviewerworkposition").setValue(editworkposition.getText().toString());
            interviewerworkposition=editworkposition.getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    public void showdata()
    {
        Intent intent=getIntent();
       interviewerfirstname=intent.getStringExtra("interviewerfirstname");
        interviewerlastname=intent.getStringExtra("interviewerlastname");
        intervieweremail=intent.getStringExtra("intervieweremail");
        intervieweraddress=intent.getStringExtra("intervieweraddress");
        interviewerQualification=intent.getStringExtra("interviewerQualification");
        interviewerworkposition=intent.getStringExtra("interviewerworkposition");

        editfirstname.setText((CharSequence) interviewerfirstname);
        editlastname.setText((CharSequence) interviewerlastname);
        editemail.setText((CharSequence) intervieweremail);
        editaddress.setText((CharSequence) intervieweraddress);
        editqualification.setText((CharSequence) interviewerQualification);
        editworkposition.setText((CharSequence) interviewerworkposition);

    }

}