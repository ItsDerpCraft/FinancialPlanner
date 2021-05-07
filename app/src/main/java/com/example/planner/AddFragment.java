package com.example.planner;

import android.content.Intent;
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

import com.example.planner.adapter.PurchaseAdapter;
import com.example.planner.model.Purchases;

import java.util.ArrayList;
import java.util.List;

public class AddFragment extends Fragment {
    private static final String TAG = "Add Fragment";
    private AppCompatButton b,p,g,i;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add,container,false);
        p = (AppCompatButton) view.findViewById(R.id.add_purchase_card);
        b = (AppCompatButton) view.findViewById(R.id.edit_balance_card);
        g = (AppCompatButton) view.findViewById(R.id.add_goal_card);
        i = (AppCompatButton) view.findViewById(R.id.add_income_card);
        p.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View p){
                Log.d(TAG, "opening dialogue");

                PurchaseDialogue dialogue = new PurchaseDialogue();
                dialogue.show(getFragmentManager(),"Add Purchase Dialogue");

            }
        });
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View b){
                Log.d(TAG, "opening dialogue");

                BalanceDialogue dialogue = new BalanceDialogue();
                dialogue.show(getFragmentManager(),"Edit Balance Dialogue");

            }
        });
        g.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View p){
                Log.d(TAG, "opening dialogue");

                GoalDialogue dialogue = new GoalDialogue();
                dialogue.show(getFragmentManager(),"Add Goal Dialogue");

            }
        });
        i.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View i){
                Log.d(TAG, "opening dialogue");

                IncomeDialogue dialogue = new IncomeDialogue();
                dialogue.show(getFragmentManager(),"Add Income Dialogue");

            }
        });

        return view;
    }


}
