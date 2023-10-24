package com.maskeit.libreria;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

// Extender de la clase SQLite y creamos el constructor
public class Conectar extends SQLiteOpenHelper{
    public Conectar(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //crear la tabla en la base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Variables.CREAR_TABLA);
    }
    //elimina la tabla de la base de datos para despues crearla, esto hara que perdamos la primera info
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Variables.ELIMINAR_TABLA);
        onCreate(db);
    }
}
