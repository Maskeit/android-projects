package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText uno, dos;
    Button sumar, restar, multiplicar, dividir, reiniciar, exponente, signoUno, signoDos;
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
        exponente = (Button) findViewById(R.id.exp);

        signoUno = (Button) findViewById(R.id.signoUno);
        signoDos = (Button) findViewById(R.id.signoDos);

        sumar.setOnClickListener(this);
        restar.setOnClickListener(this);
        resultado.setOnClickListener(this);
        multiplicar.setOnClickListener(this);
        dividir.setOnClickListener(this);
        reiniciar.setOnClickListener(this);
        exponente.setOnClickListener(this);
        signoUno.setOnClickListener(this);
        signoDos.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        primero = uno.getText().toString();
        segundo = dos.getText().toString();
        if (primero.isEmpty() || segundo.isEmpty()) {
            //Mostrar un mensaje de error o tomar alguna acción apropiada
            resultado.setText("Ingrese ambos números por favor");
            return;
        }
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
        }else if(view.getId()==R.id.exp){
            resultado.setText(primero + "exp^" + segundo + " = " + (Math.pow(n1,n2)) +"");
        }else if (view.getId()==R.id.reiniciar) {
            uno.setText("");
            dos.setText("");
            resultado.setText("0");
        } else if (view.getId()==R.id.signoUno) {
            //Cambiar el signo del primer EditText (uno)
            n1 = -n1;
            uno.setText(String.valueOf(n1));
        } else if (view.getId()==R.id.signoDos) {
            //Cambiar el signo del segundo EditText (dos)
            n2 = -n2;
            dos.setText(String.valueOf(n2));
        }
    }
}