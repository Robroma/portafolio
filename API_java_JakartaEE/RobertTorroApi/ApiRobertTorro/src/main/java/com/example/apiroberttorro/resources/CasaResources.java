package com.example.apiroberttorro.resources;

import com.example.apiroberttorro.dao.Ddbb;
import com.example.apiroberttorro.models.Casa;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/casa")
public class CasaResources {
    private Ddbb ddbb = null;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Casa getCasaPorId(@PathParam("id") int id) throws SQLException {
        ddbb = new Ddbb();
        Casa casa = ddbb.getCasaPorId(id);
        return casa;
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Casa> getCasas() throws SQLException {
        ddbb = new Ddbb();
        ArrayList<Casa> casas = ddbb.getCasas();
        return casas;
    }

    @PUT
    @Path("/putNuevaCasa")
    @Consumes(MediaType.APPLICATION_JSON)
    public void putNuevaCasa(Casa casa) throws SQLException {
        ddbb = new Ddbb();
        ddbb.insertCasa(casa);
    }

    @DELETE
    @Path("/{id}")
    public void deleteCasa(@PathParam("id") int id) throws SQLException {
        System.out.println("asd1"+id);
        ddbb = new Ddbb();
        ddbb.deleteCasa(id);

    }

}
