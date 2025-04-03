package edu.upc.dsa.models;

import java.util.UUID;

public class Maleta {
    String idMaleta;
    String usuario;

    public Maleta(String usuario) {
        this.usuario = usuario;
        this.idMaleta = UUID.randomUUID().toString();

    }
    public String getIdMaleta() {
        return idMaleta;
    }

    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
