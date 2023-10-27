package com.maskeit.libreria;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.maskeit.libreria.Models.DB;
import com.maskeit.libreria.Variables.VariablesLibros;
import com.maskeit.libreria.Variables.VariablesClientes;
import com.maskeit.libreria.Variables.VariablesVentas;

// Extender de la clase SQLite y creamos el constructor
public class Conectar extends SQLiteOpenHelper {
    private String nombreTabla;

    public Conectar(@Nullable Context context, @Nullable String nombreBD, @Nullable SQLiteDatabase.CursorFactory factory, int version, String nombreTabla) {
        super(context, nombreBD, factory, version);
        this.nombreTabla = nombreTabla;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Conectar", "Creando base de datos: " + getDatabaseName());
        if (nombreTabla.equals(VariablesLibros.NOMBRE_TABLA)) {
            db.execSQL(VariablesLibros.CREAR_TABLA);
        } else if (nombreTabla.equals(VariablesClientes.NOMBRE_TABLA)) {
            db.execSQL(VariablesClientes.CREAR_TABLA);
        } else if (nombreTabla.equals(VariablesVentas.NOMBRE_TABLA)) {
            db.execSQL(VariablesVentas.CREAR_TABLA);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (nombreTabla.equals(VariablesLibros.NOMBRE_TABLA)) {
            db.execSQL(VariablesLibros.ELIMINAR_TABLA);
            onCreate(db);
        } else if (nombreTabla.equals(VariablesClientes.NOMBRE_TABLA)) {
            db.execSQL(VariablesClientes.ELIMINAR_TABLA);
            onCreate(db);
        } else if (nombreTabla.equals(VariablesVentas.NOMBRE_TABLA)) {
            db.execSQL(VariablesVentas.ELIMINAR_TABLA);
            onCreate(db);
        }
    }
}


