#📌 Información General

Título: Creación de interfaces gráficas de usuario basada en prototipado
Asignatura: Programación Orientada a Objetos
Práctica: 4
Carrera: Computación
Estudiante: [Tu nombre]
Fecha: 25/05/2025
Profesor: Gabriel Alejandro León Paredes

##📐 Prototipo de interfaz

El diseño previo de la interfaz fue elaborado como guía para construir las ventanas principales del sistema, siguiendo una estructura clara y funcional. Se representó de forma visual en papel y se replicó en código usando los componentes de AWT.

##🎯 Objetivo

Implementar una interfaz gráfica de usuario para un sistema de gestión de compras previamente desarrollado mediante programación estructurada, utilizando exclusivamente componentes de la biblioteca AWT en Java.

El objetivo es facilitar el registro, organización y control de productos, proveedores y solicitudes de compra dentro de una empresa, integrando la lógica orientada a objetos con una interfaz visual simple y funcional.

##⚙️ Tecnologías y conceptos aplicados
- Java (AWT)
- Programación orientada a objetos
- Componentes gráficos: Frame, Panel, Label, TextField, Button, ScrollPane
- Layouts: GridLayout, BorderLayout, FlowLayout
- Listas dinámicas (List<T>)
- Bucles for-each, estructuras if, switch

##🖼️ Interfaz general del sistema

Ventanas principales:
- VentanaPrincipal: Menú inicial con botones de acceso a cada funcionalidad. Organización en GridLayout de dos columnas, con botón “Salir” centrado y ancho completo.
- RegistrarSolicitudDeCompra: Permite registrar una nueva solicitud ingresando nombre del solicitante, producto y cantidad. Se pueden agregar múltiples productos antes de finalizar.
- VentanaListarSolicitudes: Muestra todas las solicitudes registradas con sus respectivos productos y subtotales. Se adapta a la cantidad de elementos gracias al uso de ScrollPane y layout vertical.
- VentanaAprobarSolicitud: Permite aprobar o rechazar solicitudes usando botones en lugar de texto, evitando errores de ingreso.
- VentanaCalcularPrecio: Permite ingresar el número de una solicitud y visualizar su total en un campo de solo lectura.
- Ventanas de registro y listado para Usuario, Proveedor y Producto, cada una con estructura clara y compatible con la lógica orientada a objetos.


##🧪 Resultados obtenidos
Se desarrolló una interfaz gráfica funcional, clara y basada en el prototipo inicial. Se solucionaron problemas visuales como el amontonamiento de contenido mediante el uso correcto de ScrollPane y layouts verticales. Además, se integró correctamente la lógica del sistema ERP con las ventanas gráficas, mejorando la experiencia del usuario al evitar errores comunes (como el ingreso de texto en campos booleanos). Las listas se actualizan dinámicamente, y se permitió la entrada de múltiples productos por solicitud.

##✅ Recomendaciones
- Usar GridLayout(0, 1) en listados para evitar que se compriman los elementos al aumentar la cantidad de datos.
- Separar bien la lógica de negocio de la vista para mantener una arquitectura clara.
- Utilizar setPreferredSize() en los paneles que actúan como tarjetas para mantener una visualización uniforme.
- Validar los datos ingresados desde la interfaz para evitar excepciones o errores.



file_path = "/mnt/data/README_InterfazGrafica.txt"
with open(file_path, "w", encoding="utf-8") as f:
    f.write(readme_content)

file_path
