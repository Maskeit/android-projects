package com.maskeit.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner moneda1, moneda2;
    String[] opciones1 = {"moneda", "EUR", "USD", "JPY", "BGN", "CZK", "DKK", "GBP", "HUF", "PLN",
            "RON", "SEK", "CHF", "ISK", "NOK", "HRK", "RUB", "TRY", "AUD", "BRL", "CAD", "CNY", "HKD",
            "IDR", "ILS", "INR", "KRW", "MXN", "MYR", "NZD", "PHP", "SGD", "THB", "ZAR"};

    String[] opciones2 = {"moneda", "EUR", "USD", "JPY", "BGN", "CZK", "DKK", "GBP", "HUF", "PLN",
            "RON", "SEK", "CHF", "ISK", "NOK", "HRK", "RUB", "TRY", "AUD", "BRL", "CAD", "CNY", "HKD",
            "IDR", "ILS", "INR", "KRW", "MXN", "MYR", "NZD", "PHP", "SGD", "THB", "ZAR"};
    int seleccionado = 0;
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

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //obtenemos los codigos de moneda seleccionados
                String codigo1 = opciones1[moneda1.getSelectedItemPosition()];
                String codigo2 = opciones2[moneda2.getSelectedItemPosition()];

                // Realizar la solicitud a la API con los códigos de moneda
                new peticionParaCalular().execute(codigo1, codigo2);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

        seleccionado = position;
        if(adapterView.getId() == R.id.moneda1){
            Toast.makeText(MainActivity.this, "Spinner1" + " " + seleccionado
                    + " " + opciones1[seleccionado],Toast.LENGTH_LONG).show();
        }

        if(adapterView.getId() == R.id.moneda2){
            Toast.makeText(MainActivity.this, "Spinner2" + " " + seleccionado
                    + " " + opciones2[seleccionado],Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    @SuppressLint("StaticFieldLeak")

    private class peticionParaCalular extends AsyncTask<String, Void, Double> {
        protected Double doInBackground(String... params){
            if (params.length < 2) {
                return null;
            }
            String codigo1 = params[0];
            String codigo2 = params[1];

            try {
                URL url = new URL("http://www.floatrates.com/daily/usd.json");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                try {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    String jsonResponse = convertStreamToString(in);
                    JSONObject jsonObject = new JSONObject(jsonResponse);

                    if (jsonObject.has(codigo1) && jsonObject.getJSONObject(codigo1).has("rate")) {
                        double tasaMoneda1 = jsonObject.getJSONObject(codigo1).getDouble("rate");
                        double tasaMoneda2 = jsonObject.getJSONObject(codigo2).getDouble("rate");

                        // Calcular el factor de conversión
                        return tasaMoneda2 / tasaMoneda1;
                    } else {
                        return null;
                    }

                } finally {
                    urlConnection.disconnect();
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        private String convertStreamToString(InputStream is) {
            Scanner s = new Scanner(is).useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        }

        @Override// Dentro de onPostExecute
        protected void onPostExecute(Double exchangeRate) {
            if (exchangeRate != null) {
                // Obtener la cantidad ingresada por el usuario
                String cantidad = div1.getText().toString();
                if (!cantidad.isEmpty()) {
                    // Calcular la cantidad convertida
                    double cantidadConvertida = Double.parseDouble(cantidad) * exchangeRate;

                    // Actualizar la interfaz de usuario en el hilo principal
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Mostrar la cantidad convertida en el TextView
                            div2.setText(String.format("%.2f", cantidadConvertida));
                        }
                    });
                }
            } else {
                // Manejar el caso en que no se pudo obtener la tasa de cambio
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Error al obtener la tasa de cambio", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}
