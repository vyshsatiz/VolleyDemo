package com.example.vyshalis.volleyjson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView name, email, mobile;

    String json_url = "http://192.168.1.7/getinfo.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.bn);
        name = (TextView)findViewById(R.id.name);
        email = (TextView)findViewById(R.id.email);
        mobile = (TextView)findViewById(R.id.mobile);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, json_url,(String) null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    name.setText(response.getString("name"));
                                    email.setText(response.getString("email"));
                                    mobile.setText(response.getString("mobile"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public  void  onErrorResponse(VolleyError error) {

                        Toast.makeText(MainActivity.this,"Something wrong...", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });
                MySingleton.getmInstance(MainActivity.this).addToRequestque(jsonObjectRequest);

            }
        });
    }
}
