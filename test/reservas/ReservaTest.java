/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservas;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrator
 */
public class ReservaTest {

    Reserva reservas;

    @Before
    public void init() {
        String fecha = "2020-07-21";
        //reservas = new Reserva(1,1,new Pista(1,1,2),0,fecha);
    }

    @Test
    public void testDate() {
        String a = "";
        //String a = reservas.fecha.format(DateTimeFormatter.ISO_DATE);
        assertEquals(a, "2020/07/21");
    }

    @Test
    public void testHorario() {
        String horaEsperada = "9:00 - 10:00";
        assertEquals(horaEsperada, reservas.hora);
    }

    @Test
    public void testHorarioMal() {
        String horaEsperada = "13:00 - 14:00";
        reservas.setHorario(4);
        assertEquals(horaEsperada, reservas.hora);
    }

    @Test
    public void testSizeHorario() {
        int sizeEsperado = 13;
        assertEquals(sizeEsperado, reservas.horas.length);
    }
}
