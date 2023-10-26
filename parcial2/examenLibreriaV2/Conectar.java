package com.maskeit.libreria;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.maskeit.libreria.Variables.VariablesLibros;
import com.maskeit.libreria.Variables.VariablesClientes;
import com.maskeit.libreria.Models.DB.*;

// Extender de la clase SQLite y creamos el constructor
public class Conectar extends SQLiteOpenHelper{
    public Conectar(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //crear la tabla libros en la base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(VariablesLibros.CREAR_TABLA);
        db.execSQL(VariablesClientes.CREAR_TABLA);
    }
    //elimina la tabla libros de la base de datos para despues crearla, esto hara que perdamos la primera info
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(VariablesLibros.ELIMINAR_TABLA);
        db.execSQL(VariablesClientes.ELIMINAR_TABLA);
        onCreate(db);
    }
}
