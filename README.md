# 📌 Información General

Título: Creación de interfaces gráficas de usuario basada en prototipado

Asignatura: Programación Orientada a Objetos

Práctica: 4

Carrera: Computación

Estudiante: Isabel Ullauri y Victoria Andrade

Fecha: 25/05/2025

Profesor: Gabriel Alejandro León Paredes

## 📐 Prototipo de interfaz

El diseño previo de la interfaz fue elaborado como guía para construir las ventanas principales del sistema, siguiendo una estructura clara y funcional. Se representó de mediante la aplicacion Figma.
https://www.figma.com/design/HQWEdckxKma9bW2JzA8r2q/Prototipo-gestion-de-compras?node-id=0-1&t=4p4ASbRYduY3NnfF-1

## Explicacion del Programa En Youtube
https://youtu.be/pJuMlYIr_i4


## 🎯 Objetivo

Implementar una interfaz gráfica de usuario para un sistema de gestión de compras previamente desarrollado mediante programación estructurada, utilizando exclusivamente componentes de la biblioteca AWT en Java.

El objetivo es facilitar el registro, organización y control de productos, proveedores y solicitudes de compra dentro de una empresa, integrando la lógica orientada a objetos con una interfaz visual simple y funcional.

## ⚙️ Conceptos aplicados
- Java (AWT)
- Programación orientada a objetos
- Componentes gráficos: Frame, Panel, Label, TextField, Button, ScrollPane
- Layouts: GridLayout, BorderLayout, FlowLayout
- Bucles for-each, estructuras if, switch

## 🧪Incialización del Programa
El programa se inicializa ejecutando la clase `Main`, la cual carga listas de datos predefinidos y luego abre la ventana principal del sistema mediante la clase VentanaPrincipal, desde donde el usuario puede acceder a todas las funciones del sistema de gestión de compras.

## 🖼️ Interfaz general del sistema

Ventanas principales:
- VentanaPrincipal: Menú inicial con botones de acceso a cada funcionalidad. Organización en GridLayout de dos columnas, con botón “Salir” centrado y ancho completo.
- RegistrarSolicitudDeCompra: Permite registrar una nueva solicitud ingresando nombre del solicitante, producto y cantidad. Se pueden agregar múltiples productos antes de finalizar.
- VentanaListarSolicitudes: Muestra todas las solicitudes registradas con sus respectivos productos y subtotales. Se adapta a la cantidad de elementos gracias al uso de ScrollPane y layout vertical.
- VentanaAprobarSolicitud: Permite aprobar o rechazar solicitudes usando botones en lugar de texto, evitando errores de ingreso.
- VentanaCalcularPrecio: Permite ingresar el número de una solicitud y visualizar su total en un campo de solo lectura.
- Ventanas de registro y listado para Usuario, Proveedor y Producto, cada una con estructura clara y compatible con la lógica orientada a objetos.


## 🧪 Resultados obtenidos
Se desarrolló una interfaz funcional y clara que refleja el prototipo inicial. El sistema permite gestionar usuarios, productos, proveedores y solicitudes de compra. Los usuarios pueden registrar y visualizar información, así como aprobar o rechazar solicitudes según su rol. Se resolvieron problemas de visualización, mediante el uso de scroll, y también se facilitó un poco el uso del programa haciendo uso de componentes como Choice para hacer elecciones como por ejemplo de Departamento de un usuario. Además, se logró una integración del sistema en código, mejorando la experiencia del usuario al simplificar las entradas y evitar errores comunes.

## ✅ Recomendaciones
- Usar GridLayout(0, 1) en listados para evitar que se amontonen los elementos al aumentar la cantidad de datos.
- Separar bien la lógica de negocio de la vista para mantener una arquitectura clara.
- Utilizar setPreferredSize() en los paneles que actúan como tarjetas para mantener una visualización uniforme.
- Validar los datos ingresados desde la interfaz para evitar excepciones o errores.

