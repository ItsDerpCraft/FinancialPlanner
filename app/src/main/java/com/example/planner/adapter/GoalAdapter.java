package com.example.planner.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planner.R;
import com.example.planner.model.Goal;
import com.example.planner.model.Purchases;

import java.util.List;


public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.GoalViewHolder> {

    List<Goal> purchaseList;

    public GoalAdapter(List<Goal> purchaseList) {
        this.purchaseList = purchaseList;
    }

    @NonNull
    @Override
    public GoalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goal_view, parent, false);
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
            purchaseName = itemView.findViewById(R.id.goalName);
            purchaseAmount = itemView.findViewById(R.id.goalCost);
            bal = itemView.findViewById(R.id.goalBal);

        }
    }

}

