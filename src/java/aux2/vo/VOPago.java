/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aux2.vo;

import java.time.LocalDateTime;

import java.util.Date;

/**
 *
 * @author QTK
 */
public class VOPago {
    private int id;
    private int cita;
    private int cliente;
    private String clienteNombre;
    private String clientePaterno;
    private String clienteMaterno;
    private float importe;
    private Date  tiempo;
    private String tdc;
    private String propietario;
    private String caducaMes;
    private String caducaAno;
    private String cvv;                
    
    public VOPago() {
        this.id = 0;
        this.cita = 0;
        this.cliente = 0;
        this.clienteNombre="";
        this.clientePaterno="";
        this.clienteMaterno="";
        this.importe = 0;
        this.tiempo = new Date();
        this.tdc="";
        this.propietario = "";
        this.caducaMes = "";
        this.caducaAno = "";
        this.cvv = "";
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

    public int getCita() {
        return cita;
    }

    public void setCita(int cita) {
        this.cita = cita;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public Date getTiempo() {
        return tiempo;
    }

    public void setTiempo(Date tiempo) {
        this.tiempo = tiempo;
    }

    public String getTdc() {
        return tdc;
    }

    public void setTdc(String tdc) {
        this.tdc = tdc;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getCaducaMes() {
        return caducaMes;
    }

    public void setCaducaMes(String caducaMes) {
        this.caducaMes = caducaMes;
    }

    public String getCaducaAno() {
        return caducaAno;
    }

    public void setCaducaAno(String caducaAno) {
        this.caducaAno = caducaAno;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
