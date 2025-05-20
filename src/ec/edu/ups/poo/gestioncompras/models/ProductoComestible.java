package ec.edu.ups.poo.gestioncompras.models;
import ec.edu.ups.poo.gestioncompras.enums.UnidadDeMedida;

import java.time.LocalDate;

public class ProductoComestible extends Producto {

    private double peso;
    private LocalDate fechaCaducidad;
    private LocalDate fechaElaboracion;

    public ProductoComestible(String id, String nombre, String descripcion, double precioUnitario, UnidadDeMedida unidad, double peso, LocalDate fechaCaducidad, LocalDate fechaElaboracion) {
        super(id, nombre, descripcion, precioUnitario, unidad);
        this.peso = peso;
        this.fechaCaducidad = fechaCaducidad;
        this.fechaElaboracion = fechaElaboracion;
    }


    //solo setters poque no se necesita modificar el codigo ya creado
    public double getPeso() {
        return peso;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public LocalDate getFechaElaboracion() {
        return fechaElaboracion;
    }

    @Override
    public double calcularPrecio() {
        return peso * precioUnitario;
    }

    public String toString(){
        return super.toString()+ " Peso: " + peso + " " + unidad +
                " Caduca: " + fechaCaducidad +
                " Expira: " + fechaCaducidad +
                " Precio total: $" + calcularPrecio();
    }


}