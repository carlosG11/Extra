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
@WebServlet(name = "SVTClientePerfilModificar", urlPatterns = {"/SVTClientePerfilModificar"})
public class SVTClientePerfilModificar extends HttpServlet {

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
            System.out.println("SVTClientePerfilModificar: id["+request.getParameter("id")+"]");
            voUsuario.setId(Integer.parseInt(request.getParameter("id")));
            voUsuario.setUsuario(request.getParameter("usuario"));
            voUsuario.setPassword(request.getParameter("password"));
            voUsuario.setPerfil(request.getParameter("perfil"));
            voUsuario.setNombre(request.getParameter("nombre"));
            voUsuario.setPaterno(request.getParameter("paterno"));
            voUsuario.setMaterno(request.getParameter("materno"));
            voUsuario.setCalle(request.getParameter("calle"));
            voUsuario.setExterior(request.getParameter("exterior"));
            voUsuario.setInterior(request.getParameter("interior"));
            voUsuario.setColonia(request.getParameter("colonia"));
            voUsuario.setMunicipio(request.getParameter("municipio"));
            voUsuario.setTelefono(request.getParameter("telefono"));
            voUsuario.setCorreo(request.getParameter("correo")); 
            voUsuario.setSucursal(request.getParameter("sucursal")); 
            sResultado = DAOUsuario.modificar(voUsuario );
            request.setAttribute("resultado",voUsuario);
            request.getSession(true).setAttribute("usuario",voUsuario);
            //si el registro sale bien
            if ("".equals(sResultado) ){
                request.setAttribute("mensaje","El Perfil se ha modificado exitosamente.");
                request.getRequestDispatcher("clientePerfilConsultar.jsp").forward(request, response);
            } else {
                request.setAttribute("mensaje","Error al modificar Perfil.");
                request.getRequestDispatcher("clientePerfilConsultar.jsp").forward(request, response);
            }
            
        } catch(Exception exception){
            exception.printStackTrace();
            request.setAttribute("mensaje","Error al modificar Perfil.");
            request.getRequestDispatcher("clientePerfilConsultar.jsp").forward(request, response);
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
