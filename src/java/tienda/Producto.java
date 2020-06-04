/*
 *  Javier Zudaire
 */
package tienda;

public class Producto {

    String nombre;
    String precio;
    Double precio2;
    String descripcion;
    String valoracion;
    String imagen;
    String imagen_mini;

    public Producto(String nombre, String precio, Double precio2, String descripcion, String valoracion, String imagen, String imagen_mini) {
        this.nombre = nombre;
        this.precio = precio;
        this.precio2 = precio2;
        this.descripcion = descripcion;
        this.valoracion = valoracion;
        this.imagen = imagen;
        this.imagen_mini = imagen_mini;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public Double getPrecio2() {
        return precio2;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getValoracion() {
        return valoracion;
    }

    public String getImagen() {
        return imagen;
    }

    public String getImagenmini() {
        return imagen_mini;
    }
}
