package com.teamunwaste.unwaste;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.teamunwaste.unwaste.Home.ExchangeFragment;
import com.teamunwaste.unwaste.Home.ExploreFragment;
import com.teamunwaste.unwaste.Home.HomeFragment;

public class HomeVPAdapter extends FragmentStateAdapter {
    public HomeVPAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if (position == 1) {
            return new ExchangeFragment();
        } else if (position == 2) {
            return new ExploreFragment();
        }
        return new HomeFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
