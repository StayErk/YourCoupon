package model.carrello;

import model.cliente.ClienteBean;
import model.cliente.ClienteDAO;
import model.pacchetto.PacchettoBean;
import model.pacchetto.PacchettoDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CarrelloTester {
    public static void main(String[] args) {
        ClienteDAO clienteDAO = new ClienteDAO();
        PacchettoDAO pacchettoDAO = new PacchettoDAO();
        CarrelloDAO carrelloDAO = new CarrelloDAO();
        ClienteBean clienteBean = null;
        PacchettoBean pacchettoBean = null;
        CarrelloBean carrelloBean = null;

        System.out.println("Recupero un Cliente");
        try {
            clienteBean= clienteDAO.retrieveAll("", "").get(1);
            System.out.println(clienteBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Recupero un Pacchetto");
        try {
            pacchettoBean = pacchettoDAO.retrieveAll("", "").get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Retrieve By Key Del carrello");
        try {
            carrelloBean =carrelloDAO.retrieveByKey(clienteBean.getEmail());
            System.out.println(carrelloBean);
        } catch (SQLException e) {

            e.printStackTrace();
        }

        System.out.println("Retrieve All del carrello");
        try {
            ArrayList<CarrelloBean> carrelli = new ArrayList<>(carrelloDAO.retrieveAll("", ""));
            System.out.println(carrelli);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("addPacchetto");
        try {
            carrelloDAO.addPacchetto(carrelloBean, pacchettoBean);
            carrelloBean = carrelloDAO.retrieveByKey(carrelloBean.getId());
            System.out.println(carrelloBean);
        } catch (SQLException e) {
            System.out.println("Errore add pacchetto");
            e.printStackTrace();
        }

        System.out.println("removePacchetto");
        try {
            carrelloDAO.removePacchetto(carrelloBean, pacchettoBean);
            carrelloBean = carrelloDAO.retrieveByKey(carrelloBean.getId());
            System.out.println(carrelloBean);
        } catch (SQLException e) {
            System.out.println("Errore removePacchetto");
            e.printStackTrace();
        }
    }
}
