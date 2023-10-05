package com.maskeit.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner moneda1, moneda2;
    String[] opciones1 = {"moneda","MXM", "US", "Yen", "Yuan"};
    String[] opciones2 = {"moneda","MXM", "US", "Yen", "Yuan"};
    Button calcular;
    EditText div1;
    TextView div2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moneda1 = (Spinner) findViewById(R.id.moneda1);
        moneda2 = (Spinner) findViewById(R.id.moneda2);
        div1 = (EditText) findViewById(R.id.divisa1);
        calcular = (Button)findViewById(R.id.convertir);

        ArrayAdapter<String> aa = new ArrayAdapter<String>(
                MainActivity.this, android.R.layout.simple_dropdown_item_1line,
                opciones1);
        ArrayAdapter<String> ab = new ArrayAdapter<String>(
                MainActivity.this, android.R.layout.simple_dropdown_item_1line,
                opciones2);
        moneda1.setAdapter(aa);
        moneda1.setOnItemSelectedListener(this);
        moneda2.setAdapter(ab);
        moneda2.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if(i>0){
            if(adapterView.getId() == R.id.moneda1){
                Toast.makeText(MainActivity.this, "Spinner1" + " " +i + " " + opciones1[i],Toast.LENGTH_LONG).show();
            }

            if(adapterView.getId() == R.id.moneda2){
                Toast.makeText(MainActivity.this, "Spinner2" + " " +i + " " + opciones2[i],Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
