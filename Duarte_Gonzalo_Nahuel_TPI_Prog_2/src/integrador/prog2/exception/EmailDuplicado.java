/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package integrador.prog2.exception;

/**
 *
 * @author black
 */
public class EmailDuplicado extends Exception {

    /**
     * Creates a new instance of <code>EmailDuplicado</code> without detail
     * message.
     */
    public EmailDuplicado() {
    }

    /**
     * Constructs an instance of <code>EmailDuplicado</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public EmailDuplicado(String msg) {
        super(msg);
    }
}
