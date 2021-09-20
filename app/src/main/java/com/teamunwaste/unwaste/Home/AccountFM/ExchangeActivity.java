package com.teamunwaste.unwaste.Home.AccountFM;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.teamunwaste.unwaste.Home.AccountFM.Exchange.ExchangeProductVPAdapter;
import com.teamunwaste.unwaste.databinding.ActivityExchangeBinding;

public class ExchangeActivity extends AppCompatActivity {

    ActivityExchangeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExchangeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tlExchangeProduct.addTab(binding.tlExchangeProduct.newTab().setText("Exchange"));
        binding.tlExchangeProduct.addTab(binding.tlExchangeProduct.newTab().setText("Exchange History"));

        binding.vpExchangeProduct.setAdapter(new ExchangeProductVPAdapter(getSupportFragmentManager(), getLifecycle()));
        binding.vpExchangeProduct.setUserInputEnabled(false);


        // Tab onClick
        binding.tlExchangeProduct.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.vpExchangeProduct.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // ViewPager On Callback
        binding.vpExchangeProduct.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {

                binding.tlExchangeProduct.selectTab(binding.tlExchangeProduct.getTabAt(position));
            }
        });


    }
}