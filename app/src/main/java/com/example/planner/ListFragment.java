package com.example.planner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planner.adapter.PurchaseAdapter;
import com.example.planner.model.Purchases;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,container,false);

        List<Purchases> purchaseList = new ArrayList<>();
        RecyclerView purchaseRecycler = (RecyclerView) view.findViewById(R.id.purchaseRecycler);
        PurchaseAdapter purchaseAdapter = new PurchaseAdapter(purchaseList);
        purchaseRecycler.setAdapter(purchaseAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        purchaseRecycler.setLayoutManager(layoutManager);


        return view;
    }


}
