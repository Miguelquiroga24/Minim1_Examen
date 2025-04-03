package edu.upc.dsa;

import edu.upc.dsa.models.Avion;
import edu.upc.dsa.models.Maleta;
import edu.upc.dsa.models.Vuelo;
import org.apache.log4j.Logger;

import java.util.*;

public class ManagerImpl implements Manager {

    private static Manager instance;
    final static Logger logger = Logger.getLogger(ManagerImpl.class);

    List<Avion> aviones;
    List<Vuelo> vuelos;

    public ManagerImpl() {
        this.aviones = new ArrayList<>();
        this.vuelos = new ArrayList<>();
    }

    public static Manager getInstance() {
        if (instance == null) instance = new ManagerImpl();
        return instance;
    }
    @Override
    public int size() {
        logger.info("Número de vuelos registrados: " + vuelos.size());
        return vuelos.size();
    }
    @Override
    public Avion anadirOActualizarAvion(String idAvion, String modelo, String compania) {
        logger.info("anadirOActualizarAvion - Entrada: " + idAvion + ", " + modelo + ", " + compania);

        for (Avion a : aviones) {
            if (a.getIdAvion().equals(idAvion)) {
                a.setModelo(modelo);
                a.setCompania(compania);
                logger.info("Avión ya existía. Se han actualizado los datos.");
                return a;
            }



        }

        Avion nuevo = new Avion(idAvion, modelo, compania);
        aviones.add(nuevo);
        logger.info("Avión añadido correctamente.");
        return nuevo;
    }
    @Override
    public Vuelo anadirOActualizarVuelo(String idVuelo, Date horaSalida, Date horaLlegada, String idAvion, String origen, String destino) {
        logger.info("anadirOActualizarVuelo - Entrada: " + idVuelo + ", " + origen + "->" + destino);

        Avion avion = null;
        for (Avion a : aviones) {
            if (a.getIdAvion().equals(idAvion)) {
                avion = a;
                break;

            }
        }

        if (avion == null) {
            logger.error("Error: Avión con ID " + idAvion + " no encontrado.");
            return null;
        }

        for (Vuelo v : vuelos) {
            if (v.getIdVuelo().equals(idVuelo)) {
                v.setHoraSalida(horaSalida);
                v.setHoraLlegada(horaLlegada);
                v.setOrigen(origen);
                v.setDestino(destino);
                v.setAvion(avion);
                logger.info("Vuelo ya existente. Se han actualizado los datos.");
                return v;
            }
        }

        Vuelo nuevo = new Vuelo(idVuelo, horaSalida, horaLlegada, origen, destino, avion);
        vuelos.add(nuevo);
        logger.info("Vuelo añadido correctamente.");
        return nuevo;
    }

    @Override
    public Maleta facturarMaleta(String idVuelo, String usuario) {
        logger.info("facturarMaleta - Entrada: vuelo=" + idVuelo + ", usuario=" + usuario);

        for (Vuelo v : vuelos) {
            if (v.getIdVuelo().equals(idVuelo)) {
                Maleta m = new Maleta(usuario);
                v.getMaletas().push(m);
                logger.info("Maleta facturada con éxito. ID: " + m.getIdMaleta());
                return m;
            }
        }

        logger.error("Error: Vuelo con ID " + idVuelo + " no encontrado.");
        return null;

    }
    @Override
    public List<Maleta> obtenerMaletasDeVuelo(String idVuelo) {
        logger.info("obtenerMaletasDeVuelo - Entrada: vuelo=" + idVuelo);

        for (Vuelo v : vuelos) {
            if (v.getIdVuelo().equals(idVuelo)) {
                Stack<Maleta> pila = v.getMaletas();
                List<Maleta> descargadas = new ArrayList<>();

                while (!pila.isEmpty()) {
                    descargadas.add(pila.pop());  // LIFO
                }

                logger.info("Maletas descargadas correctamente. Total: " + descargadas.size());
                return descargadas;
            }
        }

        logger.error("Error: Vuelo con ID " + idVuelo + " no encontrado.");
        return null;


    }
}


