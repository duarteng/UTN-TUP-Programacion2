/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package integrador.prog2.exception;

/**
 *
 * @author black
 */
public class PrecioInvalido extends Exception {

    /**
     * Creates a new instance of <code>PrecioInvalido</code> without detail
     * message.
     */
    public PrecioInvalido() {
    }

    /**
     * Constructs an instance of <code>PrecioInvalido</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public PrecioInvalido(String msg) {
        super(msg);
    }
}
