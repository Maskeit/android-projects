package com.maskeit.libreria.Variables;

import com.maskeit.libreria.Models.DB;

public class VariablesVentas {
    public static String NOMBRE_BD = DB.NOMBRE_BD;
    public static final String NOMBRE_TABLA = "ventas";
    public static final String CAMPO_ID_VENTA = "idVenta";
    public static final String CAMPO_ID_CLIENTE = "idCliente";
    public static final String CAMPO_ID_LIBRO = "idLibro";
    public static final String CAMPO_CANTIDAD_LIBROS = "cantidadLibros";
    public static final String CAMPO_COSTO_TOTAL = "costoTotal";

    public static final String CREAR_TABLA = "CREATE TABLE " + NOMBRE_TABLA +
            " (" + CAMPO_ID_VENTA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CAMPO_ID_CLIENTE + " INTEGER, " +
            CAMPO_ID_LIBRO + " INTEGER, " +
            CAMPO_CANTIDAD_LIBROS + " INTEGER, " +
            CAMPO_COSTO_TOTAL + " FLOAT)";


    public static final String ELIMINAR_TABLA = "DROP TABLE " + NOMBRE_TABLA;
}
