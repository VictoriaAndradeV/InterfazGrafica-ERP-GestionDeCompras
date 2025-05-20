package ec.edu.ups.poo.gestioncompras.models;
import ec.edu.ups.poo.gestioncompras.enums.UnidadDeMedida;

public class ProductoLimpieza extends Producto {
    private double volumen; // en litros

    public ProductoLimpieza(String id, String nombre, String descripcion, double precioUnitario, UnidadDeMedida unidad, double volumen) {
        super(id, nombre, descripcion, precioUnitario, unidad);
        this.volumen = volumen;
    }

    public double getVolumen() {
        return volumen;
    }

    @Override
    public double calcularPrecio() {
        return precioUnitario * volumen;
    }

    @Override
    public String toString() {
        return super.toString() + " Volumen: " + volumen + " " + unidad + " Precio total: $" + calcularPrecio();
    }


}