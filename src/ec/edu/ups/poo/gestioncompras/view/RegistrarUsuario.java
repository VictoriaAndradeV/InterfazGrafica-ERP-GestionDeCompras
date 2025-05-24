package ec.edu.ups.poo.gestioncompras.view;
import ec.edu.ups.poo.gestioncompras.enums.Rol;
import ec.edu.ups.poo.gestioncompras.models.Usuario;
import ec.edu.ups.poo.gestioncompras.models.Departamento;

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

//extends frame convierte la clase en una ventana
public class RegistrarUsuario extends Frame {
    //atributos
    private List<Usuario> usuarios; //lista donde se guardan los usuarios creados

    //TextField campos de texto, donde se registra la informacion de los usuarios
    private TextField ingresoNombre;
    private TextField ingresoApellido;
    private TextField ingresoId;
    private TextField ingresoEmail;
    private TextField ingresoTelefono;

    private TextField ingresoDepartamento;
    private TextField ingresoIdDepartamento;
    private TextField ingresoNumeroEmpleados;

    //Choice nos ayuda a elegir unicamente entre 2 opciones
    //ayuda a un mejor registro
    private Choice elegirRol;

    private Button actualizarRegistro;
    private Button limpiarCampos;

    //constructor que recibe como parametros una lista de usuarios y la guarda para poder agregar nuevos
    public RegistrarUsuario(List<Usuario> usuarios) {
        this.usuarios = usuarios;

        //Configurar nuestra ventana de registrar usuarios
        setTitle("Ventana Registrar Usuario");
        setSize(500, 500);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        //panel en donde se registran los campos para ingresar los datos del usuario
        Panel panelUsuarios = new Panel(new GridLayout(10, 2, 10, 10));
        ingresoNombre = new TextField(); //campo vacio para poder ingresar una cadena de texto
        ingresoApellido = new TextField(); //no se necesita especificar su tamaño
        ingresoId = new TextField();
        ingresoEmail = new TextField();
        ingresoTelefono = new TextField();
        ingresoDepartamento = new TextField();
        ingresoIdDepartamento = new TextField();
        ingresoNumeroEmpleados = new TextField();

        elegirRol = new Choice();
        elegirRol.add("SUPERVISOR");
        elegirRol.add("JEFE_DE_DEPARTAMENTO");

        //se agrega al panel de usuarios,
        panelUsuarios.add(new Label("Nombre: "));
        panelUsuarios.add(ingresoNombre);
        panelUsuarios.add(new Label("Apellido: "));
        panelUsuarios.add(ingresoApellido);
        panelUsuarios.add(new Label("Id: "));
        panelUsuarios.add(ingresoId);
        panelUsuarios.add(new Label("Email: "));
        panelUsuarios.add(ingresoEmail);
        panelUsuarios.add(new Label("Telefono: "));
        panelUsuarios.add(ingresoTelefono);
        panelUsuarios.add(new Label("Departamento: "));
        panelUsuarios.add(ingresoDepartamento);
        panelUsuarios.add(new Label("ID departamento: "));
        panelUsuarios.add(ingresoIdDepartamento);
        panelUsuarios.add(new Label("Numero de empleados: "));
        panelUsuarios.add(ingresoNumeroEmpleados);
        panelUsuarios.add(new Label("Rol: "));
        panelUsuarios.add(elegirRol);

        Panel panelBotones = new Panel(new FlowLayout());
        actualizarRegistro = new Button("Guardar usuario");
        panelBotones.add(actualizarRegistro);
        limpiarCampos = new Button("Limpiar campos");
        panelBotones.add(limpiarCampos);

        add(new Label("REGISTRAR NUEVO USUARIO", Label.CENTER), BorderLayout.NORTH);
        add(panelUsuarios, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        //Cuando se haga click en guardar
        actualizarRegistro.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = ingresoNombre.getText().trim();
                String apellido = ingresoApellido.getText().trim();
                String id = ingresoId.getText().trim();
                String email = ingresoEmail.getText().trim();
                String telefono = ingresoTelefono.getText().trim();
                String nombreDep = ingresoDepartamento.getText().trim();
                String idDep = ingresoIdDepartamento.getText().trim();
                String numEmpTexto = ingresoNumeroEmpleados.getText().trim();

                if (!numEmpTexto.matches("\\d+")) {
                    mostrarMensaje("Numero de empleaods invalido. Ingese unicamente numeros");
                    return;
                }

                int numEmp = Integer.parseInt(numEmpTexto);
                Rol rol = Rol.valueOf(elegirRol.getSelectedItem());

                Departamento dpto = new Departamento(nombreDep, idDep, numEmp);
                Usuario usuario = new Usuario(nombre, apellido, id, email, telefono, dpto, rol);

                usuarios.add(usuario);
                mostrarMensaje("Usuario registrado exitosamente");
            }
        });

        limpiarCampos.addActionListener( new ActionListener() {
            @Override
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
        Frame mensajeVentana = new Frame("Mensaje");
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

    //se limpian los campos para poder registrar otro usuario ese instante
    public void limpiarCampos() {
        ingresoNombre.setText("");
        ingresoApellido.setText("");
        ingresoId.setText("");
        ingresoEmail.setText("");
        ingresoTelefono.setText("");
        ingresoDepartamento.setText("");
        ingresoIdDepartamento.setText("");
        ingresoNumeroEmpleados.setText("");
        elegirRol.select(0);
    }
}


