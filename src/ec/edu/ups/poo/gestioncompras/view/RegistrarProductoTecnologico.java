package ec.edu.ups.poo.gestioncompras.view;

import ec.edu.ups.poo.gestioncompras.enums.UnidadDeMedida;
import ec.edu.ups.poo.gestioncompras.models.Producto;
import ec.edu.ups.poo.gestioncompras.models.ProductoTecnologico;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.List;

public class RegistrarProductoTecnologico extends Frame{

    private List<Producto> productoTecnologico;

    private TextField campoNombre;
    private TextField campoId;
    private TextField campoDescripcion;
    private TextField campoPrecio;
    private TextField campoGarantia;

    private Choice elegirUnidad;

    private Button botonRegistrar;
    private Button botonLimpiar;
    private Button botonSalir;

    public RegistrarProductoTecnologico(List<Producto> productoTecnologico) {
        this.productoTecnologico = productoTecnologico;

        setTitle("Ventana Registrar Producto Tecnologico");
        setSize(450, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        Panel panelCampos = new Panel(new GridLayout(6,2, 15,15 ));

        campoNombre = new TextField();
        campoId = new TextField();
        campoDescripcion = new TextField();
        campoPrecio = new TextField();
        campoGarantia = new TextField();

        elegirUnidad = new Choice();
        //se imprimen las unidades de medida mostradas en enums
        for (UnidadDeMedida unidad : UnidadDeMedida.values()) {
            elegirUnidad.add(unidad.name());
        }

        panelCampos.add(new Label("Nombre: "));
        panelCampos.add(campoNombre);
        panelCampos.add(new Label("ID:"));
        panelCampos.add(campoId);
        panelCampos.add(new Label("Descripcion:"));
        panelCampos.add(campoDescripcion);
        panelCampos.add(new Label("Precio unitario:"));
        panelCampos.add(campoPrecio);
        panelCampos.add(new Label("Unidad de medida:"));
        panelCampos.add(elegirUnidad);
        panelCampos.add(new Label("Garantia en meses:"));
        panelCampos.add(campoGarantia);

        Panel panelBotones = new Panel(new FlowLayout());
        botonRegistrar = new Button("Registrar producto");
        panelBotones.add(botonRegistrar);
        botonLimpiar = new Button("Limpiar");
        panelBotones.add(botonLimpiar);
        botonSalir = new Button("Salir");
        panelBotones.add(botonSalir);


        add(new Label("REGISTRAR PRODUCTO TECNOLOGICO", Label.CENTER), BorderLayout.NORTH);
        add(panelCampos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);


        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = campoId.getText().trim();
                String nombre = campoNombre.getText().trim();
                String descripcion = campoDescripcion.getText().trim();
                String precioTexto = campoPrecio.getText().trim();
                String garantiaTexto = campoGarantia.getText().trim();

                if (!precioTexto.matches("\\d+(\\.\\d+)?")) {
                    mostrarMensaje("Precio inválido. Use solo números.");
                    return;
                }

                if (!garantiaTexto.matches("\\d+")) {
                    mostrarMensaje("Garantía inválida. Ingrese un número entero de meses.");
                    return;
                }

                double precio = Double.parseDouble(precioTexto);
                int garantia = Integer.parseInt(garantiaTexto);
                UnidadDeMedida unidad = UnidadDeMedida.valueOf(elegirUnidad.getSelectedItem());

                ProductoTecnologico producto = new ProductoTecnologico(nombre, id, descripcion, precio, unidad, garantia);
                productoTecnologico.add(producto);
                mostrarMensaje("Producto tecnológico registrado exitosamente");
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
        elegirUnidad.select(0);
        campoGarantia.setText("");
    }

}
