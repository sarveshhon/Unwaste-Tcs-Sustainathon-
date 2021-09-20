package com.teamunwaste.unwaste.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.teamunwaste.unwaste.Home.AccountFM.ExchangeActivity;
import com.teamunwaste.unwaste.Home.AccountFM.ProfileActivity;
import com.teamunwaste.unwaste.Home.AccountFM.SellProductActivity;
import com.teamunwaste.unwaste.MainActivity;
import com.teamunwaste.unwaste.R;
import com.teamunwaste.unwaste.databinding.FragmentAccountBinding;

public class AccountFragment extends Fragment {

    FragmentAccountBinding binding;
    Context context;
    FirebaseAuth mAuth;
    ImageView ivProfile;
    TextView tvUserName, tvUserEmailId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAccountBinding.inflate(getLayoutInflater());
        context = getContext();

        mAuth = FirebaseAuth.getInstance();

        Glide.with(context).load(mAuth.getCurrentUser().getPhotoUrl()).into(binding.ivProfile);

        binding.tvUserName.setText(mAuth.getCurrentUser().getDisplayName());
        binding.tvUserEmailId.setText(mAuth.getCurrentUser().getEmail());

        binding.btnLogOut.setOnClickListener(v -> {
            mAuth.signOut();
            startActivity(new Intent(context, MainActivity.class));
            getActivity().finish();
        });

        binding.llProfile.setOnClickListener(v -> {
            startActivity(new Intent(context, ProfileActivity.class));
        });
        binding.llSellProduct.setOnClickListener(v -> {
            startActivity(new Intent(context, SellProductActivity.class));
        });
        binding.llExchange.setOnClickListener(v -> {
            startActivity(new Intent(context, ExchangeActivity.class));
        });

        return binding.getRoot();
    }
}