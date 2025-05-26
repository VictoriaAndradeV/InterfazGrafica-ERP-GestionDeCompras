package ec.edu.ups.poo.gestioncompras.view;

import ec.edu.ups.poo.gestioncompras.models.Proveedor;

import java.awt.Frame;
import java.awt.TextField;
import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

public class RegistrarProveedor extends Frame {

    private List<Proveedor> proveedores;
    private TextField campoNombre;
    private TextField campoApellido;
    private TextField campoId;
    private TextField campoEmail;
    private TextField campoTelefono;
    private TextField campoRuc;
    private TextField campoDireccion;

    private Button botonActualizarRegistro;
    private Button botonLimpiarCampos;
    private Button botonCerrar;

    public RegistrarProveedor(List<Proveedor> proveedores) {
        this.proveedores = proveedores;

        //configuramos la ventana
        setTitle("Ventana Registrar Proveedor");
        setSize(500, 500);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        Panel panelCampoProveedor = new Panel(new GridLayout(8, 2, 10, 10));
        campoNombre = new TextField();
        campoApellido = new TextField();
        campoId = new TextField();
        campoEmail = new TextField();
        campoTelefono = new TextField();
        campoRuc = new TextField();
        campoDireccion = new TextField();

        panelCampoProveedor.add(new Label("Nombre: "));
        panelCampoProveedor.add(campoNombre);
        panelCampoProveedor.add(new Label("Apellido: "));
        panelCampoProveedor.add(campoApellido);
        panelCampoProveedor.add(new Label("Id: "));
        panelCampoProveedor.add(campoId);
        panelCampoProveedor.add(new Label("Email: "));
        panelCampoProveedor.add(campoEmail);
        panelCampoProveedor.add(new Label("Telefono: "));
        panelCampoProveedor.add(campoTelefono);
        panelCampoProveedor.add(new Label("Ruc: "));
        panelCampoProveedor.add(campoRuc);
        panelCampoProveedor.add(new Label("Direccion: "));
        panelCampoProveedor.add(campoDireccion);

        //Panel de los botones
        Panel panelBotones = new Panel(new FlowLayout());
        botonActualizarRegistro = new Button("Guardar Registro");
        panelBotones.add(botonActualizarRegistro);
        botonLimpiarCampos = new Button("Limpiar Campos");
        panelBotones.add(botonLimpiarCampos);
        botonCerrar = new Button("Cerrar");
        panelBotones.add(botonCerrar);

        add(new Label("REGISTRAR NUEVO PROVEEDOR", Label.CENTER), BorderLayout.NORTH);
        add(panelCampoProveedor, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        //Accion cuando el mouse hace click en guardar registro
        botonActualizarRegistro.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = campoNombre.getText().trim();
                String apellido = campoApellido.getText().trim();
                String id = campoId.getText().trim();
                String email = campoEmail.getText().trim();
                String telefono = campoTelefono.getText().trim();
                String ruc = campoRuc.getText().trim();
                String direccion = campoDireccion.getText().trim();

                Proveedor proveedor = new Proveedor(nombre, apellido, id, email, telefono, ruc, direccion);
                proveedores.add(proveedor);
                mostrarMensaje("Proveedor registrado exitosamente");
            }
        });

        botonLimpiarCampos.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        botonCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana
            }
        });

        setVisible(true);
    }

    private void mostrarMensaje(String mensaje) {
        Frame mensajeVentana = new Frame("Mensaje");
        mensajeVentana.setSize(300, 100);
        mensajeVentana.setLayout(new FlowLayout());
        mensajeVentana.setLocationRelativeTo(this);

        Label etiqueta = new Label(mensaje);
        Button cerrar = new Button("Cerrar");

        cerrar.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mensajeVentana.dispose();
            }
        });

        mensajeVentana.add(etiqueta);
        mensajeVentana.add(cerrar);
        mensajeVentana.setVisible(true);
    }

    public void limpiarCampos() {
        campoNombre.setText("");
        campoApellido.setText("");
        campoId.setText("");
        campoEmail.setText("");
        campoTelefono.setText("");
        campoRuc.setText("");
        campoDireccion.setText("");
    }
}
