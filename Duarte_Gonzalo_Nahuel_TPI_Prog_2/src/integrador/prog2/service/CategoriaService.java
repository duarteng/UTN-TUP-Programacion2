/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrador.prog2.service;

import integrador.prog2.entities.Categoria;
import integrador.prog2.entities.Producto;
import integrador.prog2.exception.EntidadNoEncontrada;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author black
 */
public class CategoriaService {

    private final DataService dataService;

    public CategoriaService(DataService dataService) {
        this.dataService = dataService;
    }

    public void crearCategoria(String nombre, String descripcion) throws IllegalArgumentException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoria no puede estar vacio.");
        }
        // Validar que no exista otra categoria activa con el mismo nombre
        for (Categoria categoria : dataService.getCategorias()) {
            if (!categoria.isEliminado() && categoria.getNombre().equalsIgnoreCase(nombre.trim())) {
                throw new IllegalArgumentException("Ya existe una categoria activa con el nombre: " + nombre);
            }
        }
        Categoria nueva = new Categoria(dataService.generarIdCategoria(), nombre.trim(), descripcion);
        dataService.getCategorias().add(nueva);
    }

    public List<Categoria> listarCategoriasActivas() {
        List<Categoria> activas = new ArrayList<>();
        for (Categoria categoria : dataService.getCategorias()) {
            if (!categoria.isEliminado()) {
                activas.add(categoria);
            }
        }
        return activas;
    }

    public Categoria buscarPorId(Long id) throws EntidadNoEncontrada {
        for (Categoria categoria : dataService.getCategorias()) {
            if (categoria.getId().equals(id) && !categoria.isEliminado()) {
                return categoria;
            }
        }
        throw new EntidadNoEncontrada("Categoria con ID " + id + " no encontrada o eliminada.");
    }

    public void editarCategoria(Long id, String nuevoNombre, String nuevaDescripcion) throws EntidadNoEncontrada, IllegalArgumentException {
        Categoria categoria = buscarPorId(id);
        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        // Validar duplicado excluyendo la categoria actual
        for (Categoria cat : dataService.getCategorias()) {
            if (!cat.isEliminado() && !cat.getId().equals(id) && cat.getNombre().equalsIgnoreCase(nuevoNombre.trim())) {
                throw new IllegalArgumentException("Ya existe otra categoria activa con el nombre: " + nuevoNombre);
            }
        }
        categoria.setNombre(nuevoNombre.trim());
        categoria.setDescripcion(nuevaDescripcion);
    }

    public void eliminarCategoria(Long id) throws EntidadNoEncontrada, IllegalStateException {
        Categoria categoria = buscarPorId(id);

        for (Producto producto : dataService.getProductos()) {
            if (!producto.isEliminado() && producto.getCategoria().getId().equals(id)) {
                throw new IllegalStateException("No se puede eliminar la categoria porque tiene productos activos asociados.");
            }
        }
        
        categoria.setEliminado(true);
    }
}
