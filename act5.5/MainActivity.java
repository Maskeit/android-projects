package com.maskeit.actividad5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText nombre, edad;
    String name, age;
    Button calculo, boston;
    Toast mensaje;
    Intent i;
    Boolean existe = true;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Ingresa tus datos");

        nombre = findViewById(R.id.nombre);
        edad = findViewById(R.id.edad);
        calculo = findViewById(R.id.go_calcular);
        boston = findViewById(R.id.go_boston);

        radioGroup = findViewById(R.id.radio_group);


        calculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                processIntent(calorias.class);
            }
        });

        boston.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processIntent(boston.class);
            }
        });
    }

//    El método processIntent es una función que toma como parámetro la clase de
//    la actividad a la que deseas dirigirte (calorias.class o boston.class).
//    Simplifica el código al evitar la duplicación y mejorar la legibilidad.
    private void processIntent(Class<?> activityClass){
        name = nombre.getText().toString();
        age = edad.getText().toString();

        // Obtener el genero seleccionado
        String selectedGender = "";
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();

        if(checkedRadioButtonId != -1){
            RadioButton checkedRadioButton = findViewById(checkedRadioButtonId);
            selectedGender = checkedRadioButton.getText().toString();
        }

        if (!name.isEmpty() && !age.isEmpty() && !selectedGender.isEmpty()) {
            i = new Intent(MainActivity.this, activityClass);
            i.putExtra("Nombre", name);
            i.putExtra("Edad", age);
            i.putExtra("Genero", selectedGender);
            startActivity(i);
            existe = false;
            mensaje = Toast.makeText(MainActivity.this, "Bienvenido!", Toast.LENGTH_LONG);
        } else {
            mensaje = Toast.makeText(MainActivity.this, "Faltan datos por rellenar", Toast.LENGTH_LONG);
        }
        mensaje.show();
    }
}
