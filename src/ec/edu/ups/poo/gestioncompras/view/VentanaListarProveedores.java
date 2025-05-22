package ec.edu.ups.poo.gestioncompras.view;

import ec.edu.ups.poo.gestioncompras.models.Proveedor;
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

public class VentanaListarProveedores extends Frame implements WindowListener {
    private TextArea areaProveedores;
    private Button botonCerrar;

    public VentanaListarProveedores(List<Proveedor> proveedores) {
        super("Listar Proveedores");
        this.setLayout(new BorderLayout());
        this.areaProveedores = new TextArea();
        this.areaProveedores.setEditable(false);
        this.add(this.areaProveedores, "Center");
        this.botonCerrar = new Button("Cerrar");
        Panel panelBoton = new Panel(new FlowLayout(0));
        panelBoton.add(this.botonCerrar);
        this.add(panelBoton, "South");
        if (proveedores.isEmpty()) {
            this.areaProveedores.setText("No existen proveedores registrados.");
        } else {
            StringBuilder texto = new StringBuilder();

            for(Proveedor p : proveedores) {
                texto.append("- ").append(p.getNombre()).append(" ").append(p.getApellido()).append(" | ID: ").append(p.getId()).append(" | Email: ").append(p.getEmail()).append(" | Teléfono: ").append(p.getTelefono()).append(" | RUC: ").append(p.getRuc()).append(" | Dirección: ").append(p.getDireccion()).append("\n\n");
            }

            this.areaProveedores.setText(texto.toString());
        }

        this.botonCerrar.addActionListener((e) -> this.dispose());
        this.addWindowListener(this);
        this.setSize(500, 400);
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
