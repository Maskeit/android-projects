package com.maskeit.basesdatos;

import androidx.appcompat.app.AppCompatActivity;
import com.maskeit.basesdatos.databinding.ActivityMainBinding;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
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
    }

    @Override
    public void onClick(View v) {
        String nombre = b.camponombre.getText().toString();
        String telefono = b.campotelefono.getText().toString();
        if(!nombre.isEmpty() || !telefono.isEmpty()) {
            if (v == b.insertar1) {
                insertar1();
            }
        } else if (v == b.ver) {
            i = new Intent(MainActivity.this, lista.class);
            startActivity(i);
        }
    }

    private void insertar1() {
        SQLiteDatabase db = conectar.getWritableDatabase(); // Nos conectarmos a la BD
        ContentValues valores = new ContentValues();
        valores.put(Variables.CAMPO_NOMBRE, b.camponombre.getText().toString());
        valores.put(Variables.CAMPO_TELEFONO, b.campotelefono.getText().toString());
        long id = db.insert(Variables.NOMBRE_TABLA, Variables.CAMPO_ID,valores);
        Toast.makeText(this, "id:"+id, Toast.LENGTH_LONG).show();
        b.camponombre.setText("");
        b.campotelefono.setText("");
        db.close();
    }
}