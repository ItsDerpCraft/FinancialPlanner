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
import androidx.fragment.app.DialogFragment;

import com.example.planner.data.AllPref;
import com.example.planner.data.BalancePref;
import com.example.planner.data.GoalPref;
import com.example.planner.model.All;
import com.example.planner.model.Goal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BalanceDialogue extends DialogFragment {
    private static final String TAG = "GoalDialogue";
    private Double balance;
    private EditText inOut, inIn, outDot, inDot;
    private List<All> allList;
    private List<Goal> goalList;
    private AppCompatButton save, close;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.add_balance_view,container,false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        inOut = view.findViewById(R.id.input_remove);
        inIn = view.findViewById(R.id.input_add);
        outDot = view.findViewById(R.id.input_remove_dot);
        inDot = view.findViewById(R.id.input_deposit_dot);
        allList = AllPref.readAllFromPref(getActivity());
        balance = BalancePref.readBalanceFromPref(getActivity());
        save = view.findViewById(R.id.save_balance);
        close = view.findViewById(R.id.close_balance);
        goalList = GoalPref.readGoalFromPref(getActivity());
        if(allList == null){
            allList = new ArrayList<>();
        }
        if(balance == null){
            balance = 0.0;
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
                String out = inOut.getText().toString();
                String in = inIn.getText().toString();
                String iDot = inDot.getText().toString();
                String oDot = outDot.getText().toString();
                String date = getDate();
                if(iDot.equals("")){
                    iDot = "00";
                }
                if(oDot.equals("")){
                    oDot = "00";
                }
                if(!out.equals("") || !in.equals("")){
                    if(out.equals("")){
                        out = "0";
                    }
                    if(in.equals("")){
                        in = "0";
                    }
                    String strIn = in + "." + iDot;
                    String strOut = out + "." + oDot;
                    Double amountChange = Double.parseDouble(strIn) - Double.parseDouble(strOut);
                    Double amount = Math.round(amountChange * 100.0) / 100.0;
                    balance += amount;
                    if(goalList != null){
                        for(int i = 0; i < goalList.size(); i++){
                            goalList.get(i).setBal("$"+balance);
                        }
                        GoalPref.writeGoalInPref(getContext(),goalList);
                    }
                    if(amount == 0){
                        getDialog().dismiss();
                    }
                    if(amount > 0){
                        allList.add(0,new All("Balance Change",date,"+$" + amount,"balance"));
                    }
                    else{
                        amount = Math.abs(amount);
                        allList.add(0,new All("Balance Change",date,"-$" + amount, "balance"));
                    }
                    BalancePref.writeBalanceInPref(getContext(),balance);
                    AllPref.writeAllInPref(getContext(),allList);

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
    private String getDate() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return String.valueOf(dateFormat.format(date));
    }
}
