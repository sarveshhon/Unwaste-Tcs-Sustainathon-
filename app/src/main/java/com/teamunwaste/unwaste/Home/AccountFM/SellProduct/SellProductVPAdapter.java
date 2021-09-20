package com.teamunwaste.unwaste.Home.AccountFM.SellProduct;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class SellProductVPAdapter extends FragmentStateAdapter {
    public SellProductVPAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 1){
            return new SellHistoryFragment();
        }
        return new SellFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
