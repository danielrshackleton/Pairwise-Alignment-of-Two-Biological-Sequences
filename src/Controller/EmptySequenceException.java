/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author MT9HY82Q69F2JG7V8V77
 */
public class EmptySequenceException extends Exception {

    /**
     * Creates a new instance of <code>EmptySequenceException</code> without
     * detail message.
     */
    public EmptySequenceException() {
    }

    /**
     * Constructs an instance of <code>EmptySequenceException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public EmptySequenceException(String msg) {
        super(msg);
    }
}
