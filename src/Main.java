import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ec.edu.ups.poo.gestioncompras.models.*;
import ec.edu.ups.poo.gestioncompras.controller.BusquedaBinaria;
import ec.edu.ups.poo.gestioncompras.enums.Rol;
import ec.edu.ups.poo.gestioncompras.enums.UnidadDeMedida;
import ec.edu.ups.poo.gestioncompras.view.VentanaPrincipal;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Proveedor> proveedores = new ArrayList<>();
    static List<SolicitudDeCompra> solicitudes = new ArrayList<>();
    static List<Usuario> usuarios = new ArrayList<>();
    static List<Producto> productos = new ArrayList<>();
    static BusquedaBinaria binaria = new BusquedaBinaria();

    public static void main(String[] args) {

        //proveedores
        proveedores.add(new Proveedor("Proveedor Uno", "ApellidoUno", "P001", "proveedor1@mail.com", "1234567890", "RUC001", "Av. 10 de Agosto"));

        proveedores.add(new Proveedor("Proveedor Dos", "ApellidoDos", "P002", "proveedor2@mail.com", "0987654321", "RUC002", "Av. Solano"));

        //usuarios
        Departamento logistica = new Departamento("LOGISTICA", "D001", 8);

        usuarios.add(new Usuario("Carlos", "Abad", "0102030405", "carlos@mail.com", "0999999999", logistica, Rol.JEFE_DE_DEPARTAMENTO));

        usuarios.add(new Usuario("Luis", "Torres", "1112131415", "luis@mail.com", "0777777777", logistica, Rol.SUPERVISOR));

        //productos
        productos.add(new ProductoComestible("Manzanas", "C001", "Frutas frescas", 1.5, UnidadDeMedida.KILOGRAMO, 10.0, LocalDate.of(2025, 5, 20), LocalDate.of(2025, 4, 10)));

        productos.add(new ProductoLimpieza("Detergente", "L001", "Detergente líquido", 3.0, UnidadDeMedida.LITRO,2.5));

        productos.add(new ProductoTecnologico("Laptop", "T001", "Laptop gama media", 600.0, UnidadDeMedida.GIGABYTES, 24));

        new VentanaPrincipal(usuarios, proveedores, solicitudes, productos);

    }
}