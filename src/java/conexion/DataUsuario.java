/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import usuarios.Usuario;

/**
 *
 * @author Administrator
 */
public class DataUsuario {

    private DataSource datasource;

    public Connection abrirConexion() throws SQLException, NamingException {
        InitialContext initialContext = new InitialContext();
        datasource = (DataSource) initialContext.lookup("jdbc/tenisceu");
        Connection conn = datasource.getConnection();
        return conn;
    }

    public Object getUser(int identificacion) throws SQLException, NamingException {
        /**
         * Statement stmt = abrirConexion().createStatement(); ResultSet rs=
         * stmt.executeQuery("SELECT * FROM usuarios where
         * id='"+identificacion+"';"); Usuario usuario; //public Usuario(int
         * id,String nombre, String apellido1, String apellido2, String
         * direccion, String dni, String email, String fechaNac, String
         * password, String username, int movil,int tipoUsuario) while
         * (rs.next()) { int id = rs.getInt("id"); String nombre =
         * rs.getString("nombre"); String apellido = rs.getString("apellido");
         * String dni = rs.getString("dni"); String email =
         * rs.getString("email"); String password = rs.getString("password");
         * int movil = rs.getInt("movil"); int tipoUsuario =
         * rs.getInt("tipoUsuario");
         *
         * switch (tipoUsuario) { case 2: usuario = new
         * Monitor(id,nombre,apellido,dni,email,password,movil,tipoUsuario);
         * break; case 0: usuario = new
         * Admin(id,nombre,apellido,dni,email,password,movil,tipoUsuario);
         * break; default: usuario = new
         * Socio(id,nombre,apellido,dni,email,password,movil,tipoUsuario);
         * break;
            }*
         */
        List<Usuario> a = allUsers();
        Usuario usuario = null;
        for (Usuario user : a) {
            if (user.getId() == identificacion) {
                usuario = user;
            }
        }
        return usuario;
    }

    public List<Usuario> allUsers() {
        List<Usuario> resultado = new ArrayList<>();
        resultado.add(new Usuario(1, "Pedro", "Ortiz", "5512312", "p@gmail.com", "123", 665555, 2));
        resultado.add(new Usuario(2, "Messi", "Ortiz", "5512312", "messi@gmail.com", "123", 132, 2));
        resultado.add(new Usuario(3, "Cr", "ASd", "5512312", "cr@gmail.com", "123", 645645, 2));
        resultado.add(new Usuario(4, "benzema", "Ortiz", "5512312", "p@gmail.com", "123", 66345555, 2));
        resultado.add(new Usuario(5, "vxd", "Ortiz", "5512312", "p@gmail.com", "123", 23454, 2));
        return resultado;
    }
}
