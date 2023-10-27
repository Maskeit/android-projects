package com.maskeit.libreria;
//archivo Libros.java
import androidx.appcompat.app.AppCompatActivity;

import com.maskeit.libreria.Variables.VariablesLibros;
import com.maskeit.libreria.Views.ListaLibros;
import com.maskeit.libreria.databinding.ActivityLibrosBinding;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class Libros extends AppCompatActivity implements View.OnClickListener{
    ActivityLibrosBinding b;
    Conectar conectar;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityLibrosBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        setTitle("Control de LibrosModel");
        b.insertar.setOnClickListener(this);
        b.buscar.setOnClickListener(this);
        b.editar.setOnClickListener(this);
        b.eliminar.setOnClickListener(this);
        b.ver.setOnClickListener(this);
        b.limpiar.setOnClickListener(this);

        //Nos conectamos a la bd
        conectar = new Conectar(this, VariablesLibros.NOMBRE_BD, null, 2, VariablesLibros.NOMBRE_TABLA);
    }

    @Override
    public void onClick(View v) {
        String titulo = b.campotitulo.getText().toString();
        String autor = b.campoautor.getText().toString();
        String paginas = b.campopaginas.getText().toString();
        String editorial = b.campoeditorial.getText().toString();
        String isbn = b.campoisbn.getText().toString();
        String precio = b.campoprecio.getText().toString();
        //String id = b.campoid.getText().toString();

        boolean b1 = !titulo.isEmpty() || !autor.isEmpty() || !paginas.isEmpty() || !editorial.isEmpty() || !isbn.isEmpty() || !precio.isEmpty();
        if(b1) {
            if (v == b.insertar) {
                insertar();
            }
        }else {
            Toast.makeText(this,"Ingrese los datos primero", Toast.LENGTH_LONG).show();
        }

        if (v == b.ver) {
            i = new Intent(Libros.this, ListaLibros.class);
            startActivity(i);
            Log.d("MainActivity", "Iniciando actividad Lista");
        }


        //Verificamos que se haya insertado algo para buscar
        if(!titulo.isEmpty() || !autor.isEmpty()){
            if(v == b.buscar){
                buscar();
            }
            if (v == b.editar) {
                editar();
            }
            if (v == b.eliminar) {
                eliminar();
            }
        }

        if(v == b.limpiar){
            limpiar();
        }

    }

    private void limpiar() {
        b.campotitulo.setText("");
        b.campoautor.setText("");
        b.campoeditorial.setText("");
        b.campoisbn.setText("");
        b.campopaginas.setText("");
    }

    private void insertar() {
        SQLiteDatabase db = conectar.getWritableDatabase(); // Nos conectarmos a la BD
        ContentValues valores = new ContentValues();
        valores.put(VariablesLibros.CAMPO_TITULO, b.campotitulo.getText().toString());
        valores.put(VariablesLibros.CAMPO_AUTOR, b.campoautor.getText().toString());
        valores.put(VariablesLibros.CAMPO_EDITORIAL, b.campoeditorial.getText().toString());
        valores.put(VariablesLibros.CAMPO_PAGINAS, b.campopaginas.getText().toString());
        valores.put(VariablesLibros.CAMPO_ISBN, b.campoisbn.getText().toString());
        valores.put(VariablesLibros.CAMPO_PRECIO, b.campoprecio.getText().toString());

        long id = db.insert(VariablesLibros.NOMBRE_TABLA, VariablesLibros.CAMPO_ID,valores);
        Toast.makeText(this, "id:"+id, Toast.LENGTH_LONG).show();
        b.campotitulo.setText("");
        b.campoautor.setText("");
        b.campoeditorial.setText("");
        b.campoisbn.setText("");
        b.campopaginas.setText("");
        b.campoprecio.setText("");
        db.close();
    }

    private void buscar() {
        SQLiteDatabase bd = conectar.getReadableDatabase();
        String[] parametros = {
                b.campoautor.getText().toString(),
                b.campotitulo.getText().toString()
        };
        String[] campos = {
                VariablesLibros.CAMPO_TITULO,
                VariablesLibros.CAMPO_AUTOR,
                VariablesLibros.CAMPO_EDITORIAL,
                VariablesLibros.CAMPO_ISBN,
                VariablesLibros.CAMPO_PAGINAS,
                VariablesLibros.CAMPO_PRECIO
        };

        try {
            Cursor cursor = bd.query(
                    VariablesLibros.NOMBRE_TABLA,
                    campos,
                    VariablesLibros.CAMPO_AUTOR + "=? OR " + VariablesLibros.CAMPO_TITULO + "=?",
                    parametros,
                    null,
                    null,
                    null
            );
            cursor.moveToFirst();
            b.campotitulo.setText(cursor.getString(0));
            b.campoautor.setText(cursor.getString(1));
            b.campoeditorial.setText(cursor.getString(2));
            b.campoisbn.setText(cursor.getString(3));
            b.campopaginas.setText(cursor.getString(4));
            b.campoprecio.setText(cursor.getString(5));
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(this,"No hay datos disponibles", Toast.LENGTH_LONG).show();
            b.campotitulo.setText("");
            b.campoautor.setText("");
            b.campoeditorial.setText("");
            b.campoisbn.setText("");
            b.campopaginas.setText("");
            b.campoprecio.setText("");
            e.printStackTrace();
        }
        bd.close();
    }
    private void eliminar() {
        SQLiteDatabase bd = conectar.getWritableDatabase();
        String[] parametro1 = {b.campoautor.getText().toString()};
        String[] parametro2 = {b.campotitulo.getText().toString()};
        int n = bd.delete(VariablesLibros.NOMBRE_TABLA, VariablesLibros.CAMPO_AUTOR + "=? OR " + VariablesLibros.CAMPO_TITULO + "=?", new String[]{parametro1[0], parametro2[0]});//n regresa registros eliminados
        Toast.makeText(this,"Usuarios eliminados: "+n,Toast.LENGTH_LONG).show();
        b.campotitulo.setText("");
        b.campoautor.setText("");
        b.campoeditorial.setText("");
        b.campoisbn.setText("");
        b.campopaginas.setText("");
        b.campoprecio.setText("");
        bd.close();
    }

    //Metodo update
    private void editar() {
        SQLiteDatabase bd = conectar.getWritableDatabase();
        String[] parametro1 = {b.campoautor.getText().toString()};
        String[] parametro2 = {b.campotitulo.getText().toString()};
        ContentValues valores = new ContentValues();
        valores.put(VariablesLibros.CAMPO_TITULO, b.campotitulo.getText().toString());
        valores.put(VariablesLibros.CAMPO_AUTOR, b.campoautor.getText().toString());
        valores.put(VariablesLibros.CAMPO_EDITORIAL, b.campoeditorial.getText().toString());
        valores.put(VariablesLibros.CAMPO_PAGINAS, b.campopaginas.getText().toString());
        valores.put(VariablesLibros.CAMPO_ISBN, b.campoisbn.getText().toString());
        valores.put(VariablesLibros.CAMPO_PRECIO, b.campoprecio.getText().toString());
        bd.update(VariablesLibros.NOMBRE_TABLA, valores,VariablesLibros.CAMPO_AUTOR+"=? OR " + VariablesLibros.CAMPO_TITULO + "=?",  new String[]{parametro1[0], parametro2[0]});
        Toast.makeText(this,"El usuario ha sido actualizado",Toast.LENGTH_LONG).show();
        bd.close();
    }

}
