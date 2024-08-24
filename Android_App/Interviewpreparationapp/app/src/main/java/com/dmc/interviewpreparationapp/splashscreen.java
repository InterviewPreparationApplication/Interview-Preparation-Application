package com.dmc.interviewpreparationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import Fragments.ScheduleInterviewFragment;

public class splashscreen extends AppCompatActivity {

    private static int SPLASH_SCREEN=5500;
    ImageView splashlogo;
    TextView interviewId,preparationId;
    Animation top,bottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        setContentView(R.layout.activity_splashscreen);

        getSupportActionBar().hide();
        splashlogo=findViewById(R.id.splashlogo);
        interviewId=findViewById(R.id.interviewId);
        preparationId=findViewById(R.id.preparationId);
        top= AnimationUtils.loadAnimation(this,R.anim.top);
        bottom=AnimationUtils.loadAnimation(this,R.anim.bottom);

        splashlogo.setAnimation(top);
        interviewId.setAnimation(bottom);
        preparationId.setAnimation(bottom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(splashscreen.this, InterviewerLogin.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);


    }
}