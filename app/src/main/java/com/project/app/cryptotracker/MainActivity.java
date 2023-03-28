package com.project.app.cryptotracker;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.project.app.cryptotracker.Dashboard.DetailFragment;
import com.project.app.cryptotracker.Database.CryptoDatabase;
import com.project.app.cryptotracker.databinding.ActivityMainBinding;

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
                if (currentFragment.getId() == R.id.detailFragment){
                    if (DetailFragment.fav != null)
                        new CryptoDatabase(getApplicationContext()).addToFavorite(DetailFragment.fav);
                }
            }
        });

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                // Todo: adding more destination if fab requires to be shown on more fragment
                if (navDestination.getId() == R.id.investmentFragment){
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

}