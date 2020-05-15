package model.fattura;

import java.util.Objects;
import java.util.UUID;

public class FatturaBean {

    private UUID id;
    private String id_carrello;
    private double totale;

    public FatturaBean(UUID id, String id_carrello, double totale) {
        this.id = id;
        this.id_carrello = id_carrello;
        this.totale = totale;
    }

    public FatturaBean() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getId_carrello() {
        return id_carrello;
    }

    public void setId_carrello(String id_carrello) {
        this.id_carrello = id_carrello;
    }

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FatturaBean that = (FatturaBean) o;
        return id.equals(that.id);
    }

    @Override
    public String toString() {
        return  getClass().getName() +"{" +
                "id='" + id + '\'' +
                ", id_carrello='" + id_carrello + '\'' +
                ", totale=" + totale +
                '}';
    }
}

