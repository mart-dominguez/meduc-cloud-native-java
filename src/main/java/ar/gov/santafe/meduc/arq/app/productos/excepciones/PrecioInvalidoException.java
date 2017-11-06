/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.gov.santafe.meduc.arq.app.productos.excepciones;

/**
 *
 * @author mdominguez
 */
public class PrecioInvalidoException extends Exception{

    public PrecioInvalidoException(Double precio) {
            super("El percio ingresado es <"+precio+"> y el stock debe estar entre 10.0 y 9999.99" );
    }
    
}
