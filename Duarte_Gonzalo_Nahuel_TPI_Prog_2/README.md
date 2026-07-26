# Food Store – Sistema de Gestión de Pedidos de Comida

**Link video YouTube:** [https://youtu.be/JMP9tgdSgE0](https://youtu.be/JMP9tgdSgE0)


Este proyecto se realizo para el Trabajo Práctico Integrador de la materia **Programación 2** de la **Tecnicatura Universitaria en Programación a Distancia de la Universidad Tecnologica Nacional de Buenos Aires (UTN)**. Consiste en una aplicación de consola desarrollada en Java 21 enfocada en la gestión de categorías, productos, usuarios y pedidos mediante las operaciones basicas CRUD.



## Requisitos Previos para la Ejecución
Para compilar y ejecutar este sistema de forma local, se debe utilizar:
1. **Java Development Kit (JDK):** Versión 21 o superior instalada.
2. **Variable de Entorno:** `JAVA_HOME` correctamente configurada en tu sistema operativo.
3. **Línea de Comandos:** Terminal, Git Bash o CMD disponible.
4. **IDE:** NetBeans, Eclipse o VS Code.

---

## Instrucciones de Ejecución desde un Entorno de Desarrollo (IDE)
Si utilizas **NetBeans**, **Eclipse** o **VS Code**:
1. Abre el IDE de tu preferencia.
2. Selecciona la opción **"Open Project"** o **"Import Project"** y elige la carpeta raíz del proyecto en este caso "Duarte_Gonzalo_Nahuel_TPI_Prog2".
3. Asegúrate de configurar el proyecto para que utilice la versión **Java 21 (JDK 21)**.
4. Busca el archivo `Main.java` ubicado en la ruta `src/integrador/prog2/Main.java`.
5. Haz clic derecho sobre el archivo y selecciona **"Run 'Main.main()'"** para iniciar la interfaz interactiva.


---

##  Instrucciones de Ejecución desde una Terminal 

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/duarteng/UTN-TUP-Programacion2/tree/main/Duarte_Gonzalo_Nahuel_TPI_Prog_2
   cd TU_REPOSITORIO
   ```

2. **Compilar el código fuente:**
   Desde la carpeta raíz del proyecto (donde se encuentra la carpeta `src`), ejecuta el compilador de Java apuntando al archivo principal:
   ```bash
   javac -d out src/integrador/prog2/Main.java src/integrador/prog2/entities/*.java src/integrador/prog2/enums/*.java src/integrador/prog2/interfaces/*.java src/integrador/prog2/exception/*.java src/integrador/prog2/service/*.java
   ```

3. **Ejecutar la aplicación:**
   Una vez compilado, inicia la interfaz de consola mediante el siguiente comando:
   ```bash
   java -cp out integrador.prog2.Main
   ```
