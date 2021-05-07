package com.example.planner.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.planner.model.Purchases;
import com.example.planner.model.Subscriptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionPref {
    private static final String LIST_KEY = "list_key104";

    public static void writeSubscriptionInPref(Context context, List<Subscriptions> list) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(list);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(LIST_KEY, jsonString);
        editor.apply();
    }

    public static List<Subscriptions> readSubscriptionFromPref(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonString = pref.getString(LIST_KEY, "");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Subscriptions>>() {}.getType();
        List<Subscriptions> list = gson.fromJson(jsonString, type);
        return list;
    }


}
