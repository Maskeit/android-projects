package com.maskeit.libreria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.maskeit.libreria.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ActivityMainBinding b;
    Intent libros;
    Intent clientes;
    Intent ventas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        b.activityClientes.setOnClickListener(this);
        b.activityLibros.setOnClickListener(this);
        b.activityVentas.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == b.activityLibros){
            libros = new Intent(MainActivity.this, Libros.class);
            startActivity(libros);
            Toast.makeText(this,"Inicia LibrosModel", Toast.LENGTH_LONG).show();
        }
        if (v == b.activityClientes) {
            clientes = new Intent(MainActivity.this, Clientes.class);
            startActivity(clientes);
            Toast.makeText(this,"Inicia clientes", Toast.LENGTH_LONG).show();

        }
        if (v == b.activityVentas) {
            ventas = new Intent(MainActivity.this, Libros.class);
            startActivity(ventas);
            Toast.makeText(this,"Inicia VentasModel", Toast.LENGTH_LONG).show();
        }
    }
}
