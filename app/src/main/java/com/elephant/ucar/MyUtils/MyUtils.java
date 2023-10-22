package com.elephant.ucar.MyUtils;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.elephant.ucar.Models.ErrorModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Locale;

/**
 * Created by aswany on 3/3/19.
 */

public class MyUtils {

    public static String CompanyID;
    public static String CarID;
    public static String TypeID;
    public static String WorkshopID;


    public static void handleError(Context context, String errorRes, int errorStatusCode) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        ErrorModel error = gson.fromJson(errorRes, ErrorModel.class);


        Toast.makeText(context, error.getMsg(), Toast.LENGTH_SHORT).show();


    }


    public static Boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();

    }

    public static void setLocale(Context context) {

        Locale locale = new Locale("ar");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config,
                context.getResources().getDisplayMetrics());


    }
}