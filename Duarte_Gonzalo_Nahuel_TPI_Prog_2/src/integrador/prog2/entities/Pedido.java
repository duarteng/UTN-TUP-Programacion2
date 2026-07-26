/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrador.prog2.entities;

import integrador.prog2.enums.Estado;
import integrador.prog2.enums.FormaPago;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author black
 */
public class Pedido extends Base {

    private LocalDate fecha;
    private Estado estado;
    private Double total;
    private FormaPago formaPago;
    private Usuario usuario; // Relación N:1 con Usuario
    private List<DetallePedido> detalles; // Composición 1:N con DetallePedido

    private static Long secuenciaDetalles = 1L;

    public Pedido(Long id, Usuario usuario, FormaPago formaPago) {
        super(id);
        this.fecha = LocalDate.now();
        this.estado = Estado.PENDIENTE;
        this.formaPago = formaPago;
        this.usuario = usuario;
        this.detalles = new ArrayList<>();
        this.total = 0.0;
    }

    public void addDetallePedido(int cantidad, Double precioReferencia, Producto producto) {
        DetallePedido nuevoDetalle = new DetallePedido(secuenciaDetalles++, cantidad, producto);
        this.detalles.add(nuevoDetalle);
        calcularTotal();
    }

    public DetallePedido findeDetallePedidoByProducto(Producto producto) {
        for (DetallePedido detalle : detalles) {
            if (detalle.getProducto().getId().equals(producto.getId())) {
                return detalle;
            }
        }
        return null;
    }

    public void deleteDetallePedidoByProducto(Producto producto) {
        DetallePedido detalleAEliminar = findeDetallePedidoByProducto(producto);
        if (detalleAEliminar != null) {
            detalles.remove(detalleAEliminar);
            calcularTotal();
        }
    }


    public void calcularTotal() {
        double sumaSubtotales = 0.0;
        for (DetallePedido detalle : detalles) {
            sumaSubtotales += detalle.getSubtotal();
        }
        this.total = sumaSubtotales;
    }

    // Getters y Setters
    public LocalDate getFecha() {
        return fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Double getTotal() {
        return total;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido [ID: ").append(getId())
                .append(", Cliente: ").append(usuario.getNombre()).append(" ").append(usuario.getApellido())
                .append(", Estado: ").append(estado)
                .append(", Pago: ").append(formaPago)
                .append(", Total: $").append(total)
                .append(", Fecha: ").append(fecha).append("]\n");
        for (DetallePedido det : detalles) {
            sb.append(det.toString()).append("\n");
        }
        return sb.toString();
    }
}
