package ec.edu.ups.poo.gestioncompras.models;
import ec.edu.ups.poo.gestioncompras.enums.UnidadDeMedida;

public class ProductoTecnologico extends Producto {

    private int garantiaEnMeses;

    public ProductoTecnologico(String nombre, String id, String descripcion, double precioUnitario, UnidadDeMedida unidad, int garantiaEnMeses) {
        super(nombre, id, descripcion, precioUnitario, unidad);
        this.garantiaEnMeses = garantiaEnMeses;

    }

    public int getGarantiaEnMeses() {
        return garantiaEnMeses;
    }

    @Override
    public double calcularPrecio() {
        return  precioUnitario * garantiaEnMeses;
    }

    @Override
    public String toString() {
        return super.toString() + "Garantia en meses: " + garantiaEnMeses  +"Precio total: $" + calcularPrecio();
    }
}