package com.example.prestamo20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Ver_Clientes_Activity extends AppCompatActivity {
    public static int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver__clientes_);
        a = 0;
        verClientes(a);
    }

    public void verClientes(int posicion){
        Client cl = PrincipalActivity.lista_clientes.get(posicion);
        TextView nombre = findViewById(R.id.txt_nombre);
        nombre.setText(cl.nombre);
        TextView apellido = findViewById(R.id.txt_apellido);
        apellido.setText(cl.apellido);
        TextView telefono = findViewById(R.id.txt_telefono);
        telefono.setText(cl.telefono);
        TextView sexo = findViewById(R.id.txt_sexo);
        sexo.setText(cl.sexo);
        TextView cedula = findViewById(R.id.txt_cedula);
        cedula.setText(cl.cedula);
        TextView direccion = findViewById(R.id.txt_direccion);
        direccion.setText(cl.direccion);
        TextView ocupacion= findViewById(R.id.txt_ocupacion);
        ocupacion.setText(cl.ocupacion);
    }

    public void onClick_siguiente(View v){
        if(a == (PrincipalActivity.lista_clientes.size() - 1)){
            Toast.makeText(this, "Llego al final", Toast.LENGTH_SHORT).show();
        }
        else{
            a++;
            verClientes(a);
        }
    }


    public void onClick_anterior(View v){
        if(a==0){
            Toast.makeText(this, "Llego al inicio", Toast.LENGTH_SHORT).show();
        }
        else{
            a--;
            verClientes(a);
        }
    }
}
