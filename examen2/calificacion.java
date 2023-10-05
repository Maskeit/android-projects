package com.maskeit.examen2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class calificacion extends AppCompatActivity {
    String nombre, resultado;
    Button regresar;

    TextView name,result;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificacion);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nombre = extras.getString("Nombre");
            resultado = extras.getString("Calificacion");
        }


        Double calificacion = Double.parseDouble(resultado);
        String aprobacion;
        if(calificacion >5.0 && calificacion<=10.0  || calificacion >5 && calificacion<=10  ){
            aprobacion = "Aprobado";
        }else {
            aprobacion = "Reprobado";
        }

        name = (TextView) findViewById(R.id.txtnombre);
        result = (TextView) findViewById(R.id.txtresultado);

        name.setText(nombre);
        result.setText(resultado);

        regresar = (Button) findViewById(R.id.regresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
