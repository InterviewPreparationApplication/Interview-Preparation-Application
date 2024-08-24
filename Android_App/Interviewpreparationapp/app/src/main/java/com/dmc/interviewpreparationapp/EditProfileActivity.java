package com.dmc.interviewpreparationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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

import Fragments.ProfileFragment;
import HelperClasses.UserHelperClass;

public class EditProfileActivity extends AppCompatActivity {

    EditText editfirstname, editlastname, editemail, editaddress, editqualification,editmno;
    Button editbt;


    public static UserHelperClass LoggedinUser = new UserHelperClass();

    Object firstname, lastname, email, address, qualification, mobileno;

    DatabaseReference reference;

    UserHelperClass userHelperClass=new UserHelperClass();
    Object mobno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        reference= FirebaseDatabase.getInstance().getReference("Users");


        editfirstname=findViewById(R.id.editfirstname);
        editlastname=findViewById(R.id.editlastname);
        editemail=findViewById(R.id.editemail);
        editaddress=findViewById(R.id.editaddress);
        editqualification=findViewById(R.id.editqualification);
        editbt=findViewById(R.id.editbt);

        getSupportActionBar().hide();


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    String name = user.getDisplayName();
                    Object tempp = snapshot.getChildren();
                    Map<String, List<UserHelperClass>> value = (Map<String, List<UserHelperClass>>) snapshot.getValue();

                    UserHelperClass uh = snapshot.getValue(UserHelperClass.class);
                    SharedPreferences SP = EditProfileActivity.this.getSharedPreferences("LoginUser", Context.MODE_PRIVATE);

                    for (Map.Entry<String, List<UserHelperClass>> entry : value.entrySet()) {

                        Map<String, UserHelperClass> TempValue = (Map<String, UserHelperClass>) entry.getValue();
                        String currentemail = String.valueOf(TempValue.get("email"));
                        String Temp2 = entry.getKey();


                        if (currentemail.equalsIgnoreCase(SP.getString("UserID", ""))) {
                            UserHelperClass TempObj = new UserHelperClass();

                            firstname = TempValue.get("firstname");
                            lastname = TempValue.get("lastname");
                            email = TempValue.get("email");
                            address = TempValue.get("address");
                            qualification = TempValue.get("qualification");
                            mobno=TempValue.get("mobileno");
                            TempObj.setFirstname(firstname.toString());
                            TempObj.setLastname(lastname.toString());
                            TempObj.setEmail(email.toString());
                            TempObj.setAddress(address.toString());
                            TempObj.setQualification(qualification.toString());
                            TempObj.setEmail(email.toString());

                            LoggedinUser = TempObj;
                            editfirstname.setText( firstname.toString());
                            editlastname.setText( lastname.toString());
                            editemail.setText( email.toString());
                            editaddress.setText(address.toString());
                            editqualification.setText(qualification.toString());



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
               if(isFirstnamechanged() || isLastnamechanged() || isEmailnamechanged() || isAddresschanged() || isQualificationchanged())
                {
                    Toast.makeText(EditProfileActivity.this, "save", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(EditProfileActivity.this, "no change", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isFirstnamechanged()
    {
        Log.e("sunbeam","Firstame" + firstname);
        if(!firstname.equals(editfirstname.getText().toString()))
        {
            reference.child((String) mobno).child("firstname").setValue(editfirstname.getText().toString());
            firstname=editfirstname.getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isLastnamechanged()
    {
        if(!lastname.equals(editlastname.getText().toString()))
        {
            reference.child((String) mobno).child("lastname").setValue(editlastname.getText().toString());
            lastname=editlastname.getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isEmailnamechanged()
    {
        if(!email.equals(editemail.getText().toString()))
        {
            reference.child((String) mobno).child("email").setValue(editemail.getText().toString());
            email=editfirstname.getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isAddresschanged()
    {
        if(!address.equals(editaddress.getText().toString()))
        {
            reference.child((String) mobno).child("address").setValue(editaddress.getText().toString());
            address=editaddress.getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isQualificationchanged()
    {
        if(!qualification.equals(editqualification.getText().toString()))
        {
            reference.child((String) mobno).child("qualification").setValue(editqualification.getText().toString());
            qualification=editqualification.getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    public void showdata()
    {
        Intent intent=getIntent();

        firstname=intent.getStringExtra("firstname");
        lastname=intent.getStringExtra("lastname");
        email=intent.getStringExtra("email");
        address=intent.getStringExtra("address");
        qualification=intent.getStringExtra("qualification");

        editfirstname.setText((CharSequence) firstname);
        editlastname.setText((CharSequence) lastname);
        editemail.setText((CharSequence) email);
        editaddress.setText((CharSequence) address);
        editqualification.setText((CharSequence) qualification);

    }
    }
