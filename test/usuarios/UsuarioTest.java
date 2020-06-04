/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrator
 */
public class UsuarioTest {
    
    @Test
    public void user(){
        Usuario user = new Usuario();
        user.setNombre("Pedro");
        assertEquals("Pedro",user.getNombre());
    }
    
    @Test
    public void testConstructor(){
        Usuario usr = new Usuario();
    }
    
    @Test
    public void testReservar(){
        
    }
    
    
}
