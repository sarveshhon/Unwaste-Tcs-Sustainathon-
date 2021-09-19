package com.teamunwaste.unwaste.Home;

import static com.teamunwaste.unwaste.Helper.API.EXCHANGE_API;
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

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.teamunwaste.unwaste.Home.ExchangeFM.ExchangeAdapter;
import com.teamunwaste.unwaste.Home.ExchangeFM.ExchangeModel;
import com.teamunwaste.unwaste.databinding.FragmentExchangeBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ExchangeFragment extends Fragment {

    FragmentExchangeBinding binding;

    List<ExchangeModel> exchangeModelList = new ArrayList<>();
    ExchangeAdapter exchangeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentExchangeBinding.inflate(getLayoutInflater());


        loadExchangeData();


        return binding.getRoot();
    }

    private void loadExchangeData() {


        StringRequest request = new StringRequest(Request.Method.POST, EXCHANGE_API, response -> {

            try {
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

                exchangeAdapter = new ExchangeAdapter(getContext(), exchangeModelList);
                binding.rvExchange.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                binding.rvExchange.setAdapter(exchangeAdapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {

        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }

}