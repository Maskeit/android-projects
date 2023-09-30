package com.maskeit.actividad5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class calorias extends AppCompatActivity implements View.OnClickListener {
    TextView txtnombre, txtedad, txtsexo, resultado;
    EditText estatura, masa;
    Button calcular, regresar;
    String nombre, edad, sexo;
    double masakg, estaturacm;
    Toast alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorias);
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

        estatura = (EditText) findViewById(R.id.estatura);
        masa = (EditText) findViewById(R.id.masa);
        calcular = (Button) findViewById(R.id.calcular);
        resultado = (TextView) findViewById(R.id.resultado);

        regresar = (Button) findViewById(R.id.regresar);

        //Asigno el evento click al boton calcular
        calcular.setOnClickListener(this);
        regresar.setOnClickListener(this);

        // Este es el código que asigna el evento OnBackPressed al botón regresar
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    public Double tmbMujer(double masa, double altura, int edad){
        double calc1 = (10 * masa);
        double calc2 = (6.25*altura);
        double calc3 = (5*edad);
        double calc = calc1 + calc2 - calc3 -161;
        return calc;
    }
    public Double tmbHombre(double masa, double altura, int edad){
        double calc1 = (10 * masa);
        double calc2 = (6.25*altura);
        double calc3 = (5*edad);
        double calc = calc1 + calc2 - calc3 +5;
        return calc;
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        String unit = " Kcal/dia";

        if(id == R.id.calcular){
            // Calcular calorias diarias basales: Tasa Metabolica Basal
            String peso = masa.getText().toString();
            String altura = estatura.getText().toString();
            masakg = Double.parseDouble(peso);
            estaturacm = Double.parseDouble(altura);
            int age = Integer.parseInt(edad);

            if(!peso.isEmpty() && !altura.isEmpty()){
                if ("Hombre".equals(sexo)){
                    resultado.setText(tmbHombre(masakg,estaturacm,age) + unit);
                }else if ("Mujer".equals(sexo)) {
                    resultado.setText(tmbMujer(masakg, estaturacm, age) + unit);
                }
            }else{
                alert = Toast.makeText(calorias.this, "Completa todos los campos.", Toast.LENGTH_LONG);
                alert.show();
            }
        }
    }


}
