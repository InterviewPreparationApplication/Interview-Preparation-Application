package Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dmc.interviewpreparationapp.FeedbacklistActivityUser;
import com.dmc.interviewpreparationapp.InterviewerListActivity;
import com.dmc.interviewpreparationapp.MainActivity;
import com.dmc.interviewpreparationapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ScheduleInterviewFragment extends Fragment {

    Spinner spinnerId;

    List<String> subjectlist;

    ArrayList<ContactsContract.Contacts.Data> javaqalist;

    TextView timeshow,dateshow;

    DatabaseReference databaseReference;

    FirebaseAuth firebaseAuth;
    Button giveinterviewbt,selecttimeslot,selectdateslot,showfeedbackbt;

    int year;
    int month;
    int day;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_interview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinnerId=view.findViewById(R.id.spinnerId);
        /*timeshow=view.findViewById(R.id.timeshow);
        selecttimeslot=view.findViewById(R.id.selecttimeslot);

        dateshow=view.findViewById(R.id.dateshow);
        selectdateslot=view.findViewById(R.id.selectdateslot);*/
        giveinterviewbt=view.findViewById(R.id.giveinterviewbt);
        showfeedbackbt=view.findViewById(R.id.showfeedbackbt);

        databaseReference= FirebaseDatabase.getInstance().getReference("Interviwer");



//        databaseReference.child("interviewerworkposition").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Log.e("sunbeam01", "Field value snapshot: " + snapshot);
//                if (snapshot.exists()) {
//                    // Retrieve the value of the field
//                    String fieldValue = snapshot.getValue(String.class);
//
///*
//                            spinnerId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                                @Override
//                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                                    if(position==0 && fieldValue.equals("java")){
//                                        Toast.makeText(getContext(), "Java", Toast.LENGTH_SHORT).show();
//                                    } else if(position==1&& fieldValue.equals("c++")) {
//                                        Toast.makeText(getContext(), "C++", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//
//                                @Override
//                                public void onNothingSelected(AdapterView<?> parent) {
//
//                                }
//                            });
//
// */
//
//                    // Do something with the retrieved field value
//                    Log.e("sunbeam01", "if Field value: " + fieldValue);
//                } else {
//                    // Handle the case where the field does not exist or the dataSnapshot is null
//                    Toast.makeText(getContext(), "inside snapshotelse", Toast.LENGTH_SHORT).show();
//                    Log.d("sunbeam01", "Field not found");
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


//        firebaseAuth = FirebaseAuth.getInstance();
//        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
//        if(currentUser == null) {
//            Toast.makeText(getContext(), "User not logged in", Toast.LENGTH_SHORT).show();
//        } else {
//            String uid = currentUser.getUid();
//
//            databaseReference = FirebaseDatabase.getInstance().getReference("Users");
//        }

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        subjectlist=new ArrayList<>();
        subjectlist.add("Devloper");
        subjectlist.add("be");
        subjectlist.add("android devlopment");
        subjectlist.add("react");
        subjectlist.add("flutter");
        ArrayAdapter arrayAdapter=new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,subjectlist);
        spinnerId.setAdapter(arrayAdapter);

/*
        selecttimeslot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Savetime();
            }

            private void Savetime() {
                Calendar calendar=Calendar.getInstance();
                int hour=calendar.get(Calendar.HOUR_OF_DAY);
                int mins=calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog=new TimePickerDialog(getContext(), androidx.appcompat.R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar calendar1=Calendar.getInstance();
                        calendar1.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar1.set(Calendar.MINUTE,minute);
                        calendar1.setTimeZone(TimeZone.getDefault());
                        SimpleDateFormat format=new SimpleDateFormat("k:mm a");
                        String time=format.format(calendar1.getTime());
                        timeshow.setText(time);
//                        UserHelperClass userTime=new UserHelperClass();
//                        userTime.setTime(time);
//                        databaseReference.push().setValue(userTime);


                    }
                },hour,mins,true);

                timePickerDialog.show();

            }
        });

        final Calendar calendar=Calendar.getInstance();
        selectdateslot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year=calendar.get(Calendar.YEAR);
                month=calendar.get(Calendar.MONTH);
                day=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(),new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month=month+1;
                        String date1=dayOfMonth+"-"+month+"-"+year;
                        dateshow.setText(date1);


                        //dateshow.setText(SimpleDateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime()));
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
*/

        /*giveinterviewbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("interviewerworkposition").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Log.e("sunbeam01", "Field value snapshot: " + snapshot);
                        if (snapshot.exists()) {
                            // Retrieve the value of the field
                            String fieldValue = snapshot.getValue(String.class);

/*
                            spinnerId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    if(position==0 && fieldValue.equals("java")){
                                        Toast.makeText(getContext(), "Java", Toast.LENGTH_SHORT).show();
                                    } else if(position==1&& fieldValue.equals("c++")) {
                                        Toast.makeText(getContext(), "C++", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });



                            // Do something with the retrieved field value
                            Log.e("sunbeam01", "if Field value: " + fieldValue);
                        } else {
                            // Handle the case where the field does not exist or the dataSnapshot is null
                            Toast.makeText(getContext(), "inside snapshotelse", Toast.LENGTH_SHORT).show();
                            Log.d("sunbeam01", "Field not found");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

         */


        javaqalist=new ArrayList<>();

        giveinterviewbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.orderByChild("interviewerworkposition").addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                       // Log.e("sunbeam01","snapshot data"+snapshot);
                        for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                            if (snapshot.exists()) {
                                // Retrieve the value of the field
                                startActivity(new Intent(getContext(), InterviewerListActivity.class));

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });
            }
        });


        showfeedbackbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), FeedbacklistActivityUser.class));
            }
        });

    }
}