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

    /*
     * Metodo que busca una solicitud por su ID, primero odrdena de forma ascendente y luego busca en la lista
     */
    public int buscarSolicitudID(List<SolicitudDeCompra> solicitudDeCompras, String id) {

        for (int i = 1; i < solicitudDeCompras.size(); i++) {
            SolicitudDeCompra pasajero = solicitudDeCompras.get(i);
            int j = i - 1;

            while (j >= 0 &&
                    solicitudDeCompras.get(j).getId() != null &&
                    pasajero.getId() != null &&
                    solicitudDeCompras.get(j).getId().compareToIgnoreCase(pasajero.getId()) > 0) {
                solicitudDeCompras.set(j + 1, solicitudDeCompras.get(j));
                j--;
            }
            solicitudDeCompras.set(j + 1, pasajero);
        }



        int bajo = 0;
        int alto = (solicitudDeCompras.size() - 1);

        while(bajo <= alto){

            int central = (bajo + alto)/2;
            SolicitudDeCompra solicitudCentral = solicitudDeCompras.get(central);

            String idCentral = solicitudCentral.getId();
            if (idCentral == null) {
                return -1;
            }

            int comparacion = idCentral.compareToIgnoreCase(id);

            if (comparacion == 0) {
                return central;
            }
            if (comparacion < 0) {
                bajo = central + 1;
            } else {
                alto = central - 1;
            }
        }
        return 0; //no se encontro el id buscado
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
