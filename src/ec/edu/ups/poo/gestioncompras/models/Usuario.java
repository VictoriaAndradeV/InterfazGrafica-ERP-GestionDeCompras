package ec.edu.ups.poo.gestioncompras.models;

import ec.edu.ups.poo.gestioncompras.enums.Rol;

public class Usuario extends Contacto {

    private Departamento departamento;
    private Rol rol;


    public Usuario(String nombre, String apellido, String id, String email, String telefono, Departamento departamento, Rol rol) {
        super(nombre, apellido, id, email, telefono); //atributos de contacto
        this.departamento = departamento;
        this.rol = rol;
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


    @Override
    public String toString() {
        return "Usuario ->" +
                ", departamento = " + departamento +
                ", rol = " + rol +
                '}';
    }
}
