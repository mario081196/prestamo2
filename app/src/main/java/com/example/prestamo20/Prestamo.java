package com.example.prestamo20;

import java.io.Serializable;

public class Prestamo implements Serializable {
    String cliente;
    String monto;
    String interes;
    String plazo;
    String fecha_inicio;
    String fecha_fin;
    String monto_pagar;
    String monto_cuota;


    public Prestamo(){
        cliente = "";
        monto = "";
        interes = "";
        plazo = "";
        fecha_inicio = "";
        fecha_fin = "";
        monto_pagar = "";
        monto_cuota = "";
    }
}
