package com.example.planner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planner.adapter.GoalAdapter;
import com.example.planner.adapter.GoalGoalAdapter;
import com.example.planner.data.AllPref;
import com.example.planner.data.BalancePref;
import com.example.planner.data.GoalPref;
import com.example.planner.model.All;
import com.example.planner.model.Goal;
import com.example.planner.model.Income;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment {
    private TextView balance, monthSpend,monthSpent,monthIn,monthSave;
    private Double savedBalance, averageSpent, income, saving, spent;
    private int curCount;
    private String date;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        List<Goal> goalList = GoalPref.readGoalFromPref(getActivity());
        List<All>  allList = AllPref.readAllFromPref(getActivity());
        savedBalance = BalancePref.readBalanceFromPref(getActivity());

        if(goalList == null){
            goalList = new ArrayList<>();
        }
        if(allList == null){
            allList = new ArrayList<>();
        }
        if(savedBalance == null){
            savedBalance = 0.0;
        }

        balance = view.findViewById(R.id.home_balance);
        monthSpend = view.findViewById(R.id.home_month_spend);
        monthSpent = view.findViewById(R.id.home_month_spent);
        monthIn = view.findViewById(R.id.home_month_income);
        monthSave = view.findViewById(R.id.home_month_saving);
        date = getDate();
        curCount = 0;
        income = 0.0;
        spent = 0.0;
        averageSpent = 0.0;

        for(int i = 0; i < allList.size(); i++){
            if(allList.get(i).getAllAmount().substring(0,1).equals("+") && allList.get(i).getAllDate().substring(3).equals(date.substring(3))) {
                income += Double.parseDouble(allList.get(i).getAllAmount().substring(2));
            }
            else{
                spent += Double.parseDouble(allList.get(i).getAllAmount().substring(2));
                curCount++;
            }
        }

        saving = income - spent;
        averageSpent = spent/curCount;
        averageSpent =  Math.round(averageSpent * 100.0) / 100.0;


        balance.setText("$"+ savedBalance);
        monthSpend.setText("$" + averageSpent);
        monthSpent.setText("$" + spent);
        monthIn.setText("$" + income);
        if(saving >= 0){
            monthSave.setText("+$" + saving);
        }
        else{
            saving *= -1;
            monthSave.setText("-$" + saving);
        }


        RecyclerView allRecycler = (RecyclerView) view.findViewById(R.id.goal_recycler);
        GoalAdapter goalAdapter = new GoalAdapter(goalList);
        allRecycler.setAdapter(goalAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        allRecycler.setLayoutManager(layoutManager);


        return view;
    }

    private String getDate() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return String.valueOf(dateFormat.format(date));
    }
}
