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
@WebServlet(name = "SVTAdminUsuarioConsultar", urlPatterns = {"/SVTAdminUsuarioConsultar"})
public class SVTAdminUsuarioConsultar extends HttpServlet {

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
            String sResultado;
            VOUsuario voUsuario = new VOUsuario();
            voUsuario.setId(Integer.parseInt(request.getParameter("id")));
            sResultado = DAOUsuario.consultar(voUsuario);
            //el usuario es un cliente
            request.setAttribute("mensaje","&Eacute;xito al consultar Usuario(s).");
            request.setAttribute("resultado",voUsuario);
            request.getRequestDispatcher("adminUsuarioConsultar.jsp").forward(request, response);
        } catch(Exception exception){
            exception.printStackTrace();            
            request.setAttribute("mensaje","Error al consultar Usuario: " + exception.getMessage());
            request.getRequestDispatcher("adminUsuarioBuscar.jsp").forward(request, response);
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
