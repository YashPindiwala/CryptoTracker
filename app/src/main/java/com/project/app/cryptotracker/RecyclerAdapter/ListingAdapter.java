package com.project.app.cryptotracker.RecyclerAdapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.project.app.cryptotracker.API.APIRequestQueue;
import com.project.app.cryptotracker.API.CoinDetailRequest;
import com.project.app.cryptotracker.API.CoinListingRequest;
import com.project.app.cryptotracker.Dashboard.DetailFragment;
import com.project.app.cryptotracker.Database.CryptoDatabase;
import com.project.app.cryptotracker.POJO.CoinListing;
import com.project.app.cryptotracker.POJO.CryptoDetail;
import com.project.app.cryptotracker.R;

import java.util.ArrayList;

public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.CustomViewHolder> {

    private ArrayList<CoinListing> coinListings;
    private Context context;

    public ListingAdapter(Context context, ArrayList<CoinListing> coinListings) {
        this.context = context;
        this.coinListings = coinListings;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_layout_listing,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        CoinListing coinListing = coinListings.get(position);
        holder.coinName.setText(coinListing.getCoinName());
        holder.coinSymbol.setText(coinListing.getCoinSymbol());
        holder.percentChange.setText(String.format("%.2f",coinListing.getPercentChange()) + "%");
        holder.itemView.setOnClickListener(v->{
            int coinID = coinListing.getCoinId();
            Bundle bundle = new Bundle();
            bundle.putInt("coinID", coinID);
            Log.d("CoinID", "CoinID in ListingAdapter: " + coinID);
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_mainFragment_to_detailFragment, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return coinListings.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        protected TextView coinName;
        protected TextView coinSymbol;
        protected TextView percentChange;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.coinName = itemView.findViewById(R.id.coinName);
            this.coinSymbol = itemView.findViewById(R.id.coinSymbol);
            this.percentChange = itemView.findViewById(R.id.percentChange);
            }
        }
    }

