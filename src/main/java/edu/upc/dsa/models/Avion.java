package edu.upc.dsa.models;

public class Avion {
    String idAvion;
    String modelo;
    String compania;
    public Avion(String idAvion, String modelo, String compania) {
        this.idAvion = idAvion;
        this.modelo = modelo;
        this.compania = compania;
    }

    public String getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(String idAvion) {
        this.idAvion = idAvion;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public static Avion buscarAvionPorId(java.util.List<Avion> lista, String idAvion) {
        for (Avion a : lista) {
            if (a.getIdAvion().equals(idAvion)) return a;
        }
        return null;
    }
}
