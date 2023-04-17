package com.project.app.cryptotracker.RecyclerAdapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.project.app.cryptotracker.Database.CryptoDatabase;
import com.project.app.cryptotracker.POJO.CoinInvestment;
import com.project.app.cryptotracker.POJO.CryptoDetail;
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
        double differenceValue = coinInvestment.getMarket() * coinInvestment.getQnty();
        holder.coinSymbol.setText(coinInvestment.getCoinSymbol());
        holder.investment.setText(String.format("%.2f",investment));
        holder.difference.setText(String.format("%.2f",Math.abs(investment-differenceValue)));
        holder.market.setText(String.format("%.2f",coinInvestment.getMarket()));
        holder.quantity.setText(String.valueOf(coinInvestment.getQnty()));
    }

    @Override
    public int getItemCount() {
        return coinInvestments.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
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
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            CoinInvestment coinInvestment = coinInvestments.get(getLayoutPosition());
            new MaterialAlertDialogBuilder(v.getContext())
                    .setTitle("Do you want to Delete?")
                    .setMessage("Are you sure you want to delete this Investment!.")
                    .setIcon(android.R.drawable.ic_menu_delete)
                    .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            boolean status = new CryptoDatabase(context).removeInvestment(coinInvestment.getId());
                            if (status)
                                Snackbar.make(v,"Investment removed.", Snackbar.LENGTH_SHORT).show();
                            else
                                Snackbar.make(v,"Something went wrong!.", Snackbar.LENGTH_SHORT).show();

                            coinInvestments.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                        }
                    })
                    .setNegativeButton("Cancel",null)
                    .show();
            return false;
        }
    }

    public void notifyInsert(){
        coinInvestments = new CryptoDatabase(context).getAllInvestment();
        notifyItemInserted(coinInvestments.size());
    }
}
