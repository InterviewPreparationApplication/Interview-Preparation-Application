package com.dmc.interviewpreparationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

import HelperClasses.Interviewerhelper;
import HelperClasses.UserHelperClass;

public class InterviewerLogin extends AppCompatActivity {


    CardView interviewerloginid;
    EditText InterviwerEmail,InterviwerPw;



    public static Interviewerhelper interviewerhelper = new Interviewerhelper();
    public static Map<String, Interviewerhelper> CurrentUserMapValueTemp;

    FirebaseAuth fAuth;

    private final DatabaseReference databaseReference_Student= FirebaseDatabase.getInstance().getReference("Interviwer");
    public static Interviewerhelper CurrentInterviewerdetail;
    Button Interviwerloginbt,Interviwerregisternbt;

    TextView InterviwerResetpw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interviewer);
        interviewerloginid = findViewById(R.id.interviewerloginid);
        InterviwerEmail = findViewById(R.id.InterviwerEmail);
        InterviwerPw = findViewById(R.id.InterviwerPw);
        Interviwerloginbt = findViewById(R.id.Interviwerloginbt);
        Interviwerregisternbt =findViewById(R.id.Interviwerregisternbt);
        InterviwerResetpw = findViewById(R.id.InterviwerResetpw);
        fAuth = FirebaseAuth.getInstance();

        getSupportActionBar().hide();

        Interviwerloginbt.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                       public void onClick(View v) {
                                                     String iemail = InterviwerEmail.getText().toString();
                                                     String ipw = InterviwerPw.getText().toString();

                                                     if (TextUtils.isEmpty(iemail)) {
                                                         Toast.makeText(InterviewerLogin.this, "email required", Toast.LENGTH_SHORT).show();
                                                     } else if (TextUtils.isEmpty(ipw)) {
                                                         Toast.makeText(InterviewerLogin.this, "password required", Toast.LENGTH_SHORT).show();
                                                     }
                                                     else {
                                                         fAuth.signInWithEmailAndPassword(iemail, ipw).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                                             @Override
                                                             public void onSuccess(AuthResult authResult) {
                                                                // FirebaseUser firebaseUser=fAuth.getCurrentUser();
                                                                 Toast.makeText(InterviewerLogin.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                                                //startActivity(new Intent(getApplicationContext(),InterviewerDashBoard.class));

                                                                 FirebaseUser currentUser = fAuth.getCurrentUser();
                                                                 if (currentUser != null) {
                                                                     // User is already authenticated, navigate to home page
                                                                     startmyservice(iemail);
                                                                     startActivity(new Intent(InterviewerLogin.this, InterviewerDashBoard.class));
                                                                     finish(); // Prevent going back to login page when pressing back button
                                                                 } else {
                                                                     // User is not authenticated, navigate to login page
                                                                     startActivity(new Intent(InterviewerLogin.this, InterviewerLogin.class));
                                                                     finish(); // Prevent going back to login page when pressing back button
                                                                 }


                                                                 SharedPreferences sharedpreferences = getSharedPreferences("LoginUser", Context.MODE_PRIVATE);
                                                                 SharedPreferences.Editor editor = sharedpreferences.edit();
                                                                 editor.putString("UserID", iemail);
                                                                 editor.putString("Password", ipw);
                                                                 editor.apply();
                                                                 GetUserdetails();
                                                             }
                                                         }).addOnFailureListener(new OnFailureListener() {
                                                             @Override
                                                             public void onFailure(@NonNull Exception e) {
                                                                 Toast.makeText(InterviewerLogin.this, "emailId or password wrong", Toast.LENGTH_SHORT).show();

                                                             }
                                                         });
                                                    }
                                                                                                     }
                                             });


                    Interviwerregisternbt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getApplicationContext(), InterviewerRegistrationpage.class));
                        }
                    });

        InterviwerResetpw.setOnClickListener(new View.OnClickListener() {
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
                                Toast.makeText(InterviewerLogin.this, "Reset Link is Send to your Mail", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(InterviewerLogin.this, "Error! Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
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
//        Log.e("dataref", databaseReference_Student + "");
        databaseReference_Student.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    // Name, email address, and profile photo Url
                    String name = user.getDisplayName();
                    Object tempp = snapshot.getChildren();
                    Map<String, List<Interviewerhelper>> value = (Map<String, List<Interviewerhelper>>) snapshot.getValue();

                    Interviewerhelper uh = snapshot.getValue(Interviewerhelper.class);
                    SharedPreferences SP = getApplicationContext().getSharedPreferences("LoginUser", Context.MODE_PRIVATE);

                    for (Map.Entry<String, List<Interviewerhelper>> entry : value.entrySet()) {

                        Map<String, Interviewerhelper> TempValue = (Map<String, Interviewerhelper>) entry.getValue();
                        Object currentemail = String.valueOf(TempValue.get("intervieweremail"));
                        String Temp2 = entry.getKey();

                        if (currentemail.toString().equalsIgnoreCase(SP.getString("UserID", ""))) {
                            Interviewerhelper TempObj = new Interviewerhelper();
//interviewerfirstname,interviewerlastname,intervieweraddress,interviewerQualification,interviewerworkposition,intervieweremail;
                            Object interviewerfirstname = TempValue.get("interviewerfirstname");
                            Object interviewerlastname = TempValue.get("interviewerlastname");
                            Object intervieweraddress = TempValue.get("intervieweraddress");
                            Object interviewerQualification = TempValue.get("interviewerQualification");
                            Object interviewerworkposition = TempValue.get("interviewerworkposition");
                            Object intervieweremail = TempValue.get("intervieweremail");
                            TempObj.setInterviewerfirstname(interviewerfirstname.toString());
                            TempObj.setInterviewerlastname(interviewerlastname.toString());
                            TempObj.setIntervieweraddress(intervieweraddress.toString());
                            TempObj.setInterviewerQualification(interviewerQualification.toString());
                            TempObj.setInterviewerworkposition(interviewerworkposition.toString());
                            TempObj.setIntervieweremail(intervieweremail.toString());
                            interviewerhelper = TempObj;
                            CurrentUserMapValueTemp = TempValue;

                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                String err = error.toString();
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