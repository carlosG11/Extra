/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aux2.servlet;

import aux2.dao.DAOUsuario;
import aux2.vo.VOUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author QTK
 */
@WebServlet(name = "SVTLogin", urlPatterns = {"/SVTLogin"})
public class SVTLogin extends HttpServlet {

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
            voUsuario.setPassword(request.getParameter("password"));
            sResultado = DAOUsuario.login(voUsuario );
            if ("admin".equals(voUsuario.getPerfil())){
                //el usuario es un cliente
                request.setAttribute("mensaje","El Usuario se ha identificado correctamente.");
                request.getSession(true).setAttribute("usuario", voUsuario);
                request.getRequestDispatcher("inicioAdmin.jsp").forward(request, response);
                return;
            }
            if ("cliente".equals(voUsuario.getPerfil())){
                //el usuario es un cliente
                request.setAttribute("mensaje","El Usuario se ha identificado correctamente.");
                request.getSession(true).setAttribute("usuario", voUsuario);
                request.getRequestDispatcher("inicioCliente.jsp").forward(request, response);
                return;
            }
            if ("gerente".equals(voUsuario.getPerfil())){
                //el usuario es un cliente
                request.setAttribute("mensaje","El Usuario se ha identificado correctamente.");
                request.getSession(true).setAttribute("usuario", voUsuario);
                request.getRequestDispatcher("inicioGerente.jsp").forward(request, response);
                return;
            }
            request.setAttribute("mensaje","Error al identificar Usuario, perfil no identificado[" + voUsuario.getPerfil()+ "].");
            request.getRequestDispatcher("login.jsp").forward(request, response);            
        } catch(Exception exception){
            exception.printStackTrace();
            request.setAttribute("mensaje","Error al identificar Usuario.");
            request.getRequestDispatcher("login.jsp").forward(request, response);            
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
