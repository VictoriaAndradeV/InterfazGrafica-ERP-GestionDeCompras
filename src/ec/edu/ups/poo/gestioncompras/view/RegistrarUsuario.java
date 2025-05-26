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
import java.util.List;

//extends frame convierte la clase en una ventana
public class RegistrarUsuario extends Frame {

    private List<Usuario> usuarios; //lista donde se guardan los usuarios creados

    //TextField campos de texto, donde se registra la informacion ingresada por el usuario
    private TextField ingresoNombre;
    private TextField ingresoApellido;
    private TextField ingresoId;
    private TextField ingresoEmail;
    private TextField ingresoTelefono;
    private TextField ingresoIdDepartamento;
    private TextField ingresoNumeroEmpleados;

    //Choice nos ayuda a elegir unicamente entre 2 opciones
    //ayuda a un mejor registro
    private Choice elegirRol;
    private Choice elegirDepartamento;

    private Button actualizarRegistro;
    private Button limpiarCampos;
    private Button cerrarVentana;

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
        ingresoApellido = new TextField(); //no especificamos su tamaño
        ingresoId = new TextField();
        ingresoEmail = new TextField();
        ingresoTelefono = new TextField();
        ingresoIdDepartamento = new TextField();
        ingresoNumeroEmpleados = new TextField();

        //nos permite seleccionar entre varios departamentos
        elegirDepartamento = new Choice();
        elegirDepartamento.add("GERENCIA");
        elegirDepartamento.add("COMPRAS");
        elegirDepartamento.add("LOGISTICA");
        elegirDepartamento.add("SISTEMAS");

        elegirRol = new Choice();
        elegirRol.add("SUPERVISOR");
        elegirRol.add("JEFE_DE_DEPARTAMENTO");

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
        panelUsuarios.add(elegirDepartamento);
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
        cerrarVentana = new Button("Cerrar");
        panelBotones.add(cerrarVentana);

        add(new Label("REGISTRAR NUEVO USUARIO", Label.CENTER), BorderLayout.NORTH);
        add(panelUsuarios, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        //Cuando se haga click en Guardar usuario
        actualizarRegistro.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //obtiene el texto que el usuario ingreso, elimina espacios en blancos
                String nombre = ingresoNombre.getText().trim();
                String apellido = ingresoApellido.getText().trim();
                String id = ingresoId.getText().trim();
                String email = ingresoEmail.getText().trim();
                String telefono = ingresoTelefono.getText().trim();
                String nombreDep = elegirDepartamento.getSelectedItem(); //obtiene lo seleccionado
                                                                         //en el componente choice
                String idDep = ingresoIdDepartamento.getText().trim();
                String numEmpTexto = ingresoNumeroEmpleados.getText().trim();

                if (!numEmpTexto.matches("\\d+")) {
                    mostrarMensaje("Numero de empleaods invalido. Ingese unicamente numeros");
                    return;
                }

                int numEmp = Integer.parseInt(numEmpTexto); //convierte de numero a texto
                Rol rol = Rol.valueOf(elegirRol.getSelectedItem());
                //convierte lo seleccionado en choice a un valor de enum

                Departamento dpto = new Departamento(nombreDep, idDep, numEmp);
                Usuario usuario = new Usuario(nombre, apellido, id, email, telefono, dpto, rol);

                usuarios.add(usuario); //agrega el usuario creado, a la lista de usuarios que fue
                mostrarMensaje("Usuario registrado exitosamente"); //pasada como parametro
            }
        });

        //cuando el mouse hace clic en limpiar campos
        limpiarCampos.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        //solo si se hace clic en cerrar ventana, esta se cerrara, no dando clic a la X
        cerrarVentana.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    //metodo para mostrar mensajes en una ventana a parte de la que estamos
    private void mostrarMensaje(String mensaje) {
        //creamos una ventana
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
        elegirDepartamento.select(0);
        ingresoIdDepartamento.setText("");
        ingresoNumeroEmpleados.setText("");
        elegirRol.select(0);
    }
}


