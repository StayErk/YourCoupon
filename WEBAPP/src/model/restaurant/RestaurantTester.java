package model.restaurant;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class RestaurantTester {

    public static void main(String[] args) {
        ArrayList<RestaurantBean> lista;
        RestaurantDAO restaurantDAO = new RestaurantDAO();

        System.out.println("RetrieveAll vuoto");
        try {
            lista = new ArrayList<>(restaurantDAO.retrieveAll("", ""));
            System.out.println("Lista ristoranti dopo retrieve: ");
            for (RestaurantBean r : lista) {
                System.out.println(r);
            }
        }
        catch (SQLException throwables) {
            System.out.println("SQL Exception su retrieveAll");
            throwables.printStackTrace();
        }

        System.out.println("\nRetrieveAll con filter (citta)");
        try {
            lista = new ArrayList<>(restaurantDAO.retrieveAll("citta", ""));
            System.out.println("Lista ristoranti dopo retrieve: ");
            for (RestaurantBean r : lista) {
                System.out.println(r);
            }
        }
        catch (SQLException throwables) {
            System.out.println("SQL Exception su retrieveAll");
            throwables.printStackTrace();
        }

        System.out.println("\nRetrieveAll con filter (costo) e order (DESC)");
        try {
            lista = new ArrayList<>(restaurantDAO.retrieveAll("costo", "DESC"));
            System.out.println("Lista ristoranti dopo retrieve: ");
            for (RestaurantBean r : lista) {
                System.out.println(r);
            }
        }
        catch (SQLException throwables) {
            System.out.println("SQL Exception su retrieveAll");
            throwables.printStackTrace();
        }

        System.out.println("\nRetrieveByKey");
        try {
            System.out.println("Risultato Query: " + restaurantDAO.retrieveByKey(UUID.fromString("272ca703-b314-408b-aa64-f8d5cb2f0b56")));
        }
        catch (SQLException throwables){
            System.out.println("SQL EXCEPTION su RetriveByKey");
            throwables.printStackTrace();
        }

        System.out.println("\ndoSave");
        try {
            RestaurantBean toSave = new RestaurantBean(UUID.randomUUID(), "via rosa 15", "Angri", "Pepp o Vitarell", 39.99, "https://bit.ly/2znnWZy", "089825770", "rzaccagnino@unisa.it");
            restaurantDAO.doSave(toSave);
            System.out.println("Ristorante salvato: " + toSave);
            lista = new ArrayList<>(restaurantDAO.retrieveAll("", ""));
            System.out.println("Lista ristoranti dopo il doSave: ");
            for(RestaurantBean r : lista){
                System.out.println(r);
            }
        }
        catch (SQLException throwables){
            System.out.println("SQL EXCEPTION su doSave");
            throwables.printStackTrace();
        }

        System.out.println("\ndoDelete");
        try {
            System.out.println("Cancellazione Ristorante con id: '25a22c6c-3e09-4859-94bd-6e09ec5dcdad'");
            restaurantDAO.doDelete(restaurantDAO.retrieveByKey(UUID.fromString("25a22c6c-3e09-4859-94bd-6e09ec5dcdad")));
            lista = new ArrayList<>(restaurantDAO.retrieveAll("", ""));
            System.out.println("Lista ristoranti dopo il doDelete: ");
            for(RestaurantBean r : lista){
                System.out.println(r);
            }
        }
        catch (SQLException throwables){
            System.out.println("SQL EXCEPTION su doDelete");
            throwables.printStackTrace();
        }

        System.out.println("\ndoUpdate");
        try {
            RestaurantBean toUpdate = restaurantDAO.retrieveByKey(UUID.fromString("af34c083-7d59-49aa-98bf-8e7180cb023a"));
            System.out.println("Il ristorante che sarà modificato è: " + toUpdate);
            System.out.println("Andremo a cambiare il costo da " + toUpdate.getCosto() + " in 27.50");
            System.out.println("Andremo a cambiare il nome da " + toUpdate.getNome() + " in Nuovo Mulino");
            System.out.println("Andremo a cambiare l'email da " + toUpdate.getEmail() + " in uvaccaro@unisa.it");
            toUpdate.setCosto(27.50);
            toUpdate.setNome("Nuovo Mulino");
            toUpdate.setEmail("uvaccaro@unisa.it");
            restaurantDAO.doUpdate(toUpdate);
            System.out.println("dopo l'update: " + restaurantDAO.retrieveByKey(toUpdate.getId()));
        }
        catch (SQLException throwables){
            System.out.println("SQL EXCEPTION su doUpdate");
            throwables.printStackTrace();
        }


    }
}
