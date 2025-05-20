package ec.edu.ups.poo.gestioncompras.models;
import ec.edu.ups.poo.gestioncompras.enums.UnidadDeMedida;

public abstract class Producto {
    protected String id;
    protected String nombre;
    protected String descripcion;
    protected double precioUnitario;
    protected UnidadDeMedida unidad;

    //constructor
    public Producto() {}
    public Producto(String id, String nombre, String descripcion,double precioUnitario, UnidadDeMedida unidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.unidad = unidad;
    }

    //getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public UnidadDeMedida getUnidad() {
        return unidad;
    }

    // metodo abstracto que cada subclase debe implementar
    public abstract double calcularPrecio();

    //tostring para facilitar la impresion
    @Override
    public String toString() {
        return "Producto--> " +
                "ID: '" + id + '\'' +
                ", Nombre:  '" + nombre + '\'' +
                ", Descripcion: " + descripcion + '\'' +
                ", Precio Unitario: " + precioUnitario +
                ", Unidad: " + unidad;
    }
}