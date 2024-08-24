package Adapters;

import static android.icu.lang.UCharacter.SentenceBreak.SP;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.dmc.interviewpreparationapp.Loginpage;
import com.dmc.interviewpreparationapp.R;
import com.dmc.interviewpreparationapp.VideoCallActivityInterviwerside;
import com.dmc.interviewpreparationapp.VideoCallActivityUserside;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import HelperClasses.Interviewerhelper;
import HelperClasses.Javademoquestionhelper;
import HelperClasses.UserHelperClass;

public class InterviewerlistAdapter extends FirebaseRecyclerAdapter<Interviewerhelper,InterviewerlistAdapter.MyViewHolder> {

    ArrayList<Interviewerhelper> listjavaquestion;
    public static UserHelperClass LoggedinUser = new UserHelperClass();
    public static Interviewerhelper Inter = new Interviewerhelper();

    DatabaseReference databaseReference;

    Context context;

    Interviewerhelper interviewerhelper = new Interviewerhelper();

    private AddRequestListener addRequestListener;

    public interface AddRequestListener {
        void onAddRequestClicked(Interviewerhelper user);
    }

    public void setAddRequestListener(AddRequestListener listener) {
        this.addRequestListener = listener;
    }

    public InterviewerlistAdapter(@NonNull FirebaseRecyclerOptions<Interviewerhelper> options, Context context) {
        super(options);
        this.context = context;
    }

    Object firstname;

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Interviewerhelper model) {

        holder.iname.setText("Firstname: " + model.getInterviewerfirstname());
        holder.iprofile.setText("Work position: " + model.getInterviewerworkposition());

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.interviwerlistfirebase, parent, false);
        return new MyViewHolder(view);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView iname, iprofile;
        Button addbt;

        public MyViewHolder(View itemView) {
            super(itemView);
            iname = itemView.findViewById(R.id.iname);
            iprofile = itemView.findViewById(R.id.iprofile);
            addbt = itemView.findViewById(R.id.addbt);

            DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Users");
            reference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //                  Log.e("sunbeam001","data: " +snapshot);
                    if (snapshot.exists()) {
//                        Toast.makeText(context, "inside if", Toast.LENGTH_SHORT).show();

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
                                firstname = TempValue.get("firstname");
                                Log.e("sunbeam001", "firstname" + firstname);
                                TempObj.setFirstname(firstname.toString());

                                LoggedinUser = TempObj;
                            }
                        }
                    }
                    //  Log.e("sunbeam001","addRequestListener"+addRequestListener);
                    if (addRequestListener != null) {
                        addRequestListener.onAddRequestClicked(Inter);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });

            addbt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //getUsername();
                  //  Toast.makeText(context, "addbt clicked", Toast.LENGTH_SHORT).show();
                    if (context != null) {
                        Intent intent = new Intent(context, VideoCallActivityUserside.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                    sendConnectionRequest(getAdapterPosition());
                 //Inter = getItem(getAdapterPosition());
                }

            });
        }
    }


    private void sendConnectionRequest(int position) {
        Interviewerhelper selectedInterviewer = getItem(position);
        if (selectedInterviewer != null) {
            DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Users");
            reference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user != null) {
                            String currentUserId = user.getUid();
                            for (com.google.firebase.database.DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                UserHelperClass tempUser = dataSnapshot.getValue(UserHelperClass.class);
                                //Log.e("sunbeam001", "tempuser: " + tempUser);
                                if (tempUser != null && currentUserId.equals(tempUser.getMobileno())) {
                                    // Current user found
                                    LoggedinUser = tempUser;
                                    firstname = LoggedinUser.getFirstname();// Assuming getInterviewermobno() returns the interviewer's phone number
                                    Log.e("sunbeam001", "firstname: " + firstname);
                                    // Log.e("sunbeam001", "loggedin:" + LoggedinUser);
                                    break;
                                }
                            }

                            DatabaseReference connectionRequestRef = FirebaseDatabase.getInstance().getReference("Interviewrequest");
                            String interviewerPhoneNumber = selectedInterviewer.getInterviewermobno();
                            firstname = LoggedinUser.getFirstname();// Assuming getInterviewermobno() returns the interviewer's phone number
                            //  Log.e("sunbeam001", "firstname: " + firstname);
                            if (firstname != null) {
                                // Store sender and receiver information under the unique requestId
                                connectionRequestRef.child(interviewerPhoneNumber).child("mobileno").setValue(LoggedinUser.getMobileno());
                                connectionRequestRef.child(interviewerPhoneNumber).child("firstname").setValue(LoggedinUser.getFirstname());
                                connectionRequestRef.child(interviewerPhoneNumber).child("receiverId").setValue(interviewerPhoneNumber);
                                connectionRequestRef.child(interviewerPhoneNumber).child("receiverFirstname").setValue(selectedInterviewer.getInterviewerfirstname());
                                // Add more fields as needed

                                if (addRequestListener != null) {
                                    addRequestListener.onAddRequestClicked(selectedInterviewer);
                                }
                            } else {
                                Log.e("InterviewerAdapter", "Request ID is null");
                            }
                        } else {
                            Log.e("InterviewerAdapter", "Current user is null");
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

}


/* private void sendConnectionRequest(int position) {
        Interviewerhelper selectedInterviewer = getItem(position);
        if (selectedInterviewer != null) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                String currentUserId = user.getUid();
                DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("Users");
                usersRef.child(currentUserId).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                String currentUserId = user.getUid();
                                for (com.google.firebase.database.DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    UserHelperClass tempUser = dataSnapshot.getValue(UserHelperClass.class);
                                    //Log.e("sunbeam001", "tempuser: " + tempUser);
                                    if (tempUser != null && currentUserId.equals(tempUser.getMobileno())) {
                                        // Current user found
                                        LoggedinUser = tempUser;
                                        firstname = LoggedinUser.getFirstname();// Assuming getInterviewermobno() returns the interviewer's phone number
                                        Log.e("sunbeam001", "firstname: " + firstname);

                                        // Log.e("sunbeam001", "loggedin:" + LoggedinUser);
                                        break;
                                    }
                                }

                                DatabaseReference connectionRequestRef = FirebaseDatabase.getInstance().getReference("Interviewrequest");
                                String interviewerPhoneNumber = selectedInterviewer.getInterviewermobno();
                                firstname = LoggedinUser.getFirstname();// Assuming getInterviewermobno() returns the interviewer's phone number
                                  Log.e("sunbeam001", "firstname: " + firstname);
                                if (firstname != null) {
                                    // Store sender and receiver information under the unique requestId
                                    Toast.makeText(context, "firstname is not null", Toast.LENGTH_SHORT).show();
                                    connectionRequestRef.child(interviewerPhoneNumber).child("mobileno").setValue(LoggedinUser.getMobileno());
                                    connectionRequestRef.child(interviewerPhoneNumber).child("firstname").setValue(LoggedinUser.getFirstname());
                                    connectionRequestRef.child(interviewerPhoneNumber).child("receiverId").setValue(interviewerPhoneNumber);
                                    connectionRequestRef.child(interviewerPhoneNumber).child("receiverFirstname").setValue(selectedInterviewer.getFirstname());
                                    // Add more fields as needed

                                    if (addRequestListener != null) {
                                        addRequestListener.onAddRequestClicked(selectedInterviewer);
                                    }
                                } else {
                                    Log.e("InterviewerAdapter", "Request ID is null");
                                }
                            } else {
                                Log.e("InterviewerAdapter", "Current user is null");
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("sunbeam001", "Database error: " + error.getMessage());
                    }
                });
            } else {
                Log.e("InterviewerAdapter", "Current user is null");
            }
        } else {
            Log.e("InterviewerAdapter", "Selected interviewer is null");
        }
    }

*/
//--------------------------------------------------------------------------------------------------------------------------------------------------------------
/*
private void sendConnectionRequest(int position) {
        Interviewerhelper selectedInterviewer = getItem(position);
        if (selectedInterviewer != null) {
            DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Users");
            reference1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user != null) {
                            String currentUserId = user.getUid();
                            for (com.google.firebase.database.DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                UserHelperClass tempUser = dataSnapshot.getValue(UserHelperClass.class);
                                //Log.e("sunbeam001", "tempuser: " + tempUser);
                                if (tempUser != null && currentUserId.equals(tempUser.getMobileno())) {
                                    // Current user found
                                    LoggedinUser = tempUser;
                                    firstname = LoggedinUser.getFirstname();// Assuming getInterviewermobno() returns the interviewer's phone number
                                    Log.e("sunbeam001", "firstname: " + firstname);

                                    // Log.e("sunbeam001", "loggedin:" + LoggedinUser);
                                    break;
                                }
                            }

                            DatabaseReference connectionRequestRef = FirebaseDatabase.getInstance().getReference("Interviewrequest");
                            String interviewerPhoneNumber = selectedInterviewer.getInterviewermobno();
                            firstname = LoggedinUser.getFirstname();// Assuming getInterviewermobno() returns the interviewer's phone number
                            //  Log.e("sunbeam001", "firstname: " + firstname);
                            if (firstname != null) {
                                // Store sender and receiver information under the unique requestId
                                connectionRequestRef.child(interviewerPhoneNumber).child("mobileno").setValue(LoggedinUser.getMobileno());
                                connectionRequestRef.child(interviewerPhoneNumber).child("firstname").setValue(LoggedinUser.getFirstname());
                                connectionRequestRef.child(interviewerPhoneNumber).child("receiverId").setValue(interviewerPhoneNumber);
                                connectionRequestRef.child(interviewerPhoneNumber).child("receiverFirstname").setValue(selectedInterviewer.getFirstname());
                                // Add more fields as needed

                                if (addRequestListener != null) {
                                    addRequestListener.onAddRequestClicked(selectedInterviewer);
                                }
                            } else {
                                Log.e("InterviewerAdapter", "Request ID is null");
                            }
                        } else {
                            Log.e("InterviewerAdapter", "Current user is null");
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
 */