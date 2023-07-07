package com.example.desino_app;


import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AgregarMensajeActivity extends AppCompatActivity {
    EditText txtNombreM,txtCorreoM,txtMensaje;
    LinearLayout fondoCarga;
    ProgressBar progressBarCarga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_mensaje);

        txtNombreM = findViewById(R.id.txtNombreM);
        txtCorreoM= findViewById(R.id.txtCorreoM);
        txtMensaje = findViewById(R.id.txtMensaje);

        fondoCarga = findViewById(R.id.fondoCarga);
        progressBarCarga = findViewById(R.id.progressBarCarga);

        fondoCarga.setVisibility(View.GONE);
        progressBarCarga.setVisibility(View.GONE);
    }

    public void goToHome(View view) {
        Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void goTo(View view) {
        Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void register(View view) {
        if(validar()){
            registerAPI();
        }else{
            Toast.makeText(getApplicationContext(),"Debe ingresar los valores",Toast.LENGTH_LONG).show();
        }
    }



    private void registerAPI() {
        fondoCarga.setVisibility(View.VISIBLE);
        progressBarCarga.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(this);

        String URL = "http://192.168.1.3/basewift/basewift/mensajes.php";
        JSONObject datos = new JSONObject();
        try {
            datos.put("name", txtNombreM.getText().toString().trim().toUpperCase());
            datos.put("email", txtCorreoM.getText().toString().trim().toLowerCase());
            datos.put("message", txtMensaje.getText().toString().trim());

            // INICIA LA SOLICITUD AL SERVIDOR
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, datos,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String status = response.getString("status");
                                String mensaje = response.getString("mensaje");

                                if (status.equals("OK")) {
                                    Toast.makeText(getApplicationContext(), "Su mensaje fue enviado a " + txtCorreoM.getText().toString(),
                                            Toast.LENGTH_LONG).show();

                                    fondoCarga.setVisibility(View.GONE);
                                    progressBarCarga.setVisibility(View.GONE);

                                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
                                    fondoCarga.setVisibility(View.GONE);
                                    progressBarCarga.setVisibility(View.GONE);
                                }
                            } catch (JSONException e) {
                                Toast.makeText(getApplicationContext(), "No es posible enviar el mensaje a " + txtCorreoM.getText().toString(), Toast.LENGTH_LONG).show();
                                fondoCarga.setVisibility(View.GONE);
                                progressBarCarga.setVisibility(View.GONE);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "ERROR VOLLEY: " + error.getMessage(), Toast.LENGTH_LONG).show();
                            fondoCarga.setVisibility(View.GONE);
                            progressBarCarga.setVisibility(View.GONE);
                        }
                    });

            queue.add(request);

        } catch (JSONException ex) {
            Toast.makeText(getApplicationContext(), "JSON ERROR: " + ex.getMessage(), Toast.LENGTH_LONG).show();
            fondoCarga.setVisibility(View.GONE);
            progressBarCarga.setVisibility(View.GONE);
        }
    }


    private boolean validar() {
        boolean valido = true;
        if(txtNombreM.getText().toString().trim().isEmpty()){
            txtNombreM.setError("Debe ingresar un nombre");
            valido = false;
        }
        if(txtCorreoM.getText().toString().trim().isEmpty()){
            txtCorreoM.setError("Debe ingresar un correo");
            valido = false;
        }
        if(txtMensaje.getText().toString().trim().isEmpty()){
            txtMensaje.setError("Debe ingresar un mensaje");
            valido = false;
        }

        return valido;
    }
}