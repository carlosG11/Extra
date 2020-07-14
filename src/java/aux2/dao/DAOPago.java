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
import aux2.vo.VOPago;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/**
 *
 * @author QTK
 */
public class DAOPago {

 
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
            pr = cn.prepareStatement("SELECT MAX(id) FROM PAGO");            
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
    
   
    public static String registrar(VOPago voPago) throws SQLException, ClassNotFoundException{
        //objeto de conexion
        Connection cn = null;
        //sentencias sql
        PreparedStatement pr = null;
        voPago.setId(0);
        try{
            voPago.setId(obtenerId());
            cn = Conexion.getConexion();
            pr = cn.prepareStatement("INSERT INTO Pago VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
            pr.setInt(1, voPago.getId());
            pr.setInt(2, voPago.getCita());
            pr.setInt(3, voPago.getCliente());
            pr.setString(4, voPago.getClienteNombre());
            pr.setString(5, voPago.getClientePaterno());
            pr.setString(6, voPago.getClienteMaterno());
            pr.setFloat(7, voPago.getImporte());
            pr.setTimestamp(8, new java.sql.Timestamp(voPago.getTiempo().getTime()));
            pr.setString(9, voPago.getTdc());
            pr.setString(10, ""); //propietario
            pr.setString(11, voPago.getCaducaMes());
            pr.setString(12, voPago.getCaducaAno());
            pr.setString(13, ""); //CVV
            pr.executeUpdate();
            return "";
        } catch(SQLException ex){
            ex.printStackTrace();
            voPago.setId(0);
            return "Error: " + ex.getMessage();
        } finally {
            try{
                pr.close();
                cn.close();
                
            } catch (SQLException ex){
                ex.printStackTrace();
                voPago.setId(0);
                return "Error: " + ex.getMessage();
            }
        }                    
    }    
    
    //la primera operacion seria la verificacion del tipo de usuario
    public static String cargarPagos(VOCita voCita) throws SQLException, ClassNotFoundException{
        ArrayList resultados = new ArrayList();
        ArrayList pagos = new ArrayList();
        VOPago resultado;
        //objeto de conexion
        Connection cn = null;
        //sentencias sql
        PreparedStatement pr = null;
        //consultas sql
        ResultSet rs = null;                
        try{            
            cn = Conexion.getConexion();
            pr = cn.prepareStatement("SELECT * FROM PAGO WHERE cita=?");
            pr.setInt(1, voCita.getId());
            rs = pr.executeQuery();
            
            while (rs.next()){
                resultado = new VOPago();
                resultado.setId(rs.getInt("id"));
                resultado.setCita(rs.getInt("cita"));
                resultado.setCliente(rs.getInt("cliente"));
                resultado.setClienteNombre(rs.getString("clienteNombre"));
                resultado.setClientePaterno(rs.getString("clientePaterno"));
                resultado.setClienteMaterno(rs.getString("clienteMaterno"));
                resultado.setImporte(rs.getFloat("importe"));
                resultado.setTiempo(rs.getTimestamp("tiempo"));
                resultado.setTdc(rs.getString("tdc"));
                resultado.setPropietario(rs.getString("propietario"));
                resultado.setCaducaMes(rs.getString("caducaMes"));
                resultado.setCaducaAno(rs.getString("caducaAno"));
                resultado.setCvv(rs.getString("cvv"));
                resultados.add(resultado);
            }
            voCita.setPagos(resultados);
            return "";
        } catch(SQLException ex){
            ex.printStackTrace();
            return "Error al cargar pagos: " + ex.getMessage();
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