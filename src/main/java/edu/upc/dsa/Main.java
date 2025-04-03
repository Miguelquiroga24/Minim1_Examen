package edu.upc.dsa;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import io.swagger.jaxrs.config.BeanConfig;

import java.io.IOException;
import java.net.URI;

public class Main {
    public static final String BASE_URI = "http://localhost:8080/";

    public static HttpServer startServer() {
        // Creamos la configuración de recursos de Jersey
        final ResourceConfig rc = new ResourceConfig()
                .packages("edu.upc.dsa.services");

        // Configuramos Swagger
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/aeroport"); // Coincide con @Path("/aeroport") del servicio        beanConfig.setResourcePackage("edu.upc.dsa.services");
        beanConfig.setPrettyPrint(true);
        beanConfig.setScan(true);

        rc.packages("io.swagger.jaxrs.listing"); // importante para que Swagger funcione

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        server.getServerConfiguration().addHttpHandler(new StaticHttpHandler("swagger"), "/swagger");

        System.out.println("Servidor iniciado en: " + BASE_URI);
        System.out.println("Swagger disponible en: " + BASE_URI + "swagger");

        System.in.read(); // Para mantener el servidor activo hasta que pulses una tecla
        server.shutdownNow();
    }
}
