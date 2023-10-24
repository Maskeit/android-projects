package com.maskeit.libreria;

import androidx.appcompat.app.AppCompatActivity;
import com.maskeit.libreria.databinding.ActivityMainBinding;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ActivityMainBinding b;
    Conectar conectar;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        setTitle("Control de Libros");
        b.insertar.setOnClickListener(this);
        b.buscar.setOnClickListener(this);
        b.editar.setOnClickListener(this);
        b.eliminar.setOnClickListener(this);
        b.ver.setOnClickListener(this);

        //Nos conectamos a la bd
        conectar = new Conectar(this, Variables.NOMBRE_BD,null,1);
    }

    @Override
    public void onClick(View v) {
        String titulo = b.campotitulo.getText().toString();
        String autor = b.campoautor.getText().toString();
        String paginas = b.campopaginas.getText().toString();
        String editorial = b.campoeditorial.getText().toString();
        String isbn = b.campoisbn.getText().toString();
        //String id = b.campoid.getText().toString();

        boolean b1 = !titulo.isEmpty() || !autor.isEmpty() || !paginas.isEmpty() || !editorial.isEmpty() || !isbn.isEmpty();
        if(b1) {
            if (v == b.insertar) {
                insertar();
            }
        }else {
            Toast.makeText(this,"Ingrese los datos primero", Toast.LENGTH_LONG).show();
        }

        if (v == b.ver) {
            i = new Intent(MainActivity.this, Lista.class);
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
        valores.put(Variables.CAMPO_TITULO, b.campotitulo.getText().toString());
        valores.put(Variables.CAMPO_AUTOR, b.campoautor.getText().toString());
        valores.put(Variables.CAMPO_EDITORIAL, b.campoeditorial.getText().toString());
        valores.put(Variables.CAMPO_PAGINAS, b.campopaginas.getText().toString());
        valores.put(Variables.CAMPO_ISBN, b.campoisbn.getText().toString());

        long id = db.insert(Variables.NOMBRE_TABLA, Variables.CAMPO_ID,valores);
        Toast.makeText(this, "id:"+id, Toast.LENGTH_LONG).show();
        b.campotitulo.setText("");
        b.campoautor.setText("");
        b.campoeditorial.setText("");
        b.campoisbn.setText("");
        b.campopaginas.setText("");
        db.close();
    }

    private void buscar() {
        SQLiteDatabase bd = conectar.getReadableDatabase();
        String[] parametros = {
                b.campoautor.getText().toString(),
                b.campotitulo.getText().toString()
        };
        String[] campos = {
                Variables.CAMPO_TITULO,
                Variables.CAMPO_AUTOR,
                Variables.CAMPO_EDITORIAL,
                Variables.CAMPO_ISBN,
                Variables.CAMPO_PAGINAS
        };

        try {
            Cursor cursor = bd.query(
                    Variables.NOMBRE_TABLA,
                    campos,
                    Variables.CAMPO_AUTOR + "=? OR " + Variables.CAMPO_TITULO + "=?",
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
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(this,"No hay datos disponibles", Toast.LENGTH_LONG).show();
            b.campotitulo.setText("");
            b.campoautor.setText("");
            b.campoeditorial.setText("");
            b.campoisbn.setText("");
            b.campopaginas.setText("");
            e.printStackTrace();
        }
        bd.close();
    }
    private void eliminar() {
        SQLiteDatabase bd = conectar.getWritableDatabase();
        String[] parametro1 = {b.campoautor.getText().toString()};
        String[] parametro2 = {b.campotitulo.getText().toString()};
        int n = bd.delete(Variables.NOMBRE_TABLA, Variables.CAMPO_AUTOR + "=? OR " + Variables.CAMPO_TITULO + "=?", new String[]{parametro1[0], parametro2[0]});//n regresa registros eliminados
        Toast.makeText(this,"Usuarios eliminados: "+n,Toast.LENGTH_LONG).show();
        b.campotitulo.setText("");
        b.campoautor.setText("");
        b.campoeditorial.setText("");
        b.campoisbn.setText("");
        b.campopaginas.setText("");
        bd.close();
    }

    //Metodo update
    private void editar() {
        SQLiteDatabase bd = conectar.getWritableDatabase();
        String[] parametro1 = {b.campoautor.getText().toString()};
        String[] parametro2 = {b.campotitulo.getText().toString()};
        ContentValues valores = new ContentValues();
        valores.put(Variables.CAMPO_TITULO, b.campotitulo.getText().toString());
        valores.put(Variables.CAMPO_AUTOR, b.campoautor.getText().toString());
        valores.put(Variables.CAMPO_EDITORIAL, b.campoeditorial.getText().toString());
        valores.put(Variables.CAMPO_PAGINAS, b.campopaginas.getText().toString());
        valores.put(Variables.CAMPO_ISBN, b.campoisbn.getText().toString());
        bd.update(Variables.NOMBRE_TABLA, valores,Variables.CAMPO_AUTOR+"=? OR " + Variables.CAMPO_TITULO + "=?",  new String[]{parametro1[0], parametro2[0]});
        Toast.makeText(this,"El usuario ha sido actualizado",Toast.LENGTH_LONG).show();
        bd.close();
    }

}
