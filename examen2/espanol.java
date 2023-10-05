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

public class espanol extends AppCompatActivity implements View.OnClickListener{

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
        setContentView(R.layout.activity_espanol);

        //inicializar las variables después de inflar la vista
        radioGroup1 = (RadioGroup) findViewById(R.id.radio_group1);
        radioGroup2 = (RadioGroup) findViewById(R.id.radio_group2);
        radioGroup3 = (RadioGroup) findViewById(R.id.radio_group3);
        radioGroup4 = (RadioGroup) findViewById(R.id.radio_group4);

        nombre = getIntent().getStringExtra("Nombre");

        //asignar el listener al botón calificar
        calificar = (Button) findViewById(R.id.resultado);
        calificar.setOnClickListener(this);

        //obtener el ID del RadioButton seleccionado de cada grupo
        checkedRadioButtonId1 = radioGroup1.getCheckedRadioButtonId();
        checkedRadioButtonId2 = radioGroup2.getCheckedRadioButtonId();
        checkedRadioButtonId3 = radioGroup3.getCheckedRadioButtonId();
        checkedRadioButtonId4 = radioGroup4.getCheckedRadioButtonId();

        //asignar el listener al botón regresar
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
        // Obtener el ID del RadioButton seleccionado de cada grupo al hacer clic
        checkedRadioButtonId1 = radioGroup1.getCheckedRadioButtonId();
        checkedRadioButtonId2 = radioGroup2.getCheckedRadioButtonId();
        checkedRadioButtonId3 = radioGroup3.getCheckedRadioButtonId();
        checkedRadioButtonId4 = radioGroup4.getCheckedRadioButtonId();

        // Asegurarse de que haya un RadioButton seleccionado en cada grupo antes de obtener el texto
        if (checkedRadioButtonId1 != -1 && checkedRadioButtonId2 != -1 && checkedRadioButtonId3 != -1 && checkedRadioButtonId4 != -1) {
            selectRespuesta1 = ((RadioButton) findViewById(checkedRadioButtonId1)).getText().toString();
            selectRespuesta2 = ((RadioButton) findViewById(checkedRadioButtonId2)).getText().toString();
            selectRespuesta3 = ((RadioButton) findViewById(checkedRadioButtonId3)).getText().toString();
            selectRespuesta4 = ((RadioButton) findViewById(checkedRadioButtonId4)).getText().toString();

            // Verificar que ninguna respuesta esté vacía
            if (!selectRespuesta1.isEmpty() && !selectRespuesta2.isEmpty() && !selectRespuesta3.isEmpty() && !selectRespuesta4.isEmpty()) {
                pregunta1 = ("27".equals(selectRespuesta1)) ? 2.5 : 0.0;
                pregunta2 = ("AEIOU".equals(selectRespuesta2)) ? 2.5 : 0.0;
                pregunta3 = ("camion".equals(selectRespuesta3)) ? 2.5 : 0.0;
                pregunta4 = ("Hay".equals(selectRespuesta4)) ? 2.5 : 0.0;

                res = calificacionRes(pregunta1, pregunta2, pregunta3, pregunta4);
                String califRes = res.toString();

                if (!califRes.isEmpty()) {
                    // Lanzar la actividad de calificación
                    i = new Intent(espanol.this, calificacion.class);
                    i.putExtra("Nombre", nombre);
                    i.putExtra("Calificacion", califRes);
                    startActivity(i);
                } else {
                    mensaje = Toast.makeText(espanol.this, "Faltan preguntas por contestar", Toast.LENGTH_LONG);
                    mensaje.show();
                }
            }
        } else {
            mensaje = Toast.makeText(espanol.this, "Faltan preguntas por contestar", Toast.LENGTH_LONG);
            mensaje.show();
        }
    }



    public Double calificacionRes(Double r1, Double r2, Double r3, Double r4){
        Double resultado = r1+r2+r3+r4;
        return resultado;
    }

}