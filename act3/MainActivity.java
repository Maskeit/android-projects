package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText uno, dos;
    Button sumar, restar, multiplicar, dividir, reiniciar;
    TextView resultado;
    String primero, segundo;
    double n1, n2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uno = (EditText) findViewById(R.id.numero_uno);
        dos = (EditText) findViewById(R.id.numero_dos);
        sumar = (Button) findViewById(R.id.sumar);
        restar = (Button) findViewById(R.id.restar);
        multiplicar = (Button) findViewById(R.id.multiplicar);
        dividir = (Button) findViewById(R.id.dividir);
        resultado = (TextView) findViewById(R.id.resultado);
        reiniciar = (Button) findViewById(R.id.reiniciar);

        sumar.setOnClickListener(this);
        restar.setOnClickListener(this);
        resultado.setOnClickListener(this);
        multiplicar.setOnClickListener(this);
        dividir.setOnClickListener(this);
        reiniciar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        primero = uno.getText().toString();
        segundo = dos.getText().toString();
        n1 = Double.parseDouble(primero);
        n2 = Double.parseDouble(segundo);
        if(view.getId()==R.id.sumar){
            resultado.setText(primero + " + " + segundo + " = " + (n1+n2) +"");
        }else if(view.getId()==R.id.restar){
            resultado.setText(primero + " - " + segundo + " = " + (n1-n2) +"");
        }else if(view.getId()==R.id.multiplicar){
            resultado.setText(primero + " * " + segundo + " = " + (n1*n2) +"");
        }else if(view.getId()==R.id.dividir){
            resultado.setText(primero + " / " + segundo + " = " + (n1/n2) +"");
        } else if (view.getId()==R.id.reiniciar) {
            n1 = 0;
            n2 = 0;
            
            resultado.setText("0");
        }
    }
}
