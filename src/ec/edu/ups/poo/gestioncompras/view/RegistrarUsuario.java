package ec.edu.ups.poo.gestioncompras.view;

import java.awt.*;
import java.awt.event.*;
import ec.edu.ups.poo.gestioncompras.models.*;
import ec.edu.ups.poo.gestioncompras.enums.Rol;

public class RegistrarUsuario extends Frame {

    public RegistrarUsuario() {
        setTitle("Registrar Usuario");
        setSize(500, 500);
        setLayout(new GridLayout(8, 2, 10, 10));
        setLocationRelativeTo(null);

        // Campos de entrada
        TextField campoNombre = new TextField();
        TextField campoApellido = new TextField();
        TextField campoId = new TextField();
        TextField campoEmail = new TextField();
        TextField campoTelefono = new TextField();
        TextField campoDepartamento = new TextField();
        TextField campoRol = new TextField(); // ahora el rol se escribe a mano

        Button botonGuardar = new Button("Guardar");

        // Etiquetas y campos
        add(new Label("Nombre:"));
        add(campoNombre);
        add(new Label("Apellido:"));
        add(campoApellido);
        add(new Label("ID:"));
        add(campoId);
        add(new Label("Email:"));
        add(campoEmail);
        add(new Label("Teléfono:"));
        add(campoTelefono);
        add(new Label("Departamento:"));
        add(campoDepartamento);
        add(new Label("Rol JEFE_DE_DEPARTAMENTO o SUPERVISOR:"));
        add(campoRol);
        add(new Label(""));
        add(botonGuardar);

        // Acción al guardar
        botonGuardar.addActionListener(e -> {
            String nombre = campoNombre.getText();
            String apellido = campoApellido.getText();
            String id = campoId.getText();
            String email = campoEmail.getText();
            String telefono = campoTelefono.getText();
            String nombreDepartamento = campoDepartamento.getText();
            String textoRol = campoRol.getText().toUpperCase();

            // Validar y crear objetos
            if (!textoRol.equals("JEFE_DE_DEPARTAMENTO") && !textoRol.equals("SUPERVISOR")) {
                mostrarVentanaMensaje("Rol inválido. Use JEFE_DE_DEPARTAMENTO o SUPERVISOR.");
                return;
            }

            Rol rol = Rol.valueOf(textoRol);
            Departamento dep = new Departamento(nombreDepartamento, "D001", 10);
            Usuario usuario = new Usuario(nombre, apellido, id, email, telefono, dep, rol);

            System.out.println("Usuario registrado:\n" + usuario);
            mostrarVentanaMensaje("¡Usuario registrado con éxito!");
        });

        // Cerrar ventana
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    private void mostrarVentanaMensaje(String mensaje) {
        Frame ventana = new Frame("Mensaje");
        ventana.setSize(300, 100);
        ventana.setLayout(new FlowLayout());
        ventana.setLocationRelativeTo(this);

        Label etiqueta = new Label(mensaje);
        Button cerrar = new Button("Cerrar");

        cerrar.addActionListener(e -> ventana.dispose());

        ventana.add(etiqueta);
        ventana.add(cerrar);

        ventana.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                ventana.dispose();
            }
        });

        ventana.setVisible(true);
    }

}
