package com.maskeit.etapavida;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nombre, dia, mes, anio;
    String name, day, month, year;
    Button etapa;
    Intent i;
    Toast mensaje;
    Boolean limpiar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = (EditText) findViewById(R.id.nombre);
        dia = (EditText) findViewById(R.id.dia);
        mes = (EditText) findViewById(R.id.mes);
        anio = (EditText) findViewById(R.id.anio);

        etapa = (Button) findViewById(R.id.btnenviar);

        etapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                name = nombre.getText().toString();
                day = dia.getText().toString();
                month = mes.getText().toString();
                year = anio.getText().toString();


                if(!name.isEmpty() && !day.isEmpty() && !month.isEmpty() && !year.isEmpty()){
                    i = new Intent(MainActivity.this, etapa.class);
                    i.putExtra("Nombre", name);
                    i.putExtra("Dia", day);
                    i.putExtra("Mes", month);
                    i.putExtra("Año",year);
                    startActivity(i);
                    mensaje = Toast.makeText(MainActivity.this, "Bienvenido!", Toast.LENGTH_LONG);
                    limpiar = true;
                    if(limpiar){
                        nombre.setText("");
                        dia.setText("");
                        mes.setText("");
                        anio.setText("");
                        limpiar = false;
                    }
                }else {
                    mensaje = Toast.makeText(MainActivity.this, "Faltan datos por rellenar", Toast.LENGTH_LONG);
                }
                mensaje.show();
            }
        });
    }

}
