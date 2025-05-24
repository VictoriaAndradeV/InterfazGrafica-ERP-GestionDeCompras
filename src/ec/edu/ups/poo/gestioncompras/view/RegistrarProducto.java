package ec.edu.ups.poo.gestioncompras.view;

import ec.edu.ups.poo.gestioncompras.models.Producto;

import java.awt.Frame;
import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

public class RegistrarProducto extends Frame{

    private List<Producto> productos;

    public RegistrarProducto(List<Producto> productos) {
        this.productos = productos;
        //configuraciones de registrar producto
        setTitle("Ventana Principal de Registrar Producto");
        setSize(300, 300);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        Label titulo = new Label("Seleccione el tipo de producto a registrar", Label.CENTER);
        add(titulo, BorderLayout.NORTH);

        Panel panelBotones = new Panel(new GridLayout(3, 1, 10, 10));
        Button botonComestible = new Button("Producto Comestible");
        panelBotones.add(botonComestible);
        Button botonLimpieza = new Button("Producto de Limpieza");
        panelBotones.add(botonLimpieza);
        Button botonTecnologia = new Button("Producto Tecnologico");
        panelBotones.add(botonTecnologia);

        add(panelBotones, BorderLayout.CENTER);

        //Acciones de cada boton
        botonComestible.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistrarProductoComestible(productos);
            }
        });

        botonTecnologia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistrarProductoTecnologico(productos);
            }
        });

        setVisible(true);
    }
}
