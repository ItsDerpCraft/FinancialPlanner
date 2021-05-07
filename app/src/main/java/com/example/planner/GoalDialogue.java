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
import com.example.planner.data.IncomePref;
import com.example.planner.model.All;
import com.example.planner.model.Goal;
import com.example.planner.model.Income;

import java.util.ArrayList;
import java.util.List;

public class GoalDialogue extends DialogFragment {
    private static final String TAG = "GoalDialogue";
    private String balance;
    private EditText inName, inAmount, inDot;
    private List<Goal> goalList;
    private AppCompatButton save;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.add_goal_view,container,false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        inName = view.findViewById(R.id.input_goal);
        inAmount = view.findViewById(R.id.input_go_price);
        inDot = view.findViewById(R.id.input_go_dot);
        goalList = GoalPref.readGoalFromPref(getActivity());
        balance = "" + BalancePref.readBalanceFromPref(getActivity());
        save = view.findViewById(R.id.save_goal);
        if(goalList == null){
            goalList = new ArrayList<>();
        }
        if(balance == null){
            balance = "0.0";
        }


        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d(TAG,"closing dialogue");
                String name = inName.getText().toString();
                String amount = inAmount.getText().toString();
                String dot = inDot.getText().toString();
                if(dot.equals("")){
                    dot = "00";
                }
                if(!name.equals("")&&!amount.equals("")){
                    goalList.add(0, new Goal(name,amount,balance));
                    GoalPref.writeGoalInPref(getContext(),goalList);
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
