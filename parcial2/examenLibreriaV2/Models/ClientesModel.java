package com.maskeit.libreria.Models;

import java.io.Serializable;

public class ClientesModel implements Serializable {
    public Integer id;
    public String nombre;
    public String rfc;

    public ClientesModel(Integer id, String nombre, String rfc) {
        this.id = id;
        this.nombre = nombre;
        this.rfc = rfc;
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

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }
}
