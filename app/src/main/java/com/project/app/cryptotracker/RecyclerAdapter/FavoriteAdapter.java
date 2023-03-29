package com.project.app.cryptotracker.RecyclerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.app.cryptotracker.POJO.CoinListing;
import com.project.app.cryptotracker.R;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.CustomViewHolder> {
    ArrayList<CoinListing> coinListings;
    public FavoriteAdapter() {

    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_layout_list_favorite,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{
        ImageView favCoinLogo;
        TextView favCoinName;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            favCoinLogo = itemView.findViewById(R.id.favCoinLogo);
            favCoinName = itemView.findViewById(R.id.favCoinName);
        }
    }
}
