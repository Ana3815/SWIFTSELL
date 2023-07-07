package com.example.desino_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.Notificaction;

public class NotificacionActivity extends AppCompatActivity {
    RecyclerView rvLista;
    ProgressBar carga;
    LinearLayout fondoCarga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacion);

        rvLista = findViewById(R.id.rvListaMensajes);
        carga = findViewById(R.id.progressBarCarga);
        fondoCarga = findViewById(R.id.fondoCarga);
        carga.setVisibility(View.GONE);
        fondoCarga.setVisibility(View.GONE);

        listarMensajes();
    }



    private void listarMensajes() {
        ArrayList<Notificaction> notificactionArrayList = new ArrayList<>();
        fondoCarga.setVisibility(View.VISIBLE);
        carga.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(this);
        String URL = "http://192.168.1.3/basewift/basewift/vermensaje.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray messages = response.getJSONArray("messages");
                    for (int i = 0; i < messages.length(); i++) {
                        JSONObject item = messages.getJSONObject(i);

                        Notificaction noti = new Notificaction();
                        noti.setMessage(item.getString("message"));
                        noti.setUser(item.getString("name"));
                        notificactionArrayList.add(noti);
                    }

                    rvLista.setHasFixedSize(true);
                    LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                    MessagesAdapter adapter = new MessagesAdapter(notificactionArrayList);
                    rvLista.setLayoutManager(manager);
                    rvLista.setAdapter(adapter);

                    fondoCarga.setVisibility(View.GONE);
                    carga.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error al procesar la respuesta del servidor", Toast.LENGTH_LONG).show();
                    fondoCarga.setVisibility(View.GONE);
                    carga.setVisibility(View.GONE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error en la solicitud al servidor: " + error.getMessage(), Toast.LENGTH_LONG).show();
                fondoCarga.setVisibility(View.GONE);
                carga.setVisibility(View.GONE);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        queue.add(request);
    }

    public void goToHome(View v){
        Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(intent);
        finish();
    }
}