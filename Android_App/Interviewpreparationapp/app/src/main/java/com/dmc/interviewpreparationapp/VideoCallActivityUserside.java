package com.dmc.interviewpreparationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;
import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton;
import com.zegocloud.uikit.service.defines.ZegoUIKitUser;

import java.util.Collections;

public class VideoCallActivityUserside extends AppCompatActivity {

    EditText loginid;

    Button loginbt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call_userside);


        loginid=findViewById(R.id.loginid);
        loginbt=findViewById(R.id.loginbt);

        loginbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startmyservice(loginid.getText().toString());
                Intent intent = new Intent(getApplicationContext(),VideoCallActivityInterviwerside.class);
                intent.putExtra("caller",loginid.getText().toString().trim());
                startActivity(intent);
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
