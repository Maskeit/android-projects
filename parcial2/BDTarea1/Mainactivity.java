package com.maskeit.basesdatos;

import androidx.appcompat.app.AppCompatActivity;
import com.maskeit.basesdatos.databinding.ActivityMainBinding;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding b;

    Conectar conectar;

    Intent i;
    String id, nombre, apellido, telefono, edad, estatura, bday,selectedGender;

    // Obtener el genero seleccionado
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        setTitle("Control de Usuarios");
        b.etBirthDate.setOnClickListener(this);
        b.insertar.setOnClickListener(this);
        b.buscar.setOnClickListener(this);
        b.editar.setOnClickListener(this);
        b.eliminar.setOnClickListener(this);
        b.ver.setOnClickListener(this);
        b.limpiar.setOnClickListener(this);
        // Conectar a la BD
        conectar = new Conectar(this, Variables.NOMBRE_BD, null, 1);

    }

    @Override
    public void onClick(View v) {
        // inicializar variables
        id = b.campoid.getText().toString();
        nombre = b.camponombre.getText().toString();
        apellido = b.campoapellido.getText().toString();
        telefono = b.campotelefono.getText().toString();
        edad = b.campoedad.getText().toString();
        estatura = b.campoestatura.getText().toString();
        bday = b.etBirthDate.getText().toString();
        selectedGender = b.generos.getText().toString();
        boolean b1 = !selectedGender.isEmpty() ||!nombre.isEmpty() || !apellido.isEmpty() || !telefono.isEmpty() || !edad.isEmpty() ||!estatura.isEmpty() || !bday.isEmpty();
        boolean noVacios = !selectedGender.isEmpty() && !nombre.isEmpty() && !apellido.isEmpty() && !telefono.isEmpty() && !edad.isEmpty() &&!estatura.isEmpty() && !bday.isEmpty();
        if(b1) {
            if ((v == b.insertar) && noVacios) {
                insertar();
            }
        }else {
            Toast.makeText(this,"Ingrese los datos primero", Toast.LENGTH_LONG).show();
        }

        if (v == b.ver) {
            i = new Intent(MainActivity.this, lista.class);
            startActivity(i);
            Log.d("MainActivity", "Iniciando actividad Lista");
        }

        // Verificamos que al menos uno de los campos tenga algún valor
        if (!TextUtils.isEmpty(id) ||!TextUtils.isEmpty(apellido) || !TextUtils.isEmpty(edad) || !TextUtils.isEmpty(estatura)) {
            if (v == b.buscar) {
                buscar();
            }
            if (v == b.editar) {
                editar();
            }
            if (v == b.eliminar) {
                eliminar();
            }
        }
        if (v == b.etBirthDate) {
            showDatePickerDialog();
        } else if (v == b.limpiar) {
            limpiarCampos();
        }

    }

    private void eliminar() {
        SQLiteDatabase bd = conectar.getWritableDatabase();
        String[] parametros = {b.campoid.getText().toString()};
        int n = bd.delete(Variables.NOMBRE_TABLA,Variables.CAMPO_ID+"=?",parametros);
        //n regresa registros eliminados
        Toast.makeText(this,"Usuarios eliminados: "+n,Toast.LENGTH_LONG).show();
        b.campoid.setText("");
        b.camponombre.setText("");
        b.campoapellido.setText("");
        b.campotelefono.setText("");
        b.campoestatura.setText("");
        b.campoedad.setText("");
        b.etBirthDate.setText("");
        b.generos.setText("");

        bd.close();
    }

    //Metodo update
    private void editar() {
        SQLiteDatabase bd = conectar.getWritableDatabase();
        String[] parametros = {b.campoid.getText().toString()};
        ContentValues valores = new ContentValues();
        // Obtener los valores de los campos de texto o elementos de la interfaz de usuario
        String nombre = b.camponombre.getText().toString();
        String apellido = b.campoapellido.getText().toString();
        String telefono = b.campotelefono.getText().toString();
        String edad = b.campoedad.getText().toString();
        String estatura = b.campoestatura.getText().toString();
        String bday = b.etBirthDate.getText().toString();
        String generos = b.generos.getText().toString();

        // Obtener el género seleccionado (puedes necesitar ajustar esto según tu lógica específica)
        valores.put(Variables.CAMPO_NOMBRE,nombre);
        valores.put(Variables.CAMPO_APELLIDO, apellido);
        valores.put(Variables.CAMPO_TELEFONO, telefono);
        valores.put(Variables.CAMPO_EDAD, edad);
        valores.put(Variables.CAMPO_ESTATURA,estatura);
        valores.put(Variables.CAMPO_BDAY, bday);
        valores.put(Variables.CAMPO_GENERO,generos);


        bd.update(Variables.NOMBRE_TABLA, valores,Variables.CAMPO_ID+"=?", parametros);
        Toast.makeText(this,"El usuario ha sido actualizado",Toast.LENGTH_LONG).show();
        bd.close();
    }

    @SuppressLint("Range")
    private void buscar() {
        SQLiteDatabase bd = conectar.getReadableDatabase();
        String[] parametros = {
                b.campoid.getText().toString(),
                b.campoapellido.getText().toString(),
                b.campoedad.getText().toString(),
                b.campoestatura.getText().toString()
        };
        String[] campos = {
                Variables.CAMPO_ID,
                Variables.CAMPO_NOMBRE,
                Variables.CAMPO_APELLIDO,
                Variables.CAMPO_TELEFONO,
                Variables.CAMPO_EDAD,
                Variables.CAMPO_ESTATURA,
                Variables.CAMPO_BDAY,
                Variables.CAMPO_GENERO
        };

        String whereClause = Variables.CAMPO_ID + "=? OR " +
                Variables.CAMPO_APELLIDO + "=? OR " +
                Variables.CAMPO_EDAD + "=? OR " +
                Variables.CAMPO_ESTATURA + "=?";


        try {
            Cursor cursor = bd.query(
                    Variables.NOMBRE_TABLA,
                    campos,
                    whereClause,
                    parametros,
                    null,
                    null,
                    null
            );

            if (cursor.getCount() == 1 && cursor.moveToFirst()) {
                // Si hay un solo resultado, asignar directamente los valores
                b.camponombre.setText(cursor.getString(cursor.getColumnIndex(Variables.CAMPO_NOMBRE)));
                b.campoapellido.setText(cursor.getString(cursor.getColumnIndex(Variables.CAMPO_APELLIDO)));
                b.campotelefono.setText(cursor.getString(cursor.getColumnIndex(Variables.CAMPO_TELEFONO)));
                b.campoedad.setText(cursor.getString(cursor.getColumnIndex(Variables.CAMPO_EDAD)));
                b.campoestatura.setText(cursor.getString(cursor.getColumnIndex(Variables.CAMPO_ESTATURA)));
                b.etBirthDate.setText(cursor.getString(cursor.getColumnIndex(Variables.CAMPO_BDAY)));
                b.generos.setText(cursor.getString(cursor.getColumnIndex(Variables.CAMPO_GENERO)));
            } else if (cursor.getCount() > 1) {
                // Si hay múltiples resultados, obtener los datos y enviarlos a la actividad Registros
                ArrayList<Usuarios> usuariosEncontrados = new ArrayList<>();
                while (cursor.moveToNext()) {
                    Usuarios usuario = new Usuarios();
                    usuario.setId(cursor.getInt(cursor.getColumnIndex(Variables.CAMPO_ID)));
                    usuario.setNombre(cursor.getString(cursor.getColumnIndex(Variables.CAMPO_NOMBRE)));
                    usuario.setApellido(cursor.getString(cursor.getColumnIndex(Variables.CAMPO_APELLIDO)));
                    usuario.setTelefono(cursor.getString(cursor.getColumnIndex(Variables.CAMPO_TELEFONO)));
                    usuario.setEdad(cursor.getInt(cursor.getColumnIndex(Variables.CAMPO_EDAD)));
                    usuario.setEstatura(cursor.getInt(cursor.getColumnIndex(Variables.CAMPO_ESTATURA)));
                    usuario.setBdate(cursor.getString(cursor.getColumnIndex(Variables.CAMPO_BDAY)));
                    usuario.setGenero(cursor.getString(cursor.getColumnIndex(Variables.CAMPO_GENERO)));

                    usuariosEncontrados.add(usuario);
                }

                // Iniciar la actividad Registros y pasar la lista de usuarios
                // En la sección donde inicias la actividad Registros, agrega el apellido al intent
                Intent reg = new Intent(MainActivity.this, Registros.class);
                reg.putExtra("apellido", b.campoapellido.getText().toString());
                startActivity(reg);

            } else if (cursor.getCount() == 0) {
                Toast.makeText(this, "No hay datos disponibles", Toast.LENGTH_LONG).show();
            }

            cursor.close();
        } catch (Exception e) {
            Toast.makeText(this, "No hay datos disponibles", Toast.LENGTH_LONG).show();
            b.camponombre.setText("");
            b.campoapellido.setText("");
            b.campotelefono.setText("");
            b.campoedad.setText("");
            b.campoestatura.setText("");
            b.etBirthDate.setText("");
            b.generos.setText("");
            e.printStackTrace();
        }
        bd.close();
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

    private void insertar() {
        SQLiteDatabase db = conectar.getWritableDatabase(); // Nos conectarmos a la BD
        ContentValues valores = new ContentValues();
        valores.put(Variables.CAMPO_NOMBRE, nombre);
        valores.put(Variables.CAMPO_APELLIDO, apellido);
        valores.put(Variables.CAMPO_TELEFONO, telefono);
        valores.put(Variables.CAMPO_EDAD, edad);
        valores.put(Variables.CAMPO_ESTATURA, estatura);
        valores.put(Variables.CAMPO_BDAY, bday);
        valores.put(Variables.CAMPO_GENERO, selectedGender);

        long id = db.insert(Variables.NOMBRE_TABLA, Variables.CAMPO_ID, valores);
        Toast.makeText(this, "id:" + id, Toast.LENGTH_LONG).show();
        b.camponombre.setText("");
        b.campoapellido.setText("");
        b.campotelefono.setText("");
        b.etBirthDate.setText("");
        b.campoedad.setText("");
        b.campoestatura.setText("");
        b.generos.setText("");
        db.close();
    }

    private void limpiarCampos(){
        b.campoid.setText("");
        b.camponombre.setText("");
        b.campoapellido.setText("");
        b.campotelefono.setText("");
        b.etBirthDate.setText("");
        b.campoedad.setText("");
        b.campoestatura.setText("");
        b.generos.setText("");
    }
}
