package com.example.planner.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.planner.model.Purchases;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BalancePref {
    private static final String LIST_KEY = "list_key101";

    public static void writeBalanceInPref(Context context, Double balance) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(balance);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(LIST_KEY, jsonString);
        editor.apply();
    }

    public static Double readBalanceFromPref(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonString = pref.getString(LIST_KEY, "");
        Gson gson = new Gson();
        Type type = new TypeToken<Double>() {}.getType();
        Double balance = gson.fromJson(jsonString, type);
        return balance;
    }


}
