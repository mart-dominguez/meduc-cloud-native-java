/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.gov.santafe.meduc.arq.app.productos.dao;

import ar.gov.santafe.meduc.arq.app.productos.modelo.Producto;
import ar.gov.santafe.meduc.arq.app.productos.util.TestDb;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

/**
 *
 * @author mdominguez
 */
@ApplicationScoped
@Transactional
public class ProductoDaoJPA implements ProductoDao{
    @Inject
    @TestDb
    EntityManager em;

    @Override
    public Producto crear(Producto p) {
        em.persist(p);
        em.flush();
        em.refresh(p);
        return p;
    }

    @Override
    public Producto actualizar(Producto p) {
        Producto actualizado = em.merge(p);
        return actualizado;
    }

    @Override
    public void borrar(Integer id) {
        em.remove(em.find(Producto.class, id));
    }

    @Override
    public Producto buscar(Integer id) {
        return em.find(Producto.class, id);
    }

    @Override
    public List<Producto> listarTodos() {
        return em.createQuery("SELECT p FROM Producto p").getResultList();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    
}
