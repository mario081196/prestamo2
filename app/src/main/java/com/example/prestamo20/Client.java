package com.example.prestamo20;

import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;

public class Client implements Serializable {
    public String nombre;
    public String apellido;
    public String telefono;
    public String sexo;
    public String cedula;
    public String ocupacion;
    public String direccion;

    public Client(){
        this.nombre = "";
        this.apellido = "";
        this.telefono = "";
        this.sexo = "";
        this.cedula = "";
        this.ocupacion = "";
        this.direccion = "";
    }
}
