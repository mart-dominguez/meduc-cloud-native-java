/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.gov.santafe.meduc.arq.app.productos.logica;

import java.util.function.Predicate;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;

/**
 *
 * @author mdominguez
 */
@ApplicationScoped
public class ProductoPredicados {
    private Predicate<Integer> stockValido = (s) -> s!=null && s>5 && s < 100;
    private Predicate<Double> precioValido = (p) -> p!=null && p>10.0 && p <9999.99;

    public Predicate<Double> validarPrecio(){
        return precioValido;
    }

    public Predicate<Integer> validarStock(){
        return stockValido;
    }

    

}
