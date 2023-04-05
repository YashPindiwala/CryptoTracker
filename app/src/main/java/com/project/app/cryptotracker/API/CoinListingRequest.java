package com.project.app.cryptotracker.API;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.project.app.cryptotracker.Database.CryptoDatabase;
import com.project.app.cryptotracker.POJO.CoinListing;
import com.project.app.cryptotracker.RecyclerAdapter.ListingAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CoinListingRequest {

    private static String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
    private static String NAME = "name";
    private static String SYMBOL = "symbol";

    private static String ID = "id";
    private Context context;
    private RecyclerView recyclerView;

    public CoinListingRequest(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
    }

    public void requestListing(){
        ArrayList<CoinListing> coinListings = new ArrayList<>();
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray data = response.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject coin = data.getJSONObject(i);
                                JSONObject quote = coin.getJSONObject("quote");
                                JSONObject USD = quote.getJSONObject("USD");
                                coinListings.add(new CoinListing(coin.getInt(ID),
                                        coin.getString(NAME),
                                        coin.getString(SYMBOL),
                                        USD.getDouble("percent_change_24h"),
                                        USD.getDouble("price")));
                            }
                            recyclerView.setAdapter(new ListingAdapter(context,coinListings));
                            new CryptoDatabase(context).addAllCoin(coinListings);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ){
            @Nullable
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  headers= new HashMap<String, String>();
                headers.put("X-CMC_PRO_API_KEY", "669ab313-c575-4483-8bb1-16f1a4cc1410");
                return headers;
            }
        };
        APIRequestQueue.getInstance(context).getRequestQueue().add(request);
    }
}
