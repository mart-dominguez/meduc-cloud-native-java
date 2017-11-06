/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.gov.santafe.meduc.arq.app.productos.logica;

import ar.gov.santafe.meduc.arq.app.productos.excepciones.PrecioInvalidoException;
import ar.gov.santafe.meduc.arq.app.productos.excepciones.StockInicialException;
import ar.gov.santafe.meduc.arq.app.productos.modelo.Producto;
import java.util.List;

/**
 *
 * @author mdominguez
 */
public interface ProductoLogica {
    public Producto crear(String nombre,Double precio,Integer stock)  throws PrecioInvalidoException,StockInicialException;
    public Producto actualizar(Integer id,String nombre );
    public void borrar(Integer id);
    public List<Producto> listar();
    public void entrada(Integer id,Integer cantidad)throws StockInicialException;
    public void salida(Integer id,Integer cantidad) throws StockInicialException;
    public void modificarPrecio(Integer id,Double porcentaje)throws PrecioInvalidoException;
    
}
