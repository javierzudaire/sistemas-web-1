/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pistas;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrator
 */
public class PistaTest {
    Pista pista = new Pista(1,1,2);
    
    @Before
    public void init(){
        
    }
    
    @Test
    public void testCubierta(){
        assertEquals(pista.tipo,"Cubierta");
    }
    @Test
    public void testDescubierta(){
        pista.setTipo(1);
        boolean a = pista.tipo =="Descubierta";
        assertTrue(a);
    }
    
    @Test
    public void testPaddel(){
        String paddel = "Padel";
        pista.setDeporte(2);
        boolean a = pista.deporte == paddel;
        assertTrue(a);
    }
    @Test
    public void testTennis(){
        String tennis = "Tenis";
        pista.setDeporte(1);
        boolean a = pista.deporte == tennis;
        assertTrue(a);
    }
    
}
