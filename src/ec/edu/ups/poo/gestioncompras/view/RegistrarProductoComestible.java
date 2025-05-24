package ec.edu.ups.poo.gestioncompras.view;

import ec.edu.ups.poo.gestioncompras.models.Producto;
import ec.edu.ups.poo.gestioncompras.enums.UnidadDeMedida;
import ec.edu.ups.poo.gestioncompras.models.ProductoComestible;

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

import java.time.LocalDate;
import java.util.List;

public class RegistrarProductoComestible extends Frame{

    private List<Producto> productoComes;

    private TextField campoNombre;
    private TextField campoId;
    private TextField campoDescripcion;
    private TextField campoPrecio;
    private TextField campoPeso;
    private TextField campoFechaElaboracion;
    private TextField campoFechaCaducidad;

    private Choice elegirUnidad;
    private Button botonRegistrar;
    private Button botonLimpiar;

    public RegistrarProductoComestible(List<Producto> productoComes) {
        this.productoComes = productoComes;

        setTitle("Ventana Registrar Producto Comestible");
        setSize(450, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        Panel panelCampos = new Panel(new GridLayout(8, 2, 5, 15));

        campoNombre = new TextField();
        campoId = new TextField();
        campoDescripcion = new TextField();
        campoPrecio = new TextField();
        campoPeso = new TextField();
        campoFechaElaboracion = new TextField();
        campoFechaCaducidad = new TextField();

        elegirUnidad = new Choice();
        //se imprimen las unidades de medida mostradas en enums
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
        panelCampos.add(new Label("Peso:"));
        panelCampos.add(campoPeso);
        panelCampos.add(new Label("Unidad de medida:"));
        panelCampos.add(elegirUnidad);
        panelCampos.add(new Label("Fecha de elaboracion (Año-Mes-Dia): "));
        panelCampos.add(campoFechaElaboracion);
        panelCampos.add(new Label("Fecha de caducidad (Año-Mes-Dia): "));
        panelCampos.add(campoFechaCaducidad);

        Panel panelBotones = new Panel(new FlowLayout());
        botonRegistrar = new Button("Registrar producto");
        panelBotones.add(botonRegistrar);
        botonLimpiar = new Button("Limpiar");
        panelBotones.add(botonLimpiar);

        add(new Label("REGISTRAR PRODUCTO COMESTIBLE", Label.CENTER), BorderLayout.NORTH);
        add(panelCampos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = campoNombre.getText().trim();
                String id = campoId.getText().trim();
                String descripcion = campoDescripcion.getText().trim();
                String precioTexto = campoPrecio.getText().trim();
                String pesoTexto = campoPeso.getText().trim();
                String fechaElaboracion = campoFechaElaboracion.getText().trim();
                String fechaCaducidad = campoFechaCaducidad.getText().trim();

                //validar que se ingresen numeros
                if (!precioTexto.matches("\\d+(\\.\\d+)?")) {
                    mostrarMensaje("Precio invalido. Solo se permiten números.");
                    return;
                }

                if (!pesoTexto.matches("\\d+(\\.\\d+)?")) {
                    mostrarMensaje("Peso invalido. Solo se permiten números");
                    return;
                }

                // Validar fechas
                if (!fechaElaboracion.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    mostrarMensaje("Fecha de elaboración invalida. Use el formato AÑo-Mes-Dia.");
                    return;
                }

                if (!fechaCaducidad.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    mostrarMensaje("Fecha de caducidad invalida. Use el formato AÑo-Mes-Dia.");
                    return;
                }

                double precio = Double.parseDouble(precioTexto);
                double peso = Double.parseDouble(pesoTexto);
                UnidadDeMedida unidad = UnidadDeMedida.valueOf(elegirUnidad.getSelectedItem());
                LocalDate fechaElab = LocalDate.parse(fechaElaboracion);
                LocalDate fechaCadu = LocalDate.parse(fechaCaducidad);

                ProductoComestible producto = new ProductoComestible(nombre, id, descripcion, precio, unidad, peso, fechaCadu, fechaElab);

                productoComes.add(producto);
                mostrarMensaje("Producto comestible registrado exitosamente");
            }
        });

        botonLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
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
        campoPeso.setText("");
        campoFechaElaboracion.setText("");
        campoFechaCaducidad.setText("");
        elegirUnidad.select(0);
    }

}
