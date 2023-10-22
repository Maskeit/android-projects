package com.maskeit.examen2;

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
    EditText nombre;
    String name;
    Button aplicar;
    Intent i;
    RadioGroup radioGroup;
    Toast mensaje;
    String selectMateria;
    //int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Ingresa tus datos");

        nombre = findViewById(R.id.nombre);
        aplicar = findViewById(R.id.go_examen);
        radioGroup = findViewById(R.id.radio_group);

        aplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if (checkedRadioButtonId != -1) {
                    RadioButton checkedRadioButton = findViewById(checkedRadioButtonId);
                    selectMateria = checkedRadioButton.getText().toString();
                }

                if ("Matematicas".equals(selectMateria)) {
                    processIntent(matematicas.class);
                } else if ("Español".equals(selectMateria)) {
                    processIntent(espanol.class);
                }
            }
        });

    }

    private void processIntent(Class<?> activityClass){
        name = nombre.getText().toString();

        if(!name.isEmpty() && !selectMateria.isEmpty()){
            i = new Intent(MainActivity.this, activityClass);
            i.putExtra("Nombre", name);
            i.putExtra("Materia", selectMateria);
            startActivity(i);
            mensaje = Toast.makeText(MainActivity.this, "Bienvenido a aplicar " + selectMateria, Toast.LENGTH_LONG);
        } else {
            mensaje = Toast.makeText(MainActivity.this, "Faltan datos por rellenar", Toast.LENGTH_LONG);
        }
        mensaje.show();
    }
}