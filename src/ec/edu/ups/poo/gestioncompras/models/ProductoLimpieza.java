package ec.edu.ups.poo.gestioncompras.models;
import ec.edu.ups.poo.gestioncompras.enums.UnidadDeMedida;

public class ProductoLimpieza extends Producto {
    private double volumen; // en litros

    public ProductoLimpieza(String nombre, String id, String descripcion, double precioUnitario, UnidadDeMedida unidad, double volumen) {
        super(nombre, id, descripcion, precioUnitario, unidad);
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