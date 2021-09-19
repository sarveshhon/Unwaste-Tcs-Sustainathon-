package com.teamunwaste.unwaste;

import static com.teamunwaste.unwaste.Helper.API.PRODUCTS_API;
import static com.teamunwaste.unwaste.Helper.PARAM._id;
import static com.teamunwaste.unwaste.Helper.PARAM.categoryId;
import static com.teamunwaste.unwaste.Helper.PARAM.categoryTitle;
import static com.teamunwaste.unwaste.Helper.PARAM.productDescription;
import static com.teamunwaste.unwaste.Helper.PARAM.productImage;
import static com.teamunwaste.unwaste.Helper.PARAM.productPrice;
import static com.teamunwaste.unwaste.Helper.PARAM.productStatus;
import static com.teamunwaste.unwaste.Helper.PARAM.productTitle;
import static com.teamunwaste.unwaste.Helper.PARAM.userEmail;
import static com.teamunwaste.unwaste.Helper.PARAM.userId;
import static com.teamunwaste.unwaste.Helper.PARAM.userName;
import static com.teamunwaste.unwaste.Helper.PARAM.userPhone;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.teamunwaste.unwaste.Home.HomeFM.ProductModel;
import com.teamunwaste.unwaste.databinding.ActivityProductListBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductListActivity extends AppCompatActivity {

    ActivityProductListBinding binding;


    List<ProductModel> productModelList = new ArrayList<>();
    ProductListAdapter productListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvCategoryTitle.setText(getIntent().getStringExtra(categoryTitle));

        loadProductList(getIntent().getStringExtra(categoryId));

    }

    private void loadProductList(String id) {

        StringRequest request = new StringRequest(Request.Method.POST, PRODUCTS_API, response -> {

            try {
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
                            jsonObject.getString(userId),
                            jsonObject.getString(userName),
                            jsonObject.getString(userPhone),
                            jsonObject.getString(userEmail)
                    ));

                }

                productListAdapter = new ProductListAdapter(this,productModelList);
                binding.rvProductList.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                binding.rvProductList.setAdapter(productListAdapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {

        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() {

                HashMap<String, String> param = new HashMap<>();
                param.put(categoryId, id);

                return param;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

}