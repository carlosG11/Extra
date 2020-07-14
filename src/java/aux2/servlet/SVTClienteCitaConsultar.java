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
import aux2.vo.VOCita;
import aux2.dao.DAOUsuario;
import aux2.dao.DAOCita;
import aux2.dao.DAOProducto;
import aux2.fachada.FACCita;
import java.util.ArrayList;

/**
 *
 * @author QTK
 */
@WebServlet(name = "SVTClienteCitaConsultar", urlPatterns = {"/SVTClienteCitaConsultar"})
public class SVTClienteCitaConsultar extends HttpServlet {

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
            ArrayList productos = new ArrayList();            
            productos = DAOProducto.listar();            
            request.setAttribute("productos",productos);                
            VOCita voCita = new VOCita();
            voCita.setId(Integer.parseInt(request.getParameter("id")));
            sResultado = FACCita.consultar(voCita);
            if ("".equals(sResultado)){
                //el usuario es un cliente
                request.setAttribute("mensaje","&Eacute;xito al consultar Cita(s).");
                request.setAttribute("cita",voCita);
                request.getRequestDispatcher("clienteCitaConsultar.jsp").forward(request, response);
            } else {
                request.setAttribute("mensaje",sResultado);
                request.getRequestDispatcher("clienteCitaBuscar.jsp").forward(request, response);
            }                        
        } catch(Exception exception){
            exception.printStackTrace();            
            request.setAttribute("mensaje","Error al consultar Cita: " + exception.getMessage());
            request.getRequestDispatcher("clienteCitaBuscar.jsp").forward(request, response);
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
