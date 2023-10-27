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
import com.maskeit.libreria.Models.VentasModel;
import com.maskeit.libreria.Variables.VariablesVentas;
import com.maskeit.libreria.databinding.ActivityListaVentasBinding;

import java.util.ArrayList;

public class ListaVentas extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lista;
    ArrayList<String> listaVentas;
    ArrayList<VentasModel> datosVentas;
    Conectar conectar;
    private ActivityListaVentasBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityListaVentasBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        setTitle("Ver Ventas");

        mostrar(); // Llena la lista de ventas
        ArrayAdapter<String> aa = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, listaVentas);
        b.listaVentas.setAdapter(aa);
        b.listaVentas.setOnItemClickListener(this); // con este evento vamos al método setOnitemClickListener
    }

    private void mostrar() {
        conectar = new Conectar(this, VariablesVentas.NOMBRE_BD, null, 1, VariablesVentas.NOMBRE_TABLA);
        SQLiteDatabase bd = conectar.getReadableDatabase();
        VentasModel venta = null;
        datosVentas = new ArrayList<>();

        Cursor cursor = bd.rawQuery("SELECT * FROM " + VariablesVentas.NOMBRE_TABLA, null);
        while (cursor.moveToNext()) {
            // Obtener los valores del cursor
            int idVenta = cursor.getInt(cursor.getColumnIndex(VariablesVentas.CAMPO_ID_VENTA));
            int idCliente = cursor.getInt(cursor.getColumnIndex(VariablesVentas.CAMPO_ID_CLIENTE));
            int idLibro = cursor.getInt(cursor.getColumnIndex(VariablesVentas.CAMPO_ID_LIBRO));
            int cantidadLibros = cursor.getInt(cursor.getColumnIndex(VariablesVentas.CAMPO_CANTIDAD_LIBROS));
            float costoTotal = cursor.getFloat(cursor.getColumnIndex(VariablesVentas.CAMPO_COSTO_TOTAL));

            // Crear una instancia de VentasModel con los valores obtenidos
            venta = new VentasModel(idVenta, idCliente, idLibro, cantidadLibros, costoTotal);

            // Agregar la venta al ArrayList
            datosVentas.add(venta);
        }
        agregarLista();
        bd.close();
    }

    private void agregarLista() {
        listaVentas = new ArrayList<>();
        for (int i = 0; i < datosVentas.size(); i++) {
            listaVentas.add(
                    "ID Venta: " + datosVentas.get(i).getIdVenta() +
                            " - ID Cliente: " + datosVentas.get(i).getIdCliente() +
                            " - ID Libro: " + datosVentas.get(i).getIdLibro() +
                            " - Cantidad Libros: " + datosVentas.get(i).getCantidadLibros() +
                            " - Costo Total: " + datosVentas.get(i).getCostoTotal()
            );
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Logica del Intent
    }
}
