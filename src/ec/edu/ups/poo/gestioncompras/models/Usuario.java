package ec.edu.ups.poo.gestioncompras.models;

import ec.edu.ups.poo.gestioncompras.enums.Estado;
import ec.edu.ups.poo.gestioncompras.enums.Rol;

import java.util.GregorianCalendar;

public class Usuario extends Contacto {
    private SolicitudDeCompra solicitud;
    private Departamento departamento;
    private Rol rol;


    public Usuario(String nombre, String apellido, String id, String email, String telefono, Departamento departamento, Rol rol) {
        super(nombre, apellido, id, email, telefono); //atributos de contacto
        this.departamento = departamento;
        this.rol = rol;
        this.solicitud = null; //inicialmente sin solicitud
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public SolicitudDeCompra getSolicitud() {
        return solicitud;
    }

    public void addSolicitud(GregorianCalendar fecha, Estado estado, String numeroSolicitud) {
        if (this.solicitud == null) {
            this.solicitud = new SolicitudDeCompra(this, fecha, estado, numeroSolicitud);
        } else {
            System.out.println("El usuario ya tiene una solicitud existente.");
        }
    }


    @Override
    public String toString() {
        return "Usuario ->" +
                " solicitud = " + solicitud +
                ", departamento = " + departamento +
                ", rol = " + rol ;
    }
}
