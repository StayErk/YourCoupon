package model.tour;

import model.Bean;

import java.util.Objects;
import java.util.UUID;

public class TourBean implements Bean, Cloneable {
    private UUID id;
    private UUID id_luogo;
    private double costo;
    private int partecipanti;

    public TourBean(){

    }

    public TourBean(UUID id, UUID id_luogo, Double costo, int partecipanti) {
        this.id = id;
        this.id_luogo = id_luogo;
        this.costo = costo;
        this.partecipanti = partecipanti;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId_luogo() {
        return id_luogo;
    }

    public void setId_luogo(UUID id_luogo) {
        this.id_luogo = id_luogo;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public int getPartecipanti() {
        return partecipanti;
    }

    public void setPartecipanti(int partecipanti) {
        this.partecipanti = partecipanti;
    }

    @Override
    public String toString() {
        return getClass().getName() + "{" +
                "id=" + id +
                ", id_luogo=" + id_luogo +
                ", costo=" + costo +
                ", partecipanti=" + partecipanti +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TourBean tourBean = (TourBean) o;
        return id.equals(tourBean.id);
    }
}
