package com.example.loginbasico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class bienvenida extends AppCompatActivity {
    TextView txt;
    String nombre, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);
        txt = (TextView) findViewById(R.id.txtbienvenida);
        //Recibimos parametro de la actividad que llamo a bienvenida
        nombre = getIntent().getStringExtra("usuario");
        pass = getIntent().getStringExtra("pass");
        txt.setText("Bienvenido " + nombre + " con contrasena " + pass);

    }
}
