import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import ec.edu.ups.poo.gestioncompras.enums.Estado;
import ec.edu.ups.poo.gestioncompras.models.*;
import ec.edu.ups.poo.gestioncompras.view.ShowConsole;
import ec.edu.ups.poo.gestioncompras.controller.BusquedaBinaria;
import java.util.GregorianCalendar;
import ec.edu.ups.poo.gestioncompras.enums.Rol;
import ec.edu.ups.poo.gestioncompras.enums.UnidadDeMedida;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Proveedor> proveedores = new ArrayList<>();
    static List<SolicitudDeCompra> solicitudes = new ArrayList<>();
    static List<Usuario> usuarios = new ArrayList<>();
    static int contadorDeSolicitudes = 1;
    static List<Producto> productos = new ArrayList<>();
    static ShowConsole showConsole = new ShowConsole(productos);
    static BusquedaBinaria binaria = new BusquedaBinaria();

    public static void main(String[] args) {

        //proveedores
        proveedores.add(new Proveedor("Proveedor Uno", "ApellidoUno", "P001", "proveedor1@mail.com", "1234567890", "RUC001", "Av. 10 de Agosto"));

        proveedores.add(new Proveedor("Proveedor Dos", "ApellidoDos", "P002", "proveedor2@mail.com", "0987654321", "RUC002", "Av. Solano"));

        //usuarios
        Departamento logistica = new Departamento("Logística", "D001", 8);

        usuarios.add(new Usuario("Carlos", "Abad", "0102030405", "carlos@mail.com", "0999999999", logistica, Rol.JEFE_DE_DEPARTAMENTO));

        usuarios.add(new Usuario("Luis", "Torres", "1112131415", "luis@mail.com", "0777777777", logistica, Rol.SUPERVISOR));

        //productos
        productos.add(new ProductoComestible("C001", "Manzanas", "Frutas frescas", 1.5, UnidadDeMedida.KILOGRAMOS, 10.0, LocalDate.of(2025, 5, 20), LocalDate.of(2025, 4, 10)));

        productos.add(new ProductoLimpieza("L001", "Detergente", "Detergente líquido", 3.0, UnidadDeMedida.LITRO,2.5));

        productos.add(new ProductoTecnologico("T001", "Laptop", "Laptop gama media", 600.0, UnidadDeMedida.MES, 24));

        ShowConsole showConsole = new ShowConsole(productos);

        boolean continuar = true;

        while (continuar) {
            int opcion = showConsole.showMenu();
            switch (opcion) {
                case 1: // agrega usuario a la lista de usuarios
                    Usuario usuario = showConsole.registrarUsuario();
                    usuarios.add(usuario);
                    break;
                case 2: //Al ingresar un proveedor, se agrega a la lista de proveedores
                    Proveedor proveedor = showConsole.registrarProveedor();
                    proveedores.add(proveedor);
                    break;
                case 3:
                    showConsole.registrarProducto();
                    break;
                case 4:
                    registrarSolicitudCompra();
                    break;
                case 5: //Muestra los usuarios ingresados
                    if (usuarios.isEmpty()) {
                        System.out.println("No existen usuarios registrados");
                    } else {
                        for (Usuario u : usuarios) {
                            System.out.println(u);
                        }
                    }
                    break;
                case 6: //Muestra la lista de proveedores
                    if (proveedores.isEmpty()) {
                        System.out.println("No existen proveedores registrados");
                    } else {
                        for (Proveedor pro : proveedores) {
                            System.out.println(pro);
                        }
                    }
                    break;
                case 7:
                    if (productos.isEmpty()) {
                        System.out.println("No existen productos registrados");
                    } else {
                        for (Producto producto : productos) {
                            System.out.println(producto);
                        }
                    }
                    break;
                case 8:
                    listarSolicitudes();
                    break;
                case 9:
                    buscarProveedorID();
                    break;
                case 10:
                    buscarProductoNombre();
                    break;
                case 11:
                    buscarSolicitudID();
                    break;
                case 12:
                    aprobarRechazarSolicitud();
                    break;
                case 13:
                    calcularTotalSolicitud();
                    break;
                case 14:
                    continuar = false;
                    System.out.println("Saliendo del sistema...");
                    break;
            }
        }
    }

    public static void listarSolicitudes() {
        if (solicitudes.isEmpty()) {
            System.out.println("No existen solicitudes registradas.");
        } else {
            for (SolicitudDeCompra solicitud : solicitudes) {
                mostrarSolicitudBien(solicitud);
                System.out.println("\n");
            }
        }
    }
    public static void buscarProveedorID(){
        if (proveedores.isEmpty()) {
            System.out.println("No existen proveedores registrados");
        } else {
            String idBuscado = showConsole.iputIDProveedor();
            int index = binaria.buscarProveedorID(proveedores, idBuscado);

            if (index == 0) {
                System.out.println("Proveedor encontrado");
                System.out.println(proveedores.get(index));
            } else {
                System.out.println("Proveedor no registrado");
            }
        }
    }

    public static void buscarProductoNombre(){
        if (productos.isEmpty()) {
            System.out.println("No existen productos registrados");
        } else {
            String nombreBuscado = showConsole.iputNombreProducto();
            int index = binaria.buscarProductoNombre(productos, nombreBuscado);

            if (index == 0) {
                System.out.println("Producto encontrado");
                System.out.println(productos.get(index));
            } else {
                System.out.println("Producto no registrado");
            }
        }
    }

    public static void buscarSolicitudID(){
        if (solicitudes.isEmpty()) {
            System.out.println("No existen solicitudes de compra");
        } else {
            String idBuscado = showConsole.iputIDSolicitud();
            int index = binaria.buscarSolicitudID(solicitudes, idBuscado);

            if (index >= 0) {
                System.out.println("Solicitud encontrada");
                SolicitudDeCompra solicitudEncontrada = solicitudes.get(index);
                mostrarSolicitudBien(solicitudEncontrada);
            } else {
                System.out.println("Solicitud no registrada");
            }
        }
    }
    public static void mostrarSolicitudBien(SolicitudDeCompra solicitud) {
        ShowConsole showConsole = new ShowConsole(productos);

        System.out.println("\n======= Detalles de la Solicitud =======");
        System.out.println("Número de Solicitud: " + solicitud.getNumeroSolicitud());
        System.out.println("Estado: " + solicitud.getEstado());
        System.out.println("Fecha de Solicitud: " + showConsole.formatearFecha(solicitud.getFechaSolicitud()));

        System.out.println("\tSolicitante:");
        System.out.println("Nombre: " + solicitud.getUsuario().getNombre() + " " + solicitud.getUsuario().getApellido());
        System.out.println("Departamento: " + solicitud.getUsuario().getDepartamento().getNombre());
        System.out.println("Rol: " + solicitud.getUsuario().getRol());

        System.out.println("\tProductos solicitados:");
        for (DetalleSolicitud detalle : solicitud.getDetalleSolicitud()) {
            System.out.println("- Producto: " + detalle.getProducto().getNombre());
            System.out.println("  Cantidad: " + detalle.getCantidad());
            System.out.println("  Subtotal: $" + detalle.calcularTotal());
        }

        System.out.println("\nTotal de la solicitud: $" + solicitud.calcularTotal());
    }

    public static void registrarSolicitudCompra() {
        ShowConsole showConsole = new ShowConsole(productos);
        String nombreSolicitante = showConsole.pedirNombreSolicitante();
        Usuario usuario = null;

        for (Usuario u : usuarios) {
            if (u.getNombre().equalsIgnoreCase(nombreSolicitante)) {
                usuario = u;
                break;
            }
        }

        if (usuario == null) {
            System.out.println("No existen usuarios registrados");
            return;
        }

        // Crear un nuevo número de solicitud
        String numeroSolicitud = "SC" + String.format("%03d", contadorDeSolicitudes);
        contadorDeSolicitudes++;

        // Crear nueva solicitud
        GregorianCalendar fecha = new GregorianCalendar();
        usuario.addSolicitud(fecha, Estado.SOLICITADA, numeroSolicitud);
        SolicitudDeCompra nuevaSolicitud = usuario.getSolicitud();

        // Agregar productos
        boolean seguirAgregando = true;
        while (seguirAgregando) {
            System.out.println("Ingrese el nombre del producto que desea agregar:");
            String nombreProducto = scanner.nextLine();

            Producto productoSeleccionado = null;
            for (Producto p : productos) {
                if (p.getNombre().equalsIgnoreCase(nombreProducto)) {
                    productoSeleccionado = p;
                    break;
                }
            }

            if (productoSeleccionado == null) {
                System.out.println("No se encontró el producto");
            } else {
                int cantidad = showConsole.pedirCantidadProducto();

                // Llamar al metodo correcto
                nuevaSolicitud.agregarDetalle(productoSeleccionado, cantidad, "Justificación no disponible");
                System.out.println("Producto agregado correctamente a la solicitud.");
            }

            seguirAgregando = showConsole.deseaAgregarProducto();
        }

        solicitudes.add(nuevaSolicitud);
        System.out.println("Solicitud registrada con éxito. número de solicitud: " + numeroSolicitud);
    }


    public static void calcularTotalSolicitud() {
        ShowConsole showConsole = new ShowConsole(productos);
        SolicitudDeCompra solicitudEncontrada = null;
        String numero = showConsole.pedirNumeroSolicitudCalcular();

        SolicitudDeCompra soicitudEcontrada= null;
        for(SolicitudDeCompra s : solicitudes) {
            if (s.getNumeroSolicitud().equalsIgnoreCase(numero)) {
                soicitudEcontrada = s;
                break;
            }
        }

        if (soicitudEcontrada == null) {
            showConsole.mostrarSolicitudNoEncontrada();
            return;
        }

        double total = solicitudEncontrada.calcularTotal();
        showConsole.mostrarSolicitud(total);
    }

    public static void aprobarRechazarSolicitud() {
        ShowConsole showConsole = new ShowConsole(productos);
        String numero = showConsole.pedirNumeroSolicitud(); //pedimos numero de solicitud

        SolicitudDeCompra solicitudEncontrada = null;
        for (SolicitudDeCompra solicitud : solicitudes) {
            if (solicitud.getNumeroSolicitud().equalsIgnoreCase(numero)) {
                solicitudEncontrada = solicitud;
                break;
            }
        }

        if (solicitudEncontrada == null) {
            showConsole.mostrarSolicitudNoEncontrada();
            return;
        }

        System.out.println("Ingrese los datos del jefe evaluador:");
        String nombreEvaluador = scanner.nextLine();
        System.out.println("Ingrese el ID del jefe evaluador:");
        String idEvaluador = scanner.nextLine();
        Usuario evaluador = null;

        for (Usuario u : usuarios) {
            if (u.getNombre().equalsIgnoreCase(nombreEvaluador)) {
                evaluador = u;
                break;
            }
        }

        if (evaluador == null) {
            System.out.println("No se encontró el jefe evaluador");
            return;
        }
        boolean aprobar = showConsole.pedirDecisionAprobacion(); //se aprueba o no

        solicitudEncontrada.aprobarEstado(evaluador, aprobar); //se llama al metodo de SolicitudDeCompra
    }
}