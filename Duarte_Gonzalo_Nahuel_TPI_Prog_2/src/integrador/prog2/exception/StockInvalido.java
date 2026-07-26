/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package integrador.prog2.exception;

/**
 *
 * @author black
 */
public class StockInvalido extends Exception {

    /**
     * Creates a new instance of <code>StockInvalido</code> without detail
     * message.
     */
    public StockInvalido() {
    }

    /**
     * Constructs an instance of <code>StockInvalido</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public StockInvalido(String msg) {
        super(msg);
    }
}
