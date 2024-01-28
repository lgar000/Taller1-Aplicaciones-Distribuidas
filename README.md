# Taller1-Aplicaciones-Distribuidas

Aplicación para consultar la información de películas de cine.  La aplicación recibirá una frase de búsqueda del título, por ejemplo “Guardians of the galaxy”  y deberá mostrar el los datos de la película correspondiente. Para esto utilice el API gratuito de https://www.omdbapi.com/ (Puede crear obtener una llave gratuita para realizar consultas). Se le pide que su implementación sea eficiente en cuanto a recursos así que debe implementar un Caché que permita evitar hacer consultas repetidas al API externo.


### Prerrequisitos

- Java
- Maven
- Git


### Instalación

Para hacer uso del proyecto clone el repositorio usando el siguiente comando

```
git clone https://github.com/lgar000/Taller1-Aplicaciones-Distribuidas.git
```

Ubiquese en la carpeta en la cual clono el repositorio. A continuación
acceda a la carpeta principal del proyecto mediante el siguiente comando

```
cd Taller1-Aplicaciones-Distribuidas
```

Para empaquetar el proyecto ejecute

```
mvn package
```



## Running the tests

Para ejecutar las pruebas desde la terminal de comandos use el comando

```
mvn test
```
## Diseño

La aplicación cuenta con un servidor fachada y un cliente que prestan un servicio rest, lo que quiere decir que funciona mediante peticiones http que pueden ser GET, POST, PUT y DELETE. En este caso será de tipo GET ya que queremos obtener la información de una película a partir de su título.

El cliente le hace la petición HTTP al servidor y este devuelve una respuesta que incluye un código de estado y un JSON con la información solicitada. HttpConnection se encarga de conectarse a la API omdbapI usando java, tendremos una URL formada por https://www.omdbapi.com, la llave obtenida de la API y el título a buscar, luego usamos una conexión URL, verificamos la conexión y definimos el método para que sea de tipo GET.
Una vez establecida la conexión se obtiene un código de respuesta, de ser exitoso se retornará el JSON con los datos de la película y si no un mensaje indicando que no se encontró.

Para evitar consultas repetitivas a la API se tiene un caché, por lo que antes de pedir el recurso a la API verifica si la búsqueda ya se había realizado y retorna los datos de la misma.

En HttpServer recibimos el JSON y mediante el uso de una dependencia de Json en maven podemos obtener las llaves y valores del JSON obtenido como respuesta y ordenarlo para mostrarlo al cliente.

## Extensibilidad

En caso de que se quieran agregar nuevas funcionalidades se deberá mantener el principio único de responsabilidades para que el código contenido en la clase se alinee a su funcionalidad. Además si se quisiera cambiar el proveedor de una funcionalidad como en este caso la API a la que le solicitamos la información de la películas bastaría con cambiar la URL que reemplazará el servicio y al ser manejada por la clase HttpConnection no debería generar un inconveniente con el código ya existente o con otras funcionalidades.

## Construido Con

* [Java 11](https://www.oracle.com/co/java/technologies/javase/jdk11-archive-downloads.html) - Lenguaje de programación y desarrollo
* [Html](https://developer.mozilla.org/es/docs/Web/HTML) - Lenguaje de marcado para la elaboración de páginas web
* [JavaScript](https://developer.mozilla.org/es/docs/Web/CSS) -JavaScript es un lenguaje de programación interpretado
* [Maven](https://maven.apache.org/) - Gestión de dependencias
* [Intellij](https://www.jetbrains.com/es-es/idea/) - Entorno de desarrollo integrado para el desarrollo de programas informáticos
* [Git](https://rometools.github.io/rome/) - Sistema de control de versiones distribuido


## Autor

* **Laura García** - [lgar000](https://github.com/lgar000)

## Licencia

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
