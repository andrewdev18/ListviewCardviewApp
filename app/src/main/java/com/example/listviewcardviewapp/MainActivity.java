package com.example.listviewcardviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView listado;
    RequestQueue requestQueue;
    Button btnVer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listado = findViewById(R.id.txtList);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        btnVer = findViewById(R.id.btnView);
        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listado.setText("Null");
                JSONRequest();
            }
        });

    }

    private void JSONRequest() {
        String urlApi = "https://reqres.in/api/users";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                urlApi, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        JSONArray jsonArray = null;
                        try {
                            jsonArray = response.getJSONArray("data");


                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String id = object.getString("id");
                                String email = object.getString("email");
                                String nombre = object.getString("first_name");
                                String apellido = object.getString("last_name");
                                String avatar = object.getString("avatar");

                                listado.append("Nombre: " + nombre + " " + apellido + "\n");
                                listado.append("Email: " + email + "\n");
                                listado.append("Avatar: " + avatar + "\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }
}