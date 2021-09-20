package com.teamunwaste.unwaste;

import static com.teamunwaste.unwaste.Helper.API.PRODUCT_API;
import static com.teamunwaste.unwaste.Helper.PARAM.productDescription;
import static com.teamunwaste.unwaste.Helper.PARAM.productId;
import static com.teamunwaste.unwaste.Helper.PARAM.productImage;
import static com.teamunwaste.unwaste.Helper.PARAM.productPrice;
import static com.teamunwaste.unwaste.Helper.PARAM.productTitle;
import static com.teamunwaste.unwaste.Helper.PARAM.userEmail;
import static com.teamunwaste.unwaste.Helper.PARAM.userName;
import static com.teamunwaste.unwaste.Helper.PARAM.userPhone;
import static com.teamunwaste.unwaste.Helper.Util.callNow;
import static com.teamunwaste.unwaste.Helper.Util.setStatusBar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.teamunwaste.unwaste.databinding.ActivityProductOverviewBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProductOverviewActivity extends AppCompatActivity {

    ActivityProductOverviewBinding binding;
    FirebaseAuth mAuth;

    String title, description, price, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductOverviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setStatusBar(this, R.color.white);

        mAuth = FirebaseAuth.getInstance();

        loadProduct(getIntent().getStringExtra(productId));

        binding.btnEmail.setOnClickListener(v -> {

            String data = "Product:\n" + title + "\n" +
                    description + "\n" +
                    price + "\n\n" +
                    "Order By:\n" +
                    mAuth.getCurrentUser().getDisplayName() + "\n" +
                    mAuth.getCurrentUser().getEmail() + "\n";

            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{getIntent().getStringExtra(userEmail)});
            email.setPackage("com.google.android.gm");
            email.putExtra(Intent.EXTRA_SUBJECT, "Order Request by " + mAuth.getCurrentUser().getDisplayName());
            email.putExtra(Intent.EXTRA_TEXT, data);

            //need this to prompts email client only
            email.setType("plain/text");

            startActivity(Intent.createChooser(email, "Choose an Email client :"));

        });

        binding.btnPhone.setOnClickListener(v -> {

            callNow(this,getIntent().getStringExtra(userPhone));

        });

    }


    private void loadProduct(String id) {

        StringRequest request = new StringRequest(Request.Method.POST, PRODUCT_API, response -> {

            try {
                JSONArray jsonArray = new JSONArray(response);
                JSONObject jsonObject = jsonArray.getJSONObject(0);

                title = jsonObject.getString(productTitle);
                description = jsonObject.getString(productDescription);
                price = getString(R.string.rupee) + jsonObject.getString(productPrice);
                email = jsonObject.getString(userEmail);
                Log.d("Email",email);

                binding.tvProductTitle.setText(title);
                binding.tvProductDescription.setText(description);
                binding.tvProductPrice.setText(price);

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