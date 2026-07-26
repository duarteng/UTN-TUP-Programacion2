/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrador.prog2;

import integrador.prog2.entities.Categoria;
import integrador.prog2.entities.Producto;
import integrador.prog2.entities.Usuario;
import integrador.prog2.enums.Estado;
import integrador.prog2.enums.FormaPago;
import integrador.prog2.enums.Rol;
import integrador.prog2.exception.EmailDuplicado;
import integrador.prog2.exception.EntidadNoEncontrada;
import integrador.prog2.exception.PrecioInvalido;
import integrador.prog2.exception.StockInvalido;
import integrador.prog2.service.CategoriaService;
import integrador.prog2.service.DataService;
import integrador.prog2.service.PedidoService;
import integrador.prog2.service.ProductoService;
import integrador.prog2.service.UsuarioService;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author black
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final DataService dataService = new DataService();

    private static final CategoriaService categoriaService = new CategoriaService(dataService);
    private static final ProductoService productoService = new ProductoService(dataService);
    private static final UsuarioService usuarioService = new UsuarioService(dataService);
    private static final PedidoService pedidoService = new PedidoService(dataService);

    public static void main(String[] args) {
        // Datos iniciales de prueba 
        precargarDatos();

        int opcionPrincipal = -1;
        while (opcionPrincipal != 0) {
            System.out.println("\n=== SISTEMA DE PEDIDOS FOOD STORE ===");
            System.out.println("1. Categorias");
            System.out.println("2. Productos");
            System.out.println("3. Usuarios");
            System.out.println("4. Pedidos");
            System.out.println("0. Salir");
            opcionPrincipal = leerEntero("Seleccione una opcion: ");

            switch (opcionPrincipal) {
                case 1 ->
                    menuCategorias();
                case 2 ->
                    menuProductos();
                case 3 ->
                    menuUsuarios();
                case 4 ->
                    menuPedidos();
                case 0 ->
                    System.out.println("Saliendo del sistema...");
                default ->
                    System.out.println("Opcion invalida.");
            }
        }
    }

    
    /* 
    ==========================================
    
    MENU CATEGORIAS
    
    ========================================== 
    */
    private static void menuCategorias() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- GESTION DE CATEGORIAS ---");
            System.out.println("1. Listar categorias");
            System.out.println("2. Crear categoria");
            System.out.println("3. Editar categoria");
            System.out.println("4. Eliminar categoria");
            System.out.println("0. Volver al menu principal");
            opcion = leerEntero("Seleccione: ");

            try {
                switch (opcion) {
                    case 1 -> {
                        List<Categoria> lista = categoriaService.listarCategoriasActivas();
                        if (lista.isEmpty()) {
                            System.out.println("No hay categorias cargadas.");
                        } else {
                            lista.forEach(System.out::println);
                        }
                    }
                    case 2 -> {
                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Descripcion: ");
                        String desc = scanner.nextLine();
                        categoriaService.crearCategoria(nombre, desc);
                        System.out.println("Categoria creada con exito.");
                    }
                    case 3 -> {
                        long id = leerEntero("ID de la categoria a editar: ");
                        System.out.print("Nuevo Nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Nueva Descripcion: ");
                        String desc = scanner.nextLine();
                        categoriaService.editarCategoria(id, nombre, desc);
                        System.out.println("Categoria modificada con exito.");
                    }
                    case 4 -> {
                        long id = leerEntero("ID de la categoria a eliminar: ");
                        System.out.print("¿Confirma la eliminacion logica? (S/N): ");
                        String conf = scanner.nextLine();
                        if (conf.equalsIgnoreCase("S")) {
                            categoriaService.eliminarCategoria(id);
                            System.out.println("Categoria dada de baja.");
                        } else {
                            System.out.println("Operacion cancelada.");
                        }
                    }
                }
            } catch (EntidadNoEncontrada | IllegalArgumentException | IllegalStateException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    
    /* 
    ==========================================
    
    MENU PRODUCTOS
    
    ========================================== 
    */
    private static void menuProductos() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- GESTION DE PRODUCTOS ---");
            System.out.println("1. Listar todos los productos");
            System.out.println("2. Listar productos por categoria");
            System.out.println("3. Crear producto");
            System.out.println("4. Editar producto");
            System.out.println("5. Eliminar producto");
            System.out.println("0. Volver");
            opcion = leerEntero("Seleccione: ");

            try {
                switch (opcion) {
                    case 1 -> {
                        List<Producto> lista = productoService.listarProductosActivos();
                        if (lista.isEmpty()) {
                            System.out.println("No hay productos.");
                        } else {
                            lista.forEach(System.out::println);
                        }
                    }
                    case 2 -> {
                        long idCat = leerEntero("ID de la Categoria: ");
                        List<Producto> lista = productoService.listarProductosPorCategoria(idCat);
                        if (lista.isEmpty()) {
                            System.out.println("No hay productos en esta categoria.");
                        } else {
                            lista.forEach(System.out::println);
                        }
                    }
                    case 3 -> {
                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();
                        double precio = leerDouble("Precio: ");
                        System.out.print("Descripcion: ");
                        String desc = scanner.nextLine();
                        int stock = leerEntero("Stock inicial: ");
                        System.out.print("URL Imagen: ");
                        String img = scanner.nextLine();
                        long idCat = leerEntero("ID de la Categoria asociada: ");

                        Categoria cat = categoriaService.buscarPorId(idCat);
                        productoService.crearProducto(nombre, precio, desc, stock, img, true, cat);
                        System.out.println("Producto añadido al catalogo.");
                    }
                    case 4 -> {
                        long idProd = leerEntero("ID del producto a editar: ");
                        productoService.buscarPorId(idProd); // Validar si existe primero

                        System.out.print("Nuevo Nombre: ");
                        String nombre = scanner.nextLine();
                        double precio = leerDouble("Nuevo Precio: ");
                        System.out.print("Nueva Descripcion: ");
                        String desc = scanner.nextLine();
                        int stock = leerEntero("Nuevo Stock: ");
                        System.out.print("Nueva URL Imagen: ");
                        String img = scanner.nextLine();
                        long idCat = leerEntero("ID de la nueva Categoria asociada: ");

                        Categoria cat = categoriaService.buscarPorId(idCat);
                        productoService.editarProducto(idProd, nombre, precio, desc, stock, img, true, cat);
                        System.out.println("Producto modificado con exito.");
                    }
                    case 5 -> {
                        long idProd = leerEntero("ID del producto a eliminar: ");
                        System.out.print("¿Confirma baja logica? (S/N): ");
                        String conf = scanner.nextLine();
                        if (conf.equalsIgnoreCase("S")) {
                            productoService.eliminarProducto(idProd);
                            System.out.println("Producto dado de baja de la vista de venta.");
                        }
                    }
                }
            } catch (EntidadNoEncontrada | PrecioInvalido | StockInvalido | IllegalArgumentException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    
    /* 
    ==========================================
    
    MENU USUARIOS
    
    ========================================== 
    */
    private static void menuUsuarios() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- GESTION DE USUARIOS ---");
            System.out.println("1. Listar usuarios");
            System.out.println("2. Crear usuario");
            System.out.println("3. Editar usuario");
            System.out.println("4. Eliminar usuario");
            System.out.println("0. Volver");
            opcion = leerEntero("Seleccione: ");

            try {
                switch (opcion) {
                    case 1 -> {
                        List<Usuario> lista = usuarioService.listarUsuariosActivos();
                        if (lista.isEmpty()) {
                            System.out.println("No hay usuarios registrados.");
                        } else {
                            lista.forEach(System.out::println);
                        }
                    }
                    case 2 -> {
                        System.out.print("Nombre: ");
                        String n = scanner.nextLine();
                        System.out.print("Apellido: ");
                        String a = scanner.nextLine();
                        System.out.print("Mail: ");
                        String m = scanner.nextLine();
                        System.out.print("Celular: ");
                        String c = scanner.nextLine();
                        System.out.print("Contraseña: ");
                        String pass = scanner.nextLine();
                        System.out.println("Seleccione Rol (1. ADMIN, 2. USUARIO): ");
                        Rol r = leerEntero("") == 1 ? Rol.ADMIN : Rol.USUARIO;
                        usuarioService.crearUsuario(n, a, m, c, pass, r);
                        System.out.println("Usuario creado con exito.");
                    }
                    case 3 -> {
                        long id = leerEntero("ID del usuario a editar: ");
                        System.out.print("Nuevo Nombre: ");
                        String n = scanner.nextLine();
                        System.out.print("Nuevo Apellido: ");
                        String a = scanner.nextLine();
                        System.out.print("Nuevo Mail: ");
                        String m = scanner.nextLine();
                        System.out.print("Nuevo Celular: ");
                        String c = scanner.nextLine();
                        System.out.print("Nueva Contraseña: ");
                        String pass = scanner.nextLine();
                        System.out.println("Nuevo Rol (1. ADMIN, 2. USUARIO): ");
                        Rol r = leerEntero("") == 1 ? Rol.ADMIN : Rol.USUARIO;
                        usuarioService.editarUsuario(id, n, a, m, c, pass, r);
                        System.out.println("Datos del usuario actualizados.");
                    }
                    case 4 -> {
                        long id = leerEntero("ID del usuario a dar de baja: ");
                        System.out.print("¿Confirma baja logica? (S/N): ");
                        if (scanner.nextLine().equalsIgnoreCase("S")) {
                            usuarioService.eliminarUsuario(id);
                            System.out.println("Usuario desactivado.");
                        }
                    }
                }
            } catch (EntidadNoEncontrada | EmailDuplicado | IllegalArgumentException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }


    /* 
    ==========================================
    
    MENU PEDIDOS
    
    ========================================== 
    */
    
    private static void menuPedidos() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- GESTION DE PEDIDOS ---");
            System.out.println("1. Listar pedidos");
            System.out.println("2. Registrar nuevo pedido (con detalles)");
            System.out.println("3. Actualizar estado y forma de pago");
            System.out.println("4. Eliminar pedido");
            System.out.println("0. Volver");
            opcion = leerEntero("Seleccione: ");
            try {
                switch (opcion) {
                    case 1 -> {
                        List lista = pedidoService.listarPedidosActivos();
                        if (lista.isEmpty()) {
                            System.out.println("No hay pedidos registrados.");
                        } else {
                            lista.forEach(System.out::println);
                        }
                    }
                    case 2 -> {
                        long idUser = leerEntero("ID del Usuario Operador: ");
                        Usuario user = usuarioService.buscarPorId(idUser);
                        System.out.println("Seleccione Forma de Pago (1. TARJETA, 2. TRANSFERENCIA, 3. EFECTIVO): ");
                        int fpOpcion = leerEntero("");
                        FormaPago fp = switch (fpOpcion) {
                            case 1 ->
                                FormaPago.TARJETA;
                            case 2 ->
                                FormaPago.TRANSFERENCIA;
                            default ->
                                FormaPago.EFECTIVO;
                        };
                        List<PedidoService.LineaPedidoDTO> carroDeCompra = new ArrayList<>();
                        boolean agregando = true;
                        while (agregando) {
                            long idProd = leerEntero("ID del Producto a añadir: ");
                            Producto prod = productoService.buscarPorId(idProd);
                            int cantidad = leerEntero("Cantidad: ");
                            carroDeCompra.add(new PedidoService.LineaPedidoDTO(prod, cantidad));
                            System.out.print("¿Desea añadir otro producto al pedido? (S/N): ");
                            if (!scanner.nextLine().equalsIgnoreCase("S")) {
                                agregando = false;
                            }
                        }// Registra de forma transaccional. Si el stock falla, se atrapa la excepcion y no se confirma nada.
                        pedidoService.registrarPedido(user, fp, carroDeCompra);
                        System.out.println("¡Pedido registrado exitosamente y stock actualizado!");
                    }
                    case 3 -> {
                        long idPed = leerEntero("ID del Pedido: ");
                        System.out.println("Nuevo Estado (1. PENDIENTE, 2. CONFIRMADO, 3. TERMINADO, 4. CANCELADO): ");
                        int estOp = leerEntero("");
                        Estado est = switch (estOp) {
                            case 1 ->
                                Estado.PENDIENTE;
                            case 2 ->
                                Estado.CONFIRMADO;
                            case 3 ->
                                Estado.TERMINADO;
                            default ->
                                Estado.CANCELADO;
                        };
                        System.out.println("Nueva Forma de Pago (1. TARJETA, 2. TRANSFERENCIA, 3. EFECTIVO): ");
                        int fpOp = leerEntero("");
                        FormaPago fp = switch (fpOp) {
                            case 1 ->
                                FormaPago.TARJETA;
                            case 2 ->
                                FormaPago.TRANSFERENCIA;
                            default ->
                                FormaPago.EFECTIVO;
                        };
                        pedidoService.actualizarEstadoYPago(idPed, est, fp);
                        System.out.println("Pedido actualizado.");
                    }
                    case 4 -> {
                        long idPed = leerEntero("ID del Pedido a eliminar: ");
                        System.out.print("¿Confirma eliminacion logica? (S/N): ");
                        if (scanner.nextLine().equalsIgnoreCase("S")) {
                            pedidoService.eliminarPedido(idPed);
                            System.out.println("Pedido archivado.");
                        }
                    }
                }
            } catch (EntidadNoEncontrada | StockInvalido | IllegalArgumentException e) {
                System.out.println("ERROR EN OPERACION: " + e.getMessage());
            }
        }
    }

    /* 
    ==========================================
    
    FILTROS DE ENTRADA ROBUSTOS 
    
    ========================================== 
    */
    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Ingrese un numero entero.");
            }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Ingrese un valor numerico decimal.");
            }
        }
    }

    private static void precargarDatos() {
        try {
            categoriaService.crearCategoria("Hamburguesas", "Combos de carne vacuna y pollo");
            categoriaService.crearCategoria("Bebidas", "Gaseosas y aguas saborizadas");
            Categoria cat1 = categoriaService.buscarPorId(1L);
            Categoria cat2 = categoriaService.buscarPorId(2L);
            productoService.crearProducto("Doble Cheddar", 4500.0, "Doble carne con queso", 50, "url_img", true, cat1);
            productoService.crearProducto("Coca Cola 500ml", 1200.0, "Gaseosa comun", 100, "url_img", true, cat2);
            usuarioService.crearUsuario("Juan", "Perez", "juan@mail.com", "11223344", "123", Rol.ADMIN);
            usuarioService.crearUsuario("Ana", "Gomez", "ana@mail.com", "55667788", "123", Rol.USUARIO);
        } catch (Exception ignored) {
        }
    }
}
