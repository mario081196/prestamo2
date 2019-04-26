package com.example.prestamo20;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    public String cadena_telefono;
    public String cadena_nombre;
    public String cadena_cedula;
    public String cadena_direccion;
    public String cadena_apellido;
    public String cadena_sexo;
    public String cadena_ocupacion;
    public Client nuevo = new Client();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick_guardar(View v){
        EditText nombre = findViewById(R.id.editText_nombre);
        cadena_nombre = nombre.getText().toString();
        EditText telefono = findViewById(R.id.editText_telefono);
        cadena_telefono = telefono.getText().toString();
        EditText cedula = findViewById(R.id.editText_cedula);
        cadena_cedula = cedula.getText().toString();
        EditText direccion = findViewById(R.id.editText_direccion);
        cadena_direccion = direccion.getText().toString();
        EditText apellido = findViewById(R.id.editText_apellido);
        cadena_apellido = apellido.getText().toString();
        EditText ocupacion = findViewById(R.id.editText_ocupacion);
        cadena_ocupacion = ocupacion.getText().toString();
        Spinner sexo = findViewById(R.id.spinner_sexo);
        cadena_sexo = sexo.getSelectedItem().toString();
        if(cadena_nombre.isEmpty()){
            nombre.setError("Debe llenar este campo");
            Toast.makeText(this, "Debe llenar ciertos campos", Toast.LENGTH_SHORT).show();
        }
        else if(cadena_telefono.isEmpty()){
            telefono.setError("Debe llenar este campo");
            Toast.makeText(this, "Debe llenar ciertos campos", Toast.LENGTH_SHORT).show();
        }
        else if(cadena_cedula.isEmpty()){
            cedula.setError("Debe llenar este campo");
            Toast.makeText(this, "Debe llenar ciertos campos", Toast.LENGTH_SHORT).show();
        }
        else if(cadena_direccion.isEmpty()){
            direccion.setError("Debe llenar este campo");
            Toast.makeText(this, "Debe llenar ciertos campos", Toast.LENGTH_SHORT).show();
        }
        else{
            nuevo.nombre = cadena_nombre;
            nuevo.telefono = cadena_telefono;
            nuevo.cedula = cadena_cedula;
            nuevo.direccion = cadena_direccion;
            nuevo.sexo = cadena_sexo;
            nuevo.ocupacion = cadena_ocupacion;
            nuevo.apellido = cadena_apellido;
            Intent intent = new Intent();
            intent.putExtra("cliente", (Serializable) nuevo);
            setResult(RESULT_OK, intent);
            finish();
        }

    }

    public void onClick_cancelar(View v){
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }


}
