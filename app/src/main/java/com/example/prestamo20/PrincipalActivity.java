package com.example.prestamo20;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    public static List<Client> lista_clientes = new ArrayList<>(); //Almacenara todos los clientes que guardemos
   public static List<Prestamo> lista_prestamo = new ArrayList<>();
   public static TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        tv = findViewById(R.id.tv_acciones);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Con esta funcion sabemos que item del menu ha sido cliqueado
        switch (item.getItemId()) { //Verificamos que item ha seleccionado el usuario
            case R.id.mn_icon_nuevo:
                Intent intent = new Intent(this, MainActivity.class);
                startActivityForResult(intent, 1234);
                break;
            case R.id.mn_nuevo_prestamo:
                if(lista_clientes.size() == 0){ //Si no se han ingresado clientes no abrimos el activity
                    Toast.makeText(this, "Primero debe ingresar un cliente", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent1 = new Intent(this, CalcularPrestamoActivity.class);
                    startActivityForResult(intent1, 2222);
                }
                break;
            case R.id.mn_acerca_de:
                Toast.makeText(this, "Electiva Android", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mn_ver_clientes:
                if(lista_clientes.size() == 0){ //Si no se han ingresado clientes no abrimos el activity
                    Toast.makeText(this, "No se han ingresado clientes", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent2 = new Intent(this, Ver_Clientes_Activity.class);
                    startActivity(intent2);
                }
                break;
            case R.id.mc_ver_prestamos:
                if(lista_prestamo.size() == 0){ //Si no se han registrado prestamos no abrimos la activity
                    Toast.makeText(this, "No se han ingresado prestamos", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent3 = new Intent(this, Ver_Prestamo_Activity.class);
                    startActivity(intent3);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { //Obtenemos los valores que nos envia la activity a la que llamamos
        if(requestCode==1234){ //El codigo que enviamos a la activity que registra los clientes
            if(resultCode==0){ //Si el usuario da clic en cancelar
                tv.append("Cancelo ingreso de cliente\n");
                registerForContextMenu(tv);
            }
            else{ //Si el usuario da clic en guardar
                Client nuevo = (Client) data.getExtras().getSerializable("cliente"); //Obtenemos los datos que nos envia la activity donde se registran los clientes
                lista_clientes.add(nuevo); //Añadimos el cliente a la lista de clientes
                tv.append("Ingreso de cliente" + " "+ nuevo.nombre + "\n");
                registerForContextMenu(tv);
            }
        }
        else{ //Si es el codigo que le mandamos a la activity que registra los prestamos
            if(resultCode==0){ //Si el usuario da clic en cancelar
                tv.append("Cancelo ingreso de prestamo\n");
                registerForContextMenu(tv);
            }
            else{ //Si el usuario da clic en guardar
                Prestamo ptr = (Prestamo) data.getExtras().getSerializable("prestamo"); //Obtenemos los datos que nos envia la activity donde se registran los prestamos
                lista_prestamo.add(ptr); //Añadimos el prestamo a la lista de prestamos
                tv.append("Ingreso de nuevo prestamo \n");
                registerForContextMenu(tv);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.contextual, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.ctm_borrar:
                tv.setText("");
                break;
            case R.id.ctm_copiar:
                ClipboardManager clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

                ClipData clipData = ClipData.newPlainText("Historial", tv.getText().toString());

                clipboardManager.setPrimaryClip(clipData);
                break;
        }
        return super.onContextItemSelected(item);
    }
}
