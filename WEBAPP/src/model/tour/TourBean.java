package model.tour;

import java.util.UUID;

public class TourBean {
    private UUID id;
    private UUID id_luogo;
    private Double costo;
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
}
