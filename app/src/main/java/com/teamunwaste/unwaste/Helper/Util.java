package com.teamunwaste.unwaste.Helper;

import android.app.Activity;
import android.view.View;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;

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


}
