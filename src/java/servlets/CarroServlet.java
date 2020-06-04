/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import conexion.ConexionBBDD;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
public class CarroServlet extends HttpServlet {

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
            throws ServletException, IOException, SQLException, ClassNotFoundException {

        Cookie[] cookies = request.getCookies();

        Cookie itemscarro = null;

        for (Cookie cookie : cookies) {

            if (cookie.getName().equals("itemscarro")) {
                itemscarro = cookie;
                request.setAttribute("itemscarro", itemscarro.getValue());
            }
        }

        HttpSession session = request.getSession();

        ConexionBBDD conexion = new ConexionBBDD();
        try {
            Double preciototal = conexion.getPrecioTotal((ArrayList<String>) session.getAttribute("carro"));
            request.setAttribute("preciototal", preciototal);
            request.setAttribute("preciototalenvio", preciototal + 10);
        } catch (NamingException ex) {
            Logger.getLogger(CarroServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/carro.xhtml");
        rd.forward(request, response);

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

        request.setCharacterEncoding("UTF-8");

        request.setAttribute("message", getServletContext().getAttribute("message"));

        String producto = request.getParameter("nombre");

        int x = 0;

        if (producto == null) {
            x = 1;
            try {
                processRequest(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(CarroServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CarroServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (x == 0) {

            HttpSession session = request.getSession();
            ArrayList<String> carro = (ArrayList<String>) session.getAttribute("carro");

            carro.add(producto);

            session.setAttribute("carro", carro);

            Cookie[] cookies = request.getCookies();

            Cookie itemscarro = null;

            for (Cookie cookie : cookies) {

                if (cookie.getName().equals("itemscarro")) {
                    itemscarro = cookie;
                    String size = Integer.toString(carro.size());
                    itemscarro.setValue(size);
                    response.addCookie(itemscarro);
                }
            }

            String size2 = Integer.toString(carro.size());

            request.setAttribute("itemscarro", size2);

            System.out.println("-------- CARRO --------");

            for (int i = 0; i < carro.size(); i++) {
                System.out.print("+ PRODUCTO AÑADIDO AL CARRO: " + carro.get(i));
            }

            System.out.println("-----------------------");

            RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/tienda.xhtml");
            rd.forward(request, response);
        }

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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CarroServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CarroServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
