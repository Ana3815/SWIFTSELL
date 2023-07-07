package com.example.desino_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
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

import model.Product;
import model.ProductLastes;

public class HomeActivity extends AppCompatActivity {

    RecyclerView rvLastest, rvProducts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rvLastest=findViewById(R.id.rvLastest);
        rvProducts=findViewById(R.id.rvProducts);

        listaLastest();
        listaProducts();
    }

    public void goToNotifications(View view) {
        Intent intent= new Intent(getApplicationContext(),NotificacionActivity.class);
        startActivity(intent);
        finish();
    }

    public void goToSeacrh(View view) {
        Intent intent= new Intent(getApplicationContext(),SearchActivity.class);
        startActivity(intent);
        finish();
    }


    private ArrayList<Product> lastestArrayList = new ArrayList<>();
    private ArrayList<Product> productsArrayList = new ArrayList<>();
    private void listaLastest() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String URL = "http://192.168.1.3/basewift/basewift/producdeotros.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    productsArrayList.clear();

                    JSONArray products = response.getJSONArray("products");
                    Log.d("Products", products.toString());
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject item = products.getJSONObject(i);

                        Product p = new Product();
                        p.setId(item.getLong("id"));
                        p.setName(item.getString("name"));
                        p.setDescription(item.getString("description"));
                        p.setPrice(item.getLong("price"));
                        p.setCurrency(item.getString("currency"));
                        p.setPhoto(item.getString("photo"));

                        productsArrayList.add(p);
                    }

                    rvLastest.setHasFixedSize(true);
                    LinearLayoutManager manager1 = new GridLayoutManager(getApplicationContext(), 1, GridLayoutManager.HORIZONTAL, false);
                    ProductAllAdapter adapter1 = new ProductAllAdapter(productsArrayList);
                    rvLastest.setLayoutManager(manager1);
                    rvLastest.setAdapter(adapter1);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error al procesar la respuesta del servidor", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error en la solicitud al servidor: " + error.getMessage(), Toast.LENGTH_LONG).show();
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


    private void listaProducts() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String URL = "http://192.168.1.3/basewift/basewift/productnuestros.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    lastestArrayList.clear(); // Limpiar la lista existente antes de agregar nuevos productos

                    JSONArray products = response.getJSONArray("products");
                    for (int i = 0; i < products.length(); i++) {
                        JSONObject item = products.getJSONObject(i);

                        Product p = new Product();
                        p.setId(item.getLong("id"));
                        p.setName(item.getString("name"));
                        p.setDescription(item.getString("description"));
                        p.setPrice(item.getLong("price"));
                        p.setCurrency(item.getString("currency"));
                        p.setPhoto(item.getString("photo"));

                        lastestArrayList.add(p);
                    }

                    rvProducts.setHasFixedSize(true);
                    LinearLayoutManager manager2 = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
                    ProductLastestAdapter adapter2 = new ProductLastestAdapter(lastestArrayList);
                    rvProducts.setLayoutManager(manager2);
                    rvProducts.setAdapter(adapter2);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error al procesar la respuesta del servidor", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error en la solicitud al servidor: " + error.getMessage(), Toast.LENGTH_LONG).show();
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

    public void goToAddItem(View view) {
        Intent intent=new Intent(getApplicationContext(),AddItemActivity.class);
        startActivity(intent);
        finish();
    }

    public void goToClose(View view){
        SharedPreferences pref = getSharedPreferences("sesion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.clear(); // Eliminar todos los valores almacenados
        editor.apply(); // Aplicar los cambios

       // Iniciar un nuevo Activity
        Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
        startActivity(intent);
        finish(); // Opcionalmente, puedes llamar a finish() para cerrar la actividad actual

    }
}