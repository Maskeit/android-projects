package com.maskeit.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner animales1, animales2;
    String[] opciones1 = {"Oso", "Tigre", "Camello", "Burro"};
    String[] opciones2 = {"Miguel", "Alejandre", "Arreola"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animales1 = (Spinner) findViewById(R.id.animales1);
        animales2 = (Spinner) findViewById(R.id.animales2);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(
                MainActivity.this, android.R.layout.simple_dropdown_item_1line,
                opciones1);
        ArrayAdapter<String> ab = new ArrayAdapter<String>(
                MainActivity.this, android.R.layout.simple_dropdown_item_1line,
                opciones2);
        animales1.setAdapter(aa);
        animales1.setOnItemSelectedListener(this);
        animales2.setAdapter(ab);
        animales2.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if(i>0){
            if(adapterView.getId() == R.id.animales1){
                Toast.makeText(MainActivity.this, "Spinner1" + " " +i + " " + opciones1[i],Toast.LENGTH_LONG).show();
            }

            if(adapterView.getId() == R.id.animales2){
                Toast.makeText(MainActivity.this, "Spinner2" + " " +i + " " + opciones2[i],Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
