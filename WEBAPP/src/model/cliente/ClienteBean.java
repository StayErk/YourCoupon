package model.cliente;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ClienteBean {
    private String nome;
    private String cognome;
    private int puntiViaggio;
    private String email;
    private byte[] password;
    private boolean admin;
    private String immagine;

    // costruttore vuoto
    public ClienteBean() {}

    // costruttore con pass già codificata
    public ClienteBean(String nome, String cognome, int puntiViaggio, String email, byte[] pass, boolean admin, String immagine) {
        this.nome = nome;
        this.cognome = cognome;
        this.puntiViaggio = puntiViaggio;
        this.email = email;
        this.immagine = immagine;
        this.admin = admin;
        password = pass.clone();
    }

    // costruttore con pass Stringa da codificare
    public ClienteBean(String nome, String cognome, int puntiViaggio, String email, String pass, String immagine) {
        this.nome = nome;
        this.cognome = cognome;
        this.puntiViaggio = puntiViaggio;
        this.email = email;
        this.admin = admin;
        this.immagine = immagine;

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(pass.getBytes("UTF-8"));

            password = hash.clone();
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }

    public void setCognome(String cognome) { this.cognome = cognome; }

    public int getPuntiViaggio() { return puntiViaggio; }

    public void setPuntiViaggio(int puntiViaggio) { this.puntiViaggio = puntiViaggio; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public boolean isAdmin() { return admin; }

    public void setAdmin(boolean admin) { this.admin = admin; }

    public String getImmagine() { return immagine; }

    public void setImmagine(String immagine) { this.immagine = immagine; }

    public byte[] getPassword() { return password; }

    public void setPassword(String pass) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(pass.getBytes("UTF-8"));

            password = hash.clone();
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


}
