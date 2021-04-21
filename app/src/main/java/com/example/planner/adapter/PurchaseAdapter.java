package com.example.planner.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planner.R;
import com.example.planner.model.Purchases;

import java.util.List;


public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.PurchasesViewHolder> {

    List<Purchases> purchaseList;

    public PurchaseAdapter(List<Purchases> purchaseList) {
        this.purchaseList = purchaseList;
    }

    @NonNull
    @Override
    public PurchasesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.purchase_view, parent, false);
        return new PurchasesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchasesViewHolder holder, int position) {
        holder.purchaseName.setText(purchaseList.get(position).getName());
        holder.purchaseDate.setText(purchaseList.get(position).getDate());
        holder.purchaseAmount.setText(purchaseList.get(position).getAmount());

    }

    @Override
    public int getItemCount() {
        return purchaseList.size();
    }


    public static final class PurchasesViewHolder extends RecyclerView.ViewHolder{
        TextView purchaseName,purchaseDate,purchaseAmount;
        public PurchasesViewHolder(@NonNull View itemView) {
            super(itemView);
            purchaseName = itemView.findViewById(R.id.purchaseName);
            purchaseDate = itemView.findViewById(R.id.purchaseDate);
            purchaseAmount = itemView.findViewById(R.id.purchaseAmount);

        }
    }

}

