package com.project.app.cryptotracker;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.project.app.cryptotracker.API.CoinListingRequest;
import com.project.app.cryptotracker.Dashboard.DetailFragment;
import com.project.app.cryptotracker.Dashboard.InvestmentFragment;
import com.project.app.cryptotracker.Database.CryptoDatabase;
import com.project.app.cryptotracker.POJO.CoinInvestment;
import com.project.app.cryptotracker.POJO.CoinListing;
import com.project.app.cryptotracker.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        binding.fab.hide();
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDestination currentFragment = navController.getCurrentDestination();
                // Todo: add a check to perform specific task on specific fragment
                if (currentFragment.getId() == R.id.detailFragment) {
                    if (DetailFragment.fav != null && new CryptoDatabase(getApplicationContext()).addToFavorite(DetailFragment.fav)) {
                        Snackbar.make(v, DetailFragment.fav.getName() + " Added to Favorites", Snackbar.LENGTH_LONG).show();
                    } else {
                        Snackbar.make(v, DetailFragment.fav.getName() + " is already Added to Favorites", Snackbar.LENGTH_LONG).show();
                    }
                } else if (currentFragment.getId() == R.id.investmentFragment) {
                    showCryptoFormDialog();
                }
            }
        });

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                // Todo: adding more destination if fab requires to be shown on more fragment
                if (navDestination.getId() == R.id.investmentFragment) {
                    binding.fab.setImageResource(R.drawable.baseline_add_24);
                    binding.fab.show();
                } else if (navDestination.getId() == R.id.detailFragment) {
                    binding.fab.setImageResource(R.drawable.baseline_favorite_24);
                    binding.fab.show();
                } else {
                    binding.fab.hide();
                }
            }
        });


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.mainFragment, R.id.educationFragment, R.id.investmentFragment, R.id.favoritesFragment)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    //method to show the dialog form
    private void showCryptoFormDialog() {
        ArrayList<CoinListing> coinListings = new CryptoDatabase(getApplicationContext()).getAlCoin();
        Log.d("MainActivity", "showCryptoFormDialog hit");
        // Adding material 3 dialog
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity.this);
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        View view = inflater.inflate(R.layout.crypto_form, null);
        builder.setView(view);

        //Dropdown and adding the API array to the drop down
        MaterialAutoCompleteTextView dropdown = view.findViewById(R.id.crypto_dropdown);
        TextInputLayout textInputLayout = view.findViewById(R.id.crypto_til);
        ArrayAdapter<CoinListing> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, coinListings);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemClickListener((parent, view1, position, id) -> {
            CoinListing selectedCoin = adapter.getItem(position);

            TextView name = view.findViewById(R.id.crypto_name);
            TextView symbol = view.findViewById(R.id.crypto_symbol);
            TextView currentPrice = view.findViewById(R.id.crypto_current_price);
            TextInputEditText buyPrice = view.findViewById(R.id.crypto_buy_price);

            if (selectedCoin != null) {
                name.setText(selectedCoin.getCoinName());
                symbol.setText(selectedCoin.getCoinSymbol());
                currentPrice.setText(String.valueOf(selectedCoin.getPrice()));
                buyPrice.setText(String.valueOf(selectedCoin.getPrice()));
            }
        });

        builder.setTitle("Select a cryptocurrency")
                .setPositiveButton("Save", (dialog, id) -> {

                    String selectedText = dropdown.getText().toString();
                    CoinListing selectedCoin = null;

                    for (CoinListing coin : coinListings) {
                        if (coin.getCoinName().equals(selectedText)) {
                            selectedCoin = coin;
                            break;
                        }
                    }

//                    if (selectedCoin != null) {
//                        // COIN IF USER SELECTS FROM LIST
//                    } else {
//                        // INSERT TOAST HERE
//                    }

                    TextInputEditText buyPriceInput = view.findViewById(R.id.crypto_buy_price);
                    TextInputEditText quantityInput = view.findViewById(R.id.crypto_buy_quantity);
                    double quantity = Double.parseDouble(quantityInput.getText().toString());
                    double buyPrice = Double.parseDouble(buyPriceInput.getText().toString());

                    // SAVE TO DB FOR USER TOTAL
//                    double userTotal = quantity * buyPrice;
//                    System.out.println(userTotal);

//                    Toast.makeText(getApplicationContext(), "Buyprice: " + buyPrice + "Qnty: " + quantity + "Coin: " + selectedCoin, Toast.LENGTH_LONG).show();


                    // SAVE TO DB HERE

                    Log.d("CryptoForm", "Selected coin: " + selectedCoin.getCoinName() + " - Buy price: " + buyPrice);
                    boolean status = new CryptoDatabase(getApplicationContext()).addToInvestment(new CoinInvestment(
                            selectedCoin.getId(),selectedCoin.getCoinSymbol(),buyPrice,quantity
                    ));
                    if (status){
                        Toast.makeText(getApplicationContext(),"Successfully added to your Investments.",Toast.LENGTH_LONG).show();
                        InvestmentFragment.investmentAdapter.notifyInsert(); // this will refresh the recyclerview
                    } else {
                        Toast.makeText(getApplicationContext(),"There was an error adding your investments.",Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Cancel", (dialog, id) -> {
                    dialog.dismiss();
                });

        // Show the dialog as an alert
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

}