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
    Button calculo;
    Toast mensaje;
    Intent i;
    Boolean existe = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Ingresa tus datos");

        nombre = findViewById(R.id.nombre);
        edad = findViewById(R.id.edad);
        calculo = findViewById(R.id.go_calcular);

        final RadioGroup radioGroup = findViewById(R.id.radio_group);

        calculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nombre.getText().toString();
                age = edad.getText().toString();

                // Obtener el género seleccionado
                String selectedGender = "";
                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if (checkedRadioButtonId != -1) {
                    RadioButton checkedRadioButton = findViewById(checkedRadioButtonId);
                    selectedGender = checkedRadioButton.getText().toString();
                }

                if (!name.isEmpty() && !age.isEmpty() && !selectedGender.isEmpty()) {
                    i = new Intent(MainActivity.this, calorias.class);
                    i.putExtra("Nombre", name);
                    i.putExtra("Edad", age);
                    i.putExtra("Genero", selectedGender);
                    startActivity(i);
                    existe = false;
                    mensaje = Toast.makeText(MainActivity.this, "Hola", Toast.LENGTH_LONG);
                } else {
                    mensaje = Toast.makeText(MainActivity.this, "Faltan datos por rellenar", Toast.LENGTH_LONG);
                }
                mensaje.show();
            }
        });
    }
}
