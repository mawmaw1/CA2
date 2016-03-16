/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author ingim
 */
public class PersonNotFoundException extends Exception {

    public PersonNotFoundException(String string) {
        super(string);
    }

    public PersonNotFoundException() {
        super("Person with requested id not found");
    }
}
