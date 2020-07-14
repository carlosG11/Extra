/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aux2.dao;

import java.sql.*;
import java.util.ArrayList;

import aux2.vo.VOProducto;
import aux2.vo.VOCitaProducto;
import aux2.vo.VOUsuario;

/**
 *
 * @author QTK
 */
public class DAOProducto {
    
    //la primera operacion seria la verificacion del tipo de usuario
    public static ArrayList listar() throws SQLException, ClassNotFoundException{
        ArrayList resultados = new ArrayList();
        VOProducto resultado;
        //objeto de conexion
        Connection cn = null;
        //sentencias sql
        PreparedStatement pr = null;
        //consultas sql
        ResultSet rs = null;                
        try{            
            cn = Conexion.getConexion();
            pr = cn.prepareStatement("SELECT * FROM PRODUCTO");
            rs = pr.executeQuery();            
            while (rs.next()){
                resultado = new VOProducto();
                resultado.setId(rs.getInt("id"));
                resultado.setTipo(rs.getString("tipo"));
                resultado.setNombre(rs.getString("nombre"));
                resultado.setDuracion(rs.getInt("duracion"));
                resultado.setPrecio(rs.getFloat("precio"));
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
    public static String consultarCitaProducto(VOCitaProducto voCitaProducto) throws SQLException, ClassNotFoundException{
        //objeto de conexion
        Connection cn = null;
        //sentencias sql
        PreparedStatement pr = null;
        //consultas sql
        ResultSet rs = null;                
        try{            
            cn = Conexion.getConexion();
            pr = cn.prepareStatement("SELECT * FROM PRODUCTO WHERE id = ? ");
            pr.setString(1, voCitaProducto.getProducto()+"%");
            rs = pr.executeQuery();
            
            if (rs.next()){
                voCitaProducto.setProducto(rs.getInt("id"));
                voCitaProducto.setProductoNombre(rs.getString("nombre"));
                voCitaProducto.setProductoDuracion(rs.getInt("duracion"));
                voCitaProducto.setProductoPrecio(rs.getFloat("precio"));
                return "";
            }
            return "Error, producto no encontrado.";
        } catch(SQLException ex){
            ex.printStackTrace();
            return "Error al consultar producto: " + ex.getMessage();
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
    

    public static String consultar(VOProducto voProducto) throws SQLException, ClassNotFoundException{
        //objeto de conexion
        Connection cn = null;
        //sentencias sql
        PreparedStatement pr = null;
        //consultas sql
        ResultSet rs = null;                
        try{            
            cn = Conexion.getConexion();
            pr = cn.prepareStatement("SELECT * FROM PRODUCTO WHERE id = ? ");
            pr.setString(1, voProducto.getId()+"%");
            rs = pr.executeQuery();
            
            if (rs.next()){
                voProducto.setId(rs.getInt("id"));
                voProducto.setTipo(rs.getString("tipo"));
                voProducto.setNombre(rs.getString("nombre"));
                voProducto.setDuracion(rs.getInt("duracion"));
                voProducto.setPrecio(rs.getFloat("precio"));
                return "";
            }
            return "Error, producto no encontrado.";
        } catch(SQLException ex){
            ex.printStackTrace();
            return "Error al consultar producto: " + ex.getMessage();
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
    
}
