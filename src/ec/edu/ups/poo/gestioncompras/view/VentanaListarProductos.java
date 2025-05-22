package ec.edu.ups.poo.gestioncompras.view;

import ec.edu.ups.poo.gestioncompras.models.Producto;
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

public class VentanaListarProductos extends Frame implements WindowListener {
    private TextArea areaProductos;
    private Button botonCerrar;

    public VentanaListarProductos(List<Producto> productos) {
        super("Lista de Productos");
        this.setLayout(new BorderLayout());
        this.areaProductos = new TextArea();
        this.areaProductos.setEditable(false);
        this.add(this.areaProductos, "Center");
        Panel panelBoton = new Panel(new FlowLayout(2));
        this.botonCerrar = new Button("Cerrar");
        panelBoton.add(this.botonCerrar);
        this.add(panelBoton, "South");
        if (productos.isEmpty()) {
            this.areaProductos.setText("No existen productos registrados.");
        } else {
            StringBuilder texto = new StringBuilder();

            for(Producto p : productos) {
                texto.append("- ").append(p.getId()).append(" | Nombre: ").append(p.getNombre()).append(" | Descripción: ").append(p.getDescripcion()).append(" | Precio: $").append(p.getPrecioUnitario()).append(" | Unidad: ").append(p.getUnidad()).append("\n\n");
            }

            this.areaProductos.setText(texto.toString());
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
