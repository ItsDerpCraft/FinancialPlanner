package com.example.planner.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planner.R;
import com.example.planner.model.Income;
import com.example.planner.model.Subscriptions;

import java.util.List;


public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.IncomeViewHolder> {

    List<Income> subscriptionsList;

    public IncomeAdapter(List<Income> purchaseList) {
        this.subscriptionsList = purchaseList;
    }

    @NonNull
    @Override
    public IncomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.income_view, parent, false);
        return new IncomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeViewHolder holder, int position) {
        holder.subscriptionName.setText(subscriptionsList.get(position).getInName());
        holder.subscriptionDate.setText(subscriptionsList.get(position).getInDate());
        holder.subscriptionAmount.setText(subscriptionsList.get(position).getInAmount());
        holder.incomeType.setText(subscriptionsList.get(position).getType());

    }

    @Override
    public int getItemCount() {
        return subscriptionsList.size();
    }


    public static final class IncomeViewHolder extends RecyclerView.ViewHolder{
        TextView subscriptionName,subscriptionDate,subscriptionAmount, incomeType;
        public IncomeViewHolder(@NonNull View itemView) {
            super(itemView);
            subscriptionName = itemView.findViewById(R.id.incomeName);
            subscriptionDate = itemView.findViewById(R.id.incomeDate);
            subscriptionAmount = itemView.findViewById(R.id.incomeAmount);
            incomeType = itemView.findViewById(R.id.incomeType);

        }
    }

}

