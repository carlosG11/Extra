/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aux2.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aux2.vo.VOUsuario;
import aux2.dao.DAOUsuario;

/**
 *
 * @author QTK
 */
@WebServlet(name = "SVTUsuarioRegistrar", urlPatterns = {"/SVTUsuarioRegistrar"})
public class SVTUsuarioRegistrar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            VOUsuario voUsuario = new VOUsuario();        
            String sResultado;
            voUsuario.setUsuario(request.getParameter("usuario"));
            //los usuarios nuevos creados con este servlet, son clientes
            voUsuario.setPerfil("cliente");
            voUsuario.setNombre(request.getParameter("nombre"));
            voUsuario.setPaterno(request.getParameter("paterno"));
            voUsuario.setMaterno(request.getParameter("materno"));
            voUsuario.setPassword(request.getParameter("password"));
            voUsuario.setCalle(request.getParameter("calle"));
            voUsuario.setExterior(request.getParameter("exterior"));
            voUsuario.setInterior(request.getParameter("interior"));
            voUsuario.setColonia(request.getParameter("colonia"));
            voUsuario.setMunicipio(request.getParameter("municipio"));
            voUsuario.setTelefono(request.getParameter("telefono"));
            voUsuario.setCorreo(request.getParameter("correo")); 
            voUsuario.setSucursal("polanco");
            sResultado = DAOUsuario.registrar(voUsuario );                
            //si el registro sale bien
            if ("".equals(sResultado) ){
                request.setAttribute("mensaje","El Usuario se ha registrado correctamente.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                request.setAttribute("mensaje","Error al registrar Usuario.");
                request.getRequestDispatcher("registrarUsuario.jsp").forward(request, response);
            }
            
        } catch(Exception exception){
            exception.printStackTrace();
            request.setAttribute("mensaje","Error al registrar Usuario.");
            request.getRequestDispatcher("registrarUsuario.jsp").forward(request, response);
        }        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
