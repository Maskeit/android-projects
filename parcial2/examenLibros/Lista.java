package com.maskeit.libreria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import com.maskeit.libreria.databinding.ActivityListaBinding;
public class Lista extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lista;
    ArrayList<String> listalibros;
    ArrayList<Libros> datoslibros; // array list de la clase Libros.java
    Conectar conectar;
    private ActivityListaBinding b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityListaBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        setTitle("Ver Libros");
        //usar "b.lista"
        mostrar();
        ArrayAdapter<String> aa =new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, listalibros);
        b.lista.setAdapter(aa);
        b.lista.setOnItemClickListener(this); //con este evento vamos al metodo setOnitemClickListener
    }

    private void mostrar() {
        conectar = new Conectar(this, Variables.NOMBRE_BD, null,1);
        SQLiteDatabase bd = conectar.getReadableDatabase();
        Libros libro = null;
        datoslibros = new ArrayList<Libros>();
        Cursor cursor = bd.rawQuery("SELECT * FROM "+Variables.NOMBRE_TABLA,null);
        while (cursor.moveToNext()){
            libro = new Libros();
            libro.setId(cursor.getInt(0));
            libro.setTitulo(cursor.getString(1));
            libro.setAutor(cursor.getString(2));
            libro.setEditorial(cursor.getString(3));
            libro.setPaginas(cursor.getInt(4));
            libro.setISBN(cursor.getInt(5));
            datoslibros.add(libro);
        }
        agregarLista();
        bd.close();
    }

    private void agregarLista() {
        listalibros = new ArrayList<String>();
        for(int i = 0; i<datoslibros.size(); i++){
            listalibros.add(
                    datoslibros.get(i).getId()+" - "+
                            datoslibros.get(i).getTitulo()+" - "+
                            datoslibros.get(i).getAutor()
            );
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Libros libros = datoslibros.get(position);
        Intent ii = new Intent(this,Detalle.class);
        Bundle b = new Bundle();
        b.putSerializable("libro", libros); //empaquetar el objeto para ser transmitido a la vista detalle, su etiqueta seria libro
        ii.putExtras(b);
        startActivity(ii);
    }
}
