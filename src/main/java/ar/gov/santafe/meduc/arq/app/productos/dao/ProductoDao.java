/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.gov.santafe.meduc.arq.app.productos.dao;

import ar.gov.santafe.meduc.arq.app.productos.modelo.Producto;
import java.util.List;

/**
 *
 * @author mdominguez
 */
public interface ProductoDao {
    public Producto crear(Producto p);
    public Producto actualizar(Producto p);
    public void borrar(Integer id);
    public Producto buscar(Integer id);
    public List<Producto> listarTodos();
    
}
