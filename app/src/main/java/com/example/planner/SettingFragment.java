package com.example.planner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.planner.data.AllPref;
import com.example.planner.data.BalancePref;
import com.example.planner.data.GoalPref;
import com.example.planner.data.HighPref;
import com.example.planner.data.IncomePref;
import com.example.planner.data.PurchasePref;
import com.example.planner.data.SubscriptionPref;
import com.example.planner.model.All;
import com.example.planner.model.Goal;
import com.example.planner.model.Income;
import com.example.planner.model.Purchases;
import com.example.planner.model.Subscriptions;

import java.util.ArrayList;
import java.util.List;

public class SettingFragment extends Fragment {
    AppCompatButton wipe, purchase, in, goal, bal,pur,sub;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings,container,false);
        wipe = view.findViewById(R.id.wipeData);
        purchase = view.findViewById(R.id.wipePurchaseData);
        in = view.findViewById(R.id.wipeIncome);
        goal = view.findViewById(R.id.wipeGoals);
        bal = view.findViewById(R.id.resetBalance);
        pur = view.findViewById(R.id.wipePur);
        sub = view.findViewById(R.id.wipeSub);


        wipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Subscriptions> subscriptionsList = new ArrayList<>();
                List<All> allList = new ArrayList<>();
                List<Purchases> purchasesList = new ArrayList<>();
                List<Goal> goalList = new ArrayList<>();
                List<Income> incomeList = new ArrayList<>();
                AllPref.writeAllInPref(getContext(),allList);
                PurchasePref.writePurchasesInPref(getContext(),purchasesList);
                SubscriptionPref.writeSubscriptionInPref(getContext(),subscriptionsList);
                GoalPref.writeGoalInPref(getContext(),goalList);
                IncomePref.writeIncomeInPref(getContext(),incomeList);
                BalancePref.writeBalanceInPref(getContext(),0.0);
                HighPref.writeBalanceInPref(getContext(),0.0);
            }
        });
        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Purchases> purchasesList = new ArrayList<>();
                List<Subscriptions> subscriptionsList = new ArrayList<>();
                List<All> allList = AllPref.readAllFromPref(getActivity());
                for(int i = 0; i<allList.size(); i++){
                    if(allList.get(i).getType().equals("purchase")||allList.get(i).getType().equals("subscription")){
                        allList.remove(i);
                        i--;
                    }
                }
                SubscriptionPref.writeSubscriptionInPref(getContext(),subscriptionsList);
                PurchasePref.writePurchasesInPref(getContext(),purchasesList);
                AllPref.writeAllInPref(getContext(),allList);
            }
        });
        pur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Purchases> purchasesList = new ArrayList<>();
                List<All> allList = AllPref.readAllFromPref(getActivity());
                for(int i = 0; i<allList.size(); i++){
                    if(allList.get(i).getType().equals("purchase")){
                        allList.remove(i);
                        i--;
                    }
                }
                PurchasePref.writePurchasesInPref(getContext(),purchasesList);
                AllPref.writeAllInPref(getContext(),allList);
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Subscriptions> subscriptionsList = new ArrayList<>();
                List<All> allList = AllPref.readAllFromPref(getActivity());
                for(int i = 0; i<allList.size(); i++){
                    if(allList.get(i).getType().equals("subscription")){
                        allList.remove(i);
                        i--;
                    }
                }
                SubscriptionPref.writeSubscriptionInPref(getContext(),subscriptionsList);
                AllPref.writeAllInPref(getContext(),allList);
            }
        });

        in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<All> allList = AllPref.readAllFromPref(getActivity());
                for(int i = 0; i<allList.size(); i++){
                    if(allList.get(i).getType().equals("income")){
                        allList.remove(i);
                        i--;
                    }
                }
                List<Income> incomeList = new ArrayList<>();
                IncomePref.writeIncomeInPref(getContext(),incomeList);
                AllPref.writeAllInPref(getContext(),allList);
            }
        });
        goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Goal> goalList = new ArrayList<>();
                GoalPref.writeGoalInPref(getContext(),goalList);
            }
        });
        bal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BalancePref.writeBalanceInPref(getContext(),0.0);
            }
        });

        return view;
    }
}
