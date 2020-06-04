/*
 *  Javier Zudaire
 */
package servlets;

import conexion.ConexionBBDD;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author javierzudaire
 */
@WebFilter("/ControlAccesoServlet")
public class ControlAccesoServlet implements Filter {

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
        response.setContentType("text/html;charset=UTF-8");
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
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        ArrayList<String> carro = new ArrayList<String>();
        session.setAttribute("carro", carro);

        String usuario = request.getParameter("email");
        String pass = request.getParameter("pass");

        int x = 0;

        try {
            if (usuario.equals("algo")) {
            }
        } catch (NullPointerException e) {
            System.out.println("-> Visitante sin autenticación");
            res.sendRedirect("InicioServlet");
            x = 1;
        }

        if (x == 0) {

            try {
                ConexionBBDD conexion = new ConexionBBDD();
                Connection conn = conexion.abrirConexion();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios;");
                conn.close();
                int i = 0;

                while (rs.next()) {

                    i = 1;

                    if (usuario.equals(rs.getString("email")) && pass.equals(rs.getString("password"))) {

                        if (rs.getString("tipoUsuario").equals("2")) {
                            res.sendRedirect("InicioServlet");
                            break;
                        } else if (rs.getString("tipoUsuario").equals("0")) {
                            res.sendRedirect("administrador.xhtml");
                            break;
                        } else if (rs.getString("tipoUsuario").equals("1")) {
                            res.sendRedirect("monitor.xhtml");
                            break;
                        }

                    }

                    i = 0;
                }

                if (i == 0) {

                    res.sendRedirect("login-error.html");
                    conn.close();

                }

            } catch (SQLException | NamingException ex) {
                Logger.getLogger(ControlAccesoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
