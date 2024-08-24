package com.dmc.interviewpreparationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admindeletecandidate extends AppCompatActivity {

    EditText deleteid;

    Button admincandidatedeleteid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindeletecandidate);

        getSupportActionBar().setTitle("delete candidate");

        deleteid=findViewById(R.id.deleteid);
        admincandidatedeleteid=findViewById(R.id.admincandidatedeleteid);

        admincandidatedeleteid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=deleteid.getText().toString();
                DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Users").child(id);

                Task<Void> mTask = databaseReference.removeValue();

                mTask.addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        Toast.makeText(Admindeletecandidate.this, "Deleted", Toast.LENGTH_SHORT).show();
                        deleteid.setText("");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Admindeletecandidate.this, "Delete task Faild", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}