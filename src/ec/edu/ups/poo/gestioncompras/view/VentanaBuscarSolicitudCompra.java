package ec.edu.ups.poo.gestioncompras.view;

import ec.edu.ups.poo.gestioncompras.controller.BusquedaBinaria;
import ec.edu.ups.poo.gestioncompras.models.SolicitudDeCompra;

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

public class VentanaBuscarSolicitudCompra extends Frame{

    private List<SolicitudDeCompra> solicitudDeCompra;
    private BusquedaBinaria bBinaria;
    private TextField campoBusqueda; //permite que el usuario escriba

    public VentanaBuscarSolicitudCompra(List<SolicitudDeCompra> solicitudDeCompra) {
        super("Buscar solicitud de compra");
        this.solicitudDeCompra = solicitudDeCompra;
        this.bBinaria = new BusquedaBinaria();

        setSize(350,200);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        Panel panelBusqueda= new Panel(new GridLayout(2, 2, 15, 15));
        panelBusqueda.add(new Label("Ingrese el numero de la solicitud: "));
        campoBusqueda = new TextField();
        panelBusqueda.add(campoBusqueda);

        Button botonBuscar = new Button("Buscar");
        panelBusqueda.add(botonBuscar);
        Button botonSalir = new Button("Salir");
        panelBusqueda.add(botonSalir);

        botonBuscar.addActionListener(new ActionListener() {
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

        add(panelBusqueda, BorderLayout.CENTER);

        setVisible(true);
    }

    public void buscarId(){
        String numero = campoBusqueda.getText().trim();

        if(numero.isEmpty()){
            mostrarMensaje("Ingrese la ID de la solicitud");
        }

        int indice = bBinaria.buscarSolicitudID(solicitudDeCompra, numero);

        if(indice >= 0){
            SolicitudDeCompra solicitud = solicitudDeCompra.get(indice);
            String info = "Número: " + solicitud.getNumeroSolicitud() +
                    "\nEstado: " + solicitud.getEstado() +
                    "\nUsuario: " + solicitud.getUsuario().getNombre() + " " + solicitud.getUsuario().getApellido() +
                    "\nDepartamento: " + solicitud.getUsuario().getDepartamento().getNombre();
            mostrarMensaje(info);
        } else {
            mostrarMensaje("Solicitud no encontrada");
        }
    }

    public void mostrarMensaje(String mensaje){
        Frame ventana = new Frame("Mensaje de busqueda");
        ventana.setSize(400, 250);
        ventana.setLayout(new BorderLayout());
        ventana.setLocationRelativeTo(null);

        Panel panelMensaje = new Panel(new GridLayout(0, 1));
        for (String linea : mensaje.split("\n")) {
            panelMensaje.add(new Label(linea));
        }
        ventana.add(panelMensaje, BorderLayout.CENTER);

        Panel panelBotonCerrar = new Panel(new FlowLayout(FlowLayout.CENTER));
        Button cerrar = new Button("Cerrar");
        cerrar.addActionListener(e -> ventana.dispose());
        panelBotonCerrar.add(cerrar);
        ventana.add(panelBotonCerrar, BorderLayout.SOUTH);

        ventana.setVisible(true);
    }
}
