/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrador.prog2.service;

import integrador.prog2.entities.Pedido;
import integrador.prog2.entities.Producto;
import integrador.prog2.entities.Usuario;
import integrador.prog2.enums.Estado;
import integrador.prog2.enums.FormaPago;
import integrador.prog2.exception.EntidadNoEncontrada;
import integrador.prog2.exception.StockInvalido;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author black
 */
public class PedidoService {

    private final DataService dataService;

    public PedidoService(DataService dataService) {
        this.dataService = dataService;
    }

    // Estructura transaccional: Si una sola linea falla por stock, no se crea el pedido (Evita datos inconsistentes)
    public void registrarPedido(Usuario usuario, FormaPago formaPago, List<LineaPedidoDTO> lineasDTO)
            throws StockInvalido, IllegalArgumentException {

        if (usuario == null || usuario.isEliminado()) {
            throw new IllegalArgumentException("Un pedido requiere de un usuario activo obligatorio.");
        }
        if (lineasDTO == null || lineasDTO.isEmpty()) {
            throw new IllegalArgumentException("No se pueden crear pedidos sin lineas de detalle.");
        }

        // Validar que todas las lineas tengan cantidad valida y stock disponible
        for (LineaPedidoDTO linea : lineasDTO) {
            if (linea.getCantidad() <= 0) {
                throw new IllegalArgumentException("La cantidad del producto '" + linea.getProducto().getNombre() + "' debe ser mayor a 0.");
            }
            if (linea.getProducto().getStock() < linea.getCantidad()) {
                throw new StockInvalido("Stock insuficiente para '" + linea.getProducto().getNombre() + "'. Solicitado: " + linea.getCantidad() + ", Disponible: " + linea.getProducto().getStock());
            }
        }

        // Si todo esta correcto, proceder a crear el pedido y descontar el stock fisico
        Pedido nuevoPedido = new Pedido(dataService.generarIdPedido(), usuario, formaPago);

        for (LineaPedidoDTO linea : lineasDTO) {
            Producto prod = linea.getProducto();
            // Descontar stock fisico del catalogo en memoria
            prod.setStock(prod.getStock() - linea.getCantidad());
            // Agregar el detalle
            nuevoPedido.addDetallePedido(linea.getCantidad(), prod.getPrecio(), prod);
        }

        dataService.getPedidos().add(nuevoPedido);
    }

    public List<Pedido> listarPedidosActivos() {
        List<Pedido> activos = new ArrayList<>();
        for (Pedido p : dataService.getPedidos()) {
            if (!p.isEliminado()) {
                activos.add(p);
            }
        }
        return activos;
    }

    public Pedido buscarPorId(Long id) throws EntidadNoEncontrada {
        for (Pedido p : dataService.getPedidos()) {
            if (p.getId().equals(id) && !p.isEliminado()) {
                return p;
            }
        }
        throw new EntidadNoEncontrada("Pedido con ID " + id + " no encontrado o eliminado.");
    }

    public void actualizarEstadoYPago(Long id, Estado nuevoEstado, FormaPago nuevaFormaPago) throws EntidadNoEncontrada {
        Pedido p = buscarPorId(id);
        p.setEstado(nuevoEstado);
        p.setFormaPago(nuevaFormaPago);
    }

    public void eliminarPedido(Long id) throws EntidadNoEncontrada {
        Pedido p = buscarPorId(id);
        
        p.setEliminado(true);
    }

    // Clase auxiliar estatica para recolectar temporalmente los datos de consola antes de persistir
    public static class LineaPedidoDTO {

        private final Producto producto;
        private final int cantidad;

        public LineaPedidoDTO(Producto producto, int cantidad) {
            this.producto = producto;
            this.cantidad = cantidad;
        }

        public Producto getProducto() {
            return producto;
        }

        public int getCantidad() {
            return cantidad;
        }
    }
}
