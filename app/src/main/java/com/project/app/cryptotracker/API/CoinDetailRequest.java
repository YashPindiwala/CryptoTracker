package com.project.app.cryptotracker.API;

import android.content.Context;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CoinDetailRequest {
    private static String url = "https://pro-api.coinmarketcap.com/v2/cryptocurrency/info";
    private Context context;

    public CoinDetailRequest(Context context) {
        this.context = context;
    }

    public void requestDetail(int itemId, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        String requestUrl = url + "?id=" + itemId;
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                requestUrl,
                null,
                responseListener,
                errorListener
        ) {
            @Nullable
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("X-CMC_PRO_API_KEY", "669ab313-c575-4483-8bb1-16f1a4cc1410");
                return headers;
            }
        };
        APIRequestQueue.getInstance(context).getRequestQueue().add(request);
    }

}

