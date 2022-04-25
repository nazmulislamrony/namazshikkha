package com.voltagelab.namazshikkhaapps;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Helper {
    Context context;
    public String PREF_NAME = "prefs_helper";
    public static SharedPreferences pref;
    SharedPreferences.Editor editor;
    public static final String THEMESETS = "themeset";
    public static final String DEFAULTTHEMESETS = "light";
    public static final int RequestPermissionCode = 1;
    private static final char[] banglaDigits = {'০', '১', '২', '৩', '৪', '৫', '৬', '৭', '৮', '৯'};
    private static final char[] englishDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};


    public Helper(Context context) {
        this.context = context;
        this.pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void setSessionInt(String str, int i) {
        editor.putInt(str, i);
        editor.apply();
        editor.commit();
    }

    public int getSessionInt(String str, int i) {
        return pref.getInt(str, i);
    }

    public void setSessionString(String str, String name) {
        editor.putString(str, name);
        editor.apply();
        editor.commit();
    }

    public String getSessionString(String str, String name) {
        return pref.getString(str, name);
    }

    public void setSessionBoolean(String str, boolean isCheck) {
        editor.putBoolean(str, isCheck);
        editor.apply();
        editor.commit();
    }

    public boolean getSessionBoolean(String str, boolean isCheck) {
        return pref.getBoolean(str, isCheck);
    }


    public static final String getDigitBanglaFromEnglish(String number) {
        if (number == null)
            return new String("");
        StringBuilder builder = new StringBuilder();
        try {
            for (int i = 0; i < number.length(); i++) {
                if (Character.isDigit(number.charAt(i))) {
                    if (((int) (number.charAt(i)) - 48) <= 9) {
                        builder.append(banglaDigits[(int) (number.charAt(i)) - 48]);
                    } else {
                        builder.append(number.charAt(i));
                    }
                } else {
                    builder.append(number.charAt(i));
                }
            }
        } catch (Exception e) {
            //logger.debug("getDigitBanglaFromEnglish: ",e);
            return new String("");
        }
        return builder.toString();
    }

    public void themeChange() {
       String name =  (getSessionString(THEMESETS,DEFAULTTHEMESETS));
       if (name.equals("light")) {
           Log.d("check_in_dat","if");
           AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

       } else if (name.equals("dark")){
           Log.d("check_in_dat","else if");
           AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

       } else {
           Log.d("check_in_dat","else ");
           AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

       }
    }

    public void backButtonPressed( Activity activity) {
        ImageView backArrow = activity.findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
    }

    public void requestPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }

    public boolean checkPermission(Context context) {
        int result = ContextCompat.checkSelfPermission(context,
                WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED ;
    }





}
