package ec.edu.ups.poo.gestioncompras.view;


import ec.edu.ups.poo.gestioncompras.models.Producto;
import ec.edu.ups.poo.gestioncompras.models.SolicitudDeCompra;
import ec.edu.ups.poo.gestioncompras.models.Usuario;
import ec.edu.ups.poo.gestioncompras.enums.Estado;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.GregorianCalendar;
import java.util.List;

public class RegistrarSolicitudDeCompra extends Frame {

    private List<Usuario> usuarios;
    private List<Producto> productos;
    private List<SolicitudDeCompra> solicitudes;

    private TextField campoNombreSolicitante;
    private TextField campoNombreProducto;
    private TextField campoCantidad;

    private Button botonRegistrar;
    private Button botonLimpiar;

    private SolicitudDeCompra nuevaSolicitud;
    private int contador;

    public RegistrarSolicitudDeCompra(List<Usuario> usuarios, List<Producto> productos, List<SolicitudDeCompra> solicitudes, int contador) {
        this.usuarios = usuarios;
        this.productos = productos;
        this.solicitudes = solicitudes;
        this.contador = contador;

        setTitle("Registrar Solicitud de Compra");
        setSize(500, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        Panel panelCampos = new Panel(new GridLayout(4, 2, 10, 10));
        campoNombreSolicitante = new TextField();
        campoNombreProducto = new TextField();
        campoCantidad = new TextField();

        panelCampos.add(new Label("Nombre del solicitante:"));
        panelCampos.add(campoNombreSolicitante);
        panelCampos.add(new Label("Nombre del producto:"));
        panelCampos.add(campoNombreProducto);
        panelCampos.add(new Label("Cantidad:"));
        panelCampos.add(campoCantidad);

        Panel panelBotones = new Panel(new FlowLayout());
        botonRegistrar = new Button("Agregar producto");
        botonLimpiar = new Button("Limpiar campos");

        panelBotones.add(botonRegistrar);
        panelBotones.add(botonLimpiar);

        add(new Label("REGISTRAR SOLICITUD DE COMPRA", Label.CENTER), BorderLayout.NORTH);
        add(panelCampos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProductoASolicitud();
            }
        });

        botonLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    private void agregarProductoASolicitud() {
        String nombreSolicitante = campoNombreSolicitante.getText().trim();
        String nombreProducto = campoNombreProducto.getText().trim();
        String cantidadTexto = campoCantidad.getText().trim();

        if (!cantidadTexto.matches("\\d+")) {
            mostrarMensaje("Cantidad inválida. Ingrese solo números enteros.");
            return;
        }

        Usuario usuario = null;
        for (Usuario u : usuarios) {
            if (u.getNombre().equalsIgnoreCase(nombreSolicitante)) {
                usuario = u;
                break;
            }
        }

        if (usuario == null) {
            mostrarMensaje("Usuario no encontrado.");
            return;
        }

        Producto producto = null;
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombreProducto)) {
                producto = p;
                break;
            }
        }

        if (producto == null) {
            mostrarMensaje("Producto no encontrado.");
            return;
        }

        int cantidad = Integer.parseInt(cantidadTexto);

        if (nuevaSolicitud == null) {
            String numeroSolicitud = "SC" + String.format("%03d", contador);
            nuevaSolicitud = new SolicitudDeCompra(usuario, new GregorianCalendar(), Estado.SOLICITADA, numeroSolicitud);
        }

        nuevaSolicitud.agregarDetalle(producto, cantidad, "Justificación no disponible");
        mostrarMensaje("Producto agregado a la solicitud.");
    }

    private void finalizarSolicitud() {
        if (nuevaSolicitud != null && !nuevaSolicitud.getDetalleSolicitud().isEmpty()) {
            solicitudes.add(nuevaSolicitud);
            mostrarMensaje("Solicitud registrada con éxito. Número: " + nuevaSolicitud.getNumeroSolicitud());
            nuevaSolicitud = null;
            contador++;
        } else {
            mostrarMensaje("Agregue productos a la solicitud");
        }
    }

    private void mostrarMensaje(String mensaje) {
        Frame mensajeVentana = new Frame("Ventana Mensaje");
        mensajeVentana.setSize(300, 100);
        mensajeVentana.setLayout(new FlowLayout());
        mensajeVentana.setLocationRelativeTo(this);

        Label etiqueta = new Label(mensaje);
        Button cerrar = new Button("Cerrar");
        cerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mensajeVentana.dispose();
            }
        });

        mensajeVentana.add(etiqueta);
        mensajeVentana.add(cerrar);
        mensajeVentana.setVisible(true);
    }

    private void limpiarCampos() {
        campoNombreSolicitante.setText("");
        campoNombreProducto.setText("");
        campoCantidad.setText("");
    }
}
