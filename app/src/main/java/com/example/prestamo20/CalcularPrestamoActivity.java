package com.example.prestamo20;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CalcularPrestamoActivity extends AppCompatActivity {

    private Spinner interes;
    private TextView monto_a_pagar;
    private EditText monto;
    private EditText plazo;
    private TextView cuota;
    private TextView fecha_fin;
    private String date;
    public Spinner sp;
    public static List<String> clientes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_prestamo);

        date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        TextView fecha = findViewById(R.id.textView_fecha);
        plazo = findViewById(R.id.editText_plazo);
        monto = findViewById(R.id.editText_monto);
        monto_a_pagar = findViewById(R.id.textViewMontoPagar);
        interes = findViewById(R.id.spinner_interes);
        fecha_fin = findViewById(R.id.textView_fechaFin);
        cuota = findViewById(R.id.textViewMontoCuota);
        fecha.setText(date);
        fecha_de_pago();
        Cargar_Clientes();
        plazo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                pago();
                fecha_de_pago();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        monto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                pago();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        interes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pago();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void pago(){
        int aux = 0;
        if(monto.getText().toString().length()!=0)
            aux=Integer.valueOf(monto.getText().toString());
        int aux_interes = Integer.valueOf(interes.getSelectedItem().toString());
        int plazo_interes = 0;
        if(plazo.getText().length()!=0){
            plazo_interes = Integer.valueOf(plazo.getText().toString());
        }
        int interes_final = ((aux*aux_interes)/100) * plazo_interes;
        Double total = Double.valueOf(aux + interes_final);
        String monto_string = String.valueOf(total);
        monto_a_pagar.setText(monto_string);
        Double aux_plazo = 0.0;
        if(plazo.getText().toString().length()!=0){
            int plazo_aux = Integer.valueOf(plazo.getText().toString());
            aux_plazo = Double.valueOf((total) / (plazo_aux));
            cuota.setText(String.valueOf(aux_plazo));

        }
        else{
            cuota.setText(String.valueOf(total));
        }
    }

    public void fecha_de_pago(){
        Calendar fechaActual = Calendar.getInstance();
        String fecha_hoy;
        int año = fechaActual.get(Calendar.YEAR);
        int mes = fechaActual.get(Calendar.MONTH);
        mes++;
        int aux;
        int dia = fechaActual.get(Calendar.DAY_OF_MONTH);
        if(plazo.getText().toString().isEmpty()){
            mes++;
            fecha_hoy = (String.valueOf(dia)) + "/" + (String.valueOf(mes)) + "/" + (String.valueOf(año));
            fecha_fin.setText(fecha_hoy);
        }
        else{
            aux = Integer.valueOf(plazo.getText().toString());
            fechaActual.add(Calendar.MONTH, aux);
            año = fechaActual.get(Calendar.YEAR);
            mes = fechaActual.get(Calendar.MONTH);
            mes++;
            dia = fechaActual.get(Calendar.DAY_OF_MONTH);
            fecha_hoy = (String.valueOf(dia)) + "/" + (String.valueOf(mes)) + "/" + (String.valueOf(año));
            fecha_fin.setText(fecha_hoy);
        }

    }

    public void onClick_guardar(View v){
        String aux_monto = monto.getText().toString();
        String aux_plazo = plazo.getText().toString();
        if((aux_monto.isEmpty()) || (aux_plazo.isEmpty())){
            Toast.makeText(this, "Debe llenar los campos Monto y Plazo", Toast.LENGTH_SHORT).show();
        }
        else{
            Prestamo prest = new Prestamo();
            prest.cliente = sp.getSelectedItem().toString();
            prest.monto = aux_monto;
            prest.plazo = aux_plazo;
            interes = findViewById(R.id.spinner_interes);
            prest.interes = interes.getSelectedItem().toString();
            TextView fecha = findViewById(R.id.textView_fecha);
            prest.fecha_inicio = fecha.getText().toString();
            prest.fecha_fin = fecha_fin.getText().toString();
            TextView monto_cuota = findViewById(R.id.textViewMontoCuota);
            prest.monto_cuota = monto_cuota.getText().toString();
            prest.monto_pagar = monto_a_pagar.getText().toString();
            Intent intent = new Intent();
            intent.putExtra("prestamo", prest);
            setResult(RESULT_OK, intent);
            clientes.removeAll(clientes);
            finish();
        }

    }

    public void onClick_cancelar(View v){
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        clientes.removeAll(clientes);
        finish();
    }

    public void Cargar_Clientes(){
        sp = findViewById(R.id.sp_clientes);
        for(int i=0; i<PrincipalActivity.lista_clientes.size(); i++){
            String aux = PrincipalActivity.lista_clientes.get(i).nombre + " " +
                    PrincipalActivity.lista_clientes.get(i).apellido;
            clientes.add(aux);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, clientes);

        sp.setAdapter(adapter);
    }

}
