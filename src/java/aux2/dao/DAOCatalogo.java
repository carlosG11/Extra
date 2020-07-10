/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aux2.dao;

import aux2.app.Constantes;
import java.sql.*;
import java.util.ArrayList;

import aux2.vo.VOUsuario;

/**
 *
 * @author QTK
 */
public class DAOCatalogo {
    
    //retorna un arreglo en javascript con el catalogo de estilistas, clasificado por sucursal
    public static String catalogoEstilistas() {
        VOUsuario voUsuario = new VOUsuario();
        ArrayList resultados;
        String sResultado="";
        String sSeparadorI;
        String sSeparadorJ;
        String sComentario = "//sucursales: ";
        sResultado = "";
        try {
            //para cada sucursal
            sSeparadorI="";
            for (int i=0; i<Constantes.SUCURSALES.length;i++){
                voUsuario.setSucursal(Constantes.SUCURSALES[i]);
                sComentario = sComentario + sSeparadorI + Constantes.SUCURSALES[i];
                resultados = buscarEstilistas(voUsuario);
                //enlista los estilistas de la sucursal
                sSeparadorJ="";
                for (int j=0; j<resultados.size();j++){
                    sResultado = sResultado + sSeparadorJ + "'" + voUsuario.getId()+"-"+voUsuario.getUsuario()+":"+voUsuario.getNombre()+" "+voUsuario.getPaterno()+" "+voUsuario.getMaterno()+"'";
                    sSeparadorJ=",";
                }
                sResultado = sResultado + "\t" + sSeparadorI + "[" + sResultado + "]\n";
                sSeparadorI=",";
            }
            return sComentario + "\n" +
                "var estilistas =[\n" + 
                    sResultado + "];";
        
        } catch (Exception ex){
            ex.printStackTrace();            
            return "var estilistas = [];";
        }                
    }    
    
    //retorna un arreglo con los estilistas que pertenecen a la sucursal provista en el VOUsuario
    public static ArrayList buscarEstilistas(VOUsuario voUsuario) throws SQLException, ClassNotFoundException{
        ArrayList resultados = new ArrayList();
        VOUsuario resultado;
        //objeto de conexion
        Connection cn = null;
        //sentencias sql
        PreparedStatement pr = null;
        //consultas sql
        ResultSet rs = null;                
        try{            
            cn = Conexion.getConexion();
            pr = cn.prepareStatement("SELECT * FROM USUARIO WHERE sucursal=? AND perfil='estilista' ");
            pr.setString(1, voUsuario.getSucursal()+"%");
            rs = pr.executeQuery();            
            while (rs.next()){
                resultado = new VOUsuario();
                resultado.setId(rs.getInt("id"));
                resultado.setUsuario(rs.getString("usuario"));
                resultado.setPerfil(rs.getString("perfil"));
                resultado.setNombre(rs.getString("nombre"));
                resultado.setPaterno(rs.getString("paterno"));
                resultado.setMaterno(rs.getString("materno"));
                resultado.setPassword(rs.getString("password"));
                resultado.setCalle(rs.getString("calle"));
                resultado.setExterior(rs.getString("exterior"));
                resultado.setInterior(rs.getString("interior"));
                resultado.setColonia(rs.getString("colonia"));
                resultado.setMunicipio(rs.getString("municipio"));
                resultado.setTelefono(rs.getString("telefono"));
                resultado.setCorreo(rs.getString("correo"));
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
