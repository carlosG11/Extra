/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aux2.servlet;

import aux2.dao.DAOProducto;
import aux2.dao.DAOUsuario;
import aux2.vo.VOUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aux2.vo.VOCita;
import aux2.fachada.FACCita;
import java.text.SimpleDateFormat;
import aux2.dao.DAOProducto;

/**
 *
 * @author QTK
 */
@WebServlet(name = "SVTClienteCitaRegistrar", urlPatterns = {"/SVTClienteCitaRegistrar"})
public class SVTClienteCitaRegistrar extends HttpServlet {

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
            productos = DAOProducto.listar();
            //ejecuta la pre-asignacion de la cita
            VOUsuario voUsuario = new VOUsuario();
            VOCita voCita = new VOCita();
            String sResultado;
            voCita.setSucursal(request.getParameter("sucursal"));
            sFechaOriginal = request.getParameter("fecha");
            voCita.setTiempo(formatter.parse(sFechaOriginal));
            voCita.setDuracion(Integer.parseInt(request.getParameter("duracion")));
            voCita.setPrecio(Float.parseFloat(request.getParameter("precio")));
            voCita.setsProductos(request.getParameterValues("producto"));
            sResultado = FACCita.consultarDisponibilidad(voCita);
            if (!"".equals(sResultado)){
                request.setAttribute("mensaje","Error al asignar Cita: " + sResultado);
                request.setAttribute("productos",productos);
                request.getRequestDispatcher("clienteCitaRegistrar.jsp").forward(request, response);
                return;
            }
            if (!sFechaOriginal.equals(formatter.format(voCita.getTiempo()))){
                request.setAttribute("mensaje","Error al asignar Cita. Ya fu&eacute; reservada por alguien m&aacute;s." );
                request.setAttribute("productos",productos);
                request.getRequestDispatcher("clienteCitaRegistrar.jsp").forward(request, response);
                return;
            }
            //si llega a este punto, la cita puede ser registrada
            sResultado = FACCita.registrarCita(voCita);
            if (!"".equals(sResultado)){
                request.setAttribute("mensaje","Error al registrar Cita: " + sResultado);
                request.setAttribute("productos",productos);
                request.getRequestDispatcher("clienteCitaRegistrar.jsp").forward(request, response);
                return;
            }
            
            
            request.setAttribute("cita",voCita);
            request.setAttribute("mensaje","La Cita fu&eacute; exitosamente registrada.");
            request.getRequestDispatcher("clienteCitaPagar.jsp").forward(request, response);
            
            

            
            
            
            
            System.out.println("precio["+request.getParameter("precio")+"]");
            System.out.println("productos.size["+voCita.getsProductos().length+"]");
            if ("".equals(sResultado)){
            } else {
                request.setAttribute("mensaje",sResultado);
                request.getRequestDispatcher("clienteCitaRegistrar.jsp").forward(request, response);
            }
        } catch(Exception exception){
            exception.printStackTrace();
            request.setAttribute("mensaje","Error al buscar Disponibilidad: " + exception.getMessage());
            request.getRequestDispatcher("inicioCliente.jsp").forward(request, response);
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
