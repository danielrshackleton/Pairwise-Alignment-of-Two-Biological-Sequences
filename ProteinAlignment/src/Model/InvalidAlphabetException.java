/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Daniel
 */
public class InvalidAlphabetException extends Exception {

    /**
     * Creates a new instance of <code>InvalidAlphabetException</code> without
     * detail message.
     */
    public InvalidAlphabetException (char alphabet, String message) {
	super (message + "'" + alphabet + "'") ;
    }

    /**
     * Constructs an instance of <code>InvalidAlphabetException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidAlphabetException(String msg) {
        super(msg);
    }
}
