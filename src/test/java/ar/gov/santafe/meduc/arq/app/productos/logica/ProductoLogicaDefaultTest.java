/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.gov.santafe.meduc.arq.app.productos.logica;
import static org.mockito.Mockito.when;

import ar.gov.santafe.meduc.arq.app.productos.dao.ProductoDao;
import ar.gov.santafe.meduc.arq.app.productos.excepciones.PrecioInvalidoException;
import ar.gov.santafe.meduc.arq.app.productos.excepciones.StockInicialException;
import ar.gov.santafe.meduc.arq.app.productos.modelo.Producto;
import java.util.function.Predicate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
    /**
 *
 * @author mdominguez
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductoLogicaDefaultTest {
    
    @Mock private ProductoPredicados predicados ;
    @Mock private ProductoDao productodao; // note the mock name attribute

    @InjectMocks private ProductoLogicaDefault logica = new ProductoLogicaDefault();

    
    public ProductoLogicaDefaultTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of crear method, of class ProductoLogicaDefault.
     */
    @Test
    public void testCrearOk() throws PrecioInvalidoException, StockInicialException {
        String nombre="";
        Double precio = 150.0;
        Integer stock = 50;
        Producto esperado = new Producto(nombre,precio,stock);
        
        when(productodao.crear(ArgumentMatchers.any())).thenReturn(esperado);

        Predicate<Double> precioMock = d -> true;
        Predicate<Integer> stockMock = d -> true;
        when(predicados.validarPrecio()).thenReturn(precioMock);
        when(predicados.validarStock()).thenReturn(stockMock);
        
        Producto p = logica.crear(nombre, precio, stock);
        assertEquals(esperado, p);
    }
    
    @Test(expected =PrecioInvalidoException.class )
    public void testCrearInvalidoPrecio() throws PrecioInvalidoException, StockInicialException {
        String nombre="";
        Double precio = 9999999.0;
        Integer stock = 50;
        Producto esperado = new Producto(nombre,precio,stock);
        Predicate<Double> precioMock = d -> false;
        when(predicados.validarPrecio()).thenReturn(precioMock);
        
        Producto p = logica.crear(nombre, precio, stock);
        assertEquals(esperado, p);
    }

    @Test(expected =StockInicialException.class)
    public void testCrearInvalidoStock() throws PrecioInvalidoException, StockInicialException {
        String nombre="";
        Double precio = 9999999.0;
        Integer stock = 50;
        Producto esperado = new Producto(nombre,precio,stock);
        Predicate<Double> precioMock = d -> true;
        Predicate<Integer> stockMock = d -> false;
        when(predicados.validarPrecio()).thenReturn(precioMock);
        when(predicados.validarStock()).thenReturn(stockMock);
        
        Producto p = logica.crear(nombre, precio, stock);
        assertEquals(esperado, p);
    }

    
    /**
     * Test of actualizar method, of class ProductoLogicaDefault.
     */
    @Test
    public void testActualizar() {
    }

    /**
     * Test of borrar method, of class ProductoLogicaDefault.
     */
    @Test
    public void testBorrar() {
    }

    /**
     * Test of modificarPrecio method, of class ProductoLogicaDefault.
     */
    @Test
    public void testModificarPrecio() throws Exception {
    }

    /**
     * Test of listar method, of class ProductoLogicaDefault.
     */
    @Test
    public void testListar() {
    }

    /**
     * Test of entrada method, of class ProductoLogicaDefault.
     */
    @Test
    public void testEntrada() throws Exception {
    }

    /**
     * Test of salida method, of class ProductoLogicaDefault.
     */
    @Test
    public void testSalida() throws Exception {
    }
    
}
