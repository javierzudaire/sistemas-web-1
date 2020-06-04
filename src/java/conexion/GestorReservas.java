/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import pistas.Pistas;
import reservas.Reserva;

/**
 *
 * @author Administrator
 */
public class GestorReservas {

    List<Reserva> allReservas;

    Pistas p = new Pistas();

    public GestorReservas() throws SQLException, NamingException {
        this.allReservas = init();
    }

    //IMPORTANTE: Esto es un mockup, necesitamos la conexion a la base de datos.
    public List<Reserva> obtenerReservas(String fcha, int deporte, int tipo) {
        List<Reserva> reservasDelDia = new ArrayList<>();
        System.out.println("Probando listas: " + fcha + " " + deporte + " " + tipo);

        for (Reserva res : allReservas) {
            if (res.getFecha().equals(fcha) && res.getPista().getDeporteId() == deporte) {
                if (tipo == 2) {
                    if (res.pista.getIdTipo() == 2) {
                        reservasDelDia.add(res);
                    }

                } else {
                    if (res.pista.getIdTipo() == tipo) {
                        reservasDelDia.add(res);
                    }
                }
            }

        }
        System.out.println("Probando listas: " + reservasDelDia.size());
        return reservasDelDia;
    }

    public void guardarReserva(Reserva reserva) {
        allReservas.add(reserva);
    }

    public List<Integer> pistasLibres(int hora, int deporte, int tipo, String fecha) {
        System.out.println("Tipo: " + tipo);
        List<Integer> idPistasOcupadas = new ArrayList<>();
        List<Reserva> res = obtenerReservas(fecha, deporte, tipo);
        System.out.println("Prueba: " + res.size());
        for (Reserva reservas : res) {
            if (reservas.getIntHora() == hora) {
                idPistasOcupadas.add(reservas.pista.getId());
                System.out.println(reservas.pista.getId());
            }
        }
        System.out.println("Prueba1");
        return idPistasOcupadas;
    }

    private List<Reserva> init() throws SQLException, NamingException {
        ConexionBBDD c = new ConexionBBDD();
        List<Reserva> reservas = c.getAllReservas();
        return reservas;
    }

}
