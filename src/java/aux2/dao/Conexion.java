/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aux2.dao;

import java.sql.*;

/**
 *
 * @author demon
 */
public class Conexion {
    
    public static Connection getConexion() throws SQLException, ClassNotFoundException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/AUX2";
            String usuario = "root";
            String password = "n0m3l0";
            
            return DriverManager.getConnection(url, usuario, password);
            
            //System.out.println("Si se conecto");
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }        
}
