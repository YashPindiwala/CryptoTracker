package com.project.app.cryptotracker.RecyclerAdapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.project.app.cryptotracker.Dashboard.DetailFragment;
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

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView favCoinLogo;
        TextView favCoinSymbol;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            favCoinLogo = itemView.findViewById(R.id.favCoinLogo);
            favCoinSymbol = itemView.findViewById(R.id.favCoinSymbol);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            CryptoDetail cryptoDetail = coinListings.get(getLayoutPosition());
            new MaterialAlertDialogBuilder(v.getContext())
                    .setTitle("Do you want to Delete?")
                    .setMessage("Are you sure you want to remove ["+ cryptoDetail.getName() +"]?.")
                    .setIcon(android.R.drawable.ic_menu_delete)
                    .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            boolean status = new CryptoDatabase(v.getContext()).removeFavCoin(cryptoDetail.getId());
                            if (status)
                                Snackbar.make(v,cryptoDetail.getName() + " was removed from Favorites.", Snackbar.LENGTH_SHORT).show();
                            else
                                Snackbar.make(v,"Something went wrong!.", Snackbar.LENGTH_SHORT).show();

                            coinListings.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                        }
                    })
                    .setNegativeButton("Cancel",null)
                    .show();
        }
    }
}
