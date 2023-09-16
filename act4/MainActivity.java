package com.example.calculadoraintermedia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button sumar, restar, multiplicar, dividir, tigual,
            t0,t1,t2,t3,t4,t5,t6,t7,t8,t9,
            tpunto,traiz, tpotencia, tborrar, tborrartodo;
    Double op1, op2, operador;
    String primero, segundo;
    EditText num;
    TextView resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num = (EditText) findViewById(R.id.numero);
        // Operadores
        sumar = (Button) findViewById(R.id.tsumar);
        restar = (Button) findViewById(R.id.trestar);
        multiplicar = (Button) findViewById(R.id.tmultiplicar);
        dividir = (Button) findViewById(R.id.tdividir);
        tigual = (Button) findViewById(R.id.tigual);
        tpunto = (Button) findViewById(R.id.tpunto);
        traiz = (Button) findViewById(R.id.traiz);
        tpotencia = (Button) findViewById(R.id.tpotencia);
        // Limpiar
        tborrar = (Button) findViewById(R.id.tborrar);
        tborrartodo = (Button) findViewById(R.id.tborrartodo);
        // Teclado numerico
        t0 = (Button) findViewById(R.id.t0);
        t1 = (Button) findViewById(R.id.t1);
        t2 = (Button) findViewById(R.id.t2);
        t3 = (Button) findViewById(R.id.t3);
        t4 = (Button) findViewById(R.id.t4);
        t5 = (Button) findViewById(R.id.t5);
        t6 = (Button) findViewById(R.id.t6);
        t7 = (Button) findViewById(R.id.t7);
        t8 = (Button) findViewById(R.id.t8);
        t9 = (Button) findViewById(R.id.t9);

        //asigar el evento Click a los botones
        sumar.setOnClickListener(this);
        restar.setOnClickListener(this);
        multiplicar.setOnClickListener(this);
        dividir.setOnClickListener(this);
        tigual.setOnClickListener(this);
        tpunto.setOnClickListener(this);
        traiz.setOnClickListener(this);
        tpunto.setOnClickListener(this);
        tpotencia.setOnClickListener(this);
        tborrar.setOnClickListener(this);
        tborrartodo.setOnClickListener(this);

        t0.setOnClickListener(this);
        t1.setOnClickListener(this);
        t2.setOnClickListener(this);
        t3.setOnClickListener(this);
        t4.setOnClickListener(this);
        t5.setOnClickListener(this);
        t6.setOnClickListener(this);
        t7.setOnClickListener(this);
        t8.setOnClickListener(this);
        t9.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        primero = num.getText().toString();

    }
}

