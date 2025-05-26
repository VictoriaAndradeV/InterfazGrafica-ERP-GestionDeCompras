package ec.edu.ups.poo.gestioncompras.view;

import ec.edu.ups.poo.gestioncompras.enums.UnidadDeMedida;
import ec.edu.ups.poo.gestioncompras.models.Producto;
import ec.edu.ups.poo.gestioncompras.models.ProductoLimpieza;

import java.awt.Frame;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

public class RegistrarProductoLimpieza extends Frame{

    private List<Producto> productoLimpieza;

    private TextField campoNombre;
    private TextField campoId;
    private TextField campoDescripcion;
    private TextField campoPrecio;
    private TextField campoVolumen;

    private Choice elegirUnidad;

    private Button botonRegistrar;
    private Button botonLimpiar;
    private Button botonSalir;

    public RegistrarProductoLimpieza(List<Producto> productoLimpieza) {
        this.productoLimpieza = productoLimpieza;

        setTitle("Ventana Registrar Producto Limpieza");
        setSize(450, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        Panel panelCampos = new Panel(new GridLayout(6, 2, 10, 15));

        campoNombre = new TextField();
        campoId = new TextField();
        campoDescripcion = new TextField();
        campoPrecio = new TextField();
        campoVolumen = new TextField();

        elegirUnidad = new Choice();
        for (UnidadDeMedida unidad : UnidadDeMedida.values()) {
            elegirUnidad.add(unidad.name());
        }

        panelCampos.add(new Label("Nombre:"));
        panelCampos.add(campoNombre);
        panelCampos.add(new Label("ID:"));
        panelCampos.add(campoId);
        panelCampos.add(new Label("Descripcion:"));
        panelCampos.add(campoDescripcion);
        panelCampos.add(new Label("Precio unitario:"));
        panelCampos.add(campoPrecio);
        panelCampos.add(new Label("Volumen:"));
        panelCampos.add(campoVolumen);
        panelCampos.add(new Label("Unidad de medida:"));
        panelCampos.add(elegirUnidad);

        Panel panelBotones = new Panel(new FlowLayout());
        botonRegistrar = new Button("Registrar producto");
        panelBotones.add(botonRegistrar);
        botonLimpiar = new Button("Limpiar");
        panelBotones.add(botonLimpiar);
        botonSalir = new Button("Salir");
        panelBotones.add(botonSalir);

        add(new Label("REGISTRAR PRODUCTO DE LIMPIEZA", Label.CENTER), BorderLayout.NORTH);
        add(panelCampos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = campoId.getText().trim();
                String nombre = campoNombre.getText().trim();
                String descripcion = campoDescripcion.getText().trim();
                String precioTexto = campoPrecio.getText().trim();
                String volumenTexto = campoVolumen.getText().trim();

                if (!precioTexto.matches("\\d+(\\.\\d+)?")) {
                    mostrarMensaje("Precio invalido. Ingrese unicamente numeros");
                    return;
                }

                if (!volumenTexto.matches("\\d+(\\.\\d+)?")) {
                    mostrarMensaje("Volumen invalido. Ingrese unicamente numeros");
                    return;
                }

                double precio = Double.parseDouble(precioTexto);
                double volumen = Double.parseDouble(volumenTexto);
                UnidadDeMedida unidad = UnidadDeMedida.valueOf(elegirUnidad.getSelectedItem());

                ProductoLimpieza producto = new ProductoLimpieza(nombre, id, descripcion, precio, unidad, volumen);
                productoLimpieza.add(producto);
                mostrarMensaje("Producto de limpieza registrado correctamente.");
            }
        });

        botonLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
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

    private void mostrarMensaje(String mensaje) {
        Frame mensajeVentana = new Frame("Mensaje error");
        mensajeVentana.setSize(300, 100);
        mensajeVentana.setLayout(new FlowLayout());
        mensajeVentana.setLocationRelativeTo(this);

        Label etiqueta = new Label(mensaje);
        Button cerrar = new Button("Cerrar");

        cerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mensajeVentana.dispose();
            }
        });

        mensajeVentana.add(etiqueta);
        mensajeVentana.add(cerrar);
        mensajeVentana.setVisible(true);
    }

    private void limpiarCampos() {
        campoId.setText("");
        campoNombre.setText("");
        campoDescripcion.setText("");
        campoPrecio.setText("");
        campoVolumen.setText("");
        elegirUnidad.select(0);
    }
}
