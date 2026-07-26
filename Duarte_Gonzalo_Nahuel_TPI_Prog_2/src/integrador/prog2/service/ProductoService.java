/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrador.prog2.service;

import integrador.prog2.entities.Categoria;
import integrador.prog2.entities.Producto;
import integrador.prog2.exception.EntidadNoEncontrada;
import integrador.prog2.exception.PrecioInvalido;
import integrador.prog2.exception.StockInvalido;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author black
 */
public class ProductoService {

    private final DataService dataService;

    public ProductoService(DataService dataService) {
        this.dataService = dataService;
    }

    public void crearProducto(String nombre, Double precio, String descripcion, int stock, String imagen, boolean disponible, Categoria categoria)
            throws PrecioInvalido, StockInvalido, IllegalArgumentException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacio.");
        }
        if (precio < 0) {
            throw new PrecioInvalido("El precio no puede ser menor a cero.");
        }
        if (stock < 0) {
            throw new StockInvalido("El stock inicial no puede ser menor a cero.");
        }
        if (categoria == null || categoria.isEliminado()) {
            throw new IllegalArgumentException("La categoria asociada debe ser valida y estar activa.");
        }

        Producto nuevo = new Producto(dataService.generarIdProducto(), nombre.trim(), precio, descripcion, stock, imagen, disponible, categoria);
        dataService.getProductos().add(nuevo);
    }

    public List<Producto> listarProductosActivos() {
        List<Producto> activos = new ArrayList<>();
        for (Producto prod : dataService.getProductos()) {
            if (!prod.isEliminado()) {
                activos.add(prod);
            }
        }
        return activos;
    }

    public List<Producto> listarProductosPorCategoria(Long idCategoria) {
        List<Producto> filtrados = new ArrayList<>();
        for (Producto prod : dataService.getProductos()) {
            if (!prod.isEliminado() && prod.getCategoria().getId().equals(idCategoria)) {
                filtrados.add(prod);
            }
        }
        return filtrados;
    }

    public Producto buscarPorId(Long id) throws EntidadNoEncontrada {
        for (Producto prod : dataService.getProductos()) {
            if (prod.getId().equals(id) && !prod.isEliminado()) {
                return prod;
            }
        }
        throw new EntidadNoEncontrada("Producto con ID " + id + " no encontrado o eliminado.");
    }

    public void editarProducto(Long id, String nuevoNombre, Double nuevoPrecio, String nuevaDescripcion, int nuevoStock, String nuevaImagen, boolean nuevoDisponible, Categoria nuevaCategoria)
            throws EntidadNoEncontrada, PrecioInvalido, StockInvalido, IllegalArgumentException {
        Producto prod = buscarPorId(id);

        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        if (nuevoPrecio < 0) {
            throw new PrecioInvalido("El precio no puede ser menor a cero.");
        }
        if (nuevoStock < 0) {
            throw new StockInvalido("El stock no puede ser menor a cero.");
        }
        if (nuevaCategoria == null || nuevaCategoria.isEliminado()) {
            throw new IllegalArgumentException("La categoria asignada debe estar activa.");
        }

        prod.setNombre(nuevoNombre.trim());
        prod.setPrecio(nuevoPrecio);
        prod.setDescripcion(nuevaDescripcion);
        prod.setStock(nuevoStock);
        prod.setImagen(nuevaImagen);
        prod.setDisponible(nuevoDisponible);
        prod.setCategoria(nuevaCategoria);
    }

    public void eliminarProducto(Long id) throws EntidadNoEncontrada {
        Producto prod = buscarPorId(id);
        
        prod.setEliminado(true);
    }
}
