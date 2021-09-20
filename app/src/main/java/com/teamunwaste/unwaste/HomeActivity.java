package com.teamunwaste.unwaste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.teamunwaste.unwaste.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;

    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tlHomeAct.addTab(binding.tlHomeAct.newTab().setText("Home").setIcon(R.drawable.ic_home));
        binding.tlHomeAct.addTab(binding.tlHomeAct.newTab().setText("Exchange").setIcon(R.drawable.ic_exchange));
        binding.tlHomeAct.addTab(binding.tlHomeAct.newTab().setText("Explore").setIcon(R.drawable.ic_explore));
        binding.tlHomeAct.addTab(binding.tlHomeAct.newTab().setText("Account").setIcon(R.drawable.ic_user));

        binding.vpHomeAct.setAdapter(new HomeVPAdapter(getSupportFragmentManager(), getLifecycle()));
        binding.vpHomeAct.setUserInputEnabled(false);


        // Tab onClick
        binding.tlHomeAct.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.vpHomeAct.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // ViewPager On Callback
        binding.vpHomeAct.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {

                binding.tlHomeAct.selectTab(binding.tlHomeAct.getTabAt(position));
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            finishAffinity();
        } else {
            Toast.makeText(this, "Tap again to exit", Toast.LENGTH_SHORT).show();
        }

        mBackPressed = System.currentTimeMillis();
    }
}