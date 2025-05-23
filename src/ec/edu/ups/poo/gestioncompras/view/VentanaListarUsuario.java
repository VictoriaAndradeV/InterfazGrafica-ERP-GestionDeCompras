package ec.edu.ups.poo.gestioncompras.view;

import ec.edu.ups.poo.gestioncompras.models.Usuario;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class VentanaListarUsuario extends Frame {

    private List<Usuario> usuarios;
    private Panel panelUsuarios;

    public VentanaListarUsuario(List<Usuario> usuarios) {
        super("Listado de Usuarios");
        this.usuarios = usuarios;

        setLayout(new BorderLayout());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        //panel botón actualizar
        Panel panelSuperior = new Panel(new FlowLayout(FlowLayout.LEFT));
        Button btnActualizar = new Button("Actualizar");
        btnActualizar.addActionListener(e -> actualizarVista());
        panelSuperior.add(btnActualizar);
        add(panelSuperior, BorderLayout.NORTH);

        //panel usuarios
        panelUsuarios = new Panel();
        panelUsuarios.setLayout(new GridLayout(0, 2, 10, 10));
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.add(panelUsuarios);
        add(scrollPane, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        actualizarVista();

    }
    private void agregarCampo(Panel panel, String etiqueta, String valor) {
        panel.add(new Label(etiqueta));
        TextField campo = new TextField(valor);
        campo.setEditable(false);
        panel.add(campo);
    }
    private void actualizarVista() {
        panelUsuarios.removeAll();

        for (Usuario u : usuarios) {
            Panel panelUsuario = new Panel(new GridLayout(0, 1, 2, 2));
            panelUsuario.setPreferredSize(new Dimension(300, 200));

            agregarCampo(panelUsuario, "ID", u.getId());
            agregarCampo(panelUsuario, "Nombre", u.getNombre());
            agregarCampo(panelUsuario, "Apellido", u.getApellido());
            agregarCampo(panelUsuario, "Email", u.getEmail());
            agregarCampo(panelUsuario, "Teléfono", u.getTelefono());
            agregarCampo(panelUsuario, "Rol", u.getRol().toString());
            agregarCampo(panelUsuario, "Departamento", u.getDepartamento().getNombre());

            panelUsuarios.add(panelUsuario);
        }

        panelUsuarios.revalidate();
    }


}
