package ec.edu.ups.poo.gestioncompras.models;

import ec.edu.ups.poo.gestioncompras.controller.Calculable;

public class DetalleSolicitud implements Calculable {

    private Producto producto;
    private int cantidad;
    private String justificacion;

    //cosntructor con atributos
    public DetalleSolicitud(Producto producto, int cantidad, String justificacion) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.justificacion = justificacion;
    }

    public Producto getProducto() {

        return producto;
    }
    public void setProducto(Producto producto) {

        this.producto = producto;
    }
    public int getCantidad() {

        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public String getJustificacion() {
        return justificacion;
    }
    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    @Override
    public double calcularTotal() {
        return producto.calcularPrecio() * cantidad;
    }

    @Override
    public String toString() {
        return "\nDetalleSolicitud--> " +
                "\nProducto: " + producto +
                ", Cantidad: " + cantidad +
                ", Justificacion: " + justificacion +" | ";
    }

}
