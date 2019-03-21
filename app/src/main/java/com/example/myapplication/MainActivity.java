package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button btnAgregar,btnLimpiar;

    private EditText edtNombre,edtdescripcion,edtPrecio;

    private RadioButton rbHerramienta,rbMedicina,rbRopa;

    //esta varible tiene el numero de la actividad actual
    public static final int MAAIN_ACTIVITY_REQUEST=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btnAgregar = findViewById(R.id.btnAgregar);
        btnLimpiar = findViewById(R.id.btnLimpiar);


        edtNombre = findViewById(R.id.edtNombre);
        edtdescripcion = findViewById(R.id.edtdescripcion);
        edtPrecio = findViewById(R.id.edtPrecio);

        rbHerramienta = findViewById(R.id.rbHerramienta);
        rbMedicina = findViewById(R.id.rbMedicina);
        rbRopa = findViewById(R.id.rbRopa);


        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = edtNombre.getText().toString().trim();
                String descripcion = edtdescripcion.getText().toString();
                Double precio =0.0;
                //si el usuario no escribe un producot se cierra la aplicacion
                //con este try catch se evita que la aplicacion se cierre
                try {
                    precio = Double.valueOf(edtPrecio.getText().toString());
                }catch (Exception e){
                    //imprime el error en la consola
                    e.printStackTrace();
                }
                String categoria = "";
                //validaciones
                if (TextUtils.isEmpty(nombre)){
                    edtNombre.setError("escribe un nombre");
                    edtNombre.requestFocus();
                }else if (TextUtils.isEmpty(descripcion)){
                    edtdescripcion.setError("escribe una descripcion");
                    edtdescripcion.requestFocus();
                }else if (TextUtils.isEmpty(String.valueOf(precio))){
                    edtPrecio.setError("escribe un precio");
                    edtPrecio.requestFocus();
                }else {
                    Intent irConfirmar = new Intent(getApplicationContext(),Main2Activity.class);
                    irConfirmar.putExtra("nombre",nombre);
                    irConfirmar.putExtra("descripcion",descripcion);
                    irConfirmar.putExtra("precio",precio);
                    if (rbHerramienta.isChecked()){
                        categoria = "heramienta";
                        irConfirmar.putExtra("categoria",categoria);
                    }else if (rbMedicina.isChecked()){
                        categoria = "medicina";
                        irConfirmar.putExtra("categoria",categoria);
                    }else  if (rbRopa.isChecked()){
                        categoria = "ropa";
                        irConfirmar.putExtra("categoria",categoria);
                    }
                    //inicia la actividad 2 y espera que esta retorne un resultado
                    startActivityForResult(irConfirmar,MainActivity.MAAIN_ACTIVITY_REQUEST);
                }
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               limpar();
            }
        });
    }

    //limpia los campos
    public void limpar(){
        edtNombre.setText("");
        edtdescripcion.setText("");
        edtPrecio.setText("");
    }
    //este metodo solo se ejecuta cuando el usuario cancela la accion en la actividad 2 y llena los campos con los datos
    //ingresados anteriormente
    public void obtenerIntent(Bundle data){

        edtNombre.setText(data.getString("nombre"));
        edtdescripcion.setText(data.getString("descripcion"));
        edtPrecio.setText(String.valueOf(data.getDouble("precio")));
        String categoria = data.getString("categoria");
        switch (categoria){
            case "heramienta":
                rbHerramienta.setChecked(true);
                break;
            case "medicina":
                rbMedicina.setChecked(true);
                break;
            case "ropa":
                rbRopa.setChecked(true);
                break;
        }
    }
    //solo se ejecuta cuanto la actividad 2 retorna un codigo
    //que puede ser RESULT_OK o RESULT_CANCELED
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //verifica que el codigo de resquestcode sea de esta actividad
        if (requestCode==MAAIN_ACTIVITY_REQUEST){
            //si el usuario cancela la operacion en la actividad 2 se ejecuta esta parte
            if (resultCode==RESULT_CANCELED){
                //obtengo el intent y verifico que no sea null
                Bundle data2 = getIntent().getExtras();
                if (data!=null){
                    obtenerIntent(data2);
                }
                Toast.makeText(getApplicationContext(),"El usuario cancelo la operacion",Toast.LENGTH_LONG).show();
            }
            // si el usuario confirma el producto entra en este if
            if (resultCode == RESULT_OK){
                Toast.makeText(getApplicationContext(),"Producto guardato",Toast.LENGTH_LONG).show();
                limpar();
            }

        }
    }
}
