/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import conexion.GestorReservas;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
public class ReservaHoras extends HttpServlet {

    HashMap<String, List<Integer>> map = new HashMap<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NamingException {
        GestorReservas gr = new GestorReservas();
        String fecha = request.getParameter("fechas");
        int deport = Integer.parseInt(request.getParameter("deporte"));
        int tipo = Integer.parseInt(request.getParameter("techo"));
        request.setAttribute("fecha", fecha);
        request.setAttribute("deporte", deport);
        if (tipo == 1) {
            request.setAttribute("techo", "Descubierto");
        } else {
            request.setAttribute("techo", "Cubierto");
        }

        System.out.println(fecha + " " + deport + " " + tipo);
        List<Reserva> reservasEseDia = gr.obtenerReservas(fecha, deport, tipo);

        String[] horas = comprobarHoras(reservasEseDia, tipo, deport);
        Arrays.sort(horas);
        getServletContext().setAttribute("mapa", map);
        //String[] horas = {"9:00 - 10:00","22:00 - 23:00"};
        request.setAttribute("lista", horas);
        RequestDispatcher rd = request.getRequestDispatcher("/reservar2.xhtml");
        rd.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ReservaHoras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ReservaHoras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(ReservaHoras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ReservaHoras.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String[] comprobarHoras(List<Reserva> reservasEseDia, int tipo, int deporte) {
        String[] horas = {"10:00 - 11:00", "11:00 - 12:00",
            "12:00 - 13:00", "13:00 - 14:00", "14:00 - 15:00", "15:00 - 16:00", "16:00 - 17:00",
            "17:00 - 18:00", "18:00 - 19:00", "19:00 - 20:00", "20:00 - 21:00", "21:00 - 22:00"};

        ArrayList<String> horasLibres = new ArrayList<>();//Arrays.asList(horas);
        for (int x = 0; x < horas.length; x++) {
            horasLibres.add(horas[x]);
        }

        map = pistasOcupadas(reservasEseDia, horasLibres, tipo, deporte);
        String[] a = map.keySet().toArray(new String[0]);

        //System.out.println("El tamano KLKLLSD: "+ map.get("14:00 - 15:00").size());
        return a;

    }

    public HashMap pistasLibresPadelDescubiertas(List<Reserva> reservasEseDia, ArrayList<String> horasLibres) {
        HashMap<String, List<Integer>> p = new HashMap<>();

        for (String horas : horasLibres) {
            List<Integer> list = new ArrayList<>();
            list.add(5);
            list.add(7);
            p.put(horas, list);
        }
        for (Reserva resfa : reservasEseDia) {
            List<Integer> uy = p.get(resfa.hora);
            for (int i = 0; i < uy.size(); i++) {
                if (uy.get(i) == resfa.pista.id) {
                    System.out.println(uy.get(i));

                    //p.forEach((k,v) -> System.out.println("KLK: " + k + ": VALOR: " + v.toString()));
                    uy.remove(i);

                }
            }
            if (uy.isEmpty()) {
                p.remove(resfa.hora);
            } else {
                p.put(resfa.hora, uy);
            }
        }
        return p;
    }

    public HashMap pistasLibresPadelCubiertas(List<Reserva> reservasEseDia, ArrayList<String> horasLibres) {
        HashMap<String, List<Integer>> p = new HashMap<>();

        for (String horas : horasLibres) {
            List<Integer> list = new ArrayList<>();
            list.add(6);
            list.add(8);
            p.put(horas, list);
        }
        for (Reserva resfa : reservasEseDia) {
            List<Integer> uy = p.get(resfa.hora);
            for (int i = 0; i < uy.size(); i++) {
                if (uy.get(i) == resfa.pista.id) {
                    System.out.println(uy.get(i));

                    //p.forEach((k,v) -> System.out.println("KLK: " + k + ": VALOR: " + v.toString()));
                    uy.remove(i);

                }
            }
            if (uy.isEmpty()) {
                p.remove(resfa.hora);
            } else {
                p.put(resfa.hora, uy);
            }
        }
        return p;
    }

    public HashMap pistasLibresTenisDescubiertas(List<Reserva> reservasEseDia, ArrayList<String> horasLibres) {
        HashMap<String, List<Integer>> p = new HashMap<>();

        for (String horas : horasLibres) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            list.add(3);
            p.put(horas, list);
        }
        for (Reserva resfa : reservasEseDia) {
            List<Integer> uy = p.get(resfa.hora);
            for (int i = 0; i < uy.size(); i++) {
                if (uy.get(i) == resfa.pista.id) {
                    System.out.println(uy.get(i));

                    //p.forEach((k,v) -> System.out.println("KLK: " + k + ": VALOR: " + v.toString()));
                    uy.remove(i);

                }
            }
            if (uy.isEmpty()) {
                p.remove(resfa.hora);
            } else {
                p.put(resfa.hora, uy);
            }
        }
        return p;
    }

    public HashMap pistasLibresTenisCubiertas(List<Reserva> reservasEseDia, ArrayList<String> horasLibres) {
        HashMap<String, List<Integer>> p = new HashMap<>();

        for (String horas : horasLibres) {
            List<Integer> list = new ArrayList<>();
            list.add(2);
            list.add(4);
            p.put(horas, list);
        }
        if (!reservasEseDia.isEmpty()) {
            for (Reserva resfa : reservasEseDia) {
                List<Integer> uy = p.get(resfa.hora);
                for (int i = 0; i < uy.size(); i++) {
                    if (uy.get(i) == resfa.pista.id) {
                        System.out.println(uy.get(i));

                        //p.forEach((k,v) -> System.out.println("KLK: " + k + ": VALOR: " + v.toString()));
                        uy.remove(i);

                    }
                }
                if (uy.isEmpty()) {
                    p.remove(resfa.hora);
                } else {
                    p.put(resfa.hora, uy);
                }
            }
        }
        return p;
    }

    private HashMap<String, List<Integer>> pistasOcupadas(List<Reserva> reservasEseDia, ArrayList<String> horasLibres, int tipo, int deporte) {

        if (tipo == 2 && deporte == 2) {
            return pistasLibresPadelCubiertas(reservasEseDia, horasLibres);
        } else if (tipo == 1 && deporte == 2) {
            return pistasLibresPadelDescubiertas(reservasEseDia, horasLibres);
        } else if (tipo == 2 && deporte == 1) {
            return pistasLibresTenisCubiertas(reservasEseDia, horasLibres);
        } else {
            return pistasLibresTenisDescubiertas(reservasEseDia, horasLibres);
        }
    }

}
