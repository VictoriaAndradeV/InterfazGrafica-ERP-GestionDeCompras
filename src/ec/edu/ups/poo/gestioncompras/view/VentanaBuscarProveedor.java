package ec.edu.ups.poo.gestioncompras.view;

import ec.edu.ups.poo.gestioncompras.controller.BusquedaBinaria;
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

public class VentanaBuscarProveedor extends Frame {

    private TextField campoBusqueda;
    private List<Proveedor> proveedores;
    private BusquedaBinaria bBinaria;

    public VentanaBuscarProveedor(List<Proveedor> proveedores) {
        super("Buscar Proveedor por ID");
        this.proveedores = proveedores;
        this.bBinaria = new BusquedaBinaria();

        setSize(300, 200);
        setTitle("Buscar Proveedor");
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        Panel panelCentro = new Panel(new GridLayout(2, 2, 10, 10));
        panelCentro.add(new Label("Ingrese el ID del proveedor: "));
        campoBusqueda = new TextField(); //para que el usuario ingrese el id
        panelCentro.add(campoBusqueda); //se agrega el textField al panel del centro

        Button botonBuscar = new Button("Buscar");
        panelCentro.add(botonBuscar);
        Button botonSalir = new Button("Salir");
        panelCentro.add(botonSalir);
        add(panelCentro, BorderLayout.CENTER);


        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarID();
            }
        });

        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana
            }
        });

        setVisible(true);
    }

    private void buscarID(){
        String id = campoBusqueda.getText().trim();

        if(id.isEmpty()){
            mostrarMensaje("Ingrese el ID del proveedor");
            return;
        }

        int indice = bBinaria.buscarProveedorID(proveedores, id);

        if(indice >= 0){
            Proveedor prov = proveedores.get(indice);
            String info = "ID: " + prov.getId() + "\nNombre: " + prov.getNombre() +
                    "\nApellido: " + prov.getApellido() + "\nEmail: " + prov.getEmail() +
                    "\nTeléfono: " + prov.getTelefono()+ "\nRUC: " + prov.getRuc() +
                    "\nDirección: " + prov.getDireccion();
            mostrarMensaje(info);
        } else {
            mostrarMensaje("No existe el proveedor");
        }
    }

    private void mostrarMensaje(String mensaje) {
        Frame ventana = new Frame("Mensaje de búsqueda");
        ventana.setSize(400, 250);
        ventana.setLayout(new BorderLayout());
        ventana.setLocationRelativeTo(null);

        // Panel de mensajes
        Panel panelMensajes = new Panel(new GridLayout(0, 1, 5, 5));
        for (String linea : mensaje.split("\n")) {
            panelMensajes.add(new Label(linea));
        }
        ventana.add(panelMensajes, BorderLayout.CENTER);

        Panel panelBoton = new Panel(new FlowLayout(FlowLayout.CENTER));
        Button cerrar = new Button("Cerrar");
        cerrar.addActionListener(e -> ventana.dispose());
        panelBoton.add(cerrar);

        ventana.add(panelBoton, BorderLayout.SOUTH);

        ventana.setVisible(true);
    }

}
