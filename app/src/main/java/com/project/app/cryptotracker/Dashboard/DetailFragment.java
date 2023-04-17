package com.project.app.cryptotracker.Dashboard;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.project.app.cryptotracker.API.APIRequestQueue;
import com.project.app.cryptotracker.API.CoinDetailRequest;
import com.project.app.cryptotracker.POJO.CryptoDetail;
import com.project.app.cryptotracker.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

    public static CryptoDetail fav = null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
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
        // Get the saved preference
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        boolean useAltLayout = sharedPreferences.getBoolean("alternative_layout", false);
        // which layout to use
        int layoutId = useAltLayout ? R.layout.alt_fragment_detail : R.layout.fragment_detail;

        View view =  inflater.inflate(layoutId, container, false);
        int coinId = getArguments().getInt("coinID");
        

        // Find UI elements
        TextView tvCoinName = view.findViewById(R.id.name);
        TextView tvCoinSymbol = view.findViewById(R.id.symbol);
        TextView tvCoinDescription = view.findViewById(R.id.description);
        ImageView ivCoinLogo = view.findViewById(R.id.logo);
        TextView tvDateAdded = view.findViewById(R.id.date_added);
        TextView tvDateLaunched = view.findViewById(R.id.date_launched);
        TextView tvCategory = view.findViewById(R.id.category);
        LinearLayout urlsLayout = view.findViewById(R.id.urls_layout);


        // Create the CoinDetailRequest
        CoinDetailRequest coinDetailRequest = new CoinDetailRequest(getContext());

        // Define the response listener
        Response.Listener<JSONObject> responseListener = response -> {
            try {
                // Parse the response and populate the UI with coin details
                JSONObject data = response.getJSONObject("data");
                JSONObject coinData = data.getJSONObject(String.valueOf(coinId));
                String coinName = coinData.getString("name");
                String coinSymbol = coinData.getString("symbol");
                String coinDescription = coinData.getString("description");
                String coinLogoUrl = coinData.getString("logo");
                String dateAdded = coinData.getString("date_added");
                String dateLaunched = coinData.getString("date_launched");
                String category = coinData.getString("category");

                CryptoDetail cryptoDetail = new CryptoDetail();
                cryptoDetail.setName(coinName);
                cryptoDetail.setSymbol(coinSymbol);
                cryptoDetail.setLogo(coinLogoUrl);

                fav = cryptoDetail;

                // Update UI elements
                tvCoinName.setText(coinName);
                tvCoinSymbol.setText(coinSymbol);
                tvCoinDescription.setText(coinDescription);
                Picasso.get().load(coinLogoUrl).into(ivCoinLogo);
                tvDateAdded.setText("Date added: " + dateAdded);
                tvDateLaunched.setText("Date launched: " + dateLaunched);
                tvCategory.setText("Category: " + category);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        };
        Response.ErrorListener errorListener = error -> {
            error.printStackTrace();
        };

        // Request
        coinDetailRequest.requestDetail(coinId, responseListener, errorListener);
        return view;
    }
}
