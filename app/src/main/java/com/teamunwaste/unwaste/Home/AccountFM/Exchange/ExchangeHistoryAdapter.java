package com.teamunwaste.unwaste.Home.AccountFM.Exchange;

import static com.teamunwaste.unwaste.Helper.API.REMOVE_EXCHANGE_POST_HISTORY_API;
import static com.teamunwaste.unwaste.Helper.PARAM.exchangeId;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.teamunwaste.unwaste.Helper.Util;
import com.teamunwaste.unwaste.Home.ExchangeFM.ExchangeModel;
import com.teamunwaste.unwaste.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExchangeHistoryAdapter extends RecyclerView.Adapter<ExchangeHistoryAdapter.ViewHolder> {

    Context context;
    List<ExchangeModel> list;

    public ExchangeHistoryAdapter(Context context, List<ExchangeModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sellhistory_product, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getExchangeImage()).into(holder.ivProductImage);

        holder.tvProductTitle.setText(list.get(position).getExchangeTitle());
        holder.tvProductDescription.setText(list.get(position).getExchangeDescription());
        holder.tvProductPrice.setText(context.getString(R.string.rupee) + " " + list.get(position).getExchangeFee());

        holder.btnRemove.setOnClickListener(v -> {

            removeProduct(Util.convertMongodbObjToString(list.get(position).getExchangeId()));
            holder.btnRemove.setVisibility(View.GONE);
            Toast.makeText(context, "Exchange Removed" + Util.convertMongodbObjToString(list.get(position).getExchangeId()), Toast.LENGTH_SHORT).show();

        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProductImage;
        TextView tvProductTitle, tvProductDescription, tvProductPrice;
        MaterialButton btnRemove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivProductImage = itemView.findViewById(R.id.ivProductImage);
            tvProductTitle = itemView.findViewById(R.id.tvProductTitle);
            tvProductDescription = itemView.findViewById(R.id.tvProductDescription);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            btnRemove = itemView.findViewById(R.id.btnRemove);

        }
    }


    private void removeProduct(String _exchangeId) {


        StringRequest request = new StringRequest(Request.Method.POST, REMOVE_EXCHANGE_POST_HISTORY_API, response -> {


        }, error -> {

        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() {

                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(exchangeId, _exchangeId);

                return hashMap;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);

    }

}
