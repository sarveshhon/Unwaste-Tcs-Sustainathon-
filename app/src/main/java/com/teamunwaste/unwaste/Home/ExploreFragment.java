package com.teamunwaste.unwaste.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamunwaste.unwaste.R;
import com.teamunwaste.unwaste.databinding.FragmentExploreBinding;

public class ExploreFragment extends Fragment {

    FragmentExploreBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentExploreBinding.inflate(getLayoutInflater());




        return binding.getRoot();
    }
}