package ec.edu.ups.poo.gestioncompras.view;

import ec.edu.ups.poo.gestioncompras.models.Producto;
import ec.edu.ups.poo.gestioncompras.models.Proveedor;
import ec.edu.ups.poo.gestioncompras.models.SolicitudDeCompra;
import ec.edu.ups.poo.gestioncompras.models.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaPrincipal extends Frame {
    private  List<Usuario> usuarios;
    private  List<Proveedor> proveedores ;
    private  List<SolicitudDeCompra> solicitudes;
    private  List<Producto> productos;
    private int contadorDeSolicitudes = 1;

    //constructor ventana principal
    public VentanaPrincipal(List<Usuario> usuarios,List<Proveedor> proveedores, List<SolicitudDeCompra> solicitudes, List<Producto> productos) {
        this.productos = productos;
        this.usuarios = usuarios;
        this.proveedores = proveedores;
        this.solicitudes = solicitudes;


        setTitle("Sistema de Compras ERP");
        setSize(700, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout()); //divide la ventana en norte, sur, este, oeste, centro

        Label titulo = new Label("SISTEMA DE GESTION DE COMPRAS ERP", Label.CENTER);
        add(titulo, BorderLayout.NORTH); //agrega la etiqueta a la parte superior de la ventana

        Panel panelBotones = new Panel(new GridLayout(6, 2, 12, 12));
        String[] opciones = {
                "Registrar usuario", "Listar proveedores",
                "Registrar proveedor", "Listar productos",
                "Registrar producto", "Listar solicitudes de compra",
                "Registrar Solicitud de compra", "Aprobar / Rechazar solicitud de compra",
                "Listar usuarios", "Costo total por Solicitud",
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

        Panel panelSalir= new Panel(new FlowLayout(FlowLayout.CENTER));
        Button botonSalir = new Button("Salir");
        botonSalir.setPreferredSize(new Dimension(200,40));
        botonSalir.addActionListener(e -> System.exit(0));
        panelSalir.add(botonSalir);
        add(panelSalir, BorderLayout.SOUTH);

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
                new RegistrarSolicitudDeCompra(this, usuarios, productos, solicitudes, contadorDeSolicitudes);
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
            case "Costo total por Solicitud"    :
                new VentanaCalcularPrecio(solicitudes);
                break;
            case "Salir":
                System.exit(0);
        }

    }

    public void setContador(int nuevoContador) {
        this.contadorDeSolicitudes = nuevoContador;
    }
}
