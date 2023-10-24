package com.maskeit.basesdatos;

public class Variables {
    public static final String NOMBRE_BD = "bd_usuarios";
    public static final String NOMBRE_TABLA = "usuarios";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_APELLIDO = "apellido";
    public static final String CAMPO_TELEFONO = "telefono";
    public static final String CAMPO_BDAY = "bdate";
    public static final String CAMPO_EDAD = "edad";
    public static final String CAMPO_GENERO = "genero";
    public static final String CAMPO_ESTATURA = "estatura";

    public static final String CREAR_TABLA = "CREATE TABLE "+ NOMBRE_TABLA+
            " ("+CAMPO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            CAMPO_NOMBRE+" TEXT, "+
            CAMPO_APELLIDO+" TEXT, "+
            CAMPO_ESTATURA+" TEXT, "+
            CAMPO_EDAD+" TEXT, "+
            CAMPO_BDAY+" TEXT, "+
            CAMPO_GENERO+" TEXT, "+
            CAMPO_TELEFONO+" TEXT)";

    public static final String ELIMINAR_TABLA = "DROP TABLE "+ NOMBRE_TABLA;
}
