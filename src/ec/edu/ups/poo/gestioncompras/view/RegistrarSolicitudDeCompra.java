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

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.GregorianCalendar;
import java.util.List;

public class RegistrarSolicitudDeCompra extends Frame {

    private VentanaPrincipal ventanaPrincipal;
    private int contador;
    private List<Usuario> usuarios;
    private List<Producto> productos;
    private List<SolicitudDeCompra> solicitudes;

    private TextField campoNombreSolicitante;
    private TextField campoNombreProducto;
    private TextField campoCantidad;

    private Button botonRegistrar;
    private Button botonLimpiar;
    private Button botonFinalizar;

    private SolicitudDeCompra nuevaSolicitud;

    public RegistrarSolicitudDeCompra(VentanaPrincipal ventanaPrincipal, List<Usuario> usuarios, List<Producto> productos, List<SolicitudDeCompra> solicitudes, int contador) {
        this.ventanaPrincipal = ventanaPrincipal;
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
        panelBotones.add(botonRegistrar);
        botonLimpiar = new Button("Limpiar campos");
        panelBotones.add(botonLimpiar);
        botonFinalizar = new Button("Finalizar solicitud");
        panelBotones.add(botonFinalizar);

        add(new Label("REGISTRAR SOLICITUD DE COMPRA", Label.CENTER), BorderLayout.NORTH);
        add(panelCampos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        botonRegistrar.addActionListener(e -> agregarProductoASolicitud());
        botonLimpiar.addActionListener(e -> limpiarCampos());
        botonFinalizar.addActionListener(e -> finalizarSolicitud());

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
            nuevaSolicitud = new SolicitudDeCompra(usuario, new GregorianCalendar(), Estado.SOLICITADA, ""); // número se define luego
        }

        nuevaSolicitud.agregarDetalle(producto, cantidad, "Justificación no disponible");
        mostrarMensaje("Producto agregado a la solicitud.");
    }

    private void finalizarSolicitud() {
        if (nuevaSolicitud == null || nuevaSolicitud.getDetalleSolicitud().isEmpty()) {
            mostrarMensaje("Agregue productos a la solicitud");
            return;
        }

        // Generar número nuevo con el contador actual
        String numeroSolicitud = "SC" + String.format("%03d", contador);
        nuevaSolicitud.setNumeroSolicitud(numeroSolicitud);

        solicitudes.add(nuevaSolicitud);
        mostrarMensaje("Solicitud registrada con éxito. ID: " + numeroSolicitud);

        contador++;
        ventanaPrincipal.setContador(contador);
        nuevaSolicitud = null;
        dispose();
    }

    private void mostrarMensaje(String mensaje) {
        Frame mensajeVentana = new Frame("Mensaje");
        mensajeVentana.setSize(300, 100);
        mensajeVentana.setLayout(new FlowLayout());
        mensajeVentana.setLocationRelativeTo(this);

        Label etiqueta = new Label(mensaje);
        Button cerrar = new Button("Cerrar");
        cerrar.addActionListener(e -> mensajeVentana.dispose());

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
