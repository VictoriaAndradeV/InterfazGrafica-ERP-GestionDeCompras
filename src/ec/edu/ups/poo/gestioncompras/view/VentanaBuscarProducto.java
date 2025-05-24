package ec.edu.ups.poo.gestioncompras.view;

import ec.edu.ups.poo.gestioncompras.controller.BusquedaBinaria;
import ec.edu.ups.poo.gestioncompras.models.Producto;
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

public class VentanaBuscarProducto extends Frame {

    private List<Producto> productos;
    private TextField campoBusqueda;
    private BusquedaBinaria bBinaria;

    public VentanaBuscarProducto(List<Producto> productos) {
        //super("Buscar Producto por ID");
        this.productos = productos;
        this.bBinaria = new BusquedaBinaria();

        //ventana en donde se ingresa el id del producto a buscar
        setTitle("Buscar Producto por ID");
        setSize(300, 200);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        Panel panelCentro = new Panel(new GridLayout(2, 2, 15, 15));
        panelCentro.add(new Label("Ingrese el ID del producto: "));
        campoBusqueda = new TextField(); //permite que el usuario ingrese el id de producto
        panelCentro.add(campoBusqueda); //agregamos el campo al panel

        Button botonBuscar = new Button("Buscar");
        panelCentro.add(botonBuscar);
        Button botonSalir = new Button("Salir");
        panelCentro.add(botonSalir);
        add(panelCentro, BorderLayout.CENTER);

        botonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarId();
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

    private void buscarId(){
        String id = campoBusqueda.getText(); //toma el id ingresado por el usuario

        if(id.isEmpty()){
            mostrarMensaje("Ingrese el ID del producto");
            return;
        }

        int indice = bBinaria.buscarProductoID(productos, id);

        if(indice >= 0){
            Producto prov = productos.get(indice);
            String info = "ID: " + prov.getId() + "\nNombre: " + prov.getNombre() +
                    "\nDescripcion: " + prov.getDescripcion() + "\nPrecio unitario: " + prov.getPrecioUnitario() +
                    "\nUnidad: " + prov.getUnidad();
            mostrarMensaje(info);
        } else {
            mostrarMensaje("No existe el producto buscado");
        }
    }

    public void mostrarMensaje(String mensaje){
        Frame ventanaMensaje = new Frame("Mensaje de busqueda");
        ventanaMensaje.setSize(400, 250);
        ventanaMensaje.setLayout(new BorderLayout());
        ventanaMensaje.setLocationRelativeTo(null);

        // Panel para colocar el mensaje
        Panel panelMensajes = new Panel(new GridLayout(0, 1));
        for (String linea : mensaje.split("\n")) {
            panelMensajes.add(new Label(linea));
        }
        ventanaMensaje.add(panelMensajes, BorderLayout.CENTER);


        Panel panelBoton = new Panel(new FlowLayout(FlowLayout.CENTER));
        Button cerrar = new Button("Cerrar");
        cerrar.addActionListener(e -> ventanaMensaje.dispose());
        panelBoton.add(cerrar);

        ventanaMensaje.add(panelBoton, BorderLayout.SOUTH);

        ventanaMensaje.setVisible(true);
    }
}
