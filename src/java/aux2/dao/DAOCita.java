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

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/**
 *
 * @author QTK
 */
public class DAOCita {

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
            
            pr = cn.prepareStatement(
                    "SELECT * FROM CITA WHERE estilista='"+voUsuario.getId()+"' AND tiempo >= '"+sFecha+" 09:00:00' AND tiempo<='"+sFecha+" 16:00:00'");
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
                resultado.setTiempo(rs.getTimestamp(rs.getString("tiempo")));
                resultado.setDuracion(rs.getInt("duracion"));
                resultado.setPrecio(rs.getFloat("precio"));
                resultado.setSaldo(rs.getFloat("saldo"));
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