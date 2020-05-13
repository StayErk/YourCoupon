package model.pacchetto;

import model.cliente.ClienteBean;
import model.cliente.ClienteDAO;
import model.hotel.HotelBean;
import model.hotel.hotelDAO;
import model.restaurant.RestaurantBean;
import model.restaurant.RestaurantDAO;
import model.tour.TourBean;
import model.tour.TourDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class PacchettoTester {
    public static void main(String[] args) {
        System.out.println("Tester Operazioni CRUD Pacchetto");
        PacchettoDAO pacchettoDAO = new PacchettoDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        RestaurantDAO restaurantDAO = new RestaurantDAO();
        TourDAO tourDAO = new TourDAO();
        hotelDAO hotelDAO = new hotelDAO();
        HotelBean hotelBean = null;
        ClienteBean clienteBean = null;
        RestaurantBean restaurantBean = null;
        TourBean tourBean = null;

        System.out.println("Creazione Cliente");
        try {
            clienteBean = clienteDAO.retrieveByKey("email@unisa.it");
            System.out.println("selezionato cliente: " + clienteBean);
        } catch (SQLException e){
            System.out.println("SQL Exception nella creazione del cliente");
        }


        try {
            System.out.println("Seleziono Hotel dal DB");
            hotelBean = hotelDAO.retrieveAll("", "").get(0);
            System.out.println("selezionato hotel: " + hotelBean);
        } catch (SQLException e ) {
            System.out.println("SQL Exception nella query dell'hotel");
        }

        PacchettoBean pacchettoBean = new PacchettoBean(UUID.fromString("9c6ba964-2afd-4921-804e-3dd55808f774"),
                0,
                clienteBean.getEmail(),
                hotelBean.getId(),
                2,
                true,
                2);


        System.out.println("Eseguo la doSave di pacchettoBean");
        try {
            pacchettoDAO.doSave(pacchettoBean);
            System.out.println("Salvataggio del pacchetto riuscito");
        } catch (SQLException e) {
            System.out.println("SQ: Exception su doSave pacchetto");
            //e.printStackTrace();
        }

        System.out.println("Eseguo la Retrieve All dei pacchetti");
        try {
            List<PacchettoBean> pacchettoBeans = pacchettoDAO.retrieveAll("", "");
            System.out.println(pacchettoBeans);
        } catch (SQLException e) {
            System.out.println("SQ: Exception su retrieveAll pacchetto");
            e.printStackTrace();
        }

        System.out.println("Eseguo la retrieve by key");
        try {
            pacchettoBean = pacchettoDAO.retrieveByKey(UUID.fromString("9c6ba964-2afd-4921-804e-3dd55808f774"));
            System.out.println(pacchettoBean);
        } catch (SQLException e) {
            System.out.println("SQ: Exception su retrieveByKey pacchetto");
            e.printStackTrace();
        }

        System.out.println("Eseguo la doUpdate ");
        try {
            pacchettoBean.setDurata(3);
            pacchettoDAO.doUpdate(pacchettoBean);

        } catch (SQLException e) {
            System.out.println("SQ: Exception su doUpdate pacchetto");
            e.printStackTrace();
        }

        try {
            pacchettoBean = pacchettoDAO.retrieveByKey(UUID.fromString("9c6ba964-2afd-4921-804e-3dd55808f774"));
            System.out.println("Costo aspettato: 546, costo aggiornato: " + pacchettoBean.getCosto());
        } catch (SQLException e) {
            System.out.println("SQ: Exception su retrieveByKey pacchetto");
            e.printStackTrace();
        }

        System.out.println("Recupero un ristorante dal DB");
        try {
            restaurantBean = restaurantDAO.retrieveAll("", "").get(0);
        } catch (SQLException e ) {
            System.out.println("Retrieve sul ristorante fallita");
        }

        System.out.println("Aggiungo ristorante al pacchetto");
        try {
            pacchettoBean = pacchettoDAO.retrieveByKey(UUID.fromString("9c6ba964-2afd-4921-804e-3dd55808f774"));
            pacchettoDAO.addRestaurant(pacchettoBean, restaurantBean);
            System.out.println("Add Restaurant riuscita");
        } catch (SQLException e ) {
            System.out.println("addRestaurant fallita");
            e.printStackTrace();
        }
        try {
            pacchettoBean = pacchettoDAO.retrieveByKey(UUID.fromString("9c6ba964-2afd-4921-804e-3dd55808f774"));
            System.out.println("Nuovo costo aspettato 716, ottenuto " + pacchettoBean.getCosto());
        } catch (SQLException e) {
            System.out.println("SQ: Exception su retrieveByKey pacchetto");
            e.printStackTrace();
        }

        System.out.println("Recupero Tour dal DB");
        try {
            tourBean = tourDAO.retrieveAll("", "").get(0);
        } catch (SQLException e) {
            System.out.println("Restrieve su tour fallita");
        }

        System.out.println("Aggiunta Tour al pacchetto");
        try {
            pacchettoBean = pacchettoDAO.retrieveByKey(UUID.fromString("9c6ba964-2afd-4921-804e-3dd55808f774"));
            pacchettoDAO.addTour(pacchettoBean, tourBean);
            System.out.println("Add Tour riuscita");
        } catch (SQLException e ) {
            System.out.println("addTour riuscita");
            e.printStackTrace();
        }

        try {
            pacchettoBean = pacchettoDAO.retrieveByKey(UUID.fromString("9c6ba964-2afd-4921-804e-3dd55808f774"));
            System.out.println("Nuovo costo aspettato 875, ottenuto " + pacchettoBean.getCosto());
        } catch (SQLException e) {
            System.out.println("SQ: Exception su retrieveByKey pacchetto");
            e.printStackTrace();
        }


        try {
            System.out.println("Remove Restaurants");
            pacchettoDAO.removeRestaurant(pacchettoBean, restaurantBean);
            System.out.println("Remove Restaurant riuscita");
        } catch (SQLException e) {
            System.out.println("removeRestaurant non funziona");
            e.printStackTrace();
        }

        try {
            pacchettoBean = pacchettoDAO.retrieveByKey(UUID.fromString("9c6ba964-2afd-4921-804e-3dd55808f774"));
            System.out.println("Nuovo costo aspettato 705.92, ottenuto " + pacchettoBean.getCosto());
        } catch (SQLException e) {
            System.out.println("SQ: Exception su retrieveByKey pacchetto");
            e.printStackTrace();
        }


        System.out.println("doDelete su pacchetto");
        try {
            pacchettoDAO.doDelete(pacchettoBean);
            System.out.println("doDelete Riuscita");
        } catch (SQLException e){
            System.out.println("doDelete non funziona");
            e.printStackTrace();
        }
    }
}
