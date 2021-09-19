package com.teamunwaste.unwaste.Home.ExchangeFM;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.teamunwaste.unwaste.R;

import java.util.List;

public class ExchangeAdapter extends RecyclerView.Adapter<ExchangeAdapter.ViewHolder> {

    Context context;
    List<ExchangeModel> list;

    public ExchangeAdapter(Context context, List<ExchangeModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ExchangeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_exchange, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExchangeAdapter.ViewHolder holder, int position) {

        holder.tvUserName.setText(list.get(position).getUserName());
        holder.tvExchangeTitle.setText(list.get(position).getExchangeTitle());
        holder.tvExchangeDescription.setText(list.get(position).getExchangeDescription());
        holder.tvExchangeAddress.setText(list.get(position).getUserAddress());
        holder.tvPostDate.setText(context.getString(R.string.posted_on) + " " + list.get(position).getPostDate());

        Glide.with(context).load(list.get(position).getExchangeImage()).into(holder.ivExchangeImage);

        holder.btnEmail.setOnClickListener(v -> {


        });

        holder.btnPhone.setOnClickListener(v -> {


        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvUserName, tvExchangeTitle, tvExchangeDescription, tvExchangeAddress, tvPostDate;
        ImageView ivExchangeImage;
        MaterialButton btnEmail, btnPhone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvExchangeTitle = itemView.findViewById(R.id.tvExchangeTitle);
            tvExchangeDescription = itemView.findViewById(R.id.tvExchangeDescription);
            tvExchangeAddress = itemView.findViewById(R.id.tvExchangeAddress);
            tvPostDate = itemView.findViewById(R.id.tvPostDate);
            ivExchangeImage = itemView.findViewById(R.id.ivExchangeImage);
            btnEmail = itemView.findViewById(R.id.btnEmail);
            btnPhone = itemView.findViewById(R.id.btnPhone);

        }
    }
}
