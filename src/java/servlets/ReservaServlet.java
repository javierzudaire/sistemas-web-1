/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
public class ReservaServlet extends HttpServlet {

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

        request.setAttribute("message", getServletContext().getAttribute("message"));

        Cookie[] cookies = request.getCookies();

        Cookie itemscarro = null;

        for (Cookie cookie : cookies) {

            if (cookie.getName().equals("itemscarro")) {
                itemscarro = cookie;
                request.setAttribute("itemscarro", itemscarro.getValue());
            }
        }

        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/reservar.xhtml");
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

    public ArrayList<String> getArrayHoras() {
        ArrayList<String> a = new ArrayList<>();
        a.add("9:00 - 10:00");
        a.add("10:00 - 11:00");
        a.add("11:00 - 12:00");
        a.add("12:00 - 13:00");
        a.add("13:00 - 14:00");
        a.add("14:00 - 15:00");
        a.add("15:00 - 16:00");
        a.add("16:00 - 17:00");
        a.add("17:00 - 18:00");
        a.add("18:00 - 19:00");
        a.add("19:00 - 20:00");
        a.add("20:00 - 21:00");
        a.add("21:00 - 22:00");

        return a;
    }

}
