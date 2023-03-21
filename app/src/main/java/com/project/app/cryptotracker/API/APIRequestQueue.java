package com.project.app.cryptotracker.API;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class APIRequestQueue {
    public static APIRequestQueue instance;
    private RequestQueue requestQueue;
    private static Context context;

    private APIRequestQueue(Context context) {
        this.context = context;
    }

    public static APIRequestQueue getInstance(Context context){
        if (instance == null){
            instance = new APIRequestQueue(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue(){
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }
}
