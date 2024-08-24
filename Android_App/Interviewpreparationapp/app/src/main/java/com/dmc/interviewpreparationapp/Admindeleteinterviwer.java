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

public class Admindeleteinterviwer extends AppCompatActivity {

    EditText interviwerid;

    Button deleteinterviweradmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindeleteinterviwer);

        getSupportActionBar().setTitle("delete interviwer");

        interviwerid = findViewById(R.id.interviwerid);
        deleteinterviweradmin = findViewById(R.id.deleteinterviweradmin);

        deleteinterviweradmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=interviwerid.getText().toString();
                DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Interviwer").child(id);

                Task<Void> mTask = databaseReference.removeValue();

                mTask.addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        Toast.makeText(Admindeleteinterviwer.this, "Deleted", Toast.LENGTH_SHORT).show();
                        interviwerid.setText("");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Admindeleteinterviwer.this, "Delete task Faild", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}