package com.maskeit.libreria.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.maskeit.libreria.Conectar;
import com.maskeit.libreria.Models.ClientesModel;
import com.maskeit.libreria.Models.DB;
import com.maskeit.libreria.Variables.VariablesClientes;
import com.maskeit.libreria.Variables.VariablesLibros;
import com.maskeit.libreria.databinding.ActivityListaClientesBinding;

import java.util.ArrayList;

public class ListaClientes extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lista;
    ArrayList<String> listaClientes;
    ArrayList<ClientesModel> datosClientes;
    Conectar conectar;
    private ActivityListaClientesBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityListaClientesBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        setTitle("Ver Clientes");

        mostrar(); // Llena la lista de clientes
        ArrayAdapter<String> aa = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, listaClientes);
        b.listaClientes.setAdapter(aa);
        b.listaClientes.setOnItemClickListener(this); // con este evento vamos al metodo setOnitemClickListener
    }

    private void mostrar() {
        conectar = new Conectar(this, VariablesClientes.NOMBRE_BD, null, 2, VariablesClientes.NOMBRE_TABLA);
        SQLiteDatabase bd = conectar.getReadableDatabase();
        ClientesModel cliente = null;
        datosClientes = new ArrayList<>();

        Cursor cursor = bd.rawQuery("SELECT * FROM " + VariablesClientes.NOMBRE_TABLA, null);
        while (cursor.moveToNext()) {
            // Obtener los valores del cursor
            int id = cursor.getInt(cursor.getColumnIndex(VariablesClientes.CAMPO_ID));
            String nombre = cursor.getString(cursor.getColumnIndex(VariablesClientes.CAMPO_NOMBRE));
            String rfc = cursor.getString(cursor.getColumnIndex(VariablesClientes.CAMPO_RFC));

            // Crear una instancia de ClientesModel con los valores obtenidos
            cliente = new ClientesModel(id, nombre, rfc);

            // Agregar el cliente al ArrayList
            datosClientes.add(cliente);
        }
        agregarLista();
        bd.close();
    }

    private void agregarLista() {
        listaClientes = new ArrayList<>();
        for (int i = 0; i < datosClientes.size(); i++) {
            listaClientes.add(
                    datosClientes.get(i).getId() + " - " +
                            datosClientes.get(i).getNombre() + " - " +
                            datosClientes.get(i).getRfc()
            );
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
