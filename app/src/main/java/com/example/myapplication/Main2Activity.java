package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    public static final int MAAIN2_ACTIVITY_REQUEST=2;
    String codigo2 ="00";

    private TextView tvcodigo,tvnombre,tvdescripcion,tvprecio,tvcategoria,tvprecioventa;
    private Button btnConfirmar,btnCancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        tvcodigo = findViewById(R.id.tvcodigo);
        tvnombre = findViewById(R.id.tvnombre);
        tvdescripcion= findViewById(R.id.tvdescripcion);
        tvcategoria = findViewById(R.id.tvcategoria);
        tvprecio = findViewById(R.id.tvprecio);
        tvprecioventa = findViewById(R.id.tvprecioventa);

        btnConfirmar = findViewById(R.id.btnConfirmar);
        btnCancelar = findViewById(R.id.btnCancelar);

        Bundle data = getIntent().getExtras();
        if (data!=null){
            obtenerIntent(data);
        }
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regresar = new Intent(getApplicationContext(),MainActivity.class);
                //startActivity(regresar);
                setResult(RESULT_OK,regresar);
                finish();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regresar = new Intent(getApplicationContext(),MainActivity.class);
                setResult(RESULT_CANCELED,regresar);
                finish();
            }
        });
    }

    public void obtenerIntent(Bundle data){
        int codigo=0;
        codigo +=1;

        Double precioVenta =0.0;
        Double total = 0.0;
        Double precio = data.getDouble("precio");
        tvcodigo.setText("codigo "+codigo2+codigo);
        tvnombre.setText("nombre "+data.getString("nombre"));
        tvdescripcion.setText("Descripcion "+data.getString("descripcion"));
        tvprecio.setText("Precio "+precio);
        tvcategoria.setText("categoria "+data.getString("categoria"));
        String categoria = data.getString("categoria");

        switch (categoria){
            case "heramienta":
                precioVenta = precio * 0.10;
                total = precio+precioVenta;
                tvprecioventa.setText("precio venta "+total);
                break;
            case "medicina":
                precioVenta = precio * 0.30;
                total = precio+precioVenta;
                tvprecioventa.setText("precio venta "+total);
                break;
            case "ropa":
                precioVenta = precio * 0.20;
                total = precio+precioVenta;
                tvprecioventa.setText("precio venta "+total);
                break;
        }
    }
}
