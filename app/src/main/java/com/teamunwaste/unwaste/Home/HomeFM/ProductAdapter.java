package com.teamunwaste.unwaste.Home.HomeFM;

import static com.teamunwaste.unwaste.Helper.PARAM.productId;

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
import com.teamunwaste.unwaste.Helper.Util;
import com.teamunwaste.unwaste.ProductOverviewActivity;
import com.teamunwaste.unwaste.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    Context context;
    List<ProductModel> list;

    public ProductAdapter(Context context, List<ProductModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvProductTitle.setText(list.get(position).getProductTitle());
        Glide.with(context).load(list.get(position).getProductImage()).into(holder.ivProductImage);

        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(context, ProductOverviewActivity.class);
            i.putExtra(productId, Util.convertMongodbObjToString(list.get(position).getProductId()));
            context.startActivity(i);
        });

    }

    @Override
    public int getItemCount() {
        if (list.size() > 7) {
            return 7;
        }
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProductImage;
        TextView tvProductTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivProductImage = itemView.findViewById(R.id.ivProductImage);
            tvProductTitle = itemView.findViewById(R.id.tvProductTitle);

        }
    }
}
