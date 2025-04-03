package edu.upc.dsa.services;

import edu.upc.dsa.Manager;
import edu.upc.dsa.ManagerImpl;
import edu.upc.dsa.models.Avion;
import edu.upc.dsa.models.Maleta;
import edu.upc.dsa.models.Vuelo;
import io.swagger.annotations.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@Api(value = "/aeroport", description = "Servicios de gestión aeronáutica")
@Path("/aeroport")
public class Service {

    private final Manager manager;

    public Service() {
        this.manager = ManagerImpl.getInstance();

        // Datos de ejemplo para pruebas
        if (manager.size() == 0) {
            manager.anadirOActualizarAvion("A1", "Boeing 747", "Iberia");
            manager.anadirOActualizarAvion("A2", "Airbus A320", "Vueling");

            Date ahora = new Date();
            Date luego = new Date(ahora.getTime() + 3600000); // +1h
            manager.anadirOActualizarVuelo("V1", ahora, luego, "A1", "Barcelona", "Madrid");
            manager.anadirOActualizarVuelo("V2", ahora, luego, "A2", "Madrid", "Sevilla");
        }
    }

    @POST
    @ApiOperation(value = "Añadir o modificar un avión", notes = "Si ya existe el ID, se actualiza")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Avión añadido/modificado correctamente"),
            @ApiResponse(code = 500, message = "Error interno")
    })
    @Path("/avions/{id}/{modelo}/{compania}")
    public Response crearOActualizarAvion(@PathParam("id") String id,
                                          @PathParam("modelo") String modelo,
                                          @PathParam("compania") String compania) {
        Avion avion = manager.anadirOActualizarAvion(id, modelo, compania);
        return Response.status(201).entity(avion).build();
    }

    @POST
    @ApiOperation(value = "Añadir o modificar un vuelo", notes = "Si ya existe, se actualizan los datos")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Vuelo añadido/modificado correctamente"),
            @ApiResponse(code = 404, message = "Avión no encontrado")
    })
    @Path("/vols/{idVuelo}/{origen}/{destino}/{idAvion}")
    public Response crearOActualizarVuelo(@PathParam("idVuelo") String idVuelo,
                                          @PathParam("origen") String origen,
                                          @PathParam("destino") String destino,
                                          @PathParam("idAvion") String idAvion) {
        Date ahora = new Date();
        Date llegada = new Date(ahora.getTime() + 3600000);
        Vuelo vuelo = manager.anadirOActualizarVuelo(idVuelo, ahora, llegada, idAvion, origen, destino);
        if (vuelo == null) return Response.status(404).entity("Avión no encontrado").build();
        return Response.status(201).entity(vuelo).build();
    }

    @POST
    @ApiOperation(value = "Facturar una maleta", notes = "Asocia una maleta al vuelo con el ID del usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Maleta facturada correctamente"),
            @ApiResponse(code = 404, message = "Vuelo no encontrado")
    })
    @Path("/facturar/{idVuelo}/{usuario}")
    public Response facturarMaleta(@PathParam("idVuelo") String idVuelo,
                                   @PathParam("usuario") String usuario) {
        Maleta maleta = manager.facturarMaleta(idVuelo, usuario);
        if (maleta == null) return Response.status(404).entity("Vuelo no encontrado").build();
        return Response.status(201).entity(maleta).build();
    }

    @GET
    @ApiOperation(value = "Obtener maletas facturadas", notes = "Devuelve maletas en orden de descarga (LIFO)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Maletas obtenidas"),
            @ApiResponse(code = 404, message = "Vuelo no encontrado")
    })
    @Path("/maletas/{idVuelo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerMaletas(@PathParam("idVuelo") String idVuelo) {
        List<Maleta> maletas = manager.obtenerMaletasDeVuelo(idVuelo);
        if (maletas == null) return Response.status(404).entity("Vuelo no encontrado").build();
        GenericEntity<List<Maleta>> entity = new GenericEntity<List<Maleta>>(maletas) {};
        return Response.status(200).entity(entity).build();
    }
}
