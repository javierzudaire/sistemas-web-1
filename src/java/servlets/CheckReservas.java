/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import conexion.ConexionBBDD;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import reservas.Reserva;

/**
 *
 * @author Administrator
 */
public class CheckReservas extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String fecha = request.getParameter("fechas");
        String deporte = request.getParameter("deporte");
        String tipo = request.getParameter("techo");
        String hora = request.getParameter("horas");
        request.setAttribute("h", hora);
        request.setAttribute("fecha", fecha);
        HashMap<String, List<Integer>> mapa = (HashMap< String, List<Integer>>) getServletContext().getAttribute("mapa");
        int idPistaLibre = obtenerPistaLibre(mapa, hora);
        request.setAttribute("pista", idPistaLibre);
        System.out.println("Prueba 1");
        try {
            Reserva reserva = new Reserva(12, 3, idPistaLibre, hora, fecha);
            ConexionBBDD gr = new ConexionBBDD();
            gr.addReserva(reserva);
            System.out.println("Prueba 2");
            RequestDispatcher rd = request.getRequestDispatcher("/reservaRealizada.xhtml");
            rd.forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(CheckReservas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(CheckReservas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    private int obtenerPistaLibre(HashMap<String, List<Integer>> mapa, String hora) {
        System.out.println("La hora es: " + hora);
        Object[] a = mapa.keySet().toArray();
        for (Object object : a) {
            System.out.println("El keyvalue es: " + object.toString());
        }

        Iterator<Entry<String, List<Integer>>> it = mapa.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, List<Integer>> e = it.next();
        }
        List<Integer> asd = mapa.get(hora);
        System.out.println("El tamn de la lista es: " + asd.size());
        return asd.get(0);
    }

}
