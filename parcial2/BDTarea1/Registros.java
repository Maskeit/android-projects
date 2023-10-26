package com.maskeit.basesdatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.maskeit.basesdatos.databinding.ActivityRegistrosBinding;

import java.util.ArrayList;

public class Registros extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ActivityRegistrosBinding b;
    private String apellido;
    ArrayList<Usuarios> datosusuarios; // array list de la clase Usuarios.java


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityRegistrosBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        setTitle("Registros por Apellido");
        datosusuarios = new ArrayList<>();

        // Obtener el apellido del intent
        Intent intent = getIntent();
        if (intent.hasExtra("apellido")) {
            apellido = intent.getStringExtra("apellido");
            setTitle("Registros para " + apellido);
            mostrarRegistrosPorApellido(apellido);
        } else {
            Toast.makeText(this, "No se proporcionó un apellido válido", Toast.LENGTH_LONG).show();
            finish(); // Cerrar la actividad si no se proporcionó un apellido válido
        }

        setSupportActionBar(b.btnTopAppBack);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        b.btnTopAppBack.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void mostrarRegistrosPorApellido(String apellido) {
        Conectar conectar = new Conectar(this, Variables.NOMBRE_BD, null, 1);
        SQLiteDatabase bd = conectar.getReadableDatabase();

        // Limpia la lista antes de agregar registros filtrados
        datosusuarios.clear();
        // Inicializar la lista de registros filtrados
        ArrayList<String> registrosFiltrados = new ArrayList<>();
        // Realizar la lógica para mostrar los registros que coincidan con el apellido
        String[] campos = {
                Variables.CAMPO_ID,
                Variables.CAMPO_NOMBRE,
                Variables.CAMPO_APELLIDO,
                Variables.CAMPO_TELEFONO,
                Variables.CAMPO_EDAD,
                Variables.CAMPO_BDAY,
                Variables.CAMPO_ESTATURA,
                Variables.CAMPO_GENERO
        };
        String whereClause = Variables.CAMPO_APELLIDO + "=?";
        String[] whereArgs = {apellido};

        Cursor cursor = bd.query(
                Variables.NOMBRE_TABLA,
                campos,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            Usuarios usuario = new Usuarios();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setApellido(cursor.getString(2));
            usuario.setTelefono(cursor.getString(3));
            usuario.setEdad(cursor.getInt(4));
            usuario.setBdate(cursor.getString(5));
            usuario.setEstatura(cursor.getInt(6));
            usuario.setGenero(cursor.getString(7));

            datosusuarios.add(usuario);

            // Agregar cada registro filtrado a la lista
            registrosFiltrados.add(usuario.getId() + " - " + usuario.getNombre() + " - " + usuario.getTelefono());
        }

        // Mostrar los registros en la interfaz de usuario
        ArrayAdapter<String> aa = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, registrosFiltrados);
        b.listaRegistros.setAdapter(aa);

        cursor.close();
        bd.close();

        // Asignar el evento OnItemClickListener después de configurar el Adapter
        b.listaRegistros.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("Registros", "Item clicked at position: " + position);
        // Obtener el usuario seleccionado
        Usuarios usuario = datosusuarios.get(position);

        // Crear un Intent para abrir la actividad detalle
        Intent intent = new Intent(this, detalle.class);

        // Poner el objeto Usuario en el Intent
        intent.putExtra("usuario", usuario);

        // Iniciar la actividad detalle
        startActivity(intent);
    }

}

