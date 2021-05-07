package com.example.planner.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planner.R;
import com.example.planner.model.Goal;

import java.util.List;


public class GoalGoalAdapter extends RecyclerView.Adapter<GoalGoalAdapter.GoalViewHolder> {

    List<Goal> purchaseList;

    public GoalGoalAdapter(List<Goal> purchaseList) {
        this.purchaseList = purchaseList;
    }

    @NonNull
    @Override
    public GoalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goal_goal_view, parent, false);
        return new GoalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoalViewHolder holder, int position) {
        holder.purchaseName.setText(purchaseList.get(position).getGoName());
        holder.purchaseAmount.setText(purchaseList.get(position).getGoAmount());
        holder.bal.setText(purchaseList.get(position).getBal());

    }

    @Override
    public int getItemCount() {
        return purchaseList.size();
    }


    public static final class GoalViewHolder extends RecyclerView.ViewHolder{
        TextView purchaseName,purchaseAmount,bal;
        public GoalViewHolder(@NonNull View itemView) {
            super(itemView);
            purchaseName = itemView.findViewById(R.id.goal_name);
            purchaseAmount = itemView.findViewById(R.id.goalCost);
            bal = itemView.findViewById(R.id.goalBal);

        }
    }

}

