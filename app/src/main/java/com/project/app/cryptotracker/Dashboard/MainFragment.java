package com.project.app.cryptotracker.Dashboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.project.app.cryptotracker.API.CoinListingRequest;
import com.project.app.cryptotracker.Database.CryptoDatabase;
import com.project.app.cryptotracker.POJO.CoinListing;
import com.project.app.cryptotracker.R;
import com.project.app.cryptotracker.RecyclerAdapter.ListingAdapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final int REFRESH_TIME = 20;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView coinListingRecycler = view.findViewById(R.id.coinListingRecycler);
        SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.refreshLayout);
        CoinListingRequest coinListingRequest = new CoinListingRequest(getContext(),coinListingRecycler);
        CryptoDatabase cryptoDatabase = new CryptoDatabase(getContext());
        CoinListing coin = cryptoDatabase.getFirstCoin();
        if (coin == null){
            coinListingRequest.requestListing();
            coinListingRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        } else {
            coinListingRecycler.setAdapter(new ListingAdapter(getContext(),cryptoDatabase.getAlCoin()));
            coinListingRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        //refreshing the Recycler view when user pulls down
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
             public void onRefresh() {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    CoinListing coin = cryptoDatabase.getFirstCoin();
//                    if (coin == null){
//                        swipeRefreshLayout.setRefreshing(false);
//                        return;
//                    }
                    Date lastDate = simpleDateFormat.parse(coin.getLastUpdate());
                    Date currentDate = new Date();
                    currentDate.setTime(System.currentTimeMillis() - (REFRESH_TIME * 60000));
                    if (lastDate.before(currentDate)){
                        cryptoDatabase.truncateCoinTable();
                        coinListingRequest.setSwipeRefreshLayout(swipeRefreshLayout);
                        coinListingRequest.requestListing();
                        coinListingRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                        Toast.makeText(getContext(),"Refreshing...",Toast.LENGTH_LONG).show();
                    } else {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return view;
    }
}