package com.maskeit.actividad5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class boston extends AppCompatActivity implements View.OnClickListener{
    TextView txtnombre, txtedad, txtsexo, resultado;
    EditText estatura, masa;
    Button calcular, regresar;
    String nombre, edad, sexo;
    double masakg, estaturacm;
    Toast alert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boston);
        txtnombre = (TextView) findViewById(R.id.txtnombre);
        txtedad = (TextView) findViewById(R.id.txtedad);
        txtsexo = (TextView) findViewById(R.id.txtsexo);
        resultado = (TextView) findViewById(R.id.resultado);
    }

    @Override
    public void onClick(View view) {

    }
}

//c) Diseñar y programar Activity boston
//        TextView Nombre: (Viene de MainActivity)
//        TextView Edad : (Viene de MainActivity)
//        TextView Sexo : (Viene de MainActivity)
//        botón "Tiempo requerido"
//        TextView (resultado de dar click en Tiempo requerido)
//        botón "Salir" (Regresar al activity principal)
