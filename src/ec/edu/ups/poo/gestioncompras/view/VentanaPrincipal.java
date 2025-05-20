package ec.edu.ups.poo.gestioncompras.view;

import java.awt.*;
import java.awt.event.*;

public class VentanaPrincipal extends Frame {

    //constructor ventana principal
    public VentanaPrincipal() {
        setTitle("Sistema de Compras ERP");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout()); //divide la ventana en norte, sur, este, oeste, centro

        Label titulo = new Label("SISTEMA DE GESTION DE COMPRAS ERP", Label.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
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
        }
    }

    public static void main(String[] args) {
        new VentanaPrincipal();
    }
}
