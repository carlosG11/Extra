/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aux2.vo;

import java.time.LocalDateTime;

/**
 *
 * @author QTK
 */
public class VOPago {
    private int id;
    private float importe;
    private LocalDateTime  tiempo;
    private String tdc;    

    public VOPago() {
        this.id = 0;
        this.importe = 0;
//        this.tiempo = tiempo;
        this.tdc = "";
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

    public LocalDateTime getTiempo() {
        return tiempo;
    }

    public void setTiempo(LocalDateTime tiempo) {
        this.tiempo = tiempo;
    }

    public String getTdc() {
        return tdc;
    }

    public void setTdc(String tdc) {
        this.tdc = tdc;
    }
}
