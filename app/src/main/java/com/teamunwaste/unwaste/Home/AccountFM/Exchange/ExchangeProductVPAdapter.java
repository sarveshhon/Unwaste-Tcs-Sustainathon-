package com.teamunwaste.unwaste.Home.AccountFM.Exchange;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ExchangeProductVPAdapter extends FragmentStateAdapter {
    public ExchangeProductVPAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1) {
            return new ExchangeHistoryFragment();
        }
        return new ExchangePostFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
