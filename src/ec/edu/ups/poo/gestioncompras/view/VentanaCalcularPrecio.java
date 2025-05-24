package ec.edu.ups.poo.gestioncompras.view;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import ec.edu.ups.poo.gestioncompras.models.SolicitudDeCompra;

public class VentanaCalcularPrecio extends Frame {

    private TextField campoNumSolicitud;
    private TextField campoTotal;
    private Label etiquetaEstado;

    private List<SolicitudDeCompra> solicitudes;

    public VentanaCalcularPrecio(List<SolicitudDeCompra> solicitudes) {
        super("Calcular Total Solicitud");
        this.solicitudes = solicitudes;

        setSize(600, 250);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        Label titulo = new Label("Calcular Total Solicitud", Label.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        Panel panelCentro = new Panel(new GridLayout(3, 2, 10, 10));
        panelCentro.setBackground(Color.LIGHT_GRAY);

        panelCentro.add(new Label("Ingrese Número de Solicitud:"));
        campoNumSolicitud = new TextField();
        panelCentro.add(campoNumSolicitud);

        panelCentro.add(new Label("El valor total es:"));
        Panel panelValor = new Panel(new FlowLayout(FlowLayout.LEFT));
        panelValor.add(new Label("$"));
        campoTotal = new TextField(10);
        campoTotal.setEditable(false);
        panelValor.add(campoTotal);
        panelCentro.add(panelValor);

        panelCentro.add(new Label("")); // Espacio vacío
        Button botonBuscar = new Button("Buscar");
        botonBuscar.addActionListener(e -> buscarYMostrarTotal());
        panelCentro.add(botonBuscar);

        add(panelCentro, BorderLayout.CENTER);

        etiquetaEstado = new Label("", Label.CENTER);
        etiquetaEstado.setForeground(Color.DARK_GRAY);
        add(etiquetaEstado, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    private void buscarYMostrarTotal() {
        String numero = campoNumSolicitud.getText().trim();
        SolicitudDeCompra encontrada = buscarSolicitudPorId(numero);

        if (encontrada != null) {
            double total = encontrada.calcularTotal();
            campoTotal.setText(String.valueOf(total));
            etiquetaEstado.setText("Solicitud encontrada");
        } else {
            campoTotal.setText("");
            etiquetaEstado.setText("Solicitud no encontrada");
        }
    }

    // Simulación de búsqueda (puedes reemplazar esto por tu lógica real)
    private SolicitudDeCompra buscarSolicitudPorId(String id) {
        for (SolicitudDeCompra s : solicitudes) {
            if (s.getNumeroSolicitud().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }
}
