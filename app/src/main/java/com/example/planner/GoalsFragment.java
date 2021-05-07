package com.example.planner;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planner.adapter.AllAdapter;
import com.example.planner.adapter.GoalAdapter;
import com.example.planner.adapter.GoalGoalAdapter;
import com.example.planner.data.GoalPref;
import com.example.planner.model.Goal;

import java.util.ArrayList;
import java.util.List;

public class GoalsFragment extends Fragment {
    private static final String TAG = "Add Fragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_goals,container,false);
        List<Goal> goalList = GoalPref.readGoalFromPref(getActivity());
        if(goalList == null){
            goalList = new ArrayList<>();
        }
        if(goalList.size() == 0) {
            Log.d(TAG, "opening dialogue");
            EmptyGoalDialogue dialogue = new EmptyGoalDialogue();
            dialogue.show(getFragmentManager(), "Empty Goal Dialogue");
        }
        RecyclerView allRecycler = (RecyclerView) view.findViewById(R.id.goal_goal_recycler);
        GoalGoalAdapter goalAdapter = new GoalGoalAdapter(goalList);
        allRecycler.setAdapter(goalAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        allRecycler.setLayoutManager(layoutManager);

        return view;
    }
}
