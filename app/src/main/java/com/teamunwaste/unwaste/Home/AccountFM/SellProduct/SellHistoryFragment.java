package com.teamunwaste.unwaste.Home.AccountFM.SellProduct;

import static com.teamunwaste.unwaste.Helper.API.SELL_PRODUCT_HISTORY_API;
import static com.teamunwaste.unwaste.Helper.PARAM._id;
import static com.teamunwaste.unwaste.Helper.PARAM.categoryId;
import static com.teamunwaste.unwaste.Helper.PARAM.productDescription;
import static com.teamunwaste.unwaste.Helper.PARAM.productImage;
import static com.teamunwaste.unwaste.Helper.PARAM.productPrice;
import static com.teamunwaste.unwaste.Helper.PARAM.productStatus;
import static com.teamunwaste.unwaste.Helper.PARAM.productTitle;
import static com.teamunwaste.unwaste.Helper.PARAM.userEmail;
import static com.teamunwaste.unwaste.Helper.PARAM.userName;
import static com.teamunwaste.unwaste.Helper.PARAM.userPhone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.teamunwaste.unwaste.Home.HomeFM.ProductModel;
import com.teamunwaste.unwaste.databinding.FragmentSellHistoryBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellHistoryFragment extends Fragment {

    FragmentSellHistoryBinding binding;

    List<ProductModel> productModelList = new ArrayList<>();
    SellHistoryAdapter sellHistoryAdapter;

    FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSellHistoryBinding.inflate(getLayoutInflater());

        mAuth = FirebaseAuth.getInstance();

        loadProductList(mAuth.getCurrentUser().getEmail());

        binding.swipe.setOnRefreshListener(() -> {

            loadProductList(mAuth.getCurrentUser().getEmail());
        });

        return binding.getRoot();
    }


    private void loadProductList(String mid) {

        productModelList.clear();

        StringRequest request = new StringRequest(Request.Method.POST, SELL_PRODUCT_HISTORY_API, response -> {

            try {
                if (binding.swipe.isRefreshing()) {
                    binding.swipe.setRefreshing(false);
                }
                JSONArray jsonArray = new JSONArray(response);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    productModelList.add(new ProductModel(
                            jsonObject.getString(categoryId),
                            jsonObject.getString(_id),
                            jsonObject.getString(productTitle),
                            jsonObject.getString(productImage),
                            jsonObject.getString(productDescription),
                            jsonObject.getString(productPrice),
                            jsonObject.getString(productStatus),
                            jsonObject.getString(userName),
                            jsonObject.getString(userPhone),
                            jsonObject.getString(userEmail)
                    ));

                }

                sellHistoryAdapter = new SellHistoryAdapter(getContext(), productModelList);
                binding.rvSellHistory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                binding.rvSellHistory.setAdapter(sellHistoryAdapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {

        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() {

                HashMap<String, String> param = new HashMap<>();
                param.put(userEmail, mid);

                return param;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }
}