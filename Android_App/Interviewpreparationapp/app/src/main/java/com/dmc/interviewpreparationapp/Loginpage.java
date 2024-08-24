package com.dmc.interviewpreparationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

import java.util.List;
import java.util.Map;

import HelperClasses.UserHelperClass;

public class Loginpage extends AppCompatActivity {

    TextView registerheretextid;

    EditText loginemail, loginpw;
    String Uid;
    public static UserHelperClass LoggedinUser = new UserHelperClass();
//    Object firstname,lastname,email,address,qualification;

    public static UserHelperClass userHelperClass = new UserHelperClass();
    public static Map<String, UserHelperClass> CurrentUserMapValueTemp;
    Button loginbtnid;
    FirebaseAuth fAuth;

    TextView forgetpasswordid;


    private final DatabaseReference databaseReference_Student = FirebaseDatabase.getInstance().getReference("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        registerheretextid = findViewById(R.id.registerheretextid);
        loginemail = findViewById(R.id.loginemail);
        loginpw = findViewById(R.id.loginpw);
        loginbtnid = findViewById(R.id.loginbtnid);
        forgetpasswordid =findViewById(R.id.forgetpasswordid);
        fAuth = FirebaseAuth.getInstance();

        getSupportActionBar().hide();


        loginbtnid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginemail.getText().toString();
                String pw = loginpw.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Loginpage.this, "email required", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(pw)) {
                    Toast.makeText(Loginpage.this, "password required", Toast.LENGTH_SHORT).show();
                } else {
                    fAuth.signInWithEmailAndPassword(email, pw).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            //      FirebaseUser firebaseUser=fAuth.getCurrentUser();
                            Toast.makeText(Loginpage.this, "Login Successfully", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));

                            FirebaseUser currentUser = fAuth.getCurrentUser();
                            if (currentUser != null) {
                                // User is already authenticated, navigate to home page
                                startmyservice(loginemail.getText().toString());
                                startActivity(new Intent(Loginpage.this, HomeActivity.class));
                                finish(); // Prevent going back to login page when pressing back button
                            } else {
                                // User is not authenticated, navigate to login page
                                startActivity(new Intent(Loginpage.this, Loginpage.class));
                                finish(); // Prevent going back to login page when pressing back button
                            }


                            SharedPreferences sharedpreferences = getSharedPreferences("LoginUser", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString("UserID", email);
                            editor.putString("Password", pw);
                            editor.apply();
                            GetUserdetails();
                            finish();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Loginpage.this, "Email or password wrong", Toast.LENGTH_SHORT).show();
                        }
                    });


                }


            }

        });


        registerheretextid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Registrationpage.class));
            }
        });

        forgetpasswordid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password ?");
                passwordResetDialog.setMessage("Enter Your Email To received Reset Link: ");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String mail = resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Loginpage.this, "Reset Link is Send to your Mail", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Loginpage.this, "Error! Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                passwordResetDialog.create().show();
            }
        });
    }

    public void GetUserdetails() {
        databaseReference_Student.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    String name = user.getDisplayName();
                    Object tempp = snapshot.getChildren();
                    Map<String, List<UserHelperClass>> value = (Map<String, List<UserHelperClass>>) snapshot.getValue();

             //       UserHelperClass uh = snapshot.getValue(UserHelperClass.class);
                    SharedPreferences SP = getApplicationContext().getSharedPreferences("LoginUser", Context.MODE_PRIVATE);

                    for (Map.Entry<String, List<UserHelperClass>> entry : value.entrySet()) {

                        Map<String, UserHelperClass> TempValue = (Map<String, UserHelperClass>) entry.getValue();
                        String currentemail = String.valueOf(TempValue.get("email"));
                        Log.e("sunbeam","currentemail"+ currentemail);
                        String Temp2 = entry.getKey();


                        if (currentemail.toString().equalsIgnoreCase(SP.getString("UserID", ""))) {
                            UserHelperClass TempObj = new UserHelperClass();

                            Object firstname = TempValue.get("firstname");
                            Object lastname = TempValue.get("lastname");
                            Object mobileno = TempValue.get("mobileno");
                            Object address = TempValue.get("address");
                            Object dob = TempValue.get("dob");
                            Object qualification = TempValue.get("qualification");
                            Object email = TempValue.get("email");
                            Object password = TempValue.get("password");
                            Object repassword = TempValue.get("repassword");
                            Object male = TempValue.get("male");
                       //     Object female = TempValue.get("female");
                            TempObj.setFirstname(firstname.toString());
                            TempObj.setLastname(lastname.toString());
                            TempObj.setMobileno(mobileno.toString());
                            TempObj.setAddress(address.toString());
                            TempObj.setDob(dob.toString());
                            TempObj.setQualification(qualification.toString());
                            TempObj.setEmail(email.toString());
                            TempObj.setPassword(password.toString());
                            TempObj.setRepassword(repassword.toString());
                            TempObj.setMale(male.toString());
//                            TempObj.setFemale(female.toString());

                            LoggedinUser = TempObj;
                            CurrentUserMapValueTemp = TempValue;
                            // DriverDetails.setText(pickup_point.toString());
                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Loginpage.this, "inside onCancelled method", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void startmyservice(String userid) {

        Application application =getApplication(); // Android's application context
        long appID = 502626721;   // yourAppID
        String appSign ="d35da2bfea1487ae4b7c173f2f4c92eefe069cfa9ff11a729f6e8b04cea3deb3";  // yourAppSign
        String userID =userid; // yourUserID, userID should only contain numbers, English characters, and '_'.
        String userName =userid;   // yourUserName

        ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();

        ZegoUIKitPrebuiltCallInvitationService.init(getApplication(), appID, appSign, userID, userName,callInvitationConfig);

    }


    public void onDestroy()
    {
        super.onDestroy();
        ZegoUIKitPrebuiltCallInvitationService.unInit();
    }


}