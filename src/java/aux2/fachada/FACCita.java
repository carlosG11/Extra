/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aux2.fachada;

import aux2.dao.DAOUsuario;
import aux2.dao.DAOCita;
import aux2.dao.DAOProducto;
import aux2.dao.DAOCitaProducto;
import aux2.dao.DAOPago;
import aux2.vo.VOCita;
import aux2.vo.VOCitaProducto;
import aux2.vo.VOProducto;
import aux2.vo.VOReporteSemanal;
import aux2.vo.VOUsuario;
import aux2.vo.VOPago;
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
        System.out.println("------------ obtenerCitaInterrumpida dInicio["+dInicio+"] dFin["+dFin+"]");
        //despu'es, evalua cada cita para ver cual tiene su INICIO dentro del intervalo 
        for (int i=0;i<citas.size();i++){
            voCita = (VOCita)citas.get(i);
            System.out.println("------------ evaluando cita.Tiempo ["+voCita.getTiempo()+"] dInicio["+dInicio.compareTo(voCita.getTiempo())+"] dFin["+dFin.compareTo(voCita.getTiempo())+"]");
            //verifica si la cita empieza dentro del intervalo
            if (dInicio.compareTo(voCita.getTiempo())<=0){
                if (dFin.compareTo(voCita.getTiempo())>0) {
                    System.out.println("------------ SE ENCUENTRA CITA INTERRUMPIDA!");
                    citas.remove(voCita);
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
            long lFinal;
            Date dateInicio = voCita.getTiempo();
            Date dateFinDelDia;
            Date dateFinCita;
            dateInicio = formatterTiempo.parse(formatterFecha.format(dateInicio)+" 09:00");
            dateFinDelDia = formatterTiempo.parse(formatterFecha.format(dateInicio)+" 16:00");
            System.out.println("-------- asignar Cita INICIO.");
            voCitaInterrumpida = obtenerCitaInterrumpida(dateInicio, iDuracion, citas);
            //mientras que existan citas interrumpidas, sigue buscando 
            while (voCitaInterrumpida !=null){
                //se pasa al final de la cita interrumpida y eval'ua si siguen habiendo citas interrumpidas
                lFinal = voCitaInterrumpida.getTiempo().getTime() + voCitaInterrumpida.getDuracion()* ONE_MINUTE_IN_MILLIS;
                dateInicio = new Date(lFinal);
                voCitaInterrumpida = obtenerCitaInterrumpida(dateInicio, iDuracion, citas);
            }
            //al final de recorrer todas las citas interrumpidas, se obtiene el tiempo de inicio posible para la cita
            dateFinCita = new Date(dateInicio.getTime() + iDuracion * ONE_MINUTE_IN_MILLIS);
            System.out.println("-------- se terminan las citas interrumpidas: dInicio["+dateInicio+"] dateFinCita["+dateFinCita+"] dateFinDelDia["+dateFinDelDia+"].");
            //si el inicio obtenido + la duracion, ocurren antes del fin del d'ia, la cita es pre-asignada
            if (dateFinCita.compareTo(dateFinDelDia)<=0){
                voCita.setTiempo(dateInicio);
                voCita.setEstilista(voUsuario.getId());
                voCita.setEstilistaNombre(voUsuario.getNombre());
                voCita.setEstilistaPaterno(voUsuario.getPaterno());
                voCita.setEstilistaMaterno(voUsuario.getMaterno());
                System.out.println("-------- SE ASIGNA EXITOSAMENTE LA CITA.");
                return "";
            }
            //pero si el inicio o la duracion de la cita, ocurren despu'es de la hora del cierre, entonces
            //se retorna con error porque no se pudo asignar la cita.
            return "NO SE CUENTA CON DISPONIBILIDAD PARA ESTE DIA, INTENTE OTRO DIA O ELEGIR MENOS SERVICIOS.";
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
            System.out.println("FACCita.consultarDisponibilidad:estilstas:"+resultados.size());
            //para cada estilista, se va a buscar quien tiene disponibilidad
            for (int i=0;i<resultados.size();i++){
                voUsuario = (VOUsuario)resultados.get(i);
                resultadosCitas = DAOCita.buscarCitasPorEstilistaFecha(voUsuario, voCita);
                System.out.println("---- estilista:"+voUsuario.getId()+"-"+voUsuario.getNombre()+": "+resultadosCitas.size()+" citas.");
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

    public static String registrar(VOCita voCita){
        VOCitaProducto voCitaProducto;
        String sResultado="";
        try {
            System.out.println("FACCita.registrar.1");
            //primero se intenta registrar la Cita
            sResultado = DAOCita.registrar(voCita);
            if (!"".equals(sResultado)){
                return "Error al registrar la Cita: " + sResultado;                
            }
            //para cada uno de los productos seleccionados
            String[] sProductos = voCita.getsProductos();
            for (int i=0;i<sProductos.length;i++){
                voCitaProducto = new VOCitaProducto();
                voCitaProducto.setCita(voCita.getId());
                voCitaProducto.setProducto(Integer.parseInt(sProductos[i]));
                voCitaProducto.setTiempo(voCita.getTiempo());
                voCitaProducto.setSucursal(voCita.getSucursal());
                //consulta la informaci'on del producto en la tabla de productos
                sResultado = DAOProducto.consultarCitaProducto(voCitaProducto);
                if (!"".equals(sResultado)){
                    return "Error al consultar el Producto: " + sResultado;
                }
                //y luego genera el registro en CitaProducto
                sResultado = DAOCitaProducto.registrar(voCitaProducto);
                if (!"".equals(sResultado)){
                    return "Error al registrar la referencia Cita/Producto: " + sResultado;
                }
            }
            return "";
        }catch (Exception ex){
            ex.printStackTrace();            
            return "Error al registrar Cita/Producto: " + ex.getMessage();
        }
    }    


    public static String consultar(VOCita voCita){
        String sResultado="";
        try {
            //primero se intenta registrar la Cita
            sResultado = DAOCita.consultar(voCita);
            if (!"".equals(sResultado)){
                return "Error al consultar la Cita: " + sResultado;
            }
            sResultado = DAOCitaProducto.cargarProductos(voCita);
            if (!"".equals(sResultado)){
                return "Error al consultar la Cita: " + sResultado;
            }
            sResultado = DAOPago.cargarPagos(voCita);
            return sResultado;
        }catch (Exception ex){
            ex.printStackTrace();            
            return "Error al registrar Cita/Producto: " + ex.getMessage();
        }
    }    
    
    public static String cancelar(VOCita voCita){
        String sResultado="";
        try {
            System.out.println("FACCita.cancelar.1");
            sResultado = DAOCita.cancelar(voCita);
            if (!"".equals(sResultado)){
                return "Error al cancelar la Cita: " + sResultado;
            }
            return consultar(voCita);
        }catch (Exception ex){
            ex.printStackTrace();            
            return "Error al cancelar Cita/Producto: " + ex.getMessage();
        }
    }    

    private static VOReporteSemanal obtenerReporteSemanal(ArrayList resultados, int id){
        VOReporteSemanal voReporteSemanal;
        for (int i=0;i<resultados.size();i++){
            voReporteSemanal = (VOReporteSemanal)resultados.get(i);
            if (id==voReporteSemanal.getId()){
                return voReporteSemanal;                
            }            
        }
        return null;
    }
    
    private static void actualizarReporteProductos(VOCitaProducto voCitaProducto, ArrayList resultados){
        VOReporteSemanal voReporteSemanal;
        voReporteSemanal = obtenerReporteSemanal(resultados, voCitaProducto.getProducto());
        if (null==voReporteSemanal){
            voReporteSemanal = new VOReporteSemanal();
            voReporteSemanal.setId(voCitaProducto.getProducto());
            voReporteSemanal.setNombre(voCitaProducto.getProductoNombre());
            voReporteSemanal.setFrecuencia(0);
            voReporteSemanal.setImporte(voCitaProducto.getProductoPrecio());
            resultados.add(voReporteSemanal);                
        }
        voReporteSemanal.setFrecuencia(voReporteSemanal.getFrecuencia()+1);
    }
    
    public static String generarReporteSemanal(VOCita voCita, ArrayList resultados){
        try {
            VOCitaProducto item;
            ArrayList <VOCitaProducto>citaProductos;
            //primero define las fechas de inicio y fin del reporte
            Calendar cal = Calendar.getInstance();        
            cal.setTime(voCita.getTiempo());
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            cal.set(Calendar.HOUR_OF_DAY, 9);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            Date dInicio = cal.getTime();
            cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
            cal.set(Calendar.HOUR_OF_DAY, 16);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            Date dFin = cal.getTime();
            voCita.setTiempo(dInicio);
            voCita.setTiempoFin(dFin);
            //luego obtiene los productos que se encuentran en el rango de fechas
            citaProductos = DAOCitaProducto.buscarPorFecha(voCita);
            System.out.println("-----> FACCita.generarReporteSemanal:citaProductos: " + citaProductos.size());
            for (int i=0;i<citaProductos.size();i++){
                item = (VOCitaProducto)citaProductos.get(i);
                actualizarReporteProductos(item, resultados);
            }            
            return "";            
        } catch(Exception ex){
            ex.printStackTrace();            
            return "Error al generar Reporte";
        }        
    }
    
    
    private static VOReporteSemanal obtenerReportePersonal(ArrayList resultados, int id){
        VOReporteSemanal voReporteSemanal;
        for (int i=0;i<resultados.size();i++){
            voReporteSemanal = (VOReporteSemanal)resultados.get(i);
            if (id==voReporteSemanal.getId()){
                return voReporteSemanal;                
            }            
        }
        return null;
    }
    
    private static void actualizarReportePersonal(VOCita voCita, ArrayList resultados){
        VOReporteSemanal voReporteSemanal;
        voReporteSemanal = obtenerReportePersonal(resultados, voCita.getEstilista());
        if (null==voReporteSemanal){
            voReporteSemanal = new VOReporteSemanal();
            voReporteSemanal.setId(voCita.getEstilista());
            voReporteSemanal.setNombre(voCita.getEstilistaNombre()+" "+voCita.getEstilistaPaterno()+" "+voCita.getEstilistaMaterno());
            voReporteSemanal.setFrecuencia(0);
            voReporteSemanal.setImporte(0);
            resultados.add(voReporteSemanal);                
        }
        voReporteSemanal.setFrecuencia(voReporteSemanal.getFrecuencia()+1);
        voReporteSemanal.setImporte(voReporteSemanal.getImporte()+voCita.getPrecio());
    }
    public static String generarReporteServicios(VOCita voCita, ArrayList resultados){
        try {
            VOCitaProducto item;
            ArrayList <VOCitaProducto>citaProductos;
            //primero define las fechas de inicio y fin del reporte
            Calendar cal = Calendar.getInstance();        
            cal.setTime(voCita.getTiempo());            
//            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            cal.set(Calendar.HOUR_OF_DAY, 9);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            Date dInicio = cal.getTime();
            cal.setTime(voCita.getTiempoFin());
//            cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
            cal.set(Calendar.HOUR_OF_DAY, 16);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            Date dFin = cal.getTime();
            voCita.setTiempo(dInicio);
            voCita.setTiempoFin(dFin);
            //luego obtiene los productos que se encuentran en el rango de fechas
            citaProductos = DAOCitaProducto.buscarPorFecha(voCita);
            System.out.println("-----> FACCita.generarReporteSemanal:citaProductos: " + citaProductos.size());
            for (int i=0;i<citaProductos.size();i++){
                item = (VOCitaProducto)citaProductos.get(i);
                actualizarReporteProductos(item, resultados);
            }            
            return "";            
        } catch(Exception ex){
            ex.printStackTrace();            
            return "Error al generar Reporte";
        }        
    }
    
    
    public static String generarReportePersonal(VOCita voCita, ArrayList resultados){
        try {
            VOCita item;
            ArrayList <VOCita>citas;
            //primero define las fechas de inicio y fin del reporte
            Calendar cal = Calendar.getInstance();        
            cal.setTime(voCita.getTiempo());            
//            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            cal.set(Calendar.HOUR_OF_DAY, 9);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            Date dInicio = cal.getTime();
            cal.setTime(voCita.getTiempoFin());
//            cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
            cal.set(Calendar.HOUR_OF_DAY, 16);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            Date dFin = cal.getTime();
            voCita.setTiempo(dInicio);
            voCita.setTiempoFin(dFin);
            //luego obtiene los productos que se encuentran en el rango de fechas
            citas = DAOCita.buscarPorFecha(voCita);
            System.out.println("-----> FACCita.generarReportePersonal:citas: " + citas.size());
            for (int i=0;i<citas.size();i++){
                item = (VOCita)citas.get(i);
                actualizarReportePersonal(item, resultados);
            }            
            return "";            
        } catch(Exception ex){
            ex.printStackTrace();            
            return "Error al generar Reporte";
        }        
    }    
}
