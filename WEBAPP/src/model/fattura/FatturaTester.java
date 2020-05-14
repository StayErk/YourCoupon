package model.fattura;

import model.carrello.CarrelloBean;
import model.carrello.CarrelloDAO;
import model.cliente.ClienteBean;
import model.cliente.ClienteDAO;
import model.pacchetto.PacchettoBean;
import model.pacchetto.PacchettoDAO;

import java.sql.SQLException;

public class FatturaTester {
    public static void main(String[] args) {
        FatturaBean fattura = new FatturaBean();
        CarrelloBean carrello = new CarrelloBean();
        ClienteBean cliente = new ClienteBean();
        PacchettoBean pacchetto1 = new PacchettoBean();
        PacchettoBean pacchetto2 = new PacchettoBean();
        FatturaDAO fatturaDAO = new FatturaDAO();
        CarrelloDAO carrelloDAO = new CarrelloDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        PacchettoDAO pacchettoDAO = new PacchettoDAO();

        System.out.println("Recupero un Cliente");
        try {
            cliente= clienteDAO.retrieveAll("", "").get(0);
            System.out.println(cliente);
        }
        catch (SQLException e) {
            System.out.println("Non sono riuscito a prendere Cliente1");
            e.printStackTrace();
        }
        System.out.println("\nRecupero un Pacchetto1");
        try {
            pacchetto1 = pacchettoDAO.retrieveAll("", "").get(0);
        }
        catch (SQLException e) {
            System.out.println("\nNon sono riuscito a recuperare pacchetto1");
            e.printStackTrace();
        }
        try {
            pacchetto2 = pacchettoDAO.retrieveAll("", "").get(1);
        } catch (SQLException e) {
            System.out.println("\nNon sono riuscito a recuperare pacchetto2");
            e.printStackTrace();
        }

    }
}
