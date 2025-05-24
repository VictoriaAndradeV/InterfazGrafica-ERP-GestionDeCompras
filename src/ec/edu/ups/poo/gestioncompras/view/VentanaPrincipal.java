package ec.edu.ups.poo.gestioncompras.view;

import ec.edu.ups.poo.gestioncompras.models.Producto;
import ec.edu.ups.poo.gestioncompras.models.Proveedor;
import ec.edu.ups.poo.gestioncompras.models.SolicitudDeCompra;
import ec.edu.ups.poo.gestioncompras.models.Usuario;
import java.util.List;

import java.awt.*;
import java.awt.event.*;

public class VentanaPrincipal extends Frame {
    private  List<Usuario> usuarios;
    private  List<Proveedor> proveedores ;
    private  List<SolicitudDeCompra> solicitudes;
    private  List<Producto> productos;


    //constructor ventana principal
    public VentanaPrincipal(List<Usuario> usuarios,List<Proveedor> proveedores, List<SolicitudDeCompra> solicitudes, List<Producto> productos) {
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
            String opcionFinal = opcion; // copia local para evitar el error de referencia
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    manejarAccion(opcionFinal);
                }
            });
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
                new RegistrarUsuario(usuarios);
                break;
            case "Registrar proveedor":
                new RegistrarProveedor(proveedores);
                break;
            case "Registrar producto":
                new RegistrarProducto(productos);
                break;
            case "Registrar Solicitud de compra":
                //new RegistrarSolicitudDeCompra(usuarios, productos, solicitudes);
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
        }

    }
}
