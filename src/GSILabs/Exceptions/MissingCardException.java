/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GSILabs.Exceptions;

/**
 *
 * @author labora1
 */
public class MissingCardException extends Exception{
    
    public MissingCardException(){
        System.err.println("El no tiene esa tarjeta");
    }
}
