/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aux2.dao;

import aux2.vo.VOCita;
import java.sql.*;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

import aux2.vo.VOProducto;
import aux2.vo.VOCitaProducto;
import aux2.vo.VOUsuario;

/**
 *
 * @author QTK
 */
public class DAOCitaProducto {
    
    
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
            pr = cn.prepareStatement("SELECT MAX(id) FROM CITAPRODUCTO");
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
    
   
    public static String registrar(VOCitaProducto voCitaProducto) throws SQLException, ClassNotFoundException{
        //objeto de conexion
        Connection cn = null;
        //sentencias sql
        PreparedStatement pr = null;
        voCitaProducto.setId(0);
        try{
            voCitaProducto.setId(obtenerId());
            cn = Conexion.getConexion();
            pr = cn.prepareStatement("INSERT INTO CitaProducto VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )");
            pr.setInt(1, voCitaProducto.getId());
            pr.setInt(2, voCitaProducto.getCita());
            pr.setInt(3, voCitaProducto.getProducto());
            pr.setString(4, voCitaProducto.getProductoNombre());
            pr.setInt(5, voCitaProducto.getProductoDuracion());
            pr.setFloat(6, voCitaProducto.getProductoPrecio());            
            pr.setTimestamp(7, new java.sql.Timestamp(voCitaProducto.getTiempo().getTime()));
            pr.setString(8, voCitaProducto.getSucursal());
            pr.executeUpdate();
            return "";
        } catch(SQLException ex){
            ex.printStackTrace();
            voCitaProducto.setId(0);
            return "Error: " + ex.getMessage();
        } finally {
            try{
                pr.close();
                cn.close();
                
            } catch (SQLException ex){
                ex.printStackTrace();
                voCitaProducto.setId(0);
                return "Error: " + ex.getMessage();
            }
        }                    
    }   

    //la primera operacion seria la verificacion del tipo de usuario
    public static String cargarProductos(VOCita voCita) throws SQLException, ClassNotFoundException{
        ArrayList resultados = new ArrayList();
        ArrayList productos = new ArrayList();
        VOCitaProducto resultado;
        //objeto de conexion
        Connection cn = null;
        //sentencias sql
        PreparedStatement pr = null;
        //consultas sql
        ResultSet rs = null;                
        try{            
            cn = Conexion.getConexion();
            pr = cn.prepareStatement("SELECT * FROM CITAPRODUCTO WHERE cita=?");
            pr.setInt(1, voCita.getId());
            rs = pr.executeQuery();
            
            while (rs.next()){
                resultado = new VOCitaProducto();
                resultado.setId(rs.getInt("id"));
                resultado.setCita(rs.getInt("cita"));
                resultado.setProducto(rs.getInt("producto"));
                productos.add(""+resultado.getProducto());
                resultado.setProductoNombre(rs.getString("productoNombre"));
                resultado.setProductoDuracion(rs.getInt("productoDuracion"));
                resultado.setProductoPrecio(rs.getFloat("productoPrecio"));
                resultado.setTiempo(rs.getTimestamp("tiempo"));
                resultados.add(resultado);
            }
            voCita.setProductos(resultados);
            String[] sProductos =new String[productos.size()];
            for (int i=0;i<sProductos.length;i++)
                sProductos[i]=(String)productos.get(i);            
            voCita.setsProductos(sProductos);
            return "";
        } catch(SQLException ex){
            ex.printStackTrace();
            return "Error al cargar productos: " + ex.getMessage();
        } finally {
            try{
                rs.close();
                pr.close();
                cn.close();
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }     //*/
    
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    //la primera operacion seria la verificacion del tipo de usuario
    public static ArrayList buscarPorFecha(VOCita voCita) throws SQLException, ClassNotFoundException{
        ArrayList resultados = new ArrayList();
        VOCitaProducto resultado;
        //objeto de conexion
        Connection cn = null;
        //sentencias sql
        PreparedStatement pr = null;
        //consultas sql
        ResultSet rs = null;                
        try{            
            System.out.println("DAOCitaProducto.buscarPorFecha.1");            
            cn = Conexion.getConexion();
            String sQuery = "SELECT * FROM CITAPRODUCTO WHERE sucursal='"+ voCita.getSucursal()+ "' AND tiempo >= '"+formatter.format(voCita.getTiempo())+"' AND tiempo<='"+formatter.format(voCita.getTiempoFin())+"' ";
            pr = cn.prepareStatement(sQuery);
            System.out.println("QUERY["+sQuery+"]");
//            pr.setString(1, sSucursal);
            rs = pr.executeQuery();            
            while (rs.next()){                                
                resultado = new VOCitaProducto();
                resultado.setId(rs.getInt("id"));
                resultado.setCita(rs.getInt("cita"));
                resultado.setProducto(rs.getInt("producto"));
                resultado.setProductoNombre(rs.getString("productoNombre"));
                resultado.setProductoDuracion(rs.getInt("productoDuracion"));
                resultado.setProductoPrecio(rs.getFloat("productoPrecio"));
                resultado.setTiempo(rs.getTimestamp("tiempo"));
                resultado.setSucursal(rs.getString("sucursal"));
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
