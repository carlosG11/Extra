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
@WebServlet(name = "SVTClienteCarritoVaciar", urlPatterns = {"/SVTClienteCarritoVaciar"})
public class SVTClienteCarritoVaciar extends HttpServlet {

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
            ArrayList productos = new ArrayList();            
            String sResultado;
            String sSeparador="";
            VOUsuario voUsuario;
            voUsuario=(VOUsuario)request.getSession(true).getAttribute("usuario");
            voUsuario.setCarrito("");
            //guarda el carrito en la base de datos
            sResultado = DAOUsuario.modificar(voUsuario);
            productos = DAOProducto.listar();
            request.setAttribute("productos",productos);
            if ("".equals(sResultado)){                                
                request.setAttribute("mensaje","El carrito se ha vaciado exitosamente.");
            } else{
                request.setAttribute("mensaje","Error al vaciar el carrito: " + sResultado);
            }
            request.getRequestDispatcher("clienteProductoBuscar.jsp").forward(request, response);
        } catch(Exception exception){
            exception.printStackTrace();            
            request.setAttribute("mensaje","Error al vaciar Carrito: " + exception.getMessage());
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
