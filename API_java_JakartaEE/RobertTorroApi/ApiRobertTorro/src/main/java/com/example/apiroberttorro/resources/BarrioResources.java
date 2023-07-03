package com.example.apiroberttorro.resources;

import com.example.apiroberttorro.dao.Ddbb;
import com.example.apiroberttorro.models.Barrio;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;

@Path("/barrio")
public class BarrioResources {
    private Ddbb ddbb;

    @POST
    @Path("/resetBarrioByName")
    public void resetBarrioByName(@QueryParam("name") String name) throws SQLException {
        ddbb = new Ddbb();
        ddbb.resetBarrioByName(name);
    }

    @GET
    @Path("/getBarrioByName")
    @Produces(MediaType.APPLICATION_JSON)
    public Barrio getBarrioByName(@QueryParam("name") String name) throws SQLException {
        ddbb = new Ddbb();
        Barrio barrio = new Barrio(name);
        barrio = ddbb.getBarrioByName(name);
        return barrio;
    }

}
