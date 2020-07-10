/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aux2.fachada;

import aux2.dao.DAOUsuario;
import aux2.dao.DAOCita;
import aux2.vo.VOCita;
import aux2.vo.VOUsuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

import java.text.SimpleDateFormat;

/**
 *
 * @author Eduardo
 */
public class FACCita {
    //duracion de un minuto en milisegundos
    private static final long ONE_MINUTE_IN_MILLIS=60000;//millisecs
    
    private static final SimpleDateFormat formatterFecha = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat formatterTiempo = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    private static VOCita obtenerCitaInterrumpida(Date dInicio, int iDuracion, ArrayList citas){
        VOCita voCita;
        //primero calcula el final del intervalo de la cita
        long lFinal = dInicio.getTime() + iDuracion * ONE_MINUTE_IN_MILLIS;
        Date dFin = new Date(lFinal);
        //despu'es, evalua cada cita para ver cual tiene su INICIO dentro del intervalo 
        for (int i=0;i<citas.size();i++){
            voCita = (VOCita)citas.get(i);
            //verifica si la cita empieza dentro del intervalo
            if (dInicio.compareTo(voCita.getTiempo())<=0){
                if (dFin.compareTo(voCita.getTiempo())>0) {
                    return voCita;
                }
            }            
        }
        return null;
    }
    
    private static String asignarCita(VOUsuario voUsuario, VOCita voCita, ArrayList citas){        
        try {
            VOCita voCitaInterrumpida;
            //primero se asigna la fecha inicial con el inicio del intervalo (09 AM)
            int iDuracion = voCita.getDuracion();
            Date dateInicio = voCita.getTiempo();
            Date dateFinDelDia;
            Date dateFinCita;
            dateInicio = formatterTiempo.parse(formatterFecha.format(dateInicio)+" 09:00");
            dateFinDelDia = formatterTiempo.parse(formatterFecha.format(dateInicio)+" 16:00");
            voCitaInterrumpida = obtenerCitaInterrumpida(dateInicio, iDuracion, citas);
            //mientras que existan citas interrumpidas, sigue buscando 
            while (voCitaInterrumpida !=null){
                dateInicio = voCitaInterrumpida.getTiempo();
                voCitaInterrumpida = obtenerCitaInterrumpida(dateInicio, iDuracion, citas);
            }
            //al final de recorrer todas las citas interrumpidas, se obtiene el tiempo de inicio posible para la cita
            dateFinCita = new Date(dateInicio.getTime() + iDuracion * ONE_MINUTE_IN_MILLIS);
            //si el inicio obtenido + la duracion, ocurren antes del fin del d'ia, la cita es pre-asignada
            if (dateFinCita.compareTo(dateFinDelDia)<=0){
                voCita.setTiempo(dateInicio);
                voCita.setEstilista(voUsuario.getId());
                voCita.setEstilistaNombre(voUsuario.getNombre());
                voCita.setEstilistaPaterno(voUsuario.getPaterno());
                voCita.setEstilistaMaterno(voUsuario.getMaterno());
                return "";
            }
            //pero si el inicio o la duracion de la cita, ocurren despu'es de la hora del cierre, entonces
            //se retorna con error porque no se pudo asignar la cita.
            return "Error: Sin disponibilidad";
        } catch(Exception ex){
            ex.printStackTrace();
            return "Error al asignar cita: " + ex.getMessage();            
        }
    }
    
    public static String consultarDisponibilidad(VOCita voCita){
        String sResultado="";
        try {
            VOUsuario voUsuario = new VOUsuario();
            voUsuario.setSucursal(voCita.getSucursal());
            ArrayList resultados = new ArrayList();
            ArrayList resultadosCitas = new ArrayList();
            resultados = DAOUsuario.buscarEstilistasPorSucursal(voUsuario);
            //para cada estilista, se va a buscar quien tiene disponibilidad
            for (int i=0;i<resultados.size();i++){
                voUsuario = (VOUsuario)resultados.get(i);
                resultadosCitas = DAOCita.buscarCitasPorEstilistaFecha(voUsuario, voCita);
                sResultado = asignarCita(voUsuario,voCita, resultadosCitas);
                //si el resultado es vac'io, entonces se pre-asign'o exitosamente la cita
                if ("".equals(sResultado))
                    return "";                
            }
            return sResultado;
        }catch (Exception ex){
            ex.printStackTrace();            
            return "Error al consultar Disponibilidad: " + ex.getMessage();        
        }
    }    
}
