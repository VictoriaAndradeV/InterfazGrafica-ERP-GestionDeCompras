package ec.edu.ups.poo.gestioncompras.models;

public class Departamento {
    private String nombre;
    private String id;
    private int numeroEmpleados;

    public Departamento(String nombre, String id, int numeroEmpleados) {
        this.nombre = nombre;
        this.id = id;
        this.numeroEmpleados = numeroEmpleados;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumeroEmpleados() {
        return numeroEmpleados;
    }

    public void setNumeroEmpleados(int numeroEmpleados) {
        this.numeroEmpleados = numeroEmpleados;
    }


    @Override
    public String toString() {
        return "Nombre: '" + nombre + '\'' +
                ", ID: '" + id + '\'' +
                ", NumeroEmpleados: " + numeroEmpleados;
    }
}
