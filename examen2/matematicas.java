package com.maskeit.examen2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class matematicas extends AppCompatActivity implements View.OnClickListener{


    String nombre;
    Button calificar, regresar;
    RadioGroup radioGroup1, radioGroup2, radioGroup3, radioGroup4;
    String selectRespuesta1 = "";
    Double pregunta1;
    String selectRespuesta2 = "";
    Double pregunta2;
    String selectRespuesta3 = "";
    Double pregunta3;
    String selectRespuesta4 = "";
    Double pregunta4;

    Double res;
    Intent i;
    Toast mensaje;
    int checkedRadioButtonId1;
    int checkedRadioButtonId2;
    int checkedRadioButtonId3;
    int checkedRadioButtonId4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matematicas);

        // Inicializar las variables después de inflar la vista
        radioGroup1 = findViewById(R.id.radio_group1);
        radioGroup2 = findViewById(R.id.radio_group2);
        radioGroup3 = findViewById(R.id.radio_group3);
        radioGroup4 = findViewById(R.id.radio_group4);

        // Obtener el nombre del intent
        nombre = getIntent().getStringExtra("Nombre");

        // Asignar el listener al botón calificar
        calificar = (Button) findViewById(R.id.resultado);
        calificar.setOnClickListener(this);

        // Mover aquí la obtención de checkedRadioButtonId
        checkedRadioButtonId1 = radioGroup1.getCheckedRadioButtonId();
        checkedRadioButtonId2 = radioGroup2.getCheckedRadioButtonId();
        checkedRadioButtonId3 = radioGroup3.getCheckedRadioButtonId();
        checkedRadioButtonId4 = radioGroup4.getCheckedRadioButtonId();

        // Asignar las respuestas solo si los RadioButton están seleccionados
        if (checkedRadioButtonId1 != -1) {
            RadioButton checkedRadioButton = findViewById(checkedRadioButtonId1);
            selectRespuesta1 = checkedRadioButton.getText().toString();
        }

        if (checkedRadioButtonId2 != -1) {
            RadioButton checkedRadioButton = findViewById(checkedRadioButtonId2);
            selectRespuesta2 = checkedRadioButton.getText().toString();
        }

        if (checkedRadioButtonId3 != -1) {
            RadioButton checkedRadioButton = findViewById(checkedRadioButtonId3);
            selectRespuesta3 = checkedRadioButton.getText().toString();
        }

        if (checkedRadioButtonId4 != -1) {
            RadioButton checkedRadioButton = findViewById(checkedRadioButtonId4);
            selectRespuesta4 = checkedRadioButton.getText().toString();
        }

        // Asignar el listener al botón regresar
        regresar = (Button) findViewById(R.id.regresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    @Override
    public void onClick(View view) {
        if("radio_R3Q1".equals(selectRespuesta1)){
            pregunta1 = 2.5;
        }else {
            pregunta1 = 0.0;
        }
        if("radio_R1Q2".equals(selectRespuesta2) ){
            pregunta2 = 2.5;
        }else {
            pregunta2 = 0.0;
        }
        if("radio_R3Q3".equals(selectRespuesta3) ){
            pregunta3 = 2.5;
        }else {
            pregunta3 = 0.0;
        }
        if("radio_R2Q4".equals(selectRespuesta4) ){
            pregunta4 = 2.5;
        }else {
            pregunta4 = 0.0;
        }

        if(!selectRespuesta1.isEmpty() && !selectRespuesta2.isEmpty() && !selectRespuesta3.isEmpty() && !selectRespuesta4.isEmpty() ){
            res = calificacion(pregunta1,pregunta2,pregunta3,pregunta4);
        }
        String califRes = res.toString();
        if (!califRes.isEmpty()){
            i = new Intent(matematicas.this, calificacion.class);
            i.putExtra("Nombre", nombre);
            i.putExtra("Calificacion", califRes);
            startActivity(i);
        }else {
            mensaje = Toast.makeText(matematicas.this, "Faltan preguntas por contestar",Toast.LENGTH_LONG);
        }
        mensaje.show();

    }
    public Double calificacion(Double r1, Double r2, Double r3, Double r4){
        Double resultado = r1+r2+r3+r4;
        return resultado;
    }

}
