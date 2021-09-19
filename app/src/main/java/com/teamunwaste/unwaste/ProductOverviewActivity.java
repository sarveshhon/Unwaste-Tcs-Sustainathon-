package com.teamunwaste.unwaste;

import static com.teamunwaste.unwaste.Helper.API.PRODUCT_API;
import static com.teamunwaste.unwaste.Helper.PARAM.productDescription;
import static com.teamunwaste.unwaste.Helper.PARAM.productId;
import static com.teamunwaste.unwaste.Helper.PARAM.productImage;
import static com.teamunwaste.unwaste.Helper.PARAM.productPrice;
import static com.teamunwaste.unwaste.Helper.PARAM.productTitle;
import static com.teamunwaste.unwaste.Helper.PARAM.userName;
import static com.teamunwaste.unwaste.Helper.Util.setStatusBar;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.teamunwaste.unwaste.databinding.ActivityProductOverviewBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProductOverviewActivity extends AppCompatActivity {

    ActivityProductOverviewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductOverviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setStatusBar(this, R.color.white);

        loadProduct(getIntent().getStringExtra(productId));

        binding.btnPlaceOrder.setOnClickListener(v -> {


        });

    }


    private void loadProduct(String id) {

        StringRequest request = new StringRequest(Request.Method.POST, PRODUCT_API, response -> {

            try {
                JSONArray jsonArray = new JSONArray(response);
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                binding.tvProductTitle.setText(jsonObject.getString(productTitle));
                binding.tvProductDescription.setText(jsonObject.getString(productDescription));
                binding.tvProductPrice.setText(getString(R.string.rupee) + jsonObject.getString(productPrice));
                binding.tvUserName.setText(jsonObject.getString(userName));
                Glide.with(this).load(jsonObject.getString(productImage)).into(binding.ivProductImage);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {

        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() {

                HashMap<String, String> param = new HashMap<>();
                param.put(productId, id);

                return param;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

}