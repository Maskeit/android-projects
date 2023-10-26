package com.maskeit.libreria;

import androidx.appcompat.app.AppCompatActivity;

import com.maskeit.libreria.Variables.VariablesClientes;
import com.maskeit.libreria.Variables.VariablesLibros;
import com.maskeit.libreria.Views.ListaClientes;
import com.maskeit.libreria.Views.ListaLibros;
import com.maskeit.libreria.databinding.ActivityClientesBinding;
import com.maskeit.libreria.databinding.ActivityLibrosBinding;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class Clientes extends AppCompatActivity implements View.OnClickListener{
    ActivityClientesBinding b;
    Conectar conectar;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityClientesBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        setTitle("Control de clientes");
        b.insertar.setOnClickListener(this);
        b.eliminar.setOnClickListener(this);
        b.ver.setOnClickListener(this);
        conectar = new Conectar(this, VariablesClientes.NOMBRE_BD,null,1);

    }

    @Override
    public void onClick(View v) {
        String nombre = b.camponombre.getText().toString();
        String rfc = b.camporfc.getText().toString();
        boolean b1 = !nombre.isEmpty() || !rfc.isEmpty();

        if(b1){
            if (v == b.insertar) {
                insertar();
            }
        } else {
            Toast.makeText(this,"Ingrese los datos primero", Toast.LENGTH_LONG).show();
        }

        if (v == b.ver) {
            i = new Intent(Clientes.this, ListaClientes.class);
            startActivity(i);
            Log.d("MainActivity", "Iniciando actividad Lista");
        }
        }


    }
}
