package com.example.planner;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planner.adapter.AllAdapter;
import com.example.planner.adapter.IncomeAdapter;
import com.example.planner.adapter.PurchaseAdapter;
import com.example.planner.adapter.SubscriptionsAdapter;
import com.example.planner.data.AllPref;
import com.example.planner.data.IncomePref;
import com.example.planner.data.PurchasePref;
import com.example.planner.data.SubscriptionPref;
import com.example.planner.model.All;
import com.example.planner.model.Income;
import com.example.planner.model.Purchases;
import com.example.planner.model.Subscriptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {
    private AppCompatButton all, subscription, purchase, income;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,container,false);
        all = view.findViewById(R.id.all_toggle);
        subscription = view.findViewById(R.id.subscription_toggle);
        purchase = view.findViewById(R.id.payment_toggle);
        income = view.findViewById(R.id.income_toggle);
        List<All> allList = AllPref.readAllFromPref(getActivity());
        if(allList == null){
            allList = new ArrayList<>();
        }
        RecyclerView allRecycler = (RecyclerView) view.findViewById(R.id.purchaseRecycler);
        AllAdapter allAdapter = new AllAdapter(allList);
        allRecycler.setAdapter(allAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        allRecycler.setLayoutManager(layoutManager);

        all.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View all){
                List<All> allList = AllPref.readAllFromPref(getActivity());
                if(allList == null){
                    allList = new ArrayList<>();
                }
                RecyclerView allRecycler = (RecyclerView) view.findViewById(R.id.purchaseRecycler);
                AllAdapter allAdapter = new AllAdapter(allList);
                allRecycler.setAdapter(allAdapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                allRecycler.setLayoutManager(layoutManager);

            }
        });
        purchase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View purchase){
                List<Purchases> purchaseList = PurchasePref.readPurchasesFromPref(getActivity());
                if(purchaseList == null){
                    purchaseList = new ArrayList<>();
                }
                RecyclerView purchaseRecycler = (RecyclerView) view.findViewById(R.id.purchaseRecycler);
                PurchaseAdapter purchaseAdapter = new PurchaseAdapter(purchaseList);
                purchaseRecycler.setAdapter(purchaseAdapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                purchaseRecycler.setLayoutManager(layoutManager);
            }
        });
        subscription.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View subscription){
                List<Subscriptions> subscriptionsList = SubscriptionPref.readSubscriptionFromPref(getActivity());
                if(subscriptionsList == null){
                    subscriptionsList = new ArrayList<>();
                }
                RecyclerView subscriptionRecycler = (RecyclerView) view.findViewById(R.id.purchaseRecycler);
                SubscriptionsAdapter subscriptionsAdapter = new SubscriptionsAdapter(subscriptionsList);
                subscriptionRecycler.setAdapter(subscriptionsAdapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                subscriptionRecycler.setLayoutManager(layoutManager);
            }
        });
        income.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View income){
                List<Income> incomesList = IncomePref.readIncomeFromPref(getActivity());
                if(incomesList == null){
                    incomesList = new ArrayList<>();
                }
                RecyclerView incomeRecycler = (RecyclerView) view.findViewById(R.id.purchaseRecycler);
                IncomeAdapter incomeAdapter = new IncomeAdapter(incomesList);
                incomeRecycler.setAdapter(incomeAdapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                incomeRecycler.setLayoutManager(layoutManager);
            }
        });




        return view;
    }


}
