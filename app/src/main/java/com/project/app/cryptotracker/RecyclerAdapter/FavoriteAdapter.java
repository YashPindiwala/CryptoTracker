package com.project.app.cryptotracker.RecyclerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.app.cryptotracker.Database.CryptoDatabase;
import com.project.app.cryptotracker.POJO.CryptoDetail;
import com.project.app.cryptotracker.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.CustomViewHolder> {
    ArrayList<CryptoDetail> coinListings;
    public FavoriteAdapter(Context context) {
        CryptoDatabase database = new CryptoDatabase(context);
        coinListings = database.getAllFavCoin();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_layout_list_favorite,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        CryptoDetail cryptoDetail = coinListings.get(position);
        holder.favCoinSymbol.setText(cryptoDetail.getSymbol());
        Picasso.get().load(cryptoDetail.getLogo()).into(holder.favCoinLogo);
    }

    @Override
    public int getItemCount() {
        return coinListings.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{
        ImageView favCoinLogo;
        TextView favCoinSymbol;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            favCoinLogo = itemView.findViewById(R.id.favCoinLogo);
            favCoinSymbol = itemView.findViewById(R.id.favCoinSymbol);
        }
    }
}
