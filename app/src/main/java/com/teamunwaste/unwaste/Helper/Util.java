package com.teamunwaste.unwaste.Helper;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.teamunwaste.unwaste.R;

public class Util {

    // Method to Convert MongoDB Object to String
    public static String convertMongodbObjToString(String id) {
        return id.substring(id.indexOf('"') + 8, id.lastIndexOf('"'));
    }


    public static void setStatusBar(Activity activity, int color) {

        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity, color));
    }

    // Show Custom Dialog
    public static void showCustomDialog(Dialog dialog, int layout) {
        dialog.setContentView(layout);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    public static boolean checkInternet(Context context) {

        Dialog internetDialog = new Dialog(context);
        showCustomDialog(internetDialog, R.layout.dialog_check_internet);
        ImageView ivImage = internetDialog.findViewById(R.id.ivImage);
        Glide.with(context).load(R.drawable.ic_no_internet).into(ivImage);
        internetDialog.findViewById(R.id.btnTryAgain).setOnClickListener(v -> {
            if (checkInternet(context)) {
                internetDialog.dismiss();
            }
        });

        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            return connected = true;
        } else {
            if (!internetDialog.isShowing()) {
                internetDialog.show();
            }
            return connected = false;
        }
    }

    public static void callNow(Context context, String phone) {

        Intent callIntent = new Intent(Intent.ACTION_VIEW);
        callIntent.setData(Uri.parse("tel:" + phone));
        context.startActivity(callIntent);
    }

}
