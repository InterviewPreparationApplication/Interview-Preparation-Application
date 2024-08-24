package com.dmc.interviewpreparationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import Adapters.TabLayoutAdapter;

public class HomeActivity extends AppCompatActivity {
    TabLayout tabLayout;

    ViewPager2 viewPager2;
    TabLayoutAdapter tabLayoutAdapter=new TabLayoutAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager2);
        viewPager2.setAdapter(tabLayoutAdapter);

        getSupportActionBar().hide();
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch (position) {
                    case 0:
                        tab.setIcon(R.drawable.question);
                        break;

                    case 1:
                        tab.setIcon(R.drawable.interviewschedule);
                        break;
                    case 2:
                        tab.setIcon(R.drawable.profile);
                        break;
                }
            }
        }).attach();

    }
}