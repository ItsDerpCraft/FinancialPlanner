package com.example.planner.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planner.R;
import com.example.planner.model.All;

import java.util.List;


public class AllAdapter extends RecyclerView.Adapter<AllAdapter.SubscriptionsViewHolder> {

    List<All> allList;

    public AllAdapter(List<All> allList) {
        this.allList = allList;
    }

    @NonNull
    @Override
    public SubscriptionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.purchase_view, parent, false);
        return new SubscriptionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubscriptionsViewHolder holder, int position) {
        holder.subscriptionName.setText(allList.get(position).getAllName());
        holder.subscriptionDate.setText(allList.get(position).getAllDate());
        holder.subscriptionAmount.setText(allList.get(position).getAllAmount());

    }

    @Override
    public int getItemCount() {
        return allList.size();
    }


    public static final class SubscriptionsViewHolder extends RecyclerView.ViewHolder{
        TextView subscriptionName,subscriptionDate,subscriptionAmount;
        public SubscriptionsViewHolder(@NonNull View itemView) {
            super(itemView);
            subscriptionName = itemView.findViewById(R.id.purchaseName);
            subscriptionDate = itemView.findViewById(R.id.purchaseDate);
            subscriptionAmount = itemView.findViewById(R.id.purchaseAmount);

        }
    }

}

