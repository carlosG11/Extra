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
public class VOReporteSemanal {

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }
    
    private int id;
    private String nombre;
    private int frecuencia;
    private float importe;
    

    public VOReporteSemanal() {
        this.id = 0;
        this.nombre = "";
        this.frecuencia = 0;
        this.importe = 0;
    }

}
