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
import java.awt.ScrollPane;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class VentanaListarProveedores extends Frame {

    private List<Proveedor> proveedores;
    private Panel panelProveedores;

    public VentanaListarProveedores(List<Proveedor> proveedores) {
        super("Listado de Proveedores");
        this.proveedores = proveedores;

        setLayout(new BorderLayout());
        setSize(800, 600);
        setLocationRelativeTo(null);

        Panel panelSuperior = new Panel(new FlowLayout(FlowLayout.LEFT));
        Button botonActualizar = new Button("Actualizar");
        botonActualizar.addActionListener(e -> actualizarVista());
        panelSuperior.add(botonActualizar);
        add(panelSuperior, BorderLayout.NORTH);

        Button botonBuscar = new Button("Buscar por ID");
        panelSuperior.add(botonBuscar);
        botonBuscar.addActionListener(e -> {
            new VentanaBuscarProveedor(proveedores);
        });

        panelProveedores = new Panel();
        panelProveedores.setLayout(new GridLayout(0, 2, 10, 10)); // 2 columnas
        ScrollPane scrollPanel = new ScrollPane();
        scrollPanel.add(panelProveedores);
        add(scrollPanel, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        actualizarVista(); //carg los datos

        setVisible(true);
    }

    private void agregarCampo(Panel panel, String etiqueta, String valor) {
        panel.add(new Label(etiqueta));
        TextField campo = new TextField(valor);
        campo.setEditable(false);
        panel.add(campo);
    }

    private void actualizarVista() {
        panelProveedores.removeAll();

        for (Proveedor p : proveedores) {
            Panel panelProveedor = new Panel(new GridLayout(0, 1, 2, 2));
            panelProveedor.setPreferredSize(new Dimension(300, 200));

            agregarCampo(panelProveedor, "ID", p.getId());
            agregarCampo(panelProveedor, "Nombre", p.getNombre());
            agregarCampo(panelProveedor, "Apellido", p.getApellido());
            agregarCampo(panelProveedor, "Email", p.getEmail());
            agregarCampo(panelProveedor, "Teléfono", p.getTelefono());
            agregarCampo(panelProveedor, "RUC", p.getRuc());
            agregarCampo(panelProveedor, "Dirección", p.getDireccion());

            panelProveedores.add(panelProveedor);
        }

        panelProveedores.revalidate();
    }


}
