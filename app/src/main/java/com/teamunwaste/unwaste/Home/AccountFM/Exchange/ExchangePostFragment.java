package com.teamunwaste.unwaste.Home.AccountFM.Exchange;

import static android.app.Activity.RESULT_OK;
import static com.teamunwaste.unwaste.Helper.API.EXCHANGE_POST_API;
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

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.teamunwaste.unwaste.R;
import com.teamunwaste.unwaste.databinding.FragmentExchangePostBinding;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ExchangePostFragment extends Fragment {

    FragmentExchangePostBinding binding;

    FirebaseAuth mAuth;


    // Uri indicates, where the image will be picked from
    private Uri filePath;

    // request code
    private final int PICK_IMAGE_REQUEST = 22;

    // instance for firebase storage and StorageReference
    FirebaseStorage storage;
    StorageReference storageReference;


    private String[] acategory = {"Select Category", "Plastic", "Glass", "Paper", "Metal", "E Waste", "Organic Waste"};
    private String[] acategoryId = {"Select Category", "614729ff977921dc588fe626", "61472b2f977921dc588fe627", "61472be3977921dc588fe628", "6148209a8d97c651ce80e7f5", "614820ae8d97c651ce80e7f6", "614820c68d97c651ce80e7f7"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentExchangePostBinding.inflate(getLayoutInflater());


        mAuth = FirebaseAuth.getInstance();

        // get the Firebase  storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        binding.btnSelect.setOnClickListener(v -> {
            SelectImage();
        });


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.item_list, R.id.
                text1, acategory);
        binding.spinnerCategory.setAdapter(arrayAdapter);


        binding.btnSubmit.setOnClickListener(v -> {

            String etPT = binding.etProductTitle.getText().toString();
            String etPD = binding.etProductDescription.getText().toString();
            String etPP = binding.etProductPrice.getText().toString();
            String etPN = binding.etUserPhone.getText().toString();
            String etPA = binding.etUserAddress.getText().toString();

            if (!etPT.equals("") && !etPD.equals("") && !etPP.equals("") && !etPN.equals("") && !etPA.equals("")) {
                if (binding.spinnerCategory.getSelectedItemPosition() != 0) {
                    String cid = acategory[binding.spinnerCategory.getSelectedItemPosition()];
                    uploadImage(cid);
                } else {
                    Toast.makeText(getContext(), "Select Category", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "Complete Fields", Toast.LENGTH_SHORT).show();
            }


        });

        return binding.getRoot();
    }

    // Select Image method
    private void SelectImage() {

        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }

    // Override onActivityResult method
    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode,
                                 Intent data) {

        super.onActivityResult(requestCode,
                resultCode,
                data);

        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContext().getContentResolver(),
                                filePath);
                binding.ivPreview.setImageBitmap(bitmap);
            } catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }

    // UploadImage method
    private void uploadImage(String cid) {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(getContext());
            progressDialog.setTitle("Uploading...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref
                    = storageReference
                    .child(
                            "images/exchange/"
                                    + UUID.randomUUID().toString());

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot) {

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {

                                            sendData(
                                                    binding.etProductTitle.getText().toString(),
                                                    uri.toString(),
                                                    cid,
                                                    binding.etProductPrice.getText().toString(),
                                                    "MyAddress",
                                                    binding.etProductDescription.getText().toString(),
                                                    mAuth.getCurrentUser().getDisplayName(),
                                                    binding.etUserPhone.getText().toString(),
                                                    mAuth.getCurrentUser().getEmail(),
                                                    getDateTime()
                                            );


                                            binding.etProductTitle.setText("");
                                            binding.etProductDescription.setText("");
                                            binding.etProductPrice.setText("");
                                            binding.etUserPhone.setText("");
                                            binding.etUserAddress.setText("");
                                            filePath = null;

                                        }
                                    });


                                    progressDialog.dismiss();
                                    Toast.makeText(getContext(), "Image Uploaded!!", Toast.LENGTH_SHORT).show();

                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int) progress + "%");
                                }
                            })
                    .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                        }
                    }).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    // Continue with the task to get the download URL
                    return ref.getDownloadUrl();
                }
            });
        } else {
            Toast.makeText(getContext(), "Select Image", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendData(String _exchangeTitle, String _exchangeImage, String _exchangeType, String _exchangeFee, String _userAddress, String _exchangeDescription, String _userName, String _userPhone, String _userEmail, String _postDate) {


        StringRequest request = new StringRequest(Request.Method.POST, EXCHANGE_POST_API, response -> {


        }, error -> {

        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() {

                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(exchangeTitle, _exchangeTitle);
                hashMap.put(exchangeImage, _exchangeImage);
                hashMap.put(exchangeType, _exchangeType);
                hashMap.put(exchangeFee, _exchangeFee);
                hashMap.put(userName, _userName);
                hashMap.put(userEmail, _userEmail);
                hashMap.put(userPhone, _userPhone);
                hashMap.put(userAddress, _userAddress);
                hashMap.put(exchangeDescription, _exchangeDescription);
                hashMap.put(postDate, _postDate);

                return hashMap;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);

    }

    private String getDateTime() {
        SimpleDateFormat timeStampFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date myDate = new Date();
        String date = timeStampFormat.format(myDate);
        return date;
    }

}