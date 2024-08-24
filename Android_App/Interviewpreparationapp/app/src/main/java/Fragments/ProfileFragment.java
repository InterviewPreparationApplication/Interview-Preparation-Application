package Fragments;

import static android.icu.lang.UCharacter.SentenceBreak.SP;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputBinding;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dmc.interviewpreparationapp.EditProfileActivity;
import com.dmc.interviewpreparationapp.Loginpage;
import com.dmc.interviewpreparationapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import HelperClasses.UserHelperClass;

public class ProfileFragment extends Fragment {
    Activity context;

    InputBinding binding;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userId;
    public static UserHelperClass LoggedinUser = new UserHelperClass();

    String firstname, lastname, email, address, qualification, dob, password, repassword;

    TextView fname, lname, uemail, uaddress, uqalification;

    FirebaseAuth Fauthprofile;
    Button editbt,logoutbt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        fname = view.findViewById(R.id.firstname);
        lname = view.findViewById(R.id.lastname);
        uemail = view.findViewById(R.id.email);
        uaddress = view.findViewById(R.id.address);
        uqalification = view.findViewById(R.id.qualification);
        editbt = view.findViewById(R.id.editbt);
        logoutbt=view.findViewById(R.id.logoutbt);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        Fauthprofile=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=Fauthprofile.getCurrentUser();

        if(firebaseUser==null)
        {
            Toast.makeText(context, "something went wrong", Toast.LENGTH_SHORT).show();
        }else {
            showUserProfile(firebaseUser);
        }

        editbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edit();
            }
        });

        logoutbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(context, Loginpage.class));
                context.finish();
            }
        });

       return view;
    }

    private void showUserProfile(FirebaseUser firebaseUser) {

        String userId=firebaseUser.getUid();
        DatabaseReference reference1=FirebaseDatabase.getInstance().getReference("Users");
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    String name = user.getDisplayName();
                    Object tempp = snapshot.getChildren();
                    Map<String, List<UserHelperClass>> value = (Map<String, List<UserHelperClass>>) snapshot.getValue();

//                    UserHelperClass uh = snapshot.getValue(UserHelperClass.class);
                    SharedPreferences SP = context.getSharedPreferences("LoginUser", Context.MODE_PRIVATE);

                    for (Map.Entry<String, List<UserHelperClass>> entry : value.entrySet()) {

                        Map<String, UserHelperClass> TempValue = (Map<String, UserHelperClass>) entry.getValue();
                        String currentemail = String.valueOf(TempValue.get("email"));
                        String Temp2 = entry.getKey();


                        if (currentemail.toString().equalsIgnoreCase(SP.getString("UserID", ""))) {
                            UserHelperClass TempObj = new UserHelperClass();

                            Object firstname = TempValue.get("firstname");
                            Object lastname = TempValue.get("lastname");
                            Object email = TempValue.get("email");
                            Object address = TempValue.get("address");
                            Object qualification = TempValue.get("qualification");
                            Object dob = TempValue.get("dob");
                            Object password = TempValue.get("password");
                            Object repassword = TempValue.get("repassword");
                            TempObj.setFirstname(firstname.toString());
                            TempObj.setLastname(lastname.toString());
                            TempObj.setEmail(email.toString());
                            TempObj.setAddress(address.toString());
                            TempObj.setQualification(qualification.toString());
                            TempObj.setPassword(password.toString());
                            TempObj.setRepassword(repassword.toString());

                            LoggedinUser = TempObj;
                            fname.setText("First Name: " + firstname.toString());
                            lname.setText("Last Name: " +lastname.toString());
                            uemail.setText("email: " +email.toString());
                            uaddress.setText("Address: " +address.toString());
                            uqalification.setText("Qualification: " +qualification);

                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void edit ()
        {
            Intent intent = new Intent(getContext(), EditProfileActivity.class);
            intent.putExtra("firstname", firstname);
            intent.putExtra("lastname", lastname);
            intent.putExtra("email", email);
            intent.putExtra("address", address);
            intent.putExtra("qualification", qualification);
            startActivity(intent);

        }


    }






