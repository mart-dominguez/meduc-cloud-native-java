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
public class StockInicialException extends Exception{
    public StockInicialException(Integer stock){
        super("El stock solicitado es <"+stock+"> y el stock debe estar entre 5 y 100" );
    }
}
