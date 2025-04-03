package edu.upc.dsa;

import edu.upc.dsa.models.Avion;
import edu.upc.dsa.models.Maleta;
import edu.upc.dsa.models.Vuelo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ManagerImplTest {

    private Manager manager;

    @Before
    public void setUp() {
        this.manager = ManagerImpl.getInstance();
    }

    @After
    public void tearDown() {

        ((ManagerImpl) manager).aviones.clear();
        ((ManagerImpl) manager).vuelos.clear();
    }

    @Test
    public void testAnadirOActualizarAvion() {
        Avion avion = manager.anadirOActualizarAvion("A1", "Airbus A320", "Vueling");
        assertNotNull(avion);
        assertEquals("A1", avion.getIdAvion());
        assertEquals("Airbus A320", avion.getModelo());
        assertEquals("Vueling", avion.getCompania());

        // Actualizamos el avión
        avion = manager.anadirOActualizarAvion("A1", "Boeing 737", "Ryanair");
        assertEquals("Boeing 737", avion.getModelo());
        assertEquals("Ryanair", avion.getCompania());
    }

    @Test
    public void testAnadirOActualizarVuelo() {
        manager.anadirOActualizarAvion("A2", "Boeing 747", "Iberia");

        Date salida = new Date();
        Date llegada = new Date(salida.getTime() + 2 * 60 * 60 * 1000); // +2h

        Vuelo vuelo = manager.anadirOActualizarVuelo("V1", salida, llegada, "A2", "Barcelona", "Madrid");
        assertNotNull(vuelo);
        assertEquals("V1", vuelo.getIdVuelo());
        assertEquals("Barcelona", vuelo.getOrigen());
        assertEquals("Madrid", vuelo.getDestino());
    }

    @Test
    public void testFacturarMaletaYOrdenDescarga() {
        manager.anadirOActualizarAvion("A3", "A380", "Lufthansa");

        Date salida = new Date();
        Date llegada = new Date(salida.getTime() + 3 * 60 * 60 * 1000); // +3h

        manager.anadirOActualizarVuelo("V2", salida, llegada, "A3", "Paris", "Tokyo");

        Maleta m1 = manager.facturarMaleta("V2", "usuario1");
        Maleta m2 = manager.facturarMaleta("V2", "usuario2");
        Maleta m3 = manager.facturarMaleta("V2", "usuario3");

        assertNotNull(m1);
        assertNotNull(m2);
        assertNotNull(m3);

        List<Maleta> maletas = manager.obtenerMaletasDeVuelo("V2");

        assertEquals(3, maletas.size());
        assertEquals("usuario3", maletas.get(0).getUsuario()); // última en entrar → primera en salir
        assertEquals("usuario2", maletas.get(1).getUsuario());
        assertEquals("usuario1", maletas.get(2).getUsuario());
    }

    @Test
    public void testVueloConAvionInexistente() {
        Date salida = new Date();
        Date llegada = new Date(salida.getTime() + 1 * 60 * 60 * 1000); // +1h

        Vuelo vuelo = manager.anadirOActualizarVuelo("V3", salida, llegada, "NO_EXISTE", "Londres", "Berlin");
        assertNull(vuelo); // no debería poderse crear el vuelo
    }

    @Test
    public void testFacturarMaletaEnVueloInexistente() {
        Maleta maleta = manager.facturarMaleta("NO_EXISTE", "usuarioX");
        assertNull(maleta); // no debería facturarse
    }
}
