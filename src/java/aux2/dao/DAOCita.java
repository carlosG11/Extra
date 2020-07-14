/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aux2.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import aux2.vo.VOUsuario;
import aux2.vo.VOCita;
import aux2.vo.VOCitaProducto;
import aux2.vo.VOPago;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/**
 *
 * @author QTK
 */
public class DAOCita {

    
    private static Integer obtenerId() throws SQLException, ClassNotFoundException{
        //objeto de conexion
        Connection cn = null;
        //sentencias sql
        PreparedStatement pr = null;
        //consultas sql
        ResultSet rs = null; 
        int id=0;
        try{
            cn = Conexion.getConexion();
            pr = cn.prepareStatement("SELECT MAX(id) FROM CITA");            
            rs = pr.executeQuery();            
            if (rs.next()){
                id = rs.getInt(1);
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        } finally {
            try{
                rs.close();
                pr.close();
                cn.close();
                
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return id+1;        
    } 
    
   
    public static String registrar(VOCita voCita) throws SQLException, ClassNotFoundException{
        //objeto de conexion
        Connection cn = null;
        //sentencias sql
        PreparedStatement pr = null;
        voCita.setId(0);
        try{
            voCita.setId(obtenerId());
            cn = Conexion.getConexion();
            pr = cn.prepareStatement("INSERT INTO Cita VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
            pr.setInt(1, voCita.getId());
            pr.setString(2, voCita.getSucursal());
            pr.setInt(3, voCita.getEstilista());
            pr.setString(4, voCita.getEstilistaNombre());
            pr.setString(5, voCita.getEstilistaPaterno());
            pr.setString(6, voCita.getEstilistaMaterno());
            pr.setInt(7, voCita.getCliente());
            pr.setString(8, voCita.getClienteNombre());
            pr.setString(9, voCita.getClientePaterno());
            pr.setString(10, voCita.getClienteMaterno());
            pr.setTimestamp(11, new java.sql.Timestamp(voCita.getTiempo().getTime()));
            pr.setInt(12, voCita.getDuracion());
            pr.setFloat(13, voCita.getPrecio());
            pr.setFloat(14, voCita.getSaldo());
            pr.setString(15, "registrada");
            pr.executeUpdate();
            return "";
        } catch(SQLException ex){
            ex.printStackTrace();
            voCita.setId(0);
            return "Error: " + ex.getMessage();
        } finally {
            try{
                pr.close();
                cn.close();
                
            } catch (SQLException ex){
                ex.printStackTrace();
                voCita.setId(0);
                return "Error: " + ex.getMessage();
            }
        }                    
    }    
    
    
    //la primera operacion seria la verificacion del tipo de usuario
    public static ArrayList buscarCitasPorEstilistaFecha(VOUsuario voUsuario, VOCita voCita) throws SQLException, ClassNotFoundException{        
        ArrayList resultados = new ArrayList();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String sFecha = formatter.format(voCita.getTiempo());
        VOCita resultado;
        //objeto de conexion
        Connection cn = null;
        //sentencias sql
        PreparedStatement pr = null;
        //consultas sql
        ResultSet rs = null;                
        try{            
            cn = Conexion.getConexion();
            String sQuery ="SELECT * FROM CITA WHERE estilista="+voUsuario.getId()+" AND tiempo >= '"+sFecha+" 09:00:00' AND tiempo<='"+sFecha+" 16:00:00' AND estatus='registrada' ";
            System.out.println("DAOCita.Query["+sQuery+"]");
            
            pr = cn.prepareStatement(sQuery);
//            pr.setString(1, voUsuario.getPerfil()+"%");
            rs = pr.executeQuery();
            
            while (rs.next()){
                resultado = new VOCita();
                resultado.setId(rs.getInt("id"));
                resultado.setSucursal(rs.getString("sucursal"));
                resultado.setEstilista(rs.getInt("estilista"));
                resultado.setEstilistaNombre(rs.getString("estilistaNombre"));
                resultado.setEstilistaPaterno(rs.getString("estilistaPaterno"));
                resultado.setEstilistaMaterno(rs.getString("estilistaMaterno"));
                resultado.setCliente(rs.getInt("cliente"));
                resultado.setClienteNombre(rs.getString("clienteNombre"));
                resultado.setClientePaterno(rs.getString("clientePaterno"));
                resultado.setClienteMaterno(rs.getString("clienteMaterno"));
                resultado.setTiempo(rs.getTimestamp("tiempo"));
                resultado.setDuracion(rs.getInt("duracion"));
                resultado.setPrecio(rs.getFloat("precio"));
                resultado.setSaldo(rs.getFloat("saldo"));
                resultado.setEstatus(rs.getString("estatus"));
                resultados.add(resultado);
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        } finally {
            try{
                rs.close();
                pr.close();
                cn.close();
                return resultados;                
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return resultados;                
    }   
    
    
    //la primera operacion seria la verificacion del tipo de usuario
    public static ArrayList buscarPorCliente(VOCita voCita) throws SQLException, ClassNotFoundException{
        ArrayList resultados = new ArrayList();
        VOCita resultado;
        //objeto de conexion
        Connection cn = null;
        //sentencias sql
        PreparedStatement pr = null;
        //consultas sql
        ResultSet rs = null;                
        try{            
            cn = Conexion.getConexion();            
            pr = cn.prepareStatement("SELECT * FROM CITA WHERE cliente=? ");
            pr.setInt(1, voCita.getCliente());
            rs = pr.executeQuery();
            
            while (rs.next()){
                resultado = new VOCita();
                resultado.setId(rs.getInt("id"));
                resultado.setSucursal(rs.getString("sucursal"));
                resultado.setEstilista(rs.getInt("estilista"));
                resultado.setEstilistaNombre(rs.getString("estilistaNombre"));
                resultado.setEstilistaPaterno(rs.getString("estilistaPaterno"));
                resultado.setEstilistaMaterno(rs.getString("estilistaMaterno"));
                resultado.setCliente(rs.getInt("cliente"));
                resultado.setClienteNombre(rs.getString("clienteNombre"));
                resultado.setClientePaterno(rs.getString("clientePaterno"));
                resultado.setClienteMaterno(rs.getString("clienteMaterno"));
                resultado.setTiempo(rs.getTimestamp("tiempo"));
                resultado.setDuracion(rs.getInt("duracion"));
                resultado.setPrecio(rs.getFloat("precio"));
                resultado.setSaldo(rs.getFloat("saldo"));
                resultado.setEstatus(rs.getString("estatus"));
                resultados.add(resultado);
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        } finally {
            try{
                rs.close();
                pr.close();
                cn.close();
                return resultados;                
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return resultados;                
    }       
    
    
    //la primera operacion seria la verificacion del tipo de usuario
    public static ArrayList buscarPorSucursal(VOCita voCita) throws SQLException, ClassNotFoundException{
        ArrayList resultados = new ArrayList();
        VOCita resultado;
        //objeto de conexion
        Connection cn = null;
        //sentencias sql
        PreparedStatement pr = null;
        //consultas sql
        ResultSet rs = null;                
        try{            
            System.out.println("DAOCita.buscarPorSucursal.1");            
            cn = Conexion.getConexion();            
            pr = cn.prepareStatement("SELECT * FROM CITA WHERE sucursal=? ");
            System.out.println("QUERY[SELECT * FROM CITA WHERE sucursal='"+voCita.getSucursal()+"']");            
            pr.setString(1, voCita.getSucursal());
            rs = pr.executeQuery();
            
            while (rs.next()){
                resultado = new VOCita();
                resultado.setId(rs.getInt("id"));
                resultado.setSucursal(rs.getString("sucursal"));
                resultado.setEstilista(rs.getInt("estilista"));
                resultado.setEstilistaNombre(rs.getString("estilistaNombre"));
                resultado.setEstilistaPaterno(rs.getString("estilistaPaterno"));
                resultado.setEstilistaMaterno(rs.getString("estilistaMaterno"));
                resultado.setCliente(rs.getInt("cliente"));
                resultado.setClienteNombre(rs.getString("clienteNombre"));
                resultado.setClientePaterno(rs.getString("clientePaterno"));
                resultado.setClienteMaterno(rs.getString("clienteMaterno"));
                resultado.setTiempo(rs.getTimestamp("tiempo"));
                resultado.setDuracion(rs.getInt("duracion"));
                resultado.setPrecio(rs.getFloat("precio"));
                resultado.setSaldo(rs.getFloat("saldo"));
                resultado.setEstatus(rs.getString("estatus"));
                resultados.add(resultado);
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        } finally {
            try{
                rs.close();
                pr.close();
                cn.close();
                return resultados;                
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return resultados;                
    }       
    
/*    
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    //la primera operacion seria la verificacion del tipo de usuario
    public static ArrayList buscarPorFecha(String sSucursal, Date dInicio, Date dFin) throws SQLException, ClassNotFoundException{
        ArrayList resultados = new ArrayList();
        VOCita resultado;
        //objeto de conexion
        Connection cn = null;
        //sentencias sql
        PreparedStatement pr = null;
        //consultas sql
        ResultSet rs = null;                
        try{            
            System.out.println("DAOCita.buscarPorFecha.1");            
            cn = Conexion.getConexion();
            pr = cn.prepareStatement("SELECT * FROM CITA WHERE sucursal=? AND tiempo >= '"+formatter.format(dInicio)+"' AND tiempo<='"+formatter.format(dFin)+"' ");
            System.out.println("QUERY[SELECT * FROM CITA WHERE sucursal=? AND tiempo >= '"+formatter.format(dInicio)+"' AND tiempo<='"+formatter.format(dFin)+"' ]");            
            pr.setString(1, sSucursal);
            rs = pr.executeQuery();            
            while (rs.next()){
                resultado = new VOCita();
                resultado.setId(rs.getInt("id"));
                resultado.setSucursal(rs.getString("sucursal"));
                resultado.setEstilista(rs.getInt("estilista"));
                resultado.setEstilistaNombre(rs.getString("estilistaNombre"));
                resultado.setEstilistaPaterno(rs.getString("estilistaPaterno"));
                resultado.setEstilistaMaterno(rs.getString("estilistaMaterno"));
                resultado.setCliente(rs.getInt("cliente"));
                resultado.setClienteNombre(rs.getString("clienteNombre"));
                resultado.setClientePaterno(rs.getString("clientePaterno"));
                resultado.setClienteMaterno(rs.getString("clienteMaterno"));
                resultado.setTiempo(rs.getTimestamp("tiempo"));
                resultado.setDuracion(rs.getInt("duracion"));
                resultado.setPrecio(rs.getFloat("precio"));
                resultado.setSaldo(rs.getFloat("saldo"));
                resultado.setEstatus(rs.getString("estatus"));
                resultados.add(resultado);
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        } finally {
            try{
                rs.close();
                pr.close();
                cn.close();
                return resultados;                
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return resultados;                
    }    //*/
    
    public static String consultar(VOCita voCita) throws SQLException, ClassNotFoundException{
        //objeto de conexion
        Connection cn = null;
        //sentencias sql
        PreparedStatement pr = null;
        //consultas sql
        ResultSet rs = null;                
        try{            
            cn = Conexion.getConexion();
            pr = cn.prepareStatement("SELECT * FROM CITA WHERE id = ? ");
            pr.setString(1, voCita.getId()+"%");
            rs = pr.executeQuery();
            
            if (rs.next()){
                voCita.setId(rs.getInt("id"));
                voCita.setSucursal(rs.getString("sucursal"));
                voCita.setEstilista(rs.getInt("estilista"));
                voCita.setEstilistaNombre(rs.getString("estilistaNombre"));
                voCita.setEstilistaPaterno(rs.getString("estilistaPaterno"));
                voCita.setEstilistaMaterno(rs.getString("estilistaMaterno"));
                voCita.setCliente(rs.getInt("cliente"));
                voCita.setClienteNombre(rs.getString("clienteNombre"));
                voCita.setClientePaterno(rs.getString("clientePaterno"));
                voCita.setClienteMaterno(rs.getString("clienteMaterno"));
                voCita.setTiempo(rs.getTimestamp("tiempo"));
                voCita.setDuracion(rs.getInt("duracion"));
                voCita.setPrecio(rs.getFloat("precio"));
                voCita.setSaldo(rs.getFloat("saldo"));                
                voCita.setEstatus(rs.getString("estatus"));
                return "";
            }
            return "Error, cita no encontrada.";
        } catch(SQLException ex){
            ex.printStackTrace();
            return "Error al consultar cita: " + ex.getMessage();
        } finally {
            try{
                rs.close();
                pr.close();
                cn.close();
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }                
   
    public static String cancelar(VOCita voCita) throws SQLException, ClassNotFoundException{
        //objeto de conexion
        Connection cn = null;
        //sentencias sql
        PreparedStatement pr = null;
        try{
            cn = Conexion.getConexion();
            pr = cn.prepareStatement("UPDATE Cita SET estatus='cancelada' WHERE id=?");
            pr.setInt(1, voCita.getId());
            pr.executeUpdate();
            return "";
        } catch(SQLException ex){
            ex.printStackTrace();
            return "Error: " + ex.getMessage();
        } finally {
            try{
                pr.close();
                cn.close();
                
            } catch (SQLException ex){
                ex.printStackTrace();
                return "Error: " + ex.getMessage();
            }
        }                    
    }  
    
    
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //la primera operacion seria la verificacion del tipo de usuario
    public static ArrayList buscarPorFecha(VOCita voCita) throws SQLException, ClassNotFoundException{
        ArrayList resultados = new ArrayList();
        VOCita resultado;
        //objeto de conexion
        Connection cn = null;
        //sentencias sql
        PreparedStatement pr = null;
        //consultas sql
        ResultSet rs = null;                
        try{            
            System.out.println("DAOCita.buscarPorFecha.1");
            cn = Conexion.getConexion();
            String sQuery = "SELECT * FROM CITA WHERE sucursal='"+ voCita.getSucursal()+ "' AND tiempo >= '"+formatter.format(voCita.getTiempo())+"' AND tiempo<='"+formatter.format(voCita.getTiempoFin())+"' ";
            pr = cn.prepareStatement(sQuery);
            System.out.println("QUERY["+sQuery+"]");
//            pr.setString(1, sSucursal);
            rs = pr.executeQuery();            
            while (rs.next()){                                
                resultado = new VOCita();                
                resultado.setId(rs.getInt("id"));
                resultado.setSucursal(rs.getString("sucursal"));
                resultado.setEstilista(rs.getInt("estilista"));
                resultado.setEstilistaNombre(rs.getString("estilistaNombre"));
                resultado.setEstilistaPaterno(rs.getString("estilistaPaterno"));
                resultado.setEstilistaMaterno(rs.getString("estilistaMaterno"));
                resultado.setCliente(rs.getInt("cliente"));
                resultado.setClienteNombre(rs.getString("clienteNombre"));
                resultado.setClientePaterno(rs.getString("clientePaterno"));
                resultado.setClienteMaterno(rs.getString("clienteMaterno"));
                resultado.setTiempo(rs.getTimestamp("tiempo"));
                resultado.setDuracion(rs.getInt("duracion"));
                resultado.setPrecio(rs.getFloat("precio"));
                resultado.setSaldo(rs.getFloat("saldo"));                
                resultado.setEstatus(rs.getString("estatus"));                
                resultados.add(resultado);
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        } finally {
            try{
                rs.close();
                pr.close();
                cn.close();
                return resultados;                
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return resultados;                
    }        
        
    
}