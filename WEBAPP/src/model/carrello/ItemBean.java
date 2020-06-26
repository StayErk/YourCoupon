package model.carrello;

import model.pacchetto.PacchettoBean;

public class ItemBean {
    private String cittaDestinazione;
    private PacchettoBean pacchetto;

    public ItemBean() {
    }


    public ItemBean(String cittaDestinazione, PacchettoBean pacchetto) {
        this.cittaDestinazione = cittaDestinazione;
        this.pacchetto = pacchetto;
    }

    public String getCittaDestinazione() {
        return cittaDestinazione;
    }

    public void setCittaDestinazione(String cittaDestinazione) {
        this.cittaDestinazione = cittaDestinazione;
    }

    public PacchettoBean getPacchetto() {
        return pacchetto;
    }

    public void setPacchetto(PacchettoBean pacchetto) {
        this.pacchetto = pacchetto;
    }

    @Override
    public String toString() {
        return "ItemBean{" +
                "cittaDestinazione='" + cittaDestinazione + '\'' +
                ", pacchetto=" + pacchetto +
                '}';
    }
}


