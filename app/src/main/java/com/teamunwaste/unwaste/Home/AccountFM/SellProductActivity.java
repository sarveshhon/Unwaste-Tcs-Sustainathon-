package com.teamunwaste.unwaste.Home.AccountFM;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.teamunwaste.unwaste.Home.AccountFM.SellProduct.SellProductVPAdapter;
import com.teamunwaste.unwaste.HomeVPAdapter;
import com.teamunwaste.unwaste.R;
import com.teamunwaste.unwaste.databinding.ActivitySellProductBinding;

public class SellProductActivity extends AppCompatActivity {

    ActivitySellProductBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySellProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.tlSellProduct.addTab(binding.tlSellProduct.newTab().setText("Sell"));
        binding.tlSellProduct.addTab(binding.tlSellProduct.newTab().setText("Sell History"));

        binding.vpSellProduct.setAdapter(new SellProductVPAdapter(getSupportFragmentManager(), getLifecycle()));
        binding.vpSellProduct.setUserInputEnabled(false);


        // Tab onClick
        binding.tlSellProduct.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.vpSellProduct.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // ViewPager On Callback
        binding.vpSellProduct.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {

                binding.tlSellProduct.selectTab(binding.tlSellProduct.getTabAt(position));
            }
        });



    }
}