package model.pacchetto;

import java.util.UUID;

public class PacchettoBean {
    private UUID id;
    private double costo;
    private String id_cliente;
    private UUID id_struttura;
    private int durata;
    private boolean predefinito;

    public PacchettoBean() {
    }

    public PacchettoBean(UUID id, double costo, String id_cliente, UUID id_struttura, int durata, boolean predefinito) {
        this.id = id;
        this.costo = costo;
        this.id_cliente = id_cliente;
        this.id_struttura = id_struttura;
        this.durata = durata;
        this.predefinito = predefinito;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public UUID getId_struttura() {
        return id_struttura;
    }

    public void setId_struttura(UUID id_struttura) {
        this.id_struttura = id_struttura;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public boolean isPredefinito() {
        return predefinito;
    }

    public void setPredefinito(boolean predefinito) {
        this.predefinito = predefinito;
    }

    @Override
    public String toString() {
        return getClass().getName() + "{" +
                "id=" + id +
                ", costo=" + costo +
                ", id_cliente='" + id_cliente + '\'' +
                ", id_struttura=" + id_struttura +
                ", durata=" + durata +
                ", predefinito=" + predefinito +
                '}';
    }
}
