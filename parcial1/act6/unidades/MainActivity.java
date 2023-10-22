package com.maskeit.unidades;

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

    Spinner unidad1, unidad2;
    String[] units1 = {"Bit", "Byte", "Kilobyte", "Kibibyte", "Megabyte", "Mebibyte", "Gigabyte", "Gibibyte", "Terabyte", "Tebibyte", "Petabyte", "Pebibyte"};
    String[] units2 = {"Bit", "Byte", "Kilobyte", "Kibibyte", "Megabyte", "Mebibyte", "Gigabyte", "Gibibyte", "Terabyte", "Tebibyte", "Petabyte", "Pebibyte"};
    int seleccionado = 0;
    Button calcular;
    EditText div1;
    TextView div2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unidad1 = (Spinner) findViewById(R.id.unidad1);
        unidad2 = (Spinner) findViewById(R.id.unidad2);
        div1 = (EditText) findViewById(R.id.div1);
        div2 = (TextView) findViewById(R.id.div2);
        calcular = (Button)findViewById(R.id.convertir);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(
                MainActivity.this, android.R.layout.simple_dropdown_item_1line,
                units1);
        ArrayAdapter<String> ab = new ArrayAdapter<String>(
                MainActivity.this, android.R.layout.simple_dropdown_item_1line,
                units2);
        unidad1.setAdapter(aa);
        unidad1.setOnItemSelectedListener(this);
        unidad2.setAdapter(ab);
        unidad2.setOnItemSelectedListener(this);

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtenemos los tamaños de datos seleccionados
                String valor = div1.getText().toString();
                String unidad1 = units1[MainActivity.this.unidad1.getSelectedItemPosition()];
                String unidad2 = units2[MainActivity.this.unidad2.getSelectedItemPosition()];

                // validamos si se ingreso un valor
                if(!valor.isEmpty()){
                  double resultado = conversion(Double.parseDouble(valor), unidad1, unidad2);
                  // Mostrar el resultado en el TextView
                    div2.setText(String.format("%.9f " + unidad2+"s",resultado));
                }else {
                    Toast.makeText(MainActivity.this, "Ingresa un valor", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private double conversion(double valor, String unidad1, String unidad2) {
        double resultadoBits = 0;

        // Convertir unidad de entrada a bits
        switch (unidad1) {
            case "Bit":
                resultadoBits = valor;
                break;
            case "Byte":
                resultadoBits = valor * 8; // 1 byte = 8 bits
                break;
            case "Kilobyte":
                resultadoBits = valor * 8 * 1024; // 1 Kilobyte = 1024 bytes
                break;
            case "Kibibyte":
                resultadoBits = valor * 8 * 1024 * 8; // 1 Kibibyte = 8192 bits
                break;
            case "Megabyte":
                resultadoBits = valor * 8 * 1024 * 8e+6; // 1 Megabyte = 8e+6 bits
                break;
            case "Mebibyte":
                resultadoBits = valor * 8 * 1024 * 8.39e+6; // 1 Mebibyte = 8.39e+6 bits
                break;
            case "Gigabyte":
                resultadoBits = valor * 8 * 1024 * 8e+9; // 1 Gigabyte = 8e+9 bits
                break;
            case "Gibibyte":
                resultadoBits = valor * 8 * 1024 * 8.59e+9; // 1 Gibibyte = 8.59e+9 bits
                break;
            case "Terabyte":
                resultadoBits = valor * 8 * 1024 * 8e+12; // 1 Terabyte = 8e+12 bits
                break;
            case "Tebibyte":
                resultadoBits = valor * 8 * 1024 * 8.8e+12; // 1 Tebibyte = 8.8e+12 bits
                break;
            case "Petabyte":
                resultadoBits = valor * 8 * 1024 * 8e+15; // 1 Petabyte = 8e+15 bits
                break;
            case "Pebibyte":
                resultadoBits = valor * 8 * 1024 * 8.88e+15; // 1 Pebibyte = 8.88e+15 bits
                break;
        }

        // Convertir de bits a unidad de salida
        switch (unidad2) {
            case "Bit":
                return resultadoBits;
            case "Byte":
                return resultadoBits / 8; // 1 byte = 8 bits
            case "Kilobyte":
                return resultadoBits / (8 * 1024.0); // 1 Kilobyte = 1024 bytes
            case "Kibibyte":
                return resultadoBits / (8 * 1024.0 * 8); // 1 Kibibyte = 8192 bits
            case "Megabyte":
                return resultadoBits / (8 * 1024.0 * 8e+6); // 1 Megabyte = 8e+6 bits
            case "Mebibyte":
                return resultadoBits / (8 * 1024.0 * 8.39e+6); // 1 Mebibyte = 8.39e+6 bits
            case "Gigabyte":
                return resultadoBits / (8 * 1024.0 * 8e+9); // 1 Gigabyte = 8e+9 bits
            case "Gibibyte":
                return resultadoBits / (8 * 1024.0 * 8.59e+9); // 1 Gibibyte = 8.59e+9 bits
            case "Terabyte":
                return resultadoBits / (8 * 1024.0 * 8e+12); // 1 Terabyte = 8e+12 bits
            case "Tebibyte":
                return resultadoBits / (8 * 1024.0 * 8.8e+12); // 1 Tebibyte = 8.8e+12 bits
            case "Petabyte":
                return resultadoBits / (8 * 1024.0 * 8e+15); // 1 Petabyte = 8e+15 bits
            case "Pebibyte":
                return resultadoBits / (8 * 1024.0 * 8.88e+15); // 1 Pebibyte = 8.88e+15 bits
        }

        return resultadoBits;
    }





    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        seleccionado = position;
//        if(adapterView.getId() == R.id.unidad1){
//            Toast.makeText(MainActivity.this, "Spinner1" + " " + seleccionado
//                    + " " + units1[seleccionado],Toast.LENGTH_LONG).show();
//        }
//
//        if(adapterView.getId() == R.id.unidad2){
//            Toast.makeText(MainActivity.this, "Spinner2" + " " + seleccionado
//                    + " " + units2[seleccionado],Toast.LENGTH_LONG).show();
//        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}