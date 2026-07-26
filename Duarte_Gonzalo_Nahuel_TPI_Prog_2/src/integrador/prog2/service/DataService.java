/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrador.prog2.service;

import integrador.prog2.entities.Categoria;
import integrador.prog2.entities.Pedido;
import integrador.prog2.entities.Producto;
import integrador.prog2.entities.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author black
 */
public class DataService {

    private final List<Categoria> categorias = new ArrayList<>();
    private final List<Producto> productos = new ArrayList<>();
    private final List<Usuario> usuarios = new ArrayList<>();
    private final List<Pedido> pedidos = new ArrayList<>();

    // Secuenciadores automaticos para simular IDs autoincrementales
    private Long idCategoriaSecuencia = 1L;
    private Long idProductoSecuencia = 1L;
    private Long idUsuarioSecuencia = 1L;
    private Long idPedidoSecuencia = 1L;

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public Long generarIdCategoria() {
        return idCategoriaSecuencia++;
    }

    public Long generarIdProducto() {
        return idProductoSecuencia++;
    }

    public Long generarIdUsuario() {
        return idUsuarioSecuencia++;
    }

    public Long generarIdPedido() {
        return idPedidoSecuencia++;
    }
}
