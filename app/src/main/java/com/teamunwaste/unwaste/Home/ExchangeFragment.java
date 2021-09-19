package com.teamunwaste.unwaste.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamunwaste.unwaste.R;
import com.teamunwaste.unwaste.databinding.FragmentExchangeBinding;

public class ExchangeFragment extends Fragment {

    FragmentExchangeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentExchangeBinding.inflate(getLayoutInflater());



        return binding.getRoot();
    }
}