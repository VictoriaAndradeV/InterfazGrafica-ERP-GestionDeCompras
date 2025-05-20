package ec.edu.ups.poo.gestioncompras.view;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import ec.edu.ups.poo.gestioncompras.models.Contacto;
import ec.edu.ups.poo.gestioncompras.models.Departamento;
import ec.edu.ups.poo.gestioncompras.models.Proveedor;
import ec.edu.ups.poo.gestioncompras.models.Usuario;
import ec.edu.ups.poo.gestioncompras.enums.UnidadDeMedida;
import ec.edu.ups.poo.gestioncompras.models.ProductoComestible;
import ec.edu.ups.poo.gestioncompras.models.ProductoLimpieza;
import ec.edu.ups.poo.gestioncompras.models.ProductoTecnologico;
import ec.edu.ups.poo.gestioncompras.models.Producto;
import ec.edu.ups.poo.gestioncompras.enums.Rol;

public class ShowConsole {

    private Scanner scanner;
    private List<Producto> productos;
    public ShowConsole(List<Producto> productos) {
        this.scanner = new Scanner(System.in);
        this.productos = productos;
    }

    //Menú de opciones, retorna un valor tipo int, al seleccionar una opción
    public int showMenu(){
        System.out.println("===== SISTEMA DE GESTIÓN DE COMPRAS ERP =====");
        System.out.println("1. Registrar usuario");
        System.out.println("2. Registrar proveedor");
        System.out.println("3. Registrar producto");
        System.out.println("4. Registrar solicitud de compra");
        System.out.println("5. Listar usuarios");
        System.out.println("6. Listar proveedores");
        System.out.println("7. Listar productos");
        System.out.println("8. Listar solicitudes de compra");
        System.out.println("9. Buscar proveedor por ID");
        System.out.println("10. Buscar producto por nombre");
        System.out.println("11. Buscar solicitud por ID");
        System.out.println("12. Aprobar / Rechazar solicitud de compra");
        System.out.println("13. Calcular total de una solicitud");
        System.out.println("14. Salir");
        int opcion = validarOpcion(scanner, 1, 14); //metodo que valida la entrada de numeros en el rango 1-14
        return opcion;                                     //valida el ingreso de numeros, no permite ingresar letras o caracteres especiales
    }


    //al ser clase padre, de proovedor y usuario, en registrar contacto se
    //agregan los atributos que tienen en comun cada clase hija
    public Contacto registrarContacto(){
        System.out.println("--------- Registrar Contacto --------- ");
        System.out.println("Nombre");
        String nombre = validarIngresoLetras(scanner);
        System.out.println("Apellido");
        String apellido = validarIngresoLetras(scanner);
        System.out.println("ID");
        String id = scanner.nextLine();
        System.out.println("Email -->");
        String email = scanner.nextLine();
        System.out.println("Telefono");
        String telefono = validarIngresoTelefono(scanner);
        return new Contacto(nombre, apellido, id, email, telefono);
    }

    public Usuario registrarUsuario(){

        Contacto contacto = registrarContacto();
        System.out.println("--------- Registrar Usuario --------- ");
        System.out.println("Rol");
        Rol roles = ingresoRol(scanner);
        System.out.println("Departamento: ");
        Departamento departamento = ingresoDepartamento(scanner);
        return new Usuario(
                contacto.getNombre(),
                contacto.getApellido(),
                contacto.getId(),
                contacto.getEmail(),
                contacto.getTelefono(),
                departamento,
                roles
        );
    }

    public Proveedor registrarProveedor(){
        Contacto contacto = registrarContacto();
        System.out.println("--------- Registrar Proveedor --------- ");
        System.out.println("Ruc: ");
        String ruc = validarRuc(scanner);
        System.out.println("Dirección");
        String direccion = validarIngresoLetras(scanner);
        return new Proveedor(
                contacto.getNombre(),
                contacto.getApellido(),
                contacto.getId(),
                contacto.getEmail(),
                contacto.getTelefono(),
                ruc,
                direccion
        );
    }


    public Departamento ingresoDepartamento(Scanner scanner){
        System.out.println("--------- Ingresar Departamento --------- ");
        System.out.println("Nombre");
        String nombre = validarIngresoLetras(scanner);
        System.out.println("ID");
        String id = scanner.nextLine();
        System.out.println("Número de empleados: ");
        int empleados = validarIngresoNumeros(scanner);
        return new Departamento(nombre, id, empleados);
    }

    public Rol ingresoRol(Scanner scanner){
        System.out.println("Roles disponibles:");

        //imprime los roles ingresados en ec.edu.ups.gestioncompras.enums.Rol
        for (Rol rol : Rol.values()) {
            System.out.println("- " + rol.name());
        }

        Rol rolAgregado = null;//se guarda el rol que el usuario elija

        while (rolAgregado == null) {
            System.out.println("Ingrese el rol: ");
            String ingreso = scanner.nextLine().toUpperCase();

            for (Rol rol : Rol.values()) {
                if (rol.name().equals(ingreso)) { //si el rol ingresado, coincide con los existentes, se retorna el rol agregado
                    rolAgregado = rol;
                    break;
                }
            }
            if (rolAgregado == null) {
                System.out.println("Rol inválido. Intente nuevamente.");
            }
        }
        return rolAgregado;
    }
    //Se ingresa el ID del proveedor que se desea buscar
    public String iputIDProveedor(){
        System.out.println("Ingrese el ID del proveedor que desea buscar ->");
        String id = scanner.nextLine();
        return id;
    }

    public String iputIDSolicitud(){
        System.out.println("Ingrese el ID de la Solicitud de Compra que desea buscar ->");
        String id = scanner.nextLine();
        return id;
    }

    public String iputNombreProducto(){
        System.out.println("Ingrese el nombre del producto que desea buscar -> ");
        String nombre = scanner.nextLine();
        return nombre.toLowerCase();
    }

    public int validarOpcion(Scanner scanner, int min, int max){
        int num;

        do {
            System.out.println("Ingrese una opción: ");

            if(scanner.hasNextInt()){ //validar ingreso de numeros
                num = scanner.nextInt();
                scanner.nextLine(); //limpia el buffer de entrada del Scanner

                if((num < min) || (num > max)){ //condición cuando se ingresa un valor fuera de los mostrados en el menú
                    System.out.println("Ingrese una opción válida desde -> " +min+" hasta " +max);
                }else{
                    return num;
                }
            } else{
                System.out.println("Ingrese únicamente números, NO letras NI caracteres especiales");
                scanner.nextLine(); //limpiar token invalido ingresado, para evitar que scanner se trabe
            }
        } while (true); //se cumple infinitamente hasta que se ingrese una opcion valida

    }

    public String validarIngresoLetras(Scanner scanner){
        String ingreso;

        do {
            System.out.println("--> ");
            ingreso = scanner.nextLine().trim().toLowerCase(); //acepta el ingreso de un string con espacios

            if(ingreso.matches("[a-zA-ZáéíóúÁÉÍÓÚ\\s]+")){ //valida el ingreso de un string valido, con letras de el alfabeto
                return ingreso;                                 //no valida ingreso de ñ
            } else {
                System.out.println("Ingrese unicamente letras, no numeros, no caracteres especiales");
            }
        } while (true);//se cumple infinitamente hasta que se ingrese una cadena de caracteres válida
    }

    public String validarIngresoTelefono(Scanner scanner){
        String numeros;

        do {
            System.out.println("--> ");

            if(scanner.hasNextLine()){ //valida el ingreso de numeros
                numeros = scanner.nextLine();

                if (numeros.matches("\\d{7,10}")) {
                    return numeros;
                } else {
                    System.out.println("Número inválido. Ingrese solo números, de 7 a 10 dígitos.");
                }
            } else{
                System.out.println("Ingrese unicamente números");
                scanner.nextLine(); //limpiar token invalido ingresado, para evitar que scanner se trabe
            }
        } while (true);
    }
    //validar el ingreso de numero de empleados
    public int validarIngresoNumeros(Scanner scanner){

        do {
            System.out.println("--> ");

            if(scanner.hasNextInt()){ //valida el ingreso de numeros
                int num = scanner.nextInt();

                if((num <= 0) || (num > 10)){ //ingresa al if si la edad ingresada esta fuera del rango
                    System.out.println("El departamento puede tener de 1 - 10 empleados. Ingrese nuevamente");
                    scanner.nextLine();
                }else{
                    return num;
                }
            } else{
                System.out.println("Ingrese unicamente numeros, no letras ni caracteres especiales");
                scanner.nextLine(); //limpiar token invalido ingresado, para evitar que scanner se trabe
            }
        } while (true);

    }

    public String validarRuc(Scanner scanner) {
        String ruc;
        do {
            System.out.println("--> ");
            ruc = scanner.nextLine().trim();

            if (ruc.matches("[0-9]+")) { // valida que el ruc ingresado sean solo numeros
                return ruc;
            } else {
                System.out.println("Ingrese únicamente números, no letras ni caracteres especiales.");
            }
        } while (true);
    }

    public String pedirNumeroSolicitud() {
        System.out.print("Ingrese el número de solicitud: ");
        return scanner.nextLine();
    }

    public boolean pedirDecisionAprobacion() {
        System.out.print("¿Desea aprobar la solicitud? (true/false): ");
        return Boolean.parseBoolean(scanner.nextLine());
    }

    public String pedirNumeroSolicitudCalcular() {
        System.out.print("Ingrese el numero de solicitud para calcular el total: ");
        return scanner.nextLine();
    }

    public void mostrarSolicitud(double totalSolicitud) {
        System.out.println("El total de la solicitu es: $"+ totalSolicitud);

    }

    public void mostrarSolicitudNoEncontrada() {
        System.out.println("No se encontro ninguna solicitud con ese numero");
    }

    //4
    public String pedirNombreSolicitante() {
        System.out.print("Ingrese el nombre del solicitante: ");
        return scanner.nextLine();
    }

    public boolean deseaAgregarProducto() {
        System.out.print("¿Desea agregar un producto a la solicitud? (true/false): ");
        return Boolean.parseBoolean(scanner.nextLine());
    }

    public int pedirCantidadProducto() {
        System.out.print("Ingrese la cantidad del producto: ");
        return Integer.parseInt(scanner.nextLine());
    }

    //REGISTRAR PRODUCTO
    public void registrarProducto() {
        System.out.println("Seleccione el tipo de producto:");
        System.out.println("1. Producto Comestible");
        System.out.println("2. Producto de Limpieza");
        System.out.println("3. Producto Tecnológico");

        int tipo = validarOpcion(scanner, 1, 3);

        // Datos generales
        System.out.println("Ingrese el ID del producto:");
        String id = scanner.nextLine();

        System.out.println("Ingrese el nombre del producto:");
        String nombre = validarIngresoLetras(scanner);

        System.out.println("Ingrese la descripción del producto:");
        String descripcion = scanner.nextLine();

        System.out.println("Ingrese el precio unitario del producto:");
        double precioUnitario = Double.parseDouble(scanner.nextLine());

        // Selección de unidad de medida
        System.out.println("Seleccione la unidad de medida:");
        for (UnidadDeMedida unidad : UnidadDeMedida.values()) {
            System.out.println("- " + unidad);
        }

        UnidadDeMedida unidad = null;
        while (unidad == null) {
            System.out.println("Ingrese la unidad de medida:");
            String unidadInput = scanner.nextLine().toUpperCase();
            try {
                unidad = UnidadDeMedida.valueOf(unidadInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Unidad inválida. Intente nuevamente.");
            }
        }

        Producto nuevoProducto = null;

        switch (tipo) {
            case 1:
                System.out.println("Ingrese el peso en kg o gramos:");
                double peso = Double.parseDouble(scanner.nextLine());

                System.out.println("Ingrese la fecha de caducidad (AAAA-MM-DD):");
                LocalDate fechaCaducidad = LocalDate.parse(scanner.nextLine());

                System.out.println("Ingrese la fecha de elaboración (AAAA-MM-DD):");
                LocalDate fechaElaboracion = LocalDate.parse(scanner.nextLine());

                nuevoProducto = new ProductoComestible(id, nombre, descripcion, precioUnitario, unidad, peso, fechaCaducidad, fechaElaboracion);
                break;

            case 2:
                System.out.println("Ingrese el volumen en litros:");
                double volumen = Double.parseDouble(scanner.nextLine());

                nuevoProducto = new ProductoLimpieza(id, nombre, descripcion, precioUnitario, unidad, volumen);
                break;

            case 3:
                System.out.println("Ingrese la garantía en meses:");
                int garantiaMeses = Integer.parseInt(scanner.nextLine());

                nuevoProducto = new ProductoTecnologico(id, nombre, descripcion, precioUnitario, unidad, garantiaMeses);
                break;
        }

        productos.add(nuevoProducto);
        System.out.println("Producto registrado correctamente.");
    }


/*
    public void registrarProducto() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione el tipo de producto:");
        System.out.println("1. Producto Comestible");
        System.out.println("2. Producto de Limpieza");
        System.out.println("3. Producto Tecnológico");
        System.out.println("4. Salir");
        int tipo = Integer.parseInt(scanner.nextLine());

        //datos generales
        System.out.println("Ingrese el ID del producto:");
        String id = scanner.nextLine();

        System.out.println("Ingrese el nombre del producto:");
        String nombre = validarIngresoLetras(scanner);

        System.out.println("Ingrese la descripción del producto:");
        String descripcion = scanner.nextLine();

        System.out.println("Ingrese el por unidad de medida del producto:");
        double precioUnitario = Double.parseDouble(scanner.nextLine());

        //opciones de unidad de medida
        System.out.println("Seleccione la unidad de medida:");
        for (UnidadDeMedida unidad : UnidadDeMedida.values()) { //se recorre enums
            System.out.println("- " + unidad);                  // y se imprime
        }
        String unidadIngresada = scanner.nextLine().toUpperCase();//convertimos a mayus ;ara que no haya errores
        UnidadDeMedida unidad = UnidadDeMedida.valueOf(unidadIngresada);//se convierte el txt en UnidadMedida

        Producto nuevoProducto = null;

        switch(tipo){
            case 1:
                System.out.println("Ingrese el peso en kg o gramos:");
                double peso = Double.parseDouble(scanner.nextLine());

                System.out.println("Ingrese la fecha de caducidad AÑO-MES-DIA (ej: 2025-12-03): ");
                LocalDate fechaCaducidad = LocalDate.parse(scanner.nextLine());

                System.out.println("Ingrese la fecha de elaboración AÑO-MES-DIA (ej: 2025-12-03): ");
                LocalDate fechaElaboracion = LocalDate.parse(scanner.nextLine());

                nuevoProducto= new ProductoComestible(id, nombre, descripcion, precioUnitario, unidad, peso, fechaCaducidad, fechaElaboracion);
                break;
            case 2:
                System.out.println("Ingrese el volumen en litros:");
                double volumen = Double.parseDouble(scanner.nextLine());

                nuevoProducto= new ProductoLimpieza(id, nombre, descripcion, precioUnitario, unidad, volumen);
                break;
            case 3:
                System.out.println("Ingrese la garantía en meses:");
                int garantiaMeses = Integer.parseInt(scanner.nextLine());

                nuevoProducto = new ProductoTecnologico(id, nombre, descripcion, precioUnitario, unidad, garantiaMeses);
                break;
        }

        //guarda en la lista
        productos.add(nuevoProducto);
        System.out.println("Producto registrado correctamente.");
    }*/

    //metodo imprimir bien 11
    public String formatearFecha(GregorianCalendar fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fecha.getTime());
    }

}
