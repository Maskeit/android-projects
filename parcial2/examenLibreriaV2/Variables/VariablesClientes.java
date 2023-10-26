package com.maskeit.libreria.Variables;

import com.maskeit.libreria.Models.DB;

public class VariablesClientes {
    public static String NOMBRE_BD = DB.NOMBRE_BD;
    public static final String NOMBRE_TABLA = "clientes";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_RFC = "rfc";

    public static final String CREAR_TABLA = "CREATE TABLE "+ NOMBRE_TABLA+
            " ("+CAMPO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            CAMPO_NOMBRE+" TEXT, "+
            CAMPO_RFC+" TEXT)";

    public static final String ELIMINAR_TABLA = "DROP TABLE "+ NOMBRE_TABLA;
}
