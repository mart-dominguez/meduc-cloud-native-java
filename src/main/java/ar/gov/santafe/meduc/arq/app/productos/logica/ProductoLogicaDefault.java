/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.gov.santafe.meduc.arq.app.productos.logica;

import ar.gov.santafe.meduc.arq.app.productos.dao.ProductoDao;
import ar.gov.santafe.meduc.arq.app.productos.excepciones.PrecioInvalidoException;
import ar.gov.santafe.meduc.arq.app.productos.excepciones.StockInicialException;
import ar.gov.santafe.meduc.arq.app.productos.modelo.Producto;
import java.util.List;
import java.util.function.Predicate;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author mdominguez
 */
@ApplicationScoped
public class ProductoLogicaDefault implements ProductoLogica{
    @Inject
    private ProductoDao dao;


    @Inject
    private ProductoPredicados prodValidator;
    
    @Override
    public Producto crear(String nombre, Double precio, Integer stock) throws PrecioInvalidoException,StockInicialException{
        Producto p = null;
        if(prodValidator.validarPrecio().negate().test(precio)) throw new PrecioInvalidoException(precio);
        if(prodValidator.validarStock().negate().test(stock)) throw new StockInicialException(stock);
        p = dao.crear(new Producto(nombre, precio, stock));
        return p;
    }

    @Override
    public Producto actualizar(Integer id,String nombre)  {
        Producto p = dao.buscar(id);
        if(nombre !=null & nombre.length()>0) {
            p.setNombre(nombre);
            return dao.actualizar(p);
        }
        else return null;
    }

    @Override
    public void borrar(Integer id) {
        dao.borrar(id);
    }

  
    @Override
    public void modificarPrecio(Integer id,Double porcentaje) throws PrecioInvalidoException {        
        Producto p = dao.buscar(id);
        Double nuevoPrecio = p.getPrecio()*(1+porcentaje);
        if(this.prodValidator.validarPrecio().negate().test(nuevoPrecio)) throw new PrecioInvalidoException(nuevoPrecio);
        else {
            p.setPrecio(nuevoPrecio);
            dao.actualizar(p);
        }        
    }

    @Override
    public List<Producto> listar() {
        return  dao.listarTodos();
    }

    @Override
    public void entrada(Integer id, Integer cantidad) throws StockInicialException{
        Producto p = dao.buscar(id);
        Integer nuevoStock = p.getStock()+cantidad;
        if(this.prodValidator.validarStock().negate().test(nuevoStock)) throw new StockInicialException(nuevoStock);
        else{
            p.setStock(nuevoStock);
            dao.actualizar(p);
        }
    }

    @Override
    public void salida(Integer id, Integer cantidad) throws StockInicialException{
        Producto p = dao.buscar(id);
        Integer nuevoStock = p.getStock()-cantidad;
        if(nuevoStock<0) throw new StockInicialException(nuevoStock);
        else{
            p.setStock(nuevoStock);
            dao.actualizar(p);
        }        
    }

    public ProductoDao getDao() {
        return dao;
    }

    public void setDao(ProductoDao dao) {
        this.dao = dao;
    }

    public ProductoPredicados getProdValidator() {
        return prodValidator;
    }

    public void setProdValidator(ProductoPredicados prodValidator) {
        this.prodValidator = prodValidator;
    }

    
}
