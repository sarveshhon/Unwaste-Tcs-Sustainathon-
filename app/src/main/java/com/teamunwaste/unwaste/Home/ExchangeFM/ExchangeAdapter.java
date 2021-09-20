package com.teamunwaste.unwaste.Home.ExchangeFM;

import static com.teamunwaste.unwaste.Helper.PARAM.userEmail;
import static com.teamunwaste.unwaste.Helper.Util.callNow;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.teamunwaste.unwaste.R;

import java.util.List;

public class ExchangeAdapter extends RecyclerView.Adapter<ExchangeAdapter.ViewHolder> {

    Context context;
    List<ExchangeModel> list;
    FirebaseAuth mAuth;

    public ExchangeAdapter(Context context, List<ExchangeModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ExchangeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mAuth = FirebaseAuth.getInstance();

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_exchange, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExchangeAdapter.ViewHolder holder, int position) {

        holder.tvUserName.setText(list.get(position).getUserName());
        holder.tvExchangeTitle.setText(list.get(position).getExchangeTitle());
        holder.tvExchangeDescription.setText(list.get(position).getExchangeDescription());
        holder.tvExchangeAddress.setText(list.get(position).getUserAddress());
        holder.tvPostDate.setText(context.getString(R.string.posted_on) + " " + list.get(position).getPostDate());


        if(list.get(position).getExchangeFee().equals("0")){
            holder.tvFee.setText("Free");
        } else {
            holder.tvFee.setText(context.getString(R.string.rupee)+" "+list.get(position).getExchangeFee());
        }

        Glide.with(context).load(list.get(position).getExchangeImage()).into(holder.ivExchangeImage);

        holder.btnEmail.setOnClickListener(v -> {

            String data = "Product:\n" + list.get(position).getExchangeTitle() + "\n" +
                    list.get(position).getExchangeDescription() + "\n" +
                    list.get(position).getExchangeFee() + "\n\n" +
                    "Exchange By:\n" +
                    mAuth.getCurrentUser().getDisplayName() + "\n" +
                    mAuth.getCurrentUser().getEmail() + "\n";

            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{list.get(position).getUserEmail()});
            email.setPackage("com.google.android.gm");
            email.putExtra(Intent.EXTRA_SUBJECT, "Exchange Request by " + mAuth.getCurrentUser().getDisplayName());
            email.putExtra(Intent.EXTRA_TEXT, data);

            //need this to prompts email client only
            email.setType("plain/text");

            context.startActivity(Intent.createChooser(email, "Choose an Email client :"));


        });

        holder.btnPhone.setOnClickListener(v -> {

            callNow(context,list.get(position).getUserPhone());

        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvUserName, tvExchangeTitle, tvExchangeDescription, tvExchangeAddress, tvPostDate, tvFee;
        ImageView ivExchangeImage;
        MaterialButton btnEmail, btnPhone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvExchangeTitle = itemView.findViewById(R.id.tvExchangeTitle);
            tvExchangeDescription = itemView.findViewById(R.id.tvExchangeDescription);
            tvExchangeAddress = itemView.findViewById(R.id.tvExchangeAddress);
            tvFee = itemView.findViewById(R.id.tvFee);
            tvPostDate = itemView.findViewById(R.id.tvPostDate);
            ivExchangeImage = itemView.findViewById(R.id.ivExchangeImage);
            btnEmail = itemView.findViewById(R.id.btnEmail);
            btnPhone = itemView.findViewById(R.id.btnPhone);

        }
    }
}
