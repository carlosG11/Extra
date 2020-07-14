/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aux2.dao;

import java.sql.*;
import java.util.ArrayList;

import aux2.vo.VOUsuario;

/**
 *
 * @author QTK
 */
public class DAOUsuario {

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
            pr = cn.prepareStatement("SELECT MAX(id) FROM USUARIO");            
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
    
   
    public static String registrar(VOUsuario voUsuario) throws SQLException, ClassNotFoundException{
        //objeto de conexion
        Connection cn = null;
        //sentencias sql
        PreparedStatement pr = null;
        voUsuario.setId(0);
        try{
            voUsuario.setId(obtenerId());
            cn = Conexion.getConexion();
            pr = cn.prepareStatement("INSERT INTO Usuario VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pr.setInt(1, voUsuario.getId());
            pr.setString(2, voUsuario.getUsuario());
            pr.setString(3, voUsuario.getPerfil());
            pr.setString(4, voUsuario.getNombre());
            pr.setString(5, voUsuario.getPaterno());
            pr.setString(6, voUsuario.getMaterno());
            pr.setString(7, voUsuario.getPassword());
            pr.setString(8, voUsuario.getCalle());
            pr.setString(9, voUsuario.getExterior());
            pr.setString(10, voUsuario.getInterior());
            pr.setString(11, voUsuario.getColonia());
            pr.setString(12, voUsuario.getMunicipio());
            pr.setString(13, voUsuario.getTelefono());
            pr.setString(14, voUsuario.getCorreo());
            pr.setString(15, voUsuario.getSucursal());
            pr.setString(16, voUsuario.getCarrito());
            pr.executeUpdate();                    
            return "";
        } catch(SQLException ex){
            ex.printStackTrace();
            voUsuario.setId(0);
            return "Error: " + ex.getMessage();
        } finally {
            try{
                pr.close();
                cn.close();
                
            } catch (SQLException ex){
                ex.printStackTrace();
                voUsuario.setId(0);
                return "Error: " + ex.getMessage();
            }
        }                    
    }    
    
    
    //la primera operacion seria la verificacion del tipo de usuario
    public static ArrayList buscar(VOUsuario voUsuario) throws SQLException, ClassNotFoundException{
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
            pr = cn.prepareStatement("SELECT * FROM USUARIO WHERE usuario LIKE ? OR nombre LIKE ? OR  paterno LIKE ? OR materno LIKE ?");
            pr.setString(1, voUsuario.getNombre()+"%");
            pr.setString(2, voUsuario.getNombre()+"%");
            pr.setString(3, voUsuario.getNombre()+"%");
            pr.setString(4, voUsuario.getNombre()+"%");            
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
                resultado.setCarrito(rs.getString("carrito"));
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
    public static String consultar(VOUsuario voUsuario) throws SQLException, ClassNotFoundException{
        //objeto de conexion
        Connection cn = null;
        //sentencias sql
        PreparedStatement pr = null;
        //consultas sql
        ResultSet rs = null;                
        try{            
            cn = Conexion.getConexion();
            pr = cn.prepareStatement("SELECT * FROM USUARIO WHERE id = ? ");
            pr.setString(1, voUsuario.getId()+"%");
            rs = pr.executeQuery();
            
            if (rs.next()){
                voUsuario.setId(rs.getInt("id"));
                voUsuario.setUsuario(rs.getString("usuario"));
                voUsuario.setPerfil(rs.getString("perfil"));
                voUsuario.setNombre(rs.getString("nombre"));
                voUsuario.setPaterno(rs.getString("paterno"));
                voUsuario.setMaterno(rs.getString("materno"));
                voUsuario.setPassword(rs.getString("password"));
                voUsuario.setCalle(rs.getString("calle"));
                voUsuario.setExterior(rs.getString("exterior"));
                voUsuario.setInterior(rs.getString("interior"));
                voUsuario.setColonia(rs.getString("colonia"));
                voUsuario.setMunicipio(rs.getString("municipio"));
                voUsuario.setTelefono(rs.getString("telefono"));
                voUsuario.setCorreo(rs.getString("correo"));
                voUsuario.setSucursal(rs.getString("sucursal"));
                voUsuario.setCarrito(rs.getString("carrito"));
                return "";
            }
            return "Error, usuario no encontrado.";
        } catch(SQLException ex){
            ex.printStackTrace();
            return "Error al consultar usuario: " + ex.getMessage();
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
        
 //la primera operacion seria la verificacion del tipo de usuario
    public static String login(VOUsuario voUsuario) throws SQLException, ClassNotFoundException{
        //objeto de conexion
        Connection cn = null;
        //sentencias sql
        PreparedStatement pr = null;
        //consultas sql
        ResultSet rs = null;                
        voUsuario.setId(0);
        try{            
            cn = Conexion.getConexion();
            pr = cn.prepareStatement("SELECT * FROM USUARIO WHERE usuario = ? AND password = ?");
            pr.setString(1, voUsuario.getUsuario());
            pr.setString(2, voUsuario.getPassword());
            
            rs = pr.executeQuery();
            
            if (rs.next()){
                voUsuario.setId(rs.getInt("id"));
                voUsuario.setUsuario(rs.getString("usuario"));
                voUsuario.setPerfil(rs.getString("perfil"));
                voUsuario.setNombre(rs.getString("nombre"));
                voUsuario.setPaterno(rs.getString("paterno"));
                voUsuario.setMaterno(rs.getString("materno"));
                voUsuario.setPassword(rs.getString("password"));
                voUsuario.setCalle(rs.getString("calle"));
                voUsuario.setExterior(rs.getString("exterior"));
                voUsuario.setInterior(rs.getString("interior"));
                voUsuario.setColonia(rs.getString("colonia"));
                voUsuario.setMunicipio(rs.getString("municipio"));
                voUsuario.setTelefono(rs.getString("telefono"));
                voUsuario.setCorreo(rs.getString("correo"));
                voUsuario.setSucursal(rs.getString("sucursal"));
                voUsuario.setCarrito(rs.getString("carrito"));
                return "";
            }
            return "Error al identificar usuario.";
        
        } catch(SQLException ex){
            ex.printStackTrace();
            return "DAOUsuario.login: Error: " + ex.getMessage();
        } finally {
            try{
                rs.close();
                pr.close();
                cn.close();                
            } catch (SQLException ex){
                ex.printStackTrace();
                return "DAOUsuario.login: Error: " + ex.getMessage();
            }
        }
    }     
    
 
    public static String modificar(VOUsuario voUsuario) throws SQLException, ClassNotFoundException{
        //objeto de conexion
        Connection cn = null;        
        //sentencias sql
        Statement st = null;        
        try{
            int renglonesAfectados=0;
            cn = Conexion.getConexion();
            st = cn.createStatement();
            String sQuery = "UPDATE USUARIO SET " +
                    "perfil='" + voUsuario.getPerfil() + "', "+
                    "nombre='" + voUsuario.getNombre() + "', "+
                    "paterno='" + voUsuario.getPaterno() + "', "+
                    "materno='" + voUsuario.getMaterno() + "', "+
                    "calle='" + voUsuario.getCalle() + "', "+
                    "exterior='" + voUsuario.getExterior() + "', "+
                    "interior='" + voUsuario.getInterior() + "', "+
                    "colonia='" + voUsuario.getColonia() + "', "+
                    "municipio='" + voUsuario.getMunicipio() + "', "+
                    "telefono='" + voUsuario.getTelefono() + "', "+
                    "correo='" + voUsuario.getCorreo() + "', "+
                    "sucursal='" + voUsuario.getSucursal() + "', " +
                    "carrito='" + voUsuario.getCarrito() + "' " +
                "WHERE id=" + voUsuario.getId();
            System.out.println("QUERY[" + sQuery+ "]");
            renglonesAfectados = st.executeUpdate(sQuery);
            System.out.println("DAOUsuario.modificar: renglonesAfectados="+renglonesAfectados);
            return "";
        } catch(SQLException ex){
            ex.printStackTrace();
            return "Error: " + ex.getMessage();
        } finally {
            try{
                st.close();
                cn.close();
                
            } catch (SQLException ex){
                ex.printStackTrace();
                return "Error: " + ex.getMessage();
            }
        }                    
    }        
    
    
    //la primera operacion seria la verificacion del tipo de usuario
    public static ArrayList buscarEstilistasPorSucursal(VOUsuario voUsuario) throws SQLException, ClassNotFoundException{
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
            pr = cn.prepareStatement("SELECT * FROM USUARIO WHERE perfil='estilista' AND sucursal='"+voUsuario.getSucursal()+"'");
//            pr.setString(1, voUsuario.getPerfil()+"%");
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
                resultado.setCarrito(rs.getString("carrito"));
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
