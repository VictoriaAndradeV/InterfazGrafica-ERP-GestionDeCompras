package ec.edu.ups.poo.gestioncompras.models;

import ec.edu.ups.poo.gestioncompras.controller.Calculable;
import ec.edu.ups.poo.gestioncompras.enums.Estado;
import ec.edu.ups.poo.gestioncompras.enums.Rol;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class SolicitudDeCompra implements Calculable{
    private Usuario usuario;
    private Estado estado;
    private String id;
    private String numeroSolicitud;
    private List<DetalleSolicitud> detalleSolicitud;
    private GregorianCalendar fechaSolicitud;
    private Producto producto;

    public SolicitudDeCompra() {
        this.detalleSolicitud = new ArrayList<>();
    }

    public SolicitudDeCompra(Usuario usuario, GregorianCalendar fechaSolicitud, Estado estado, String numeroSolicitud) {
        this.usuario = usuario;
        this.fechaSolicitud = fechaSolicitud;
        this.estado = estado;
        this.numeroSolicitud = numeroSolicitud;
        this.detalleSolicitud = new ArrayList<>();
    }

    public void setNumeroSolicitud(String numero) {
        this.numeroSolicitud = numero;
    }

    public String getNumeroSolicitud() {
        return numeroSolicitud;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Usuario getUsuario() {
        return usuario;
    }

    public GregorianCalendar getFechaSolicitud() {
        return fechaSolicitud;
    }
    public void setFechaSolicitud(GregorianCalendar fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Estado getEstado() {
        return estado;
    }

    public List<DetalleSolicitud> getDetalleSolicitud() {
        return detalleSolicitud;
    }

    public void agregarDetalle(Producto producto, int cantidad, String justificacion) {
        DetalleSolicitud detalle = new DetalleSolicitud(producto, cantidad, justificacion);
        this.detalleSolicitud.add(detalle);
    }


    @Override
    public double calcularTotal() {
        double total = 0.0;
        for (DetalleSolicitud dS : detalleSolicitud) {
            total += dS.calcularTotal(); // Usa la interfaz aquí
        }
        return total;
    }

    public void aprobarEstado(Usuario evaluador, boolean aprobar) {
        boolean jefe= evaluador.getRol()==Rol.JEFE_DE_DEPARTAMENTO;
        boolean mismoDepartamento= evaluador.getDepartamento()==usuario.getDepartamento();
        if (evaluador.getRol() == Rol.JEFE_DE_DEPARTAMENTO && evaluador.getDepartamento() == usuario.getDepartamento()) {
            if (aprobar) {
                this.estado = Estado.APROBADO;
            } else {
                this.estado = Estado.RECHAZADO;
            }
            System.out.println("Estado de la solicitud: "+ (aprobar ? "aprobada" : "rechazada") );
        }else{
            System.out.println("No tiene permiso para aprobar el solicitud");
        }
    }

    @Override
    public String toString() {
        return "SolicitudDeCompra: " +
                usuario +
                "\nEstado: " + estado +
                ", id: " + id + '\'' +
                ", numeroSolicitud: " + numeroSolicitud + '\'' +
                ", detalleSolicitud: " + detalleSolicitud +
                ", fechaSolicitud: " + fechaSolicitud;
    }

}
