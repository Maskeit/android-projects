package com.maskeit.spinner;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.maskeit.spinner.modelo.ModeloRetorno;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import androidx.appcompat.app.AppCompatActivity;

import com.maskeit.spinner.interfaces.DivisasApi;
import com.maskeit.spinner.modelo.Moneda;


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

    Toast mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialización de vistas
        moneda1 = (Spinner) findViewById(R.id.moneda1);
        moneda2 = (Spinner) findViewById(R.id.moneda2);
        div1 = (EditText) findViewById(R.id.divisa1);
        div2 = (TextView) findViewById(R.id.divisa2);
        calcular = (Button) findViewById(R.id.convertir);

        // Configuración de adaptadores para Spinners
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

        // Configuración de OnClickListener para el botón
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtenemos los códigos de moneda seleccionados
                String valor = div1.getText().toString();
                String codigo1 = opciones1[moneda1.getSelectedItemPosition()];
                String codigo2 = opciones2[moneda2.getSelectedItemPosition()];

                if (!valor.isEmpty()) {
                    // Realizar la solicitud a la API con los códigos de moneda
                    consultarApi rg = new consultarApi(MainActivity.this, new ModeloRetorno());

                    rg.respuesta(codigo1);
                    muestraToast("Procesando...");
                } else {
                    Toast.makeText(MainActivity.this, "Ingresa un valor", Toast.LENGTH_SHORT).show();
                }
            }

            private void muestraToast(String textoMensaje){
                mensaje = Toast.makeText(MainActivity.this,
                        textoMensaje,
                        Toast.LENGTH_SHORT);
                mensaje.show();
            }
        });
    }
    public void onSuccess(ModeloRetorno modeloRetorno) {
        div2.setText("Resultado: " + modeloRetorno.getCode());
    }

    public void onError(Throwable throwable) {

        Toast.makeText(MainActivity.this, "Error en la consulta API: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

//    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

}
