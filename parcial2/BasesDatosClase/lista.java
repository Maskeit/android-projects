package com.maskeit.basesdatos;

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

import java.lang.reflect.Array;
import java.util.ArrayList;
import com.maskeit.basesdatos.databinding.ActivityListaBinding;
import com.maskeit.basesdatos.databinding.ActivityMainBinding;

public class lista extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lista;
    ArrayList<String> listausuarios;
    ArrayList<Usuarios> datosusuarios; // array list de la clase Usuarios.java
    Conectar conectar;
    private ActivityListaBinding b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityListaBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        setTitle("Ver usuarios");
        //usar "b.lista"
        mostrar();
        ArrayAdapter<String> aa =new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, listausuarios);
        b.lista.setAdapter(aa);
        b.lista.setOnItemClickListener(this); //con este evento vamos al metodo setOnitemClickListener
    }

    private void mostrar() {
        conectar = new Conectar(this, Variables.NOMBRE_BD, null,1);
        SQLiteDatabase bd = conectar.getReadableDatabase();
        Usuarios usuario = null;
        datosusuarios = new ArrayList<Usuarios>();
        Cursor cursor = bd.rawQuery("SELECT * FROM "+Variables.NOMBRE_TABLA,null);
        while (cursor.moveToNext()){
            usuario = new Usuarios();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setTelefono(cursor.getString(2));
            datosusuarios.add(usuario);
        }
        agregarLista();
        bd.close();
    }

    private void agregarLista() {
        listausuarios = new ArrayList<String>();
        for(int i = 0; i<datosusuarios.size(); i++){
            listausuarios.add(
                    datosusuarios.get(i).getId()+" - "+
                    datosusuarios.get(i).getNombre()+" - "+
                    datosusuarios.get(i).getTelefono()
            );
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Usuarios usuario = datosusuarios.get(position);
        Intent ii = new Intent(this,detalle.class);
        Bundle b = new Bundle();
        b.putSerializable("usuario", usuario); //emaquetar el objeto para ser transmitido a la vista detalle, su etiqueta seria usuario
        ii.putExtras(b);
        startActivity(ii);
    }
}