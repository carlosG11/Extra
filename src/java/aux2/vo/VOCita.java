/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aux2.vo;

import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author QTK
 */
public class VOCita {
    private int id;
    private String sucursal;
    private int estilista;
    private String estilistaNombre;
    private String estilistaPaterno;
    private String estilistaMaterno;
    private int cliente;
    private String clienteNombre;
    private String clientePaterno;
    private String clienteMaterno;
    private Date  tiempo;
    private Date  tiempoFin;
    private int duracion; //minutos
    private float precio; 
    private float saldo; 
    private String estatus;
    private ArrayList <VOProducto>productos;
    private ArrayList <VOPago>pagos;
    private String[] sProductos;

    public VOCita() {
        this.id = 0;
        this.sucursal = "";
        this.estilista = 0;
        this.estilistaNombre = "";
        this.estilistaPaterno = "";
        this.estilistaMaterno = "";
        this.cliente = 0;
        this.clienteNombre = "";
        this.clientePaterno = "";
        this.clienteMaterno = "";
//        this.tiempo = 0;
        this.duracion = 0;
        this.precio = 0;
        this.saldo = 0;
        this.estatus = "";
        this.productos = new ArrayList();
        this.pagos = new ArrayList();
    }

    public Date getTiempoFin() {
        return tiempoFin;
    }

    public void setTiempoFin(Date tiempoFin) {
        this.tiempoFin = tiempoFin;
    }
    
    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public String getClientePaterno() {
        return clientePaterno;
    }

    public void setClientePaterno(String clientePaterno) {
        this.clientePaterno = clientePaterno;
    }

    public String getClienteMaterno() {
        return clienteMaterno;
    }

    public void setClienteMaterno(String clienteMaterno) {
        this.clienteMaterno = clienteMaterno;
    }


    public ArrayList<VOProducto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<VOProducto> productos) {
        this.productos = productos;
    }

    public ArrayList<VOPago> getPagos() {
        return pagos;
    }

    public void setPagos(ArrayList<VOPago> pagos) {
        this.pagos = pagos;
    }

    public String[] getsProductos() {
        return sProductos;
    }

    public void setsProductos(String[] sProductos) {
        this.sProductos = sProductos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public int getEstilista() {
        return estilista;
    }

    public void setEstilista(int estilista) {
        this.estilista = estilista;
    }

    public String getEstilistaNombre() {
        return estilistaNombre;
    }

    public void setEstilistaNombre(String estilistaNombre) {
        this.estilistaNombre = estilistaNombre;
    }

    public String getEstilistaPaterno() {
        return estilistaPaterno;
    }

    public void setEstilistaPaterno(String estilistaPaterno) {
        this.estilistaPaterno = estilistaPaterno;
    }

    public String getEstilistaMaterno() {
        return estilistaMaterno;
    }

    public void setEstilistaMaterno(String estilistaMaterno) {
        this.estilistaMaterno = estilistaMaterno;
    }

    public Date getTiempo() {
        return tiempo;
    }

    public void setTiempo(Date tiempo) {
        this.tiempo = tiempo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }    
}