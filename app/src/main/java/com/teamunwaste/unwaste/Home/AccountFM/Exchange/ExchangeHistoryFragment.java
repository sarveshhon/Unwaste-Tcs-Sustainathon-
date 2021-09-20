package com.teamunwaste.unwaste.Home.AccountFM.Exchange;

import static com.teamunwaste.unwaste.Helper.API.EXCHANGE_HISTORY_API;
import static com.teamunwaste.unwaste.Helper.API.SELL_PRODUCT_HISTORY_API;
import static com.teamunwaste.unwaste.Helper.PARAM._id;
import static com.teamunwaste.unwaste.Helper.PARAM.exchangeDescription;
import static com.teamunwaste.unwaste.Helper.PARAM.exchangeFee;
import static com.teamunwaste.unwaste.Helper.PARAM.exchangeImage;
import static com.teamunwaste.unwaste.Helper.PARAM.exchangeTitle;
import static com.teamunwaste.unwaste.Helper.PARAM.exchangeType;
import static com.teamunwaste.unwaste.Helper.PARAM.postDate;
import static com.teamunwaste.unwaste.Helper.PARAM.userAddress;
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
import com.teamunwaste.unwaste.Home.ExchangeFM.ExchangeAdapter;
import com.teamunwaste.unwaste.Home.ExchangeFM.ExchangeModel;
import com.teamunwaste.unwaste.databinding.FragmentExchangeHistoryBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExchangeHistoryFragment extends Fragment {

    FragmentExchangeHistoryBinding binding;

    List<ExchangeModel> exchangeModelList = new ArrayList<>();
    ExchangeHistoryAdapter exchangeHistoryAdapter;

    FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentExchangeHistoryBinding.inflate(getLayoutInflater());


        mAuth = FirebaseAuth.getInstance();

        loadProductList(mAuth.getCurrentUser().getEmail());

        binding.swipe.setOnRefreshListener(() -> {

            loadProductList(mAuth.getCurrentUser().getEmail());
        });


        return binding.getRoot();
    }


    private void loadProductList(String mid) {

        exchangeModelList.clear();

        StringRequest request = new StringRequest(Request.Method.POST, EXCHANGE_HISTORY_API, response -> {

            try {
                if (binding.swipe.isRefreshing()) {
                    binding.swipe.setRefreshing(false);
                }
                JSONArray jsonArray = new JSONArray(response);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    exchangeModelList.add(new ExchangeModel(
                            jsonObject.getString(_id),
                            jsonObject.getString(exchangeTitle),
                            jsonObject.getString(exchangeImage),
                            jsonObject.getString(exchangeDescription),
                            jsonObject.getString(exchangeType),
                            jsonObject.getString(exchangeFee),
                            jsonObject.getString(userName),
                            jsonObject.getString(userEmail),
                            jsonObject.getString(userPhone),
                            jsonObject.getString(userAddress),
                            jsonObject.getString(postDate)
                    ));

                }

                exchangeHistoryAdapter = new ExchangeHistoryAdapter(getContext(), exchangeModelList);
                binding.rvExchangeHistory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                binding.rvExchangeHistory.setAdapter(exchangeHistoryAdapter);


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