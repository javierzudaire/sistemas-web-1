/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservas;

import conexion.DataUsuario;
import java.sql.SQLException;
import javax.naming.NamingException;
import pistas.Pista;
import pistas.Pistas;
import usuarios.Usuario;

/**
 *
 * @author Administrator
 */
public class Reserva {

    public int idReserva;
    public Usuario usuario;
    public Pista pista;
    public String hora;
    public String fecha;
    public String[] horas = {"10:00 - 11:00", "11:00 - 12:00",
        "12:00 - 13:00", "13:00 - 14:00", "14:00 - 15:00", "15:00 - 16:00", "16:00 - 17:00",
        "17:00 - 18:00", "18:00 - 19:00", "19:00 - 20:00", "20:00 - 21:00", "21:00 - 22:00"};

    public Reserva(int idReserva, int idSocio, int hora, int pista, String fecha) throws SQLException, NamingException {
        this.idReserva = idReserva;
        this.usuario = buscarSocio(idSocio);
        this.pista = setPista(pista);
        this.hora = horas[hora];
        this.fecha = fecha;
    }

    public Reserva(int socio, int pista, int hora, String fecha) throws SQLException, NamingException {
        this.usuario = buscarSocio(socio);
        this.pista = setPista(pista);
        this.hora = horas[hora];
        this.fecha = fecha;
    }

    public Reserva(int socio, int pista, String hora, String fecha) throws SQLException, NamingException {
        this.usuario = buscarSocio(socio);
        this.pista = setPista(pista);
        this.hora = hora;
        this.fecha = fecha;
    }

    public Reserva(int id, int idSocio, int idPistaLibre, String hora, String fecha) throws SQLException, NamingException {
        this.idReserva = id;
        this.usuario = buscarSocio(idSocio);
        this.pista = setPista(idPistaLibre);
        this.hora = hora;
        this.fecha = fecha;

    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdSocio() {
        return usuario.getId();
    }

    public Usuario buscarSocio(int idUsuario) throws SQLException, NamingException {
        DataUsuario du = new DataUsuario();
        Usuario user = (Usuario) du.getUser(idUsuario);
        return user;
    }

    public Usuario getSocio() {
        return usuario;
    }

    public int getIntHora() {
        for (int i = 0; i < horas.length; i++) {
            if (hora == horas[i]) {
                return i;
            }
        }
        return 1;
    }

    public Pista setPista(int pista1) {
        Pistas p = new Pistas();
        Pista pista = p.obtenerPista(pista1);
        return pista;
    }

    public Pista getPista() {
        return pista;
    }

    public String getHorario() {
        return hora;
    }

    public void setHorario(int h) {
        this.hora = horas[h];
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String toString() {
        return "Id: " + getIdReserva() + "\n" + "IdSocio: " + getIdSocio() + "\n" + "Hora: " + getIntHora();
    }

}
