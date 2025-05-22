package ec.edu.ups.poo.gestioncompras.view;

import ec.edu.ups.poo.gestioncompras.models.Producto;
import ec.edu.ups.poo.gestioncompras.models.Proveedor;
import ec.edu.ups.poo.gestioncompras.models.SolicitudDeCompra;
import ec.edu.ups.poo.gestioncompras.models.Usuario;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class VentanaPrincipal extends Frame {
    private  java.util.List<Usuario> usuarios;
    private  java.util.List<Proveedor> proveedores ;
    private  java.util.List<SolicitudDeCompra> solicitudes;
    private  java.util.List<Producto> productos;



    //constructor ventana principal
    public VentanaPrincipal(java.util.List<Usuario> usuarios,java.util.List<Proveedor> proveedores, java.util.List<SolicitudDeCompra> solicitudes, java.util.List<Producto> productos) {
        this.productos = productos;
        this.usuarios = usuarios;
        this.proveedores = proveedores;
        this.solicitudes = solicitudes;

        setTitle("Sistema de Compras ERP");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout()); //divide la ventana en norte, sur, este, oeste, centro

        Label titulo = new Label("SISTEMA DE GESTION DE COMPRAS ERP", Label.CENTER);
        add(titulo, BorderLayout.NORTH); //agrega la etiqueta a la parte superior de la ventana

        Panel panelBotones = new Panel(new GridLayout(7, 2, 10, 10));
        String[] opciones = {
                "Registrar usuario", "Listar solicitudes de compra",
                "Registrar proveedor", "Buscar proveedor por ID",
                "Registrar producto", "Buscar producto por nombre",
                "Registrar Solicitud de compra", "Buscar solicitud por ID",
                "Listar usuarios", "Aprobar / Rechazar solicitud de compra",
                "Listar proveedores", "Costo total por Solicitud",
                "Listar productos", "Salir"
        };

        for (String opcion : opciones) {
            Button boton = new Button(opcion);
            boton.addActionListener(e -> manejarAccion(opcion));
            panelBotones.add(boton);
        }

        add(panelBotones, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    // abre la ventana principal
    private void manejarAccion(String opcion) {
        switch (opcion) {
            case "Registrar usuario":
                new RegistrarUsuario();
                break;
            case "Listar solicitudes de compra":
                new VentanaListarSolicitudes(solicitudes);
                break;
            case "Listar usuarios":
                new VentanaListarUsuario(usuarios);
                break;
            case "Listar proveedores":
                new VentanaListarProveedores(proveedores);
                break;
            case "Listar productos":
                new VentanaListarProductos(productos);
                break;
            case "Aprobar / Rechazar solicitud de compra":
                new VentanaAprobarSolicitud(solicitudes, usuarios);
                break;
            case "Salir":
                System.exit(0);
            default:
                Frame nuevaVentana = new Frame(opcion);
                nuevaVentana.setSize(500, 300);
                nuevaVentana.setLayout(new FlowLayout());
                nuevaVentana.add(new Label("Aquí irá la lógica de: " + opcion));
                nuevaVentana.setVisible(true);
                nuevaVentana.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        nuevaVentana.dispose();
                    }
                });
            break;


        /*
        if (opcion.equals("Salir")) {
            System.exit(0);
        } else if (opcion.equals("Registrar usuario")) {
            new RegistrarUsuario();

        } else {
            Frame nuevaVentana = new Frame(opcion);
            nuevaVentana.setSize(500, 300);
            nuevaVentana.setLayout(new FlowLayout());
            nuevaVentana.add(new Label("Aquí irá la lógica de: " + opcion));
            nuevaVentana.setVisible(true);
            nuevaVentana.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    nuevaVentana.dispose();
                }
            });
        }*/
        }

    }
}
