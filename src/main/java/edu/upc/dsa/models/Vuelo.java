package edu.upc.dsa.models;

import java.util.Date;
import java.util.List;
import java.util.Stack;

public class Vuelo {
    String idVuelo;
    Date horaSalida;
    Date horaLlegada;
    String origen;
    String destino;
    Avion avion;
    Stack<Maleta> maletas;

    public Vuelo(String idVuelo, Date horaSalida, Date horaLlegada, String origen, String destino, Avion avion) {
        this.idVuelo = idVuelo;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.origen = origen;
        this.destino = destino;
        this.avion = avion;
        this.maletas = new Stack<>();
    }

    public String getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(String idVuelo) {
        this.idVuelo = idVuelo;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Date getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(Date horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public Stack<Maleta> getMaletas() {
        return maletas;
    }

    public void setMaletas(Stack<Maleta> maletas) {
        this.maletas = maletas;
    }

    public static Vuelo buscarVueloPorId(List<Vuelo> lista, String idVuelo) {
        for (Vuelo v : lista) {
            if (v.getIdVuelo().equals(idVuelo)) return v;
        }
        return null;
    }
}
