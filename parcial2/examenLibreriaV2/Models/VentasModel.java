package com.maskeit.libreria.Models;

import java.io.Serializable;

public class VentasModel implements Serializable {
    private Integer idVenta;
    private Integer idCliente;
    private Integer idLibro;
    private Integer cantidadLibros;
    private float costoTotal;

    // Constructor
    public VentasModel(Integer idVenta, Integer idCliente, Integer idLibro, Integer cantidadLibros, float costoTotal) {
        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.idLibro = idLibro;
        this.cantidadLibros = cantidadLibros;
        this.costoTotal = costoTotal;
    }

    // Getters y Setters

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    public Integer getCantidadLibros() {
        return cantidadLibros;
    }

    public void setCantidadLibros(Integer cantidadLibros) {
        this.cantidadLibros = cantidadLibros;
    }

    public float getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(float costoTotal) {
        this.costoTotal = costoTotal;
    }
}
