/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.gov.santafe.meduc.arq.app.productos.service;

import ar.gov.santafe.meduc.arq.app.productos.excepciones.PrecioInvalidoException;
import ar.gov.santafe.meduc.arq.app.productos.excepciones.StockInicialException;
import ar.gov.santafe.meduc.arq.app.productos.logica.ProductoLogica;
import ar.gov.santafe.meduc.arq.app.productos.modelo.Producto;
import java.io.StringReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author mdominguez
 */
@Path("producto")
@RequestScoped
public class ProductoResource {

    @Context
    private UriInfo context;

    @Inject 
    ProductoLogica logica;
    /**
     * Creates a new instance of ApiResource
     */
    public ProductoResource() {
    }

    /**
     * Retrieves representation of an instance of ar.gov.santafe.meduc.arq.app.productos.dao.ProductoResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson() {
        List<Producto> lista = logica.listar();
        JsonArrayBuilder array2 = Json.createArrayBuilder();
        //JsonArray array = Json.createArrayBuilder()
        for(Producto p : lista){
            JsonObject object = Json.createObjectBuilder().add("id",p.getId()).add("nombre",p.getNombre()).add("precio",p.getPrecio()).add("stock",p.getStock()).build();
            array2.add(object);
        }                        
        return Response.ok(array2.build().toString()).build();
    }

    /**
     * PUT method for updating or creating an instance of ProductoResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putJson(String content) {
        Response r = null;
        try (JsonReader jsonReader = Json.createReader(new StringReader(content))) {
            JsonObject obj = jsonReader.readObject();
            try {
                logica.crear(obj.getString("nombre"), obj.getJsonNumber("precio").doubleValue(), obj.getInt("stock"));
                r=Response.ok().build();
            } catch (PrecioInvalidoException ex) {
                Logger.getLogger(ProductoResource.class.getName()).log(Level.SEVERE, null, ex);
                r= Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
            } catch (StockInicialException ex) {
                Logger.getLogger(ProductoResource.class.getName()).log(Level.SEVERE, null, ex);
                r= Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
            }
        }
        return r;
    }
}
