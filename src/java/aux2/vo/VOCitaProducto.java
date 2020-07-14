/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aux2.vo;

import java.util.Date;

/**
 *
 * @author QTK
 */
public class VOCitaProducto {
    private int id;
    private int cita;
    private int producto;
    private String productoNombre;
    private int productoDuracion;
    private float productoPrecio;
    private Date tiempo;
    private String sucursal;

    public VOCitaProducto() {
        this.id = 0;
        this.cita = 0;
        this.producto = 0;
        this.productoNombre = "";
        this.productoDuracion = 0;
        this.productoPrecio = 0;
        tiempo = new Date();
        this.sucursal = "";
    }


    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }
    public Date getTiempo() {
        return tiempo;
    }

    public void setTiempo(Date tiempo) {
        this.tiempo = tiempo;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCita() {
        return cita;
    }

    public void setCita(int cita) {
        this.cita = cita;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    public int getProductoDuracion() {
        return productoDuracion;
    }

    public void setProductoDuracion(int productoDuracion) {
        this.productoDuracion = productoDuracion;
    }

    public float getProductoPrecio() {
        return productoPrecio;
    }

    public void setProductoPrecio(float productoPrecio) {
        this.productoPrecio = productoPrecio;
    }

}
