package ec.edu.ups.poo.gestioncompras.controller;

import ec.edu.ups.poo.gestioncompras.models.Producto;
import ec.edu.ups.poo.gestioncompras.models.Proveedor;
import ec.edu.ups.poo.gestioncompras.models.SolicitudDeCompra;

import java.util.List;

public class BusquedaBinaria {

    public int buscarProveedorID(List<Proveedor> proveedores, String id) {

        for (int i = 1; i < proveedores.size(); i++) {
            Proveedor proveedorActual = proveedores.get(i);
            int j = i - 1;

            while (j >= 0 && proveedores.get(j).getId().compareToIgnoreCase(proveedorActual.getId()) > 0) {
                proveedores.set(j + 1, proveedores.get(j));
                j--;
            }
            proveedores.set(j + 1, proveedorActual);
        }
        int bajo = 0;
        int alto = proveedores.size() - 1;

        while(bajo <= alto){

            int central = (bajo + alto)/2;
            Proveedor proveedorCentral = proveedores.get(central);

            int comparacion = proveedorCentral.getId().compareToIgnoreCase(id);

            if(comparacion == 0){ //si es ==0 significa que las comparaciones entre ambos nombres coinciden
                return central;
            }
            if(comparacion < 0){ //si alfabeticamente se encuentra antes, va a la derecha
                bajo = central + 1;
            }else {
                alto = central - 1;
            }
        }
        return -1; //no se encontro el id buscado
    }

    public int buscarProductoID(List<Producto> productos, String id) {

        // Ordenamiento por ID usando inserción
        for (int i = 1; i < productos.size(); i++) {
            Producto actual = productos.get(i);
            int j = i - 1;

            while (j >= 0 && productos.get(j).getId().compareToIgnoreCase(actual.getId()) > 0) {
                productos.set(j + 1, productos.get(j));
                j--;
            }
            productos.set(j + 1, actual);
        }

        // Búsqueda binaria por ID
        int bajo = 0;
        int alto = productos.size() - 1;

        while (bajo <= alto) {
            int central = (bajo + alto) / 2;
            Producto productoCentral = productos.get(central);

            int comparacion = productoCentral.getId().compareToIgnoreCase(id);

            if (comparacion == 0) {
                return central;
            }
            if (comparacion < 0) {
                bajo = central + 1;
            } else {
                alto = central - 1;
            }
        }

        return -1; // No encontrado
    }

    /*
     * Metodo que busca una solicitud por su ID, primero odrdena de forma ascendente y luego busca en la lista
     */
    public int buscarSolicitudID(List<SolicitudDeCompra> lista, String numeroSolicitud) {

        // Ordenar la lista por número de solicitud
        for (int i = 1; i < lista.size(); i++) {
            SolicitudDeCompra actual = lista.get(i);
            int j = i - 1;

            while (j >= 0 && lista.get(j).getNumeroSolicitud().compareToIgnoreCase(actual.getNumeroSolicitud()) > 0) {
                lista.set(j + 1, lista.get(j));
                j--;
            }
            lista.set(j + 1, actual);
        }

        // Búsqueda binaria por número de solicitud
        int bajo = 0;
        int alto = lista.size() - 1;

        while (bajo <= alto) {
            int medio = (bajo + alto) / 2;
            SolicitudDeCompra central = lista.get(medio);

            int comparacion = central.getNumeroSolicitud().compareToIgnoreCase(numeroSolicitud);

            if (comparacion == 0) {
                return medio;
            } else if (comparacion < 0) {
                bajo = medio + 1;
            } else {
                alto = medio - 1;
            }
        }

        return -1; // No encontrada
    }


    public int buscarProductoNombre(List<Producto> productos, String nombreProducto) {

        for (int i = 1; i < productos.size(); i++) {
            Producto pasajero = productos.get(i);
            int j = i - 1;

            while (j >= 0 && productos.get(j).getNombre().compareToIgnoreCase(pasajero.getNombre()) > 0) {
                productos.set(j + 1, productos.get(j));
                j--;
            }
            productos.set(j + 1, pasajero);
        }

        int bajo = 0;
        int alto = (productos.size() - 1);

        while(bajo <= alto){

            int central = (bajo + alto)/2;
            Producto productoCentral = productos.get(central);

            int comparacion = productoCentral.getNombre().compareToIgnoreCase(nombreProducto);

            if(comparacion == 0){ //si es ==0 significa que las comparaciones entre ambos nombres coinciden
                return central;
            }
            if(comparacion < 0){ //si alfabeticamente se encuentra antes, va a la derecha
                bajo = central + 1;
            }else {
                alto = central - 1;
            }
        }
        return -1; //no se encontro el id buscado
    }

}
