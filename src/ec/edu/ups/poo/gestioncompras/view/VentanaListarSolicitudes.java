package ec.edu.ups.poo.gestioncompras.view;

import ec.edu.ups.poo.gestioncompras.models.SolicitudDeCompra;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;

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
            panel.setPreferredSize(new Dimension(350, 250));

            agregarCampo(panel, "Número de Solicitud", s.getNumeroSolicitud());
            agregarCampo(panel, "Estado", s.getEstado().toString());
            agregarCampo(panel, "Fecha", formatoFecha.format(s.getFechaSolicitud().getTime()));
            agregarCampo(panel, "Solicitante", s.getUsuario().getNombre() + " " + s.getUsuario().getApellido());
            agregarCampo(panel, "Departamento", s.getUsuario().getDepartamento().getNombre());
            agregarCampo(panel, "Rol", s.getUsuario().getRol().toString());
            agregarCampo(panel, "Total de la Solicitud", String.valueOf(s.calcularTotal()));

            panelSolicitudes.add(panel);
        }

        panelSolicitudes.revalidate();
    }


}
