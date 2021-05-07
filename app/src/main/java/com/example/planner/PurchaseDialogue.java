package com.example.planner;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.wifi.aware.SubscribeConfig;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatToggleButton;
import androidx.fragment.app.DialogFragment;

import com.example.planner.data.AllPref;
import com.example.planner.data.BalancePref;
import com.example.planner.data.GoalPref;
import com.example.planner.data.PurchasePref;
import com.example.planner.data.SubscriptionPref;
import com.example.planner.model.All;
import com.example.planner.model.Goal;
import com.example.planner.model.Purchases;
import com.example.planner.model.Subscriptions;

import java.util.ArrayList;
import java.util.List;

public class PurchaseDialogue extends DialogFragment {
    private static final String TAG = "PurchaseDialogue";
    private EditText inName, inAmount, inDate, inMonth, inYear,inPoint;
    AppCompatToggleButton isOne;
    AppCompatButton save, close;
    private Double balance;
    private List<Purchases> purchasesList;
    private List<Subscriptions> subscriptionsList;
    private List<All> allList;
    private List<Goal> goalList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.add_purchase_view,container,false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        inName = view.findViewById(R.id.input_purchase);
        inAmount = view.findViewById(R.id.input_amount);
        inDate = view.findViewById(R.id.input_date);
        inMonth = view.findViewById(R.id.input_month);
        inYear = view.findViewById(R.id.input_year);
        save = view.findViewById(R.id.save_one_payment);
        inPoint = view.findViewById(R.id.input_dot);
        isOne = view.findViewById(R.id.purchase_type);
        close = view.findViewById(R.id.close_purchase);
        purchasesList = PurchasePref.readPurchasesFromPref(getActivity());
        subscriptionsList = SubscriptionPref.readSubscriptionFromPref(getActivity());
        allList = AllPref.readAllFromPref(getActivity());
        balance = BalancePref.readBalanceFromPref(getActivity());
        goalList = GoalPref.readGoalFromPref(getActivity());
        if(balance == null){
            balance = 0.0;
        }
        if(purchasesList == null){
            purchasesList = new ArrayList<>();
        }
        if(subscriptionsList == null){
            subscriptionsList = new ArrayList<>();
        }
        if(allList == null){
            allList = new ArrayList<>();
        }

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d(TAG,"closing dialogue");
                String name = inName.getText().toString();
                String amount = inAmount.getText().toString();
                String date = inDate.getText().toString();
                String month = inMonth.getText().toString();
                String year = inYear.getText().toString();
                String point = inPoint.getText().toString();
                Boolean isNotOneTime = isOne.isChecked();
                if(point.equals("")){
                    point = "00";
                }
                if(!name.equals("")&&!amount.equals("")&&!date.equals("")&&!month.equals("")&&!year.equals("")){
                    balance -= Double.parseDouble(amount + "." + point);
                    if(goalList != null){
                        for(int i = 0; i < goalList.size(); i++){
                            goalList.get(i).setBal("$"+balance);
                        }
                        GoalPref.writeGoalInPref(getContext(),goalList);
                    }
                    if(isNotOneTime){
                        subscriptionsList.add(0, new Subscriptions(name, date + "/" + month + "/" + year, amount + "." + point));
                        allList.add(0, new All(name, date + "/" + month + "/" + year, "-$" + amount + "." + point, "subscription"));
                        SubscriptionPref.writeSubscriptionInPref(getContext(),subscriptionsList);
                    }
                    else {
                        purchasesList.add(0, new Purchases(name, date + "/" + month + "/" + year, amount + "." + point));
                        allList.add(0, new All(name, date + "/" + month + "/" + year, "-$" + amount + "." + point,"purchase"));
                        PurchasePref.writePurchasesInPref(getContext(), purchasesList);
                    }
                    AllPref.writeAllInPref(getContext(),allList);
                    BalancePref.writeBalanceInPref(getContext(),balance);
                }

                getDialog().dismiss();
            }

        });
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }
}
