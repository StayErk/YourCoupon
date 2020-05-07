package model.restaurant;

import java.util.UUID;

public class RestaurantBean {

    private UUID id;
    private String indirizzo;
    private String nome;
    private double costo;
    private String immagine;
    private String numeroTelefono;
    private String email;

    public RestaurantBean() { }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public RestaurantBean(UUID id, String indirizzo, String nome, double costo, String immagine, String numeroTelefono, String email) {
        this.id = id;
        this.indirizzo = indirizzo;
        this.nome = nome;
        this.costo = costo;
        this.immagine = immagine;
        this.numeroTelefono = numeroTelefono;
        this.email = email;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
