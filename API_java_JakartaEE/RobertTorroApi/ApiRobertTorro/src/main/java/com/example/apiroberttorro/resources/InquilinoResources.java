package com.example.apiroberttorro.resources;

import com.example.apiroberttorro.dao.Ddbb;
import com.example.apiroberttorro.models.Inquilino;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/inquilino")
public class InquilinoResources {
    private Ddbb ddbb;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Inquilino getInquilinoPorId(@PathParam("id") int id) throws SQLException {
        ddbb = new Ddbb();
        Inquilino inquilino = ddbb.getInquilinoPorId(id);
        return inquilino;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Inquilino> getInquilinos() throws SQLException {
        ddbb = new Ddbb();
        List<Inquilino> inquilinos = ddbb.getInquilinos();
        return inquilinos;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putNuevoInquilino(Inquilino inquilino) throws SQLException {
        ddbb = new Ddbb();
        ddbb.putNuevoInquilino(inquilino);
    }

    @Path("/{id}")
    @DELETE
    public void deleteInquilino(@PathParam("id") int id) throws SQLException {
        ddbb = new Ddbb();
        ddbb.deleteInquilino(id);
    }

}
