package ec.edu.ups.poo.gestioncompras.models;

public class Contacto {

    private String nombre;
    private String apellido;
    private String id;
    private String email;
    private String telefono;

    public Contacto(String nombre, String apellido, String id, String email, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
        this.email = email;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "\nContacto: " +
                "\nNombre ='" + nombre + '\'' +
                ", Apellido='" + apellido + '\'' +
                ", ID = '" + id + '\'' +
                ", Email='" + email + '\'' +
                ", Telefono='" + telefono + '\''
                ;
    }
}
