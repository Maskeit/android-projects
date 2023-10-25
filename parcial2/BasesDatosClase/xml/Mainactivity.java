package com.maskeit.basesdatos;

import androidx.appcompat.app.AppCompatActivity;
import com.maskeit.basesdatos.databinding.ActivityMainBinding;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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
        //nos conectamos a la bd
        conectar = new Conectar(this, Variables.NOMBRE_BD,null,1);
    }

    @Override
    public void onClick(View v) {
        String nombre = b.camponombre.getText().toString();
        String telefono = b.campotelefono.getText().toString();
        String id = b.campoid.getText().toString();
        if(!nombre.isEmpty() || !telefono.isEmpty()) {
            if (v == b.insertar1) {
                insertar1();
            }
            if (v == b.insertar2) {
                insertar2();
            }
        }else {
            Toast.makeText(this,"Ingrese los datos primero", Toast.LENGTH_LONG).show();
        }
        if (v == b.ver) {
            i = new Intent(MainActivity.this, lista.class);
            startActivity(i);
        }
        //Verificamos que se haya buscado
        if(!id.isEmpty()){
            if(v == b.buscar1){
                buscar1();
            }
            if(v == b.buscar2){
                buscar2();
            }
            if (v == b.editar) {
                editar();
            }if (v == b.eliminar) {
                eliminar();
            }
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
        b.campotelefono.setText("");
        bd.close();
    }

    //Metodo update
    private void editar() {
        SQLiteDatabase bd = conectar.getWritableDatabase();
        String[] parametros = {b.campoid.getText().toString()};
        ContentValues valores = new ContentValues();
        String nombre = b.camponombre.getText().toString();
        String telefono = b.campotelefono.getText().toString();
        valores.put(Variables.CAMPO_NOMBRE,nombre);
        valores.put(Variables.CAMPO_TELEFONO, telefono);
        bd.update(Variables.NOMBRE_TABLA, valores,Variables.CAMPO_ID+"=?", parametros);
        Toast.makeText(this,"El usuario ha sido actualizado",Toast.LENGTH_LONG).show();
        bd.close();
    }

    private void buscar2() {
        SQLiteDatabase bd = conectar.getReadableDatabase();
        String[] parametros = {b.campoid.getText().toString()}; //select * from

        try {
            Cursor cursor = bd.rawQuery("SELECT "+Variables.CAMPO_NOMBRE+", "+Variables.CAMPO_TELEFONO+
                    " FROM "+ Variables.NOMBRE_TABLA+" WHERE "+Variables.CAMPO_ID+" =?",parametros);
            cursor.moveToFirst();
            b.camponombre.setText(cursor.getString(0));
            b.campotelefono.setText(cursor.getString(1));
            cursor.close();
            bd.close();
        } catch (Exception e) {
            Toast.makeText(this,"Error en la consulta", Toast.LENGTH_LONG).show();
            b.camponombre.setText("");
            b.campotelefono.setText("");
            e.printStackTrace();
        }
        bd.close();
    }

    private void buscar1() {
        SQLiteDatabase bd = conectar.getReadableDatabase();
        String[] parametros = {b.campoid.getText().toString()};
        String[] campos = {Variables.CAMPO_NOMBRE,Variables.CAMPO_TELEFONO};

        try {
            Cursor cursor = bd.query(Variables.NOMBRE_TABLA, campos, Variables.CAMPO_ID+"=?",parametros, null, null, null);
            cursor.moveToFirst();
            b.camponombre.setText(cursor.getString(0));
            b.campotelefono.setText(cursor.getString(1));
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(this,"No hay datos disponibles", Toast.LENGTH_LONG).show();
            b.camponombre.setText("");
            b.campotelefono.setText("");
            e.printStackTrace();
        }
        bd.close();
    }

    private void insertar2() {
        SQLiteDatabase bd = conectar.getWritableDatabase();
        String nombre = b.camponombre.getText().toString();
        String telefono = b.campotelefono.getText().toString();
        String insertar = "INSERT INTO "+Variables.NOMBRE_TABLA+
                " ("+Variables.CAMPO_NOMBRE+", "
                    +Variables.CAMPO_TELEFONO+") VALUES ('"+nombre+"','"+telefono+"')";
        bd.execSQL(insertar);
        Toast.makeText(this,"Se inserto el contacto", Toast.LENGTH_LONG).show();
        b.camponombre.setText("");
        b.campotelefono.setText("");
        bd.close();
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
