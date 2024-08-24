package com.dmc.interviewpreparationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton;
import com.zegocloud.uikit.service.defines.ZegoUIKitUser;

import java.util.Collections;

public class VideoCallActivityInterviwerside extends AppCompatActivity {

    EditText useridsearch;

    ZegoSendCallInvitationButton callbt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call_interviwerside);

        useridsearch=findViewById(R.id.useridsearch);
        callbt = findViewById(R.id.callbt);


        useridsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                startvideoCall(useridsearch.getText().toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    public void startvideoCall(String targetuserid)
    {
        callbt.setIsVideoCall(true);
        callbt.setResourceID("zego_uikit_call"); // Please fill in the resource ID name that has been configured in the ZEGOCLOUD's console here.
        callbt.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetuserid,targetuserid)));
    }
}