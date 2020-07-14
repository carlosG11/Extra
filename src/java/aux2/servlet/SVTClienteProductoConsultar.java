/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aux2.servlet;

import aux2.app.Constantes;
import aux2.dao.DAOUsuario;
import aux2.vo.VOUsuario;
import aux2.vo.VOProducto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aux2.app.Constantes;
import aux2.dao.DAOProducto;

/**
 *
 * @author QTK
 */
@WebServlet(name = "SVTClienteProductoConsultar", urlPatterns = {"/SVTClienteProductoConsultar"})
public class SVTClienteProductoConsultar extends HttpServlet {

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
            VOProducto voProducto = new VOProducto();
            voProducto.setId(Integer.parseInt(request.getParameter("id")));
            sResultado = DAOProducto.consultar(voProducto);
            //el usuario es un cliente
            request.setAttribute("mensaje","&Eacute;xito al consultar Producto(s).");
            request.setAttribute("producto",voProducto);
            request.getRequestDispatcher("clienteProductoConsultar.jsp").forward(request, response);
        } catch(Exception exception){
            exception.printStackTrace();            
            request.setAttribute("mensaje","Error al consultar Producto: " + exception.getMessage());
            request.getRequestDispatcher("clienteProductoBuscar.jsp").forward(request, response);
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
