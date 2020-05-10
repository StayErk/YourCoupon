package model.restaurant;

import java.util.UUID;

public class RestaurantBean {

    private UUID id;
    private String indirizzo;
    private String citta;
    private String nome;
    private double costo;
    private String immagine;
    private String numeroTelefono;
    private String email;

    public RestaurantBean() { }

    public RestaurantBean(UUID id, String indirizzo, String citta, String nome, double costo, String immagine, String numeroTelefono, String email) {
        this.id = id;
        this.indirizzo = indirizzo;
        this.citta = citta;
        this.nome = nome;
        this.costo = costo;
        this.immagine = immagine;
        this.numeroTelefono = numeroTelefono;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCitta() { return citta; }

    public void setCitta(String citta) { this.citta = citta; }

    public String getNome() { return nome; }

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

    @Override
    public String toString() {
        return "RestaurantBean{" +
                "id=" + id +
                ", indirizzo='" + indirizzo + '\'' +
                ", citta='" + citta + '\'' +
                ", nome='" + nome + '\'' +
                ", costo=" + costo +
                ", immagine='" + immagine + '\'' +
                ", numeroTelefono='" + numeroTelefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
