/*
 *  Javier Zudaire
 */
package conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import reservas.Reserva;
import tienda.Producto;

/**
 *
 * @author javierzudaire
 */
public class ConexionBBDD extends HttpServlet {

    private DataSource datasource;

    public Connection abrirConexion() throws SQLException, NamingException {
        InitialContext initialContext = new InitialContext();
        datasource = (DataSource) initialContext.lookup("jdbc/tenisceu");
        Connection conn = datasource.getConnection();
        return conn;
    }

    public List getTienda() throws SQLException, ClassNotFoundException, NamingException {

        Connection conn = abrirConexion();

        List tienda = new ArrayList();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM tienda;");
        int i = 0;

        while (rs.next()) {

            String nombre = rs.getString("nombre");
            String precio = rs.getString("precio");
            double precio2 = Double.valueOf(precio);
            precio2 = precio2 + 10;
            String descripcion = rs.getString("descripcion");
            String valoracion = rs.getString("valoracion");
            String imagen = rs.getString("imagen");
            String imagen_mini = rs.getString("imagen_mini");

            tienda.add(i, new Producto(nombre, precio, precio2, descripcion, valoracion, imagen, imagen_mini));
            i++;

        }

        conn.close();
        return tienda;
    }

    public List micarro;

    public List getCarro() throws SQLException, ClassNotFoundException, NamingException {

        Connection conn = abrirConexion();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        ArrayList<String> carro = (ArrayList<String>) session.getAttribute("carro");

        List tienda = new ArrayList();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM tienda;");
        int i = 0;
        double precioTotal = 0;

        while (rs.next()) {

            String nombre = rs.getString("nombre");
            String precio = rs.getString("precio");
            double precio2 = Double.valueOf(precio);
            precio2 = precio2 + 10;
            String descripcion = rs.getString("descripcion");
            String valoracion = rs.getString("valoracion");
            String imagen = rs.getString("imagen");
            String imagen_mini = rs.getString("imagen_mini");

            if (carro.contains(nombre)) {
                precioTotal += Double.parseDouble(precio);
                tienda.add(i, new Producto(nombre, precio, precio2, descripcion, valoracion, imagen, imagen_mini));
                i++;
            }

        }

        //micarro = tienda;
        conn.close();
        System.out.print(precioTotal);
        return tienda;
    }

    public Double getPrecioTotal(ArrayList<String> carro) throws SQLException, ClassNotFoundException, NamingException {

        Connection conn = abrirConexion();

        List tienda = new ArrayList();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM tienda;");
        int i = 0;
        double precioTotal = 0;

        while (rs.next()) {

            String nombre = rs.getString("nombre");
            String precio = rs.getString("precio");
            double precio2 = Double.valueOf(precio);
            precio2 = precio2 + 10;
            String descripcion = rs.getString("descripcion");
            String valoracion = rs.getString("valoracion");
            String imagen = rs.getString("imagen");
            String imagen_mini = rs.getString("imagen_mini");

            if (carro.contains(nombre)) {
                precioTotal += Double.parseDouble(precio);
                tienda.add(i, new Producto(nombre, precio, precio2, descripcion, valoracion, imagen, imagen_mini));
                i++;
            }

        }

        conn.close();
        System.out.print("PEDIDO TOTAL: " + precioTotal);
        return precioTotal;
    }

    public void addUser(String nombre, String apellido, String DNI, String email, String password, String tipo) throws SQLException, NamingException {

        Connection conn = abrirConexion();

        Statement stmt = conn.createStatement();
        stmt.executeUpdate("INSERT INTO usuarios (nombre, apellido, dni, email, password, tipoUsuario) VALUES ('" + nombre + "', '" + apellido + "', '" + DNI + "', '" + email + "', '" + password + "', " + tipo + ");");
        conn.close();

    }

    public void addProduct(String nombre, String precio, String descripcion, String valoracion, String imagen, String imagen_mini) throws SQLException, NamingException {

        Connection conn = abrirConexion();

        Statement stmt = conn.createStatement();
        stmt.executeUpdate("INSERT INTO tienda (nombre, precio, descripcion, valoracion, imagen, imagen_mini) VALUES ('" + nombre + "', '" + precio + "', '" + descripcion + "', '" + valoracion + "', '" + imagen + "', '" + imagen_mini + "');");
        conn.close();

    }

    public List<Reserva> getAllReservas() throws SQLException, NamingException {

        Connection conn = abrirConexion();

        List<Reserva> reservas = new ArrayList<>();
//int idReserva, int idSocio, int pista, int hora, String fecha
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM reservas;");

        while (rs.next()) {

            int id = rs.getInt("reserva_id");
            int hora = rs.getInt("hora");
            int usuarioId = rs.getInt("usuario_id");
            int pistaId = rs.getInt("pista_id");
            String fecha = rs.getString("fecha");

            reservas.add(new Reserva(id, hora, usuarioId, pistaId, fecha));

        }

        //micarro = tienda;
        conn.close();
        for (int i = 0; i < reservas.size(); i++) {
            System.out.print("QWERRTTY: " + reservas.get(i).toString());
        }

        return reservas;
    }

    public void addReserva(Reserva reserva) throws SQLException, NamingException {

        Connection conn = abrirConexion();

        Statement stmt = abrirConexion().createStatement();
        int a = 1;
        stmt.executeUpdate("INSERT INTO reservas (hora, usuario_id, pista_id, fecha) VALUES ('" + reserva.getIntHora() + "', '" + /*reserva.usuario.getId()*/ a + "', '" + reserva.pista.id + "', '" + reserva.fecha + "');");
        conn.close();

    }

}
