package ec.edu.ups.poo.gestioncompras.view;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import ec.edu.ups.poo.gestioncompras.models.Usuario;

public class VentanaListarUsuario extends Frame implements WindowListener {
    private TextArea areaUsuarios;
    private Button botonCerrar;

    public VentanaListarUsuario(List<Usuario> usuarios) {
        super("Lista de Usuarios");
        setLayout(new BorderLayout());

        // Área de texto para mostrar los usuarios
        areaUsuarios = new TextArea();
        areaUsuarios.setEditable(false);
        add(areaUsuarios, BorderLayout.CENTER);

        // Panel inferior con botón
        Panel panelBoton = new Panel(new FlowLayout(FlowLayout.RIGHT));
        botonCerrar = new Button("Cerrar");
        panelBoton.add(botonCerrar);
        add(panelBoton, BorderLayout.SOUTH);

        // Mostrar usuarios en el TextArea
        if (usuarios.isEmpty()) {
            areaUsuarios.setText("No existen usuarios registrados.");
        } else {
            StringBuilder texto = new StringBuilder();
            for (Usuario u : usuarios) {
                texto.append("- ").append(u.getNombre())
                        .append(" ").append(u.getApellido())
                        .append(" | ID: ").append(u.getId())
                        .append(" | Email: ").append(u.getEmail())
                        .append(" | Teléfono: ").append(u.getTelefono())
                        .append(" | Rol: ").append(u.getRol())
                        .append(" | Departamento: ").append(u.getDepartamento().getNombre())
                        .append("\n\n");
            }
            areaUsuarios.setText(texto.toString());
        }

        // Eventos
        botonCerrar.addActionListener(e -> dispose());
        addWindowListener(this);

        setSize(500, 400);
        setLocationRelativeTo(null); // Centrar ventana
        setVisible(true);
    }

    public void windowClosing(WindowEvent e) {
        dispose();
    }

    //metodos vacios de WL
    public void windowOpened(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
}
