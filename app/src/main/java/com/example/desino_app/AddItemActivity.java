package com.example.desino_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddItemActivity extends AppCompatActivity {

    EditText txtNombre,txtDescripcion,txtPrecio,txtPhoto;
    Spinner spCategoria,spMoneda;
    LinearLayout fondoCarga;
    ProgressBar carga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        txtDescripcion=findViewById(R.id.txtDescripcion);
        txtPrecio=findViewById(R.id.txtPrecio);
        txtNombre=findViewById(R.id.txtNombre);
        txtPhoto=findViewById(R.id.txtphoto);
        spCategoria=findViewById(R.id.spinnerCategoria);
        spMoneda=findViewById(R.id.spMoneda);
        fondoCarga=findViewById(R.id.fondoCarga);
        carga=findViewById(R.id.progressBarCarga);


        fondoCarga.setVisibility(View.GONE);
        carga.setVisibility(View.GONE);



    }


    private void addItemAPI() {
        fondoCarga.setVisibility(View.VISIBLE);
        carga.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(this);
        String URL = "http://192.168.1.3/basewift/basewift/buscarop.php";
        JSONObject datos = new JSONObject();
        try{
            datos.put("name",txtNombre.getText().toString().trim());
            datos.put("description",txtDescripcion.getText().toString().trim());
            datos.put("price",Long.parseLong(txtPrecio.getText().toString().trim()));
            datos.put("currency",spMoneda.getSelectedItem().toString().trim());
            datos.put("photo",txtPhoto.getText().toString().trim());
            datos.put("category",spCategoria.getSelectedItem().toString().trim());

            //INICIA LA SOLICITUD AL SERVER
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, datos, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent);
                    finish();

                    try {
                        Toast.makeText(getApplicationContext(),"Producto "+response.getString("name")+" guardado",Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    fondoCarga.setVisibility(View.GONE);
                    carga.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"ERROR VOLLEY :"+error.getMessage(),Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap headers = new HashMap();
                   SharedPreferences pref = getSharedPreferences("sesion", Context.MODE_PRIVATE);
                    //String token = pref.getString("token", "");
                  //  headers.put("Authorization","Bearer "+token);
                    headers.put("Content-Type","application/json");
                    return headers;
                }
            };
            queue.add(request);

        }catch (JSONException ex){
            fondoCarga.setVisibility(View.GONE);
            carga.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(),"JSON ERROR:"+ex.getMessage(),Toast.LENGTH_LONG).show();
        }


    }

    public void register(View view) {
        if(txtDescripcion.getText().toString().isEmpty() || txtNombre.getText().toString().isEmpty() || txtPrecio.getText().toString().isEmpty() || spMoneda.getSelectedItemPosition()==0 || spCategoria.getSelectedItemPosition()==0){
            Toast.makeText(getApplicationContext(),"Faltan campos por llenar",Toast.LENGTH_LONG).show();

        }else{
            addItemAPI();
        }
    }

    public void goToHome(View view) {
        Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(intent);
        finish();
    }
}