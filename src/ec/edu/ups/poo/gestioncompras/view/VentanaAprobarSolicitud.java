package ec.edu.ups.poo.gestioncompras.view;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import ec.edu.ups.poo.gestioncompras.enums.Rol;
import ec.edu.ups.poo.gestioncompras.models.*;

public class VentanaAprobarSolicitud extends Frame implements WindowListener {

    private TextField textNumSolicitud, textNombreEval, textCedulaEval, textDecision, texttEstadoFinal;
    private Button botonBuscar, botonCerrar, botonAprobar;
    private List<SolicitudDeCompra> solicitudes;
    private List<Usuario> usuarios;

    public VentanaAprobarSolicitud(List<SolicitudDeCompra> solicitudes, List<Usuario> usuarios) {
        super("Aprobar/Rechazar solicitud");
        this.solicitudes = solicitudes;
        this.usuarios = usuarios;

        setLayout(new BorderLayout());

        Label titulo = new Label("Aprobar/Rechazar solicitud", Label.CENTER);
        add(titulo, BorderLayout.NORTH);

        Panel centro = new Panel(new GridLayout(6, 2, 10, 5));

        // Entradas
        centro.add(new Label("Ingrese el num. de solicitud:"));
        textNumSolicitud = new TextField();
        centro.add(textNumSolicitud);

        centro.add(new Label("Desea aprobar la solicitud? (true/false):"));
        textDecision = new TextField();
        centro.add(textDecision);

        centro.add(new Label("Solicitud Encontrada")); centro.add(new Label(""));

        centro.add(new Label("Ingrese el nombre del evaluador:"));
        textNombreEval = new TextField();
        centro.add(textNombreEval);

        centro.add(new Label("Ingrese la Cédula:"));
        textCedulaEval = new TextField();
        centro.add(textCedulaEval);

        centro.add(new Label("Estado de la solicitud:"));
        texttEstadoFinal = new TextField();
        texttEstadoFinal.setEditable(false);
        centro.add(texttEstadoFinal);

        add(centro, BorderLayout.CENTER);

        Panel abajo = new Panel(new FlowLayout(FlowLayout.RIGHT));
        botonAprobar = new Button("OK");
        botonAprobar = new Button("Cerrar");
        abajo.add(botonAprobar);
        abajo.add(botonAprobar);
        add(abajo, BorderLayout.SOUTH);

        // Eventos
        botonAprobar.addActionListener(e -> ejecutarAccion());
        botonCerrar.addActionListener(e -> dispose());
        addWindowListener(this);

        setSize(600, 350);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void ejecutarAccion() {
        String num = textNumSolicitud.getText().trim();//trim quita los espacios
        String decision = textDecision.getText().trim().toLowerCase();
        String nombreEval = textNombreEval.getText().trim();
        String cedulaEval = textCedulaEval.getText().trim();

        SolicitudDeCompra encontrada = null;//solicitud encontrada
        for (SolicitudDeCompra s : solicitudes) {
            if (s.getNumeroSolicitud().equalsIgnoreCase(num)) {
                encontrada = s;
                break;
            }
        }

        if (encontrada == null) { //o no
            texttEstadoFinal.setText("No encontrada");
            return;
        }

        Usuario evaluador = null; //existe el usuario
        for (Usuario u : usuarios) {
            if (u.getNombre().equalsIgnoreCase(nombreEval) &&
                    u.getId().equalsIgnoreCase(cedulaEval)) {
                evaluador = u;
                break;
            }
        }

        if (evaluador == null) {
            texttEstadoFinal.setText("Evaluador no encontrado");
            return;
        }

        if (evaluador.getRol() != Rol.JEFE_DE_DEPARTAMENTO || //evaluamos rol
                evaluador.getDepartamento() != encontrada.getUsuario().getDepartamento()) {
            texttEstadoFinal.setText("No tiene el permiso");
            return;
        }

        boolean aprobar = decision.equals("true"); //muestra estado
        encontrada.aprobarEstado(evaluador, aprobar);
        texttEstadoFinal.setText(encontrada.getEstado().toString());
    }

    public void windowClosing(WindowEvent e) {
        dispose(); }
    public void windowOpened(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
}
