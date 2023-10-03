package com.maskeit.etapavida;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionBarPolicy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class etapa extends AppCompatActivity {
    TextView txtnombre, txtfecha, txtedad, txtetapa;
    String nombre, dia, mes, anio;
    Integer edad;
    Button regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etapa);

        regresar = (Button) findViewById(R.id.regresar);

        txtnombre = (TextView) findViewById(R.id.txtname);
        txtfecha = (TextView) findViewById(R.id.txtfecha);
        txtedad = (TextView) findViewById(R.id.txtedad);
        txtetapa = (TextView) findViewById(R.id.txtetapa);

        nombre = getIntent().getStringExtra("Nombre");
        dia =  getIntent().getStringExtra("Dia");
        mes =  getIntent().getStringExtra("Mes");
        anio = getIntent().getStringExtra("Año");


        Integer nmes = Integer.parseInt(mes);
        

        String nameMon = "";
        if(nmes == 1){
            nameMon = "Enero";
        } else if (nmes == 2) {
            nameMon = "Febrero";
        }else if (nmes == 3) {
            nameMon = "Marzo";
        }else if (nmes == 4) {
            nameMon = "Abril";
        }else if (nmes == 5) {
            nameMon = "Mayo";
        }else if (nmes == 6) {
            nameMon = "Junio";
        }else if (nmes == 7) {
            nameMon = "Julio";
        }else if (nmes == 8) {
            nameMon = "Agosto";
        }else if (nmes == 9) {
            nameMon = "Septiembre";
        }else if (nmes == 10) {
            nameMon = "Octubre";
        }else if (nmes == 11) {
            nameMon = "Noviembre";
        }else if (nmes == 12) {
            nameMon = "Diciembre";
        }

        //Seteamos el texto
        txtnombre.setText("Nombre: " + nombre);
        txtfecha.setText("Nacimiento: "+dia+ " de "+ nameMon + " del "+ anio);
        Integer nac = Integer.parseInt(anio);
        edad = calculoEdad(nac);
        txtedad.setText(edad + " años.");
        MostrarDatos();

        // Este es el código que asigna el evento OnBackPressed al botón regresar
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    private int etapaMetodo(int year){
        if(year>= 0 && year<=2){
            return 1;
        }else if(year>=3 && year<=5){
            return 2;
        }else if(year>=6 && year<=12){
            return 3;
        }else if(year>=13 && year<=15){
            return 4;
        }else if(year>=16 && year<=18){
            return 5;
        }else if(year>=19 && year<=24){
            return 6;
        }else if(year>=25 && year<=65){
            return 7;
        }else if(year>=65){
            return 8;
        }
        return year;
    }

    private int calculoEdad(int year){
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        return currentYear - year;
    }

    public void MostrarDatos(){
        if(edad != null) {
            int ctetapa = etapaMetodo(edad);
            String tiempo = "";
            if (ctetapa == 1) {
                tiempo = "Maternal";
                txtetapa.setText("Etapa: " +tiempo);
            } else if (ctetapa == 2) {
                tiempo = "Kinder";
                txtetapa.setText("Etapa: " +tiempo);
            } else if (ctetapa == 3) {
                tiempo = "Primaria";
                txtetapa.setText("Etapa: " +tiempo);
            } else if (ctetapa == 4) {
                tiempo = "Secundaria";
                txtetapa.setText("Etapa: " +tiempo);
            } else if (ctetapa == 5) {
                tiempo = "Prepa";
                txtetapa.setText("Etapa: " +tiempo);
            } else if (ctetapa == 6) {
                tiempo = "Universidad";
                txtetapa.setText("Etapa: " +tiempo);
            } else if (ctetapa == 7) {
                tiempo = "Trabaja";
                txtetapa.setText("Etapa: " +tiempo);
            } else if (ctetapa == 8) {
                tiempo = "Jubilado(a)";
                txtetapa.setText("Etapa: " +tiempo);
            }
        }
    }

}
