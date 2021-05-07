package com.example.planner;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatToggleButton;
import androidx.fragment.app.DialogFragment;

import com.example.planner.data.AllPref;
import com.example.planner.data.BalancePref;
import com.example.planner.data.GoalPref;
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

public class IncomeDialogue extends DialogFragment {
    private static final String TAG = "IncomeDialogue";
    private EditText inName, inAmount, inDate, inMonth, inYear,inPoint;
    AppCompatButton save, weekly, monthly, annual, close;
    private List<Income> incomeList;
    private List<All> allList;
    private List<Goal> goalList;
    private String type;
    private Double balance;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.add_income_view,container,false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        inName = view.findViewById(R.id.input_income);
        inAmount = view.findViewById(R.id.input_in_amount);
        inDate = view.findViewById(R.id.input_in_date);
        inMonth = view.findViewById(R.id.input_in_month);
        inYear = view.findViewById(R.id.input_in_year);
        save = view.findViewById(R.id.save_income);
        inPoint = view.findViewById(R.id.input_in_dot);
        weekly = view.findViewById(R.id.income_weekly);
        monthly = view.findViewById(R.id.income_monthly);
        annual = view.findViewById(R.id.income_annual);
        allList = AllPref.readAllFromPref(getActivity());
        goalList = GoalPref.readGoalFromPref(getActivity());
        incomeList = IncomePref.readIncomeFromPref(getActivity());
        balance = BalancePref.readBalanceFromPref(getActivity());
        close = view.findViewById(R.id.close_income);
        type = "Monthly";
        if(balance == null){
            balance = 0.0;
        }
        if(incomeList == null){
            incomeList = new ArrayList<>();
        }
        if(allList == null){
            allList = new ArrayList<>();
        }

        weekly.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                type = "Weekly";
            }
        });
        monthly.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                type = "Monthly";
            }
        });
        annual.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                type = "Annually";
            }
        });

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
                if(point.equals("")){
                    point = "00";
                }
                if(!name.equals("")&&!amount.equals("")&&!date.equals("")&&!month.equals("")&&!year.equals("")){
                    balance += Double.parseDouble(amount + "." + point);
                    if(goalList != null){
                        for(int i = 0; i < goalList.size(); i++){
                            goalList.get(i).setBal("$"+balance);
                        }
                        GoalPref.writeGoalInPref(getContext(),goalList);
                    }
                    incomeList.add(0, new Income(name, date + "/" + month + "/" + year, amount + "." + point, type));
                    allList.add(0, new All(name, date + "/" + month + "/" + year, "+$" + amount + "." + point,"income"));
                    AllPref.writeAllInPref(getContext(),allList);
                    IncomePref.writeIncomeInPref(getContext(),incomeList);
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
