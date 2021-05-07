package com.example.planner.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planner.R;
import com.example.planner.model.Purchases;
import com.example.planner.model.Subscriptions;

import java.util.List;


public class SubscriptionsAdapter extends RecyclerView.Adapter<SubscriptionsAdapter.SubscriptionsViewHolder> {

    List<Subscriptions> subscriptionsList;

    public SubscriptionsAdapter(List<Subscriptions> purchaseList) {
        this.subscriptionsList = purchaseList;
    }

    @NonNull
    @Override
    public SubscriptionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subscription_view, parent, false);
        return new SubscriptionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubscriptionsViewHolder holder, int position) {
        holder.subscriptionName.setText(subscriptionsList.get(position).getSubName());
        holder.subscriptionDate.setText(subscriptionsList.get(position).getSubDate());
        holder.subscriptionAmount.setText(subscriptionsList.get(position).getSubAmount());

    }

    @Override
    public int getItemCount() {
        return subscriptionsList.size();
    }


    public static final class SubscriptionsViewHolder extends RecyclerView.ViewHolder{
        TextView subscriptionName,subscriptionDate,subscriptionAmount;
        public SubscriptionsViewHolder(@NonNull View itemView) {
            super(itemView);
            subscriptionName = itemView.findViewById(R.id.subscriptionName);
            subscriptionDate = itemView.findViewById(R.id.subscriptionDate);
            subscriptionAmount = itemView.findViewById(R.id.subscriptionAmount);

        }
    }

}

