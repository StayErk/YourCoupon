package model.hotel;

import java.util.Objects;
import java.util.UUID;

public class HotelBean {
    private UUID id;
    private String nome;
    private String indirizzo;
    private String citta;
    private double costoNotte;
    private int stelle;
    private String immagine;
    private String email;
    private String numeroTelefono;

    public HotelBean() {
    }

    public HotelBean(UUID id, String nome, String indirizzo,  String citta, double costoNotte, int stelle, String immagine, String email, String numeroTelefono) {
        this.id = id;
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.citta = citta;
        this.costoNotte = costoNotte;
        this.stelle = stelle;
        this.immagine = immagine;
        this.email = email;
        this.numeroTelefono = numeroTelefono;
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

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public double getCostoNotte() {
        return costoNotte;
    }

    public void setCostoNotte(double costoNotte) {
        this.costoNotte = costoNotte;
    }

    public int getStelle() {
        return stelle;
    }

    public void setStelle(int stelle) {
        this.stelle = stelle;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }


    @Override
    public String toString() {
        return getClass().getName() + "{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", costoNotte=" + costoNotte +
                ", stelle=" + stelle +
                ", immagine='" + immagine + '\'' +
                ", email='" + email + '\'' +
                ", numeroTelefono='" + numeroTelefono + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        HotelBean hotelBean = (HotelBean) o;
        return this.id.equals(hotelBean.id);
    }
}
