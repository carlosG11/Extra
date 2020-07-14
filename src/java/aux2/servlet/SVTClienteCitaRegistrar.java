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
            ArrayList pagos = new ArrayList();
            productos = DAOProducto.listar();
            //ejecuta la pre-asignacion de la cita
            VOUsuario voUsuario = new VOUsuario();
            VOCita voCita = new VOCita();
            VOPago voPago = new VOPago();
            voUsuario = (VOUsuario)request.getSession(true).getAttribute("usuario");
            String sResultado;
            voCita.setSucursal(request.getParameter("sucursal"));
            sFechaOriginal = request.getParameter("fecha");
            System.out.println("sFechaOriginal=["+sFechaOriginal+"]");
            voCita.setTiempo(formatter.parse(sFechaOriginal));
            voCita.setDuracion(Integer.parseInt(request.getParameter("duracion")));
            voCita.setPrecio(Float.parseFloat(request.getParameter("precio")));
            voCita.setCliente(voUsuario.getId());
            voCita.setClienteNombre(voUsuario.getNombre());
            voCita.setClientePaterno(voUsuario.getPaterno());
            voCita.setClienteMaterno(voUsuario.getMaterno());            
            voCita.setsProductos(request.getParameterValues("producto"));
            sResultado = FACCita.consultarDisponibilidad(voCita);
            if (!"".equals(sResultado)){
                request.setAttribute("mensaje","Error al asignar Cita: " + sResultado);
                request.setAttribute("productos",productos);
                request.getRequestDispatcher("clienteCitaRegistrar.jsp").forward(request, response);
                return;
            }
            System.out.println("sFechaOriginal=["+sFechaOriginal+"] cita.tiempo["+formatter.format(voCita.getTiempo())+"]");
            if (!sFechaOriginal.equals(formatter.format(voCita.getTiempo()))){
                request.setAttribute("mensaje","Error al asignar Cita. Ya fu&eacute; reservada por alguien m&aacute;s." );
                request.setAttribute("productos",productos);
                request.getRequestDispatcher("clienteCitaRegistrar.jsp").forward(request, response);
                return;
            }
            //si llega a este punto, la cita puede ser registrada
            sResultado = FACCita.registrar(voCita);
            if (!"".equals(sResultado)){
                request.setAttribute("mensaje","Error al registrar Cita: " + sResultado);
                request.setAttribute("productos",productos);
                request.getRequestDispatcher("clienteCitaRegistrar.jsp").forward(request, response);
                return;
            }
            voPago.setCita(voCita.getId());
            voPago.setCliente(voUsuario.getId());
            voPago.setClienteNombre(voUsuario.getNombre());
            voPago.setClientePaterno(voUsuario.getPaterno());
            voPago.setClienteMaterno(voUsuario.getMaterno());
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
                request.getRequestDispatcher("clienteCitaRegistrar.jsp").forward(request, response);
                return;
            }
            //si todo sale bien, dirige a Consultar Cita
            pagos.add(voPago);
            voCita.setPagos(pagos);
            request.setAttribute("cita",voCita);
            request.setAttribute("productos",productos);
            request.setAttribute("mensaje","La Cita fu&eacute; exitosamente registrada.");
            request.getRequestDispatcher("clienteCitaConsultar.jsp").forward(request, response);
        } catch(Exception exception){
            exception.printStackTrace();
            request.setAttribute("mensaje","Error al registrar Cita: " + exception.getMessage());
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
