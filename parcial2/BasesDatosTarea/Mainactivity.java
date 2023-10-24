package com.maskeit.basesdatos;

import androidx.appcompat.app.AppCompatActivity;
import com.maskeit.basesdatos.databinding.ActivityMainBinding;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding b;

    Conectar conectar;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        setTitle("Control de Usuarios");
        b.insertar1.setOnClickListener(this);
        b.insertar2.setOnClickListener(this);
        b.buscar1.setOnClickListener(this);
        b.buscar2.setOnClickListener(this);
        b.editar.setOnClickListener(this);
        b.eliminar.setOnClickListener(this);
        b.ver.setOnClickListener(this);
        conectar = new Conectar(this, Variables.NOMBRE_BD,null,1);

        EditText etBirthDate = (EditText) findViewById(R.id.etBirthDate);
        etBirthDate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String nombre = b.camponombre.getText().toString();
        String telefono = b.campotelefono.getText().toString();
        if(!nombre.isEmpty() || !telefono.isEmpty()) {
            if (v == b.insertar1) {
                insertar1();
            }else if (v == b.insertar2) {
                //insertar2();
                Toast.makeText(this, "Programar insertar 2", Toast.LENGTH_LONG).show();
            }
        } else if (v == b.ver) {
            i = new Intent(MainActivity.this, lista.class);
            startActivity(i);
        }else if(v == b.etBirthDate){
            //Toast.makeText(this, "Calendario ok", Toast.LENGTH_LONG).show();
            showDatePickerDialog();
        }else if (v == b.eliminar) { //metodo para eliminar tabla de la BD
            eliminarTabla();
        }
    }
    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month+1); //incluir year si es necesario mostrar el año
                b.etBirthDate.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void insertar1() {
        SQLiteDatabase db = conectar.getWritableDatabase(); // Nos conectarmos a la BD
        ContentValues valores = new ContentValues();
        valores.put(Variables.CAMPO_NOMBRE, b.camponombre.getText().toString());
        valores.put(Variables.CAMPO_APELLIDO, b.campoapellido.getText().toString());
        valores.put(Variables.CAMPO_EDAD, b.campoedad.getText().toString());
        valores.put(Variables.CAMPO_BDAY, b.etBirthDate.getText().toString());
        valores.put(Variables.CAMPO_TELEFONO, b.campotelefono.getText().toString());
        long id = db.insert(Variables.NOMBRE_TABLA, Variables.CAMPO_ID,valores);
        Toast.makeText(this, "id:"+id, Toast.LENGTH_LONG).show();
        b.camponombre.setText("");
        b.campotelefono.setText("");
        b.campoapellido.setText("");
        b.campoedad.setText("");
        b.campoestatura.setText("");
        db.close();
    }

    private void  eliminarTabla(){
        SQLiteDatabase db = conectar.getWritableDatabase(); // Nos conectarmos a la BD
        db.execSQL(Variables.ELIMINAR_TABLA);
        Toast.makeText(this, "Tabla eliminada", Toast.LENGTH_LONG).show();
        db.close();
    }
}
