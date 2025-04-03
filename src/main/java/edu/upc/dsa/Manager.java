package edu.upc.dsa;

import edu.upc.dsa.models.Avion;
import edu.upc.dsa.models.Maleta;
import edu.upc.dsa.models.Vuelo;

import java.util.Date;
import java.util.List;

public interface Manager {

    int size();

    Avion anadirOActualizarAvion(String idAvion, String modelo, String compania);

    Vuelo anadirOActualizarVuelo(String idVuelo, Date horaSalida, Date horaLlegada, String origen, String destino, String idAvion);

    Maleta facturarMaleta(String idVuelo, String usuario);

    List<Maleta> obtenerMaletasDeVuelo(String idVuelo);
}
