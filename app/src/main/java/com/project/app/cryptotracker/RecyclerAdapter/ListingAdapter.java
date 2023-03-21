package com.project.app.cryptotracker.RecyclerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.app.cryptotracker.R;

public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.CustomViewHolder> {
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_layout_listing,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        protected TextView coinName;
        protected TextView coinSymbol;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.coinName = itemView.findViewById(R.id.coinName);
            this.coinSymbol = itemView.findViewById(R.id.coinSymbol);
        }
    }
}
