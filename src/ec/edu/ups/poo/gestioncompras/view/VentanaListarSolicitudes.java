package ec.edu.ups.poo.gestioncompras.view;

import ec.edu.ups.poo.gestioncompras.models.DetalleSolicitud;
import ec.edu.ups.poo.gestioncompras.models.Producto;
import ec.edu.ups.poo.gestioncompras.models.SolicitudDeCompra;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

public class VentanaListarSolicitudes extends Frame implements WindowListener {
    private TextArea areaSolicitudes;
    private Button botonCerrar;

    public VentanaListarSolicitudes(List<SolicitudDeCompra> solicitudes) {
        super("Lista de Solicitudes de Compra");
        this.setLayout(new BorderLayout());
        this.areaSolicitudes = new TextArea();
        this.areaSolicitudes.setEditable(false);
        this.add(this.areaSolicitudes, "Center");
        Panel panelBoton = new Panel(new FlowLayout(2));
        this.botonCerrar = new Button("Cerrar");
        panelBoton.add(this.botonCerrar);
        this.add(panelBoton, "South");
        if (solicitudes.isEmpty()) {
            this.areaSolicitudes.setText("No existen solicitudes registradas.");
        } else {
            StringBuilder texto = new StringBuilder();

            for(SolicitudDeCompra s : solicitudes) {
                texto.append("Solicitud: ").append(s.getNumeroSolicitud()).append(" | Estado: ").append(s.getEstado()).append(" | Fecha: ").append(s.getFechaSolicitud().getTime().toString()).append("\nSolicitante: ").append(s.getUsuario().getNombre()).append(" ").append(s.getUsuario().getApellido()).append(" | Departamento: ").append(s.getUsuario().getDepartamento().getNombre()).append("\nProductos:\n");

                for(DetalleSolicitud d : s.getDetalleSolicitud()) {
                    Producto p = d.getProducto();
                    texto.append("   - ").append(p.getNombre()).append(" | Cantidad: ").append(d.getCantidad()).append(" | Subtotal: $").append(d.calcularTotal()).append("\n");
                }

                texto.append("Total de solicitud: $").append(s.calcularTotal()).append("\n");
                texto.append("------------------------------------------------------\n\n");
            }

            this.areaSolicitudes.setText(texto.toString());
        }

        this.botonCerrar.addActionListener((e) -> this.dispose());
        this.addWindowListener(this);
        this.setSize(600, 450);
        this.setLocationRelativeTo((Component)null);
        this.setVisible(true);
    }

    public void windowClosing(WindowEvent e) {
        this.dispose();
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }
}

