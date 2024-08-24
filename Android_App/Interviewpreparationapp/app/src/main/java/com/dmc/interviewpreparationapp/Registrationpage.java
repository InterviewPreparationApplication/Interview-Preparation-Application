package com.dmc.interviewpreparationapp;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import HelperClasses.UserHelperClass;

public class Registrationpage extends AppCompatActivity {

    TextView alreadyaccounttxt;
    EditText firstname, lastname, mobileno, address, dob, qualification, email, password, repassword;
    RadioButton male, female;

    Button regbtn;

    FirebaseAuth firebaseAuth;

    FirebaseUser user;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private int minute;

    private int year, month, day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationpage);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        mobileno = findViewById(R.id.mobileno);
        address = findViewById(R.id.address);
        dob = findViewById(R.id.dob);
        qualification = findViewById(R.id.qualification);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        alreadyaccounttxt = findViewById(R.id.alreadyaccounttxt);
        regbtn = findViewById(R.id.regbtn);


        getSupportActionBar().hide();

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get current date
                final Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                // Create DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(Registrationpage.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Registrationpage.this.year = year;
                                month = monthOfYear;
                                day = dayOfMonth;

                                // Update EditText with selected date
                                String date = String.format("%02d-%02d-%04d", dayOfMonth, monthOfYear + 1, year);
                                dob.setText(date);
                            }
                        }, year, month, day);

                // Show DatePickerDialog
                datePickerDialog.show();
            }
        });


        alreadyaccounttxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registrationpage.this, Loginpage.class));
            }
        });
        firebaseAuth = FirebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();
//        if (firebaseAuth.getCurrentUser() != null) {
//            Toast.makeText(Registrationpage.this, "User already exist", Toast.LENGTH_SHORT).show();
//        }
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Users");
                String fname = firstname.getText().toString().trim();
                String lname = lastname.getText().toString().trim();
                String mno = mobileno.getText().toString().trim();
                String addr = address.getText().toString().trim();
                String dateob = dob.getText().toString().trim();
                String qualific = qualification.getText().toString().trim();
                String emailid = email.getText().toString().trim();
                String pw = password.getText().toString().trim();
                String repw = repassword.getText().toString().trim();
                String ml = male.getText().toString();
                String fml = female.getText().toString();

                if (TextUtils.isEmpty(fname)) {
                    //Toast.makeText(Registrationpage.this, "first name required", Toast.LENGTH_SHORT).show();
                    firstname.setError("first name required");
                    return;
                }
                if (TextUtils.isEmpty(lname)) {
                    //Toast.makeText(Registrationpage.this, "last name required", Toast.LENGTH_SHORT).show();
                    lastname.setError("last name required");
                    return;
                }
                if (TextUtils.isEmpty(mno)) {
                    // Toast.makeText(Registrationpage.this, " mobile number required", Toast.LENGTH_SHORT).show();
                    mobileno.setError(" mobile number required");
                    return;
                }
                if (TextUtils.isEmpty(addr)) {
                    // Toast.makeText(Registrationpage.this, " address required", Toast.LENGTH_SHORT).show();
                    address.setError(" address required");
                    return;
                }
                if (TextUtils.isEmpty(dateob)) {
                    //Toast.makeText(Registrationpage.this, "date of birth required", Toast.LENGTH_SHORT).show();
                    dob.setError("date of birth required");
                    return;
                }
                if (TextUtils.isEmpty(qualific)) {
                    //  Toast.makeText(Registrationpage.this, " qualification required", Toast.LENGTH_SHORT).show();
                    qualification.setError("qualification required");
                    return;
                }
                if (TextUtils.isEmpty(emailid)) {
                    //Toast.makeText(Registrationpage.this, "emailid required", Toast.LENGTH_SHORT).show();
                    email.setError("emailid required");
                    return;
                }
                if (TextUtils.isEmpty(pw)) {
                    //Toast.makeText(Registrationpage.this, "password required", Toast.LENGTH_SHORT).show();
                    password.setError("password required");
                    return;
                }
                if (TextUtils.isEmpty(repw)) {
                    //Toast.makeText(Registrationpage.this, "repassword required", Toast.LENGTH_SHORT).show();
                    repassword.setError("repassword required");
                    return;
                }
                else {
                    UserHelperClass userHelperClass = new UserHelperClass(fname, lname, mno, addr, dateob, qualific, emailid, pw, repw,"","");

                    if (male.isChecked()) {
                        userHelperClass.setMale(ml);
                    } else {
                        userHelperClass.setFemale(fml);
                    }
                    reference.child(mno).setValue(userHelperClass);

                    Toast.makeText(Registrationpage.this, "User Register successfully", Toast.LENGTH_SHORT).show();
                }

                firebaseAuth.createUserWithEmailAndPassword(emailid, pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(Registrationpage.this, "Register Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Loginpage.class));

                        }
                            else {
                                Toast.makeText(Registrationpage.this, "Register faild", Toast.LENGTH_SHORT).show();
                            }


                    }
                });

            }
        });
    }
}