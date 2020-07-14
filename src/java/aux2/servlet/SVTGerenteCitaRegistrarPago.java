/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aux2.servlet;

import aux2.dao.DAOProducto;
import aux2.dao.DAOUsuario;
import aux2.dao.DAOPago;
import aux2.vo.VOUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aux2.vo.VOCita;
import aux2.vo.VOPago;
import aux2.fachada.FACCita;
import java.text.SimpleDateFormat;
import aux2.dao.DAOProducto;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author QTK
 */
@WebServlet(name = "SVTGerenteCitaRegistrarPago", urlPatterns = {"/SVTGerenteCitaRegistrarPago"})
public class SVTGerenteCitaRegistrarPago extends HttpServlet {

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
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
            String sFechaOriginal;
            //carga los productos para su despliegue
            ArrayList productos = new ArrayList();            
            ArrayList pagos = new ArrayList();
            productos = DAOProducto.listar();
            //ejecuta la pre-asignacion de la cita
            VOUsuario voUsuario = new VOUsuario();
            VOCita voCita = new VOCita();
            VOPago voPago = new VOPago();
            voUsuario = (VOUsuario)request.getSession(true).getAttribute("usuario");            
            String sResultado;
            
            System.out.println("SVTGerenteCitaConsultar.Id["+request.getParameter("id")+"]");
            voCita.setId(Integer.parseInt(request.getParameter("id")));            
            voPago.setCita(Integer.parseInt(request.getParameter("id")));            
            voPago.setCliente(Integer.parseInt(request.getParameter("cliente")));
            voPago.setClienteNombre(request.getParameter("clienteNombre"));
            voPago.setClientePaterno(request.getParameter("clientePaterno"));
            voPago.setClienteMaterno(request.getParameter("clienteMaterno"));
            voPago.setImporte(Float.parseFloat(request.getParameter("importe")));
            voPago.setTiempo(new Date());
            voPago.setTdc(request.getParameter("tdc"));
            voPago.setCaducaAno(request.getParameter("caducaano"));
            voPago.setCaducaMes(request.getParameter("caducames"));
            //registra el pago
            sResultado = DAOPago.registrar(voPago);
            if (!"".equals(sResultado)){
                request.setAttribute("mensaje","Error al registrar Pago: " + sResultado);
                request.setAttribute("productos",productos);
                sResultado = FACCita.consultar(voCita);
                request.getRequestDispatcher("gerenteCitaConsultar.jsp").forward(request, response);
                return;
            }
            //si todo sale bien, dirige a Consultar Cita
            sResultado = FACCita.consultar(voCita);            
            request.setAttribute("cita",voCita);
            request.setAttribute("productos",productos);
            request.setAttribute("mensaje","El Pago fu&eacute; exitosamente registrado.");
            request.getRequestDispatcher("gerenteCitaConsultar.jsp").forward(request, response);
        } catch(Exception exception){
            exception.printStackTrace();
            request.setAttribute("mensaje","Error al registrar Pago: " + exception.getMessage());
            request.getRequestDispatcher("inicioGerente.jsp").forward(request, response);
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
