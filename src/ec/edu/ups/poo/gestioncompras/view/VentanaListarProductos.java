package ec.edu.ups.poo.gestioncompras.view;
import ec.edu.ups.poo.gestioncompras.models.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class VentanaListarProductos extends Frame {

    private List<Producto> productos;
    private Panel panelProductos;
    private String categoriaSeleccionada = "";


    public VentanaListarProductos(List<Producto> productos) {
        super("Listado de Productos");
        this.productos = productos;

        setLayout(new BorderLayout());
        setSize(800, 600);
        setLocationRelativeTo(null);

        Panel panelSuperior = new Panel(new BorderLayout());

        // Botón actualizar
        Button botonActualizar = new Button("Actualizar");
        botonActualizar.addActionListener(e -> actualizarVista());
        panelSuperior.add(botonActualizar, BorderLayout.NORTH);

        Panel panelBotones = new Panel(new FlowLayout());

        Button botonComestibles = new Button("Productos Comestibles");
        botonComestibles.addActionListener(e -> {
            categoriaSeleccionada = "comestible";
            mostrarProductosPorCategoria(categoriaSeleccionada);
        });
        panelBotones.add(botonComestibles);

        Button botonLimpieza = new Button("Productos de Limpieza");
        botonLimpieza.addActionListener(e -> {
            categoriaSeleccionada = "limpieza";
            mostrarProductosPorCategoria(categoriaSeleccionada);
        });
        panelBotones.add(botonLimpieza);

        Button botonTecnologicos = new Button("Productos Tecnológicos");
        botonTecnologicos.addActionListener(e -> {
            categoriaSeleccionada="tecnologico";
            mostrarProductosPorCategoria(categoriaSeleccionada);
        });
        panelBotones.add(botonTecnologicos);

        panelSuperior.add(panelBotones, BorderLayout.SOUTH);
        add(panelSuperior, BorderLayout.NORTH);

        panelProductos = new Panel();
        panelProductos.setLayout(new GridLayout(0, 2, 10, 10));
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.add(panelProductos);
        add(scrollPane, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    private boolean perteneceACategoria(Producto producto, String categoria) {
        return switch (categoria.toLowerCase()) {
            case "comestible" -> producto instanceof ProductoComestible;
            case "limpieza" -> producto instanceof ProductoLimpieza;
            case "tecnologico" -> producto instanceof ProductoTecnologico;
            default -> false;
        };
    }

    private void agregarCampo(Panel panel, String etiqueta, String valor) {
        panel.add(new Label(etiqueta));
        TextField campo = new TextField(valor);
        campo.setEditable(false);
        panel.add(campo);
    }


    private void mostrarProductosPorCategoria(String categoria) {
        panelProductos.removeAll();

        for (Producto producto : productos) {
            if (perteneceACategoria(producto, categoria)) {
                Panel panelProducto = new Panel(new GridLayout(0, 1, 2, 2));
                panelProducto.setPreferredSize(new Dimension(300, 200));

                agregarCampo(panelProducto, "ID", producto.getId());
                agregarCampo(panelProducto, "Nombre", producto.getNombre());
                agregarCampo(panelProducto, "Descripción", producto.getDescripcion());
                agregarCampo(panelProducto, "Precio Unitario", String.valueOf(producto.getPrecioUnitario()));
                agregarCampo(panelProducto, "Unidad de Venta", producto.getUnidad().toString());

                if (producto instanceof ProductoComestible p) {
                    agregarCampo(panelProducto, "Peso", String.valueOf(p.getPeso()));
                    agregarCampo(panelProducto, "Fecha Elaboración", p.getFechaElaboracion().toString());
                    agregarCampo(panelProducto, "Fecha Caducidad", p.getFechaCaducidad().toString());
                } else if (producto instanceof ProductoLimpieza p) {
                    agregarCampo(panelProducto, "Volumen", String.valueOf(p.getVolumen()));
                } else if (producto instanceof ProductoTecnologico p) {
                    agregarCampo(panelProducto, "Garantía (meses)", String.valueOf(p.getGarantiaEnMeses()));
                }

                panelProductos.add(panelProducto);
            }
        }

        panelProductos.revalidate();
        panelProductos.repaint();
    }



    private void actualizarVista() {
        //reutilizable
        if (!categoriaSeleccionada.isEmpty()) {
            mostrarProductosPorCategoria(categoriaSeleccionada);
        } else {
            System.out.println("No hay categoría seleccionada para actualizar.");
        }
    }
}
