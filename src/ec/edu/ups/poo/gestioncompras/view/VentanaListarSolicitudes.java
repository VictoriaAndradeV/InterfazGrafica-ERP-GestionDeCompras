package ec.edu.ups.poo.gestioncompras.view;

import ec.edu.ups.poo.gestioncompras.models.DetalleSolicitud;
import ec.edu.ups.poo.gestioncompras.models.SolicitudDeCompra;

import java.awt.Frame;
import java.awt.TextField;
import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Label;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.text.SimpleDateFormat;


public class VentanaListarSolicitudes extends Frame {

    private List<SolicitudDeCompra> solicitudes;
    private Panel panelSolicitudes;
    private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

    public VentanaListarSolicitudes(List<SolicitudDeCompra> solicitudes) {
        super("Listado de Solicitudes");
        this.solicitudes = solicitudes;

        setLayout(new BorderLayout());
        setSize(900, 600);
        setLocationRelativeTo(null);

        Panel panelSuperior = new Panel(new FlowLayout(FlowLayout.LEFT));

        Button btnActualizar = new Button("Actualizar");
        btnActualizar.addActionListener(e -> actualizarVista());
        panelSuperior.add(btnActualizar);

        Button btnBuscar = new Button("Buscar por ID");
        btnBuscar.addActionListener(e -> new VentanaBuscarSolicitudCompra(solicitudes));
        panelSuperior.add(btnBuscar);

        add(panelSuperior, BorderLayout.NORTH);

        panelSolicitudes = new Panel(new GridLayout(0, 2, 10, 10));
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.add(panelSolicitudes);
        add(scrollPane, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        actualizarVista();
        setVisible(true);
    }
    private void agregarCampo(Panel panel, String etiqueta, String valor) {
        panel.add(new Label(etiqueta));
        TextField campo = new TextField(valor);
        campo.setEditable(false);
        panel.add(campo);
    }

    private void actualizarVista() {
        panelSolicitudes.removeAll();

        for (SolicitudDeCompra s : solicitudes) {
            Panel panel = new Panel(new GridLayout(0, 1, 2, 2));
            panel.setPreferredSize(new Dimension(400, 500));

            agregarCampo(panel, "Número de Solicitud", s.getNumeroSolicitud());
            agregarCampo(panel, "Estado", s.getEstado().toString());
            agregarCampo(panel, "Fecha", formatoFecha.format(s.getFechaSolicitud().getTime()));
            agregarCampo(panel, "Solicitante", s.getUsuario().getNombre() + " " + s.getUsuario().getApellido());
            agregarCampo(panel, "Cédula del Solicitante", s.getUsuario().getId());
            agregarCampo(panel, "Departamento", s.getUsuario().getDepartamento().getNombre());
            agregarCampo(panel, "Rol", s.getUsuario().getRol().toString());
            agregarCampo(panel, "Total de la Solicitud", String.valueOf(s.calcularTotal()));

            for (DetalleSolicitud d : s.getDetalleSolicitud()) {
                agregarCampo(panel, "Producto", d.getProducto().getNombre());
                agregarCampo(panel, "Cantidad", String.valueOf(d.getCantidad()));
                agregarCampo(panel, "Subtotal", String.valueOf(d.calcularTotal()));
                agregarCampo(panel, "Justificación", d.getJustificacion());
            }
            panelSolicitudes.add(panel);
        }

        panelSolicitudes.revalidate();
    }


}
