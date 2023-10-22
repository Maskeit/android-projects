package com.maskeit.actividad5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class boston extends AppCompatActivity implements View.OnClickListener{
    TextView txtnombre, txtedad, txtsexo, resultado;
    Button calcular, regresar;
    String nombre, edad, sexo;

    Integer ageGroup;
    Double menStand, womStand;
    Toast alert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boston);
        txtnombre = (TextView) findViewById(R.id.txtnombre);
        txtedad = (TextView) findViewById(R.id.txtedad);
        txtsexo = (TextView) findViewById(R.id.txtsexo);
        //Recibimos parametro de la MainActivity
        nombre = getIntent().getStringExtra("Nombre");
        edad = getIntent().getStringExtra("Edad");
        sexo = getIntent().getStringExtra("Genero");
        txtnombre.setText("Nombre: " + nombre);
        txtedad.setText("Edad: " + edad);
        txtsexo.setText("Sexo: "+ sexo);

        calcular = (Button) findViewById(R.id.calcular);
        resultado = (TextView) findViewById(R.id.resultado);
        regresar = (Button) findViewById(R.id.regresar);

        //Asigno el evento click al boton calcular
        calcular.setOnClickListener(this);
        // Este es el código que asigna el evento OnBackPressed al botón regresar
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    public Double calculoHoras(double edad, String sexo){

        if(edad >=18 && edad <=34){
            if("Hombre".equals(sexo)){
                return 3.00;
            } else if ("Mujer".equals(sexo)) {
                return 3.305;
            }
        }else if (edad >=35 && edad <=39) {
            if("Hombre".equals(sexo)){
                return 3.055;
            } else if ("Mujer".equals(sexo)) {
                return 3.355;
            }
        }
        else if (edad >=40 && edad <=44) {
                        if("Hombre".equals(sexo)){
                return 3.105;
            } else if ("Mujer".equals(sexo)) {
                return 3.405;
            }
        }else if (edad >=45 && edad <=49) {
                        if("Hombre".equals(sexo)){
                return 3.205;
            } else if ("Mujer".equals(sexo)) {
                return 3.505;
            }
        }else if (edad >=50 && edad <=54) {
                        if("Hombre".equals(sexo)){
                return 3.255;
            } else if ("Mujer".equals(sexo)) {
                return 3.555;
            }
        }else if (edad >=55 && edad <=59) {
                        if("Hombre".equals(sexo)){
                return 3.355;
            } else if ("Mujer".equals(sexo)) {
                return 4.055;
            }
        }else if (edad >=60 && edad <=64) {
                        if("Hombre".equals(sexo)){
                return 3.505;
            } else if ("Mujer".equals(sexo)) {
                return 4.205;
            }
        }else if (edad >=65 && edad <=69) {
                        if("Hombre".equals(sexo)){
                return 4.055;
            } else if ("Mujer".equals(sexo)) {
                return 4.355;
            }
        }else if (edad >=70 && edad <=74) {
                        if("Hombre".equals(sexo)){
                return 4.205;
            } else if ("Mujer".equals(sexo)) {
                return 4.505;
            }
        }else if (edad >=75 && edad <=79) {
                        if("Hombre".equals(sexo)){
                return 4.355;
            } else if ("Mujer".equals(sexo)) {
                return 5.055;
            }
        }else if (edad >=80) {
                        if("Hombre".equals(sexo)){
                return 4.505;
            } else if ("Mujer".equals(sexo)) {
                return 5.205;
            }
        }
        return 0.0;
    }
    @Override
    public void onClick(View view) {
        // calculo de tiempo en horas requeridas para clasificar al maraton de Boston
        int id = view.getId();
        if(id == R.id.calcular){
            Double age = Double.parseDouble(edad);

            if(age<18){
                alert = Toast.makeText(boston.this, "No se puede clasificar con esta edad", Toast.LENGTH_LONG);
                alert.show();
            }else {
                Double clasificacion = calculoHoras(age,sexo);
                if(clasificacion != null){
                    // Formatear el resultado como horas y minutos
                    String resformat = formatearHoras(clasificacion);
                    resultado.setText("Tiempo requerido para clasificar: " + resformat + " Horas");
                }
            }
        }
    }

    private String formatearHoras(Double hours) {
        int intPart = hours.intValue();
        int minutesPart = (int) ((hours - intPart) * 100); // Convierte decimales a minutos

        // Formatear como "H:MM"
        return intPart + ":" + String.format("%02d", minutesPart);
    }
}