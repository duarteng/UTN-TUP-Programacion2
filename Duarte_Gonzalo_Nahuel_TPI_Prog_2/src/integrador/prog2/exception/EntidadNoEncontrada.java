/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package integrador.prog2.exception;

/**
 *
 * @author black
 */
public class EntidadNoEncontrada extends Exception {

    /**
     * Creates a new instance of <code>EntidadNoEncontrada</code> without detail
     * message.
     */
    public EntidadNoEncontrada() {
    }

    /**
     * Constructs an instance of <code>EntidadNoEncontrada</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public EntidadNoEncontrada(String msg) {
        super(msg);
    }
}
