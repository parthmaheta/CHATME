package com.chatme;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {
    private static MySingleton MyInstance;
    private RequestQueue requestQueue;
    private static Context cntx;

    private MySingleton(Context context){
        cntx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized MySingleton getInstance(Context context){
        if (MyInstance == null){
            MyInstance = new MySingleton(context);
        }
        return MyInstance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(cntx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T>void addRequestQueue(Request<T> request){
        requestQueue.add(request);
    }
}
