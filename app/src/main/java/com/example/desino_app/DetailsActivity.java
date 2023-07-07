package com.example.desino_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import model.Product;
import model.ProductLastes;

public class DetailsActivity extends AppCompatActivity {
    TextView txtNombre,txtTitulo,txtPrecio,txtDescripcion;
    ImageView ivImagen,ivImagen2;

    LinearLayout fondoCarga;
    ProgressBar carga;

    Button btnMensaje;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destails);

        txtNombre=findViewById(R.id.txtNombre);
        txtTitulo=findViewById(R.id.txtTitulo);
        txtPrecio=findViewById(R.id.txtPrecio);
        txtDescripcion=findViewById(R.id.txtDescripcion);
        ivImagen=findViewById(R.id.ivImagen);

        fondoCarga = findViewById(R.id.fondoCarga);
        carga = findViewById(R.id.progressBarCarga);
        btnMensaje = findViewById(R.id.btnMensaje);

        long id = getIntent().getExtras().getLong("id",0);
        getDetalle(id);

        btnMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AgregarMensajeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void goToHome(View v){
        Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(intent);
        finish();
    }




    private void getDetalle(long id) {
        fondoCarga.setVisibility(View.VISIBLE);
        carga.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(this);
        String URL = "http://192.168.1.3/basewift/basewift/buscarop.php";

        // Crear el objeto JSON para enviar los datos
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, jsonParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String status = response.getString("status");
                    if (status.equals("OK")) {
                        JSONObject item = response.getJSONObject("product");

                        ProductLastes p = new ProductLastes();
                        p.setId(item.getLong("id"));
                        p.setName(item.getString("name"));
                        p.setDescription(item.getString("description"));
                        p.setPrice(item.getLong("price"));
                        p.setCurrency(item.getString("currency"));
                        p.setPhoto(item.getString("photo"));

                        txtDescripcion.setText(p.getDescription());
                        txtTitulo.setText(p.getName());
                        txtNombre.setText(p.getName());
                        txtPrecio.setText(String.valueOf(p.getPrice()));

                        Glide.with(getApplicationContext()).load(p.getPhoto()).into(ivImagen);
                    } else {
                        Toast.makeText(getApplicationContext(), "Producto no encontrado", Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error al procesar la respuesta del servidor", Toast.LENGTH_LONG).show();

                }
                fondoCarga.setVisibility(View.GONE);
                carga.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error en la solicitud al servidor: " + error.getMessage(), Toast.LENGTH_LONG).show();
                fondoCarga.setVisibility(View.GONE);
                carga.setVisibility(View.GONE);
            }
        });

        queue.add(request);
    }




}