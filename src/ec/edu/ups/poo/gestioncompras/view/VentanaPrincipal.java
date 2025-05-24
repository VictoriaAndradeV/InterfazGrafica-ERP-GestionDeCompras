package ec.edu.ups.poo.gestioncompras.view;

import ec.edu.ups.poo.gestioncompras.models.Producto;
import ec.edu.ups.poo.gestioncompras.models.Proveedor;
import ec.edu.ups.poo.gestioncompras.models.SolicitudDeCompra;
import ec.edu.ups.poo.gestioncompras.models.Usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import java.awt.Frame;
import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaPrincipal extends Frame {
    private  List<Usuario> usuarios;
    private  List<Proveedor> proveedores ;
    private  List<SolicitudDeCompra> solicitudes;
    private  List<Producto> productos;
    private int contadorDeSolicitudes;

    //constructor ventana principal
    public VentanaPrincipal(List<Usuario> usuarios,List<Proveedor> proveedores, List<SolicitudDeCompra> solicitudes, List<Producto> productos, int contadorDeSolicitudes) {
        this.productos = productos;
        this.usuarios = usuarios;
        this.proveedores = proveedores;
        this.solicitudes = solicitudes;
        this.contadorDeSolicitudes = contadorDeSolicitudes;

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
                "Salir"
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
                RegistrarSolicitudDeCompra ventana = new RegistrarSolicitudDeCompra(usuarios, productos, solicitudes, contadorDeSolicitudes);
                contadorDeSolicitudes = ventana.getContador(); // sincroniza
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
