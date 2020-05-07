package model.tour;

import java.util.UUID;

public class LuogoBean {

    private UUID id;
    private String nome;
    private String indirizzo;
    private String descrizione;
    private String immagine;

    public LuogoBean(){

    }

    public LuogoBean(UUID id, String nome, String indirizzo, String descrizione, String immagine) {
        this.id = id;
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.descrizione = descrizione;
        this.immagine = immagine;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }
}