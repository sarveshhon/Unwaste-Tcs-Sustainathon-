package com.teamunwaste.unwaste.Home.HomeFM;

import static com.teamunwaste.unwaste.Helper.PARAM.categoryId;
import static com.teamunwaste.unwaste.Helper.PARAM.categoryTitle;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.teamunwaste.unwaste.ProductListActivity;
import com.teamunwaste.unwaste.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    Context context;
    List<CategoryModel> list;

    public CategoryAdapter(Context context, List<CategoryModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvCategoryTitle.setText(list.get(position).getCategoryTitle());
        Glide.with(context).load(list.get(position).getCategoryImage()).into(holder.ivCategoryImage);

        holder.btnMoreProducts.setOnClickListener(v -> {
            Intent i = new Intent(context, ProductListActivity.class);
            i.putExtra(categoryId,list.get(position).getCategoryId());
            i.putExtra(categoryTitle,list.get(position).getCategoryTitle());
            context.startActivity(i);
        });

        holder.rvProductList.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
        holder.rvProductList.setAdapter(new ProductAdapter(context,list.get(position).getList()));



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvCategoryTitle;
        ImageView ivCategoryImage;
        MaterialButton btnMoreProducts;
        RecyclerView rvProductList;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCategoryTitle = itemView.findViewById(R.id.tvCategoryTitle);
            ivCategoryImage = itemView.findViewById(R.id.ivCategoryImage);
            btnMoreProducts = itemView.findViewById(R.id.btnMoreProducts);
            rvProductList = itemView.findViewById(R.id.rvProductList);

        }
    }
}
