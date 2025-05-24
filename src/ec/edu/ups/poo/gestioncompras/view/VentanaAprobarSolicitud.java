package ec.edu.ups.poo.gestioncompras.view;

import ec.edu.ups.poo.gestioncompras.enums.Rol;
import ec.edu.ups.poo.gestioncompras.models.*;

import java.awt.Frame;
import java.awt.TextField;
import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class VentanaAprobarSolicitud extends Frame {

    private TextField textNumSolicitud, textNombreEvaluadoar, textCedulaEvaluador, textEstadoFinal;
    private boolean decisionTomada = false;
    private boolean aprobarDecision = false;

    private List<SolicitudDeCompra> solicitudes;
    private List<Usuario> usuarios;

    public VentanaAprobarSolicitud(List<SolicitudDeCompra> solicitudes, List<Usuario> usuarios) {
        super("Aprobar/Rechazar Solicitud");
        this.solicitudes = solicitudes;
        this.usuarios = usuarios;

        setLayout(new BorderLayout());
        setSize(600, 400);
        setLocationRelativeTo(null);


        Panel panelCentro = new Panel(new GridLayout(0, 2, 10, 10));

        panelCentro.add(new Label("Número de Solicitud"));
        textNumSolicitud = new TextField();
        panelCentro.add(textNumSolicitud);

        panelCentro.add(new Label("Nombre del Evaluador"));
        textNombreEvaluadoar = new TextField();
        panelCentro.add(textNombreEvaluadoar);

        panelCentro.add(new Label("Cédula del Evaluador"));
        textCedulaEvaluador = new TextField();
        panelCentro.add(textCedulaEvaluador);

        panelCentro.add(new Label("Estado Final de la Solicitud"));
        textEstadoFinal = new TextField();
        textEstadoFinal.setEditable(false);
        panelCentro.add(textEstadoFinal);

        add(panelCentro, BorderLayout.CENTER);

        //panel botones A/R
        Panel panelDecision = new Panel(new FlowLayout(FlowLayout.CENTER));
        Button btnAprobar = new Button("Aprobar");
        Button btnRechazar = new Button("Rechazar");

        btnAprobar.addActionListener(e -> {
            aprobarDecision = true;
            decisionTomada = true;
            textEstadoFinal.setText("Aprobar");
        });

        btnRechazar.addActionListener(e -> {
            aprobarDecision = false;
            decisionTomada = true;
            textEstadoFinal.setText("Rechazar");
        });

        panelDecision.add(new Label("Decisión:"));
        panelDecision.add(btnAprobar);
        panelDecision.add(btnRechazar);

        add(panelDecision, BorderLayout.NORTH);


        Panel panelBotones = new Panel(new FlowLayout(FlowLayout.RIGHT));
        Button botonConfirmar = new Button("Confirmar");
        Button botonCerrar = new Button("Cerrar");

        botonConfirmar.addActionListener(e -> ejecutarAccion());
        botonCerrar.addActionListener(e -> dispose());

        panelBotones.add(botonConfirmar);
        panelBotones.add(botonCerrar);

        add(panelBotones, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    private void ejecutarAccion() {
        if (!decisionTomada) {
            textEstadoFinal.setText("Seleccione aprobar o rechazar.");
            return;
        }

        String num = textNumSolicitud.getText().trim();
        String nombreEval = textNombreEvaluadoar.getText().trim();
        String cedulaEval = textCedulaEvaluador.getText().trim();

        SolicitudDeCompra encontrada = null;
        for (SolicitudDeCompra s : solicitudes) {
            if (s.getNumeroSolicitud().equalsIgnoreCase(num)) {
                encontrada = s;
                break;
            }
        }

        if (encontrada == null) {
            textEstadoFinal.setText("Solicitud no encontrada");
            return;
        }

        Usuario evaluador = null;
        for (Usuario u : usuarios) {
            if (u.getNombre().equalsIgnoreCase(nombreEval) && u.getId().equalsIgnoreCase(cedulaEval)) {
                evaluador = u;
                break;
            }
        }

        if (evaluador == null) {
            textEstadoFinal.setText("Evaluador no encontrado");
            return;
        }

        if (evaluador.getRol() != Rol.JEFE_DE_DEPARTAMENTO ||
                evaluador.getDepartamento() != encontrada.getUsuario().getDepartamento()) {
            textEstadoFinal.setText("Sin permiso");
            return;
        }

        encontrada.aprobarEstado(evaluador, aprobarDecision);
        textEstadoFinal.setText("Estado actual: " + encontrada.getEstado());
    }
}
