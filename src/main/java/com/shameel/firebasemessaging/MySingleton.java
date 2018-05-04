package com.shameel.firebasemessaging;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by pc15 on 02/05/2018.
 */

public class MySingleton {

    private static MySingleton mInstance;
    private static Context ctx;
    private RequestQueue requestQueue;

    private MySingleton(Context context){
        ctx = context;
        requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue(){
        if(requestQueue==null){
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized MySingleton getmInstance(Context context){
        if(mInstance == null){
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }

    public<T> void addRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }
}
