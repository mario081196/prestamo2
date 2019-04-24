package com.example.prestamo20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Ver_Prestamo_Activity extends AppCompatActivity {
    public static int posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver__prestamo_);
        posicion = 0;
        verPrestamos(posicion);
    }

   public void verPrestamos(int posicion){ //Mostramos los datos de los prestamos en la activity
        Prestamo pt = PrincipalActivity.lista_prestamo.get(posicion);
        TextView cliente = findViewById(R.id.txt_cliente);
        cliente.setText(pt.cliente);
        TextView monto = findViewById(R.id.txt_monto);
        monto.setText(pt.monto);
        TextView interes = findViewById(R.id.txt_interes);
        interes.setText(pt.interes);
        TextView fecha_inicio = findViewById(R.id.txt_fecha_inicio);
        fecha_inicio.setText(pt.fecha_inicio);
        TextView fecha_fin = findViewById(R.id.txt_fecha_fin);
        fecha_fin.setText(pt.fecha_fin);
        TextView monto_pagar = findViewById(R.id.txt_monto_pagar);
        monto_pagar.setText(pt.monto_pagar);
        TextView monto_cuota = findViewById(R.id.txt_monto_cuota);
        monto_cuota.setText(pt.monto_cuota);
        TextView plazo = findViewById(R.id.txt_plazo);
        plazo.setText(pt.plazo);
    }

    public void onClick_siguiente(View v){
        if(posicion == (PrincipalActivity.lista_prestamo.size() - 1)){ //Verificamos si esta al final de la lista
            Toast.makeText(this, "Llego al final", Toast.LENGTH_SHORT).show();
        }
        else{ //Si no esta al final de la lista recorremos una posicion
            posicion++;
            verPrestamos(posicion);
        }

    }

    public void onClick_anterior(View v){
        if(posicion==0){ //Verificamos que no esta al inicio de la lista
            Toast.makeText(this, "Llego al inicio", Toast.LENGTH_SHORT).show();
        }
        else{ //Si no esta al inicio de la lista decrementamos la posicion
            posicion--;
            verPrestamos(posicion);
        }
    }
}
