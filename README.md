# Food Store – Sistema de Gestión de Pedidos de Comida

Este proyecto se realizo para el Trabajo Práctico Integrador de la materia **Programación 2** de la **Tecnicatura Universitaria en Programación a Distancia (UTN)**. Consiste en una aplicación de consola desarrollada en Java 21 enfocada en la gestión de categorías, productos, usuarios y pedidos mediante las operaciones basicas CRUD.

---

## Requisitos Previos para la Ejecución
Para compilar y ejecutar este sistema de forma local, se debe utilizar:
1. **Java Development Kit (JDK):** Versión 21 o superior instalada.
2. **Variable de Entorno:** `JAVA_HOME` correctamente configurada en tu sistema operativo.
3. **Línea de Comandos:** Terminal, Git Bash o CMD disponible.

---

##  Instrucciones de Ejecución

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com
   cd TU_REPOSITORIO
   ```

2. **Compilar el código fuente:**
   Desde la carpeta raíz del proyecto (donde se encuentra la carpeta `src`), ejecuta el compilador de Java apuntando al archivo principal:
   ```bash
   javac -d out src/integrado/prog2/Main.java src/integrado/prog2/entities/*.java src/integrado/prog2/enums/*.java src/integrado/prog2/interfaces/*.java src/integrado/prog2/exception/*.java src/integrado/prog2/service/*.java
   ```

3. **Ejecutar la aplicación:**
   Una vez compilado, inicia la interfaz de consola mediante el siguiente comando:
   ```bash
   java -cp out integrado.prog2.Main
   ```

---

## 💻 Ejecución desde un Entorno de Desarrollo (IDE)
Si utilizas **NetBeans**, **Eclipse** o **VS Code**:
1. Abre el IDE de tu preferencia.
2. Selecciona la opción **"Open Project"** o **"Import Project"** y elige la carpeta raíz del proyecto.
3. Asegúrate de configurar el proyecto para que utilice la versión **Java 21 (JDK 21)**.
4. Busca el archivo `Main.java` ubicado en la ruta `src/integrado/prog2/Main.java`.
5. Haz clic derecho sobre el archivo y selecciona **"Run 'Main.main()'"** para iniciar la interfaz interactiva.
