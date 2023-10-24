package com.maskeit.basesdatos;

import java.io.Serializable;
// getters and setters
public class Usuarios implements Serializable {
    public Integer id;
    public String nombre;
    public String apellido;
    public String telefono;
    public String bdate;
    public Integer edad;
    public Integer estatura;
    public String genero;

    public Usuarios(Integer id, String nombre, String apellido,
                    String telefono, String bdate,
                    Integer edad, Integer estatura,
                    String genero) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.bdate = bdate;
        this.edad = edad;
        this.estatura = estatura;
        this.genero = genero;
    }

    public Usuarios(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getEstatura() {
        return estatura;
    }

    public void setEstatura(Integer estatura) {
        this.estatura = estatura;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
