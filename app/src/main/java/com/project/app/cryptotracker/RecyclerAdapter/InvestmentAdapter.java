package com.project.app.cryptotracker.RecyclerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.app.cryptotracker.Database.CryptoDatabase;
import com.project.app.cryptotracker.POJO.CoinInvestment;
import com.project.app.cryptotracker.R;

import java.util.ArrayList;

public class InvestmentAdapter extends RecyclerView.Adapter<InvestmentAdapter.CustomViewHolder>{
    private ArrayList<CoinInvestment> coinInvestments;
    private Context context;

    public InvestmentAdapter(Context context) {
        this.coinInvestments = new CryptoDatabase(context).getAllInvestment();
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item_layout_investment,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        CoinInvestment coinInvestment = coinInvestments.get(position);
        double investment = coinInvestment.getQnty() * coinInvestment.getPrice();
        holder.coinSymbol.setText(coinInvestment.getCoinSymbol());
        holder.investment.setText(String.format("%.2f",investment));
        holder.difference.setText(String.format("%.2f",Math.abs(investment-coinInvestment.getMarket())/coinInvestment.getQnty()));
        holder.market.setText(String.format("%.2f",coinInvestment.getMarket()));
        holder.quantity.setText(String.valueOf(coinInvestment.getQnty()));
    }

    @Override
    public int getItemCount() {
        return coinInvestments.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{
        protected TextView coinSymbol;
        protected TextView investment;
        protected TextView market;
        protected TextView difference;
        protected TextView quantity;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            coinSymbol = itemView.findViewById(R.id.investmentCoinSymbol);
            investment = itemView.findViewById(R.id.investment);
            market = itemView.findViewById(R.id.market);
            difference = itemView.findViewById(R.id.difference);
            quantity = itemView.findViewById(R.id.coinQuantity);
        }
    }

    public void notifyInsert(){
        coinInvestments = new CryptoDatabase(context).getAllInvestment();
        notifyItemInserted(coinInvestments.size());
    }
}
