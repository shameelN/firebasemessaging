package com.shameel.firebasemessaging;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String token = "abc123";
    private final String app_server_url = "localhost:8081/test.asp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        final String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Toast.makeText(this,"Passenger details" + refreshedToken, Toast.LENGTH_LONG).show();
        Log.d("MyAndroidFCMIIDService", "Refreshed token: " + refreshedToken);
        Button btn = (Button)findViewById(R.id.btnSndTkn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, app_server_url, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("fcm_token",token);
                        return params;
                    }
                };
                MySingleton.getmInstance(MainActivity.this).addRequestQueue(stringRequest);
            }
        });
    }
}
