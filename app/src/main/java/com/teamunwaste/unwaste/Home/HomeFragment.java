package com.teamunwaste.unwaste.Home;

import static com.teamunwaste.unwaste.Helper.API.PRODUCTS_API;
import static com.teamunwaste.unwaste.Helper.PARAM._id;
import static com.teamunwaste.unwaste.Helper.PARAM.categoryImage;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.teamunwaste.unwaste.Home.HomeFM.CategoryAdapter;
import com.teamunwaste.unwaste.Home.HomeFM.CategoryModel;
import com.teamunwaste.unwaste.Home.HomeFM.ProductModel;
import com.teamunwaste.unwaste.databinding.FragmentHomeBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;

    List<CategoryModel> categoryModelList = new ArrayList<>();

    List<ProductModel> productModelList;
    CategoryAdapter categoryAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(getLayoutInflater());


        loadProducts();


        return binding.getRoot();
    }


    private void loadProducts() {

        StringRequest request = new StringRequest(Request.Method.POST, PRODUCTS_API, response -> {

            try {
                JSONArray jsonArray = new JSONArray(response);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    JSONArray jsonArray2 = jsonObject.getJSONArray("products");
                    productModelList = new ArrayList<>();
                    if (jsonArray2.length() != 0) {
                        for (int j = 0; j < jsonArray2.length(); j++) {
                            JSONObject jsonObject2 = jsonArray2.getJSONObject(j);
                            productModelList.add(new ProductModel(
                                    jsonObject.getString(_id),
                                    jsonObject2.getString(_id),
                                    jsonObject2.getString(productTitle),
                                    jsonObject2.getString(productImage),
                                    jsonObject2.getString(productDescription),
                                    jsonObject2.getString(productPrice),
                                    jsonObject2.getString(productStatus),
                                    jsonObject2.getString(userName),
                                    jsonObject2.getString(userPhone),
                                    jsonObject2.getString(userEmail)
                            ));
                        }
                    } else {
                        continue;
                    }

                    categoryModelList.add(new CategoryModel(jsonObject.getString(_id), jsonObject.getString(categoryTitle), jsonObject.getString(categoryImage), productModelList));
                    categoryAdapter = new CategoryAdapter(getContext(), categoryModelList);
                }

                binding.rvHome.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                binding.rvHome.setAdapter(categoryAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {

        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }

}