package model.fattura;

import model.carrello.CarrelloBean;
import model.carrello.CarrelloDAO;
import model.cliente.ClienteBean;
import model.cliente.ClienteDAO;
import model.hotel.HotelBean;
import model.hotel.HotelDAO;
import model.pacchetto.PacchettoBean;
import model.pacchetto.PacchettoDAO;
import model.restaurant.RestaurantBean;
import model.restaurant.RestaurantDAO;
import model.tour.LuogoBean;
import model.tour.LuogoDAO;
import model.tour.TourBean;
import model.tour.TourDAO;

import java.sql.SQLException;
import java.util.UUID;

public class FatturaTester {
    public static void main(String[] args) {

        //Bean usati
        ClienteBean clienteToSave = new ClienteBean("Giuseppe", "Cardaropoli", 10, "g.cardaropoli99@gmail.com", "pippo", false, "https://bit.ly/2LudIJS");
        LuogoBean luogoToSave = new LuogoBean(UUID.randomUUID(), "Castello MSS", "via roma 10", "M.S.S.", "Castello storico", "https://bit.ly/2WUYxOZ3");
        TourBean tourToSave = new TourBean(UUID.randomUUID(), luogoToSave.getId(), 99.99, 10);
        RestaurantBean restaurantToSave = new RestaurantBean(UUID.randomUUID(), "via stella 11", "Roma", "Mo te sdrumo", 14.99, "https://bit.ly/2AwF2VD", "089825770", "pipp8@hotmail.it");
        HotelBean hotelToSave = new HotelBean(UUID.randomUUID(), "Hotel Rosa", "via superga 9", "Roma", 50, 4, "https://bit.ly/2T6p7Ui", "hrosa@libero.it", "081903472");
        PacchettoBean pacchettoToSave = new PacchettoBean(UUID.randomUUID(), 0, clienteToSave.getEmail(), hotelToSave.getId(), 7, false, 4);

        //DAO usati
        ClienteDAO clienteDAO = new ClienteDAO();
        LuogoDAO luogoDAO = new LuogoDAO();
        TourDAO tourDAO = new TourDAO();
        RestaurantDAO restaurantDAO = new RestaurantDAO();
        HotelDAO hotelDAO = new HotelDAO();
        PacchettoDAO pacchettoDAO = new PacchettoDAO();
        CarrelloDAO carrelloDAO = new CarrelloDAO();
        FatturaDAO fatturaDAO = new FatturaDAO();


        System.out.println("Salvo Cliente");
        try {
            clienteDAO.doSave(clienteToSave);
        }
        catch (SQLException e){
            System.out.println("Errore salvataggio Cliente");
            e.printStackTrace();
        }

        System.out.println("\nSalvo Luogo");
        try {
            luogoDAO.doSave(luogoToSave);
        }
        catch (SQLException e){
            System.out.println("Errore salvataggio Luogo");
            e.printStackTrace();
        }

        System.out.println("\nSalvo Tour");
        try {
            tourDAO.doSave(tourToSave);
        }
        catch (SQLException e){
            System.out.println("Errore salvataggio Tour");
            e.printStackTrace();
        }

        System.out.println("\nSalvo Ristorante");
        try {
            restaurantDAO.doSave(restaurantToSave);
        }
        catch (SQLException e){
            System.out.println("Errore salvataggio Ristorante");
            e.printStackTrace();
        }

        System.out.println("\nSalvo Hotel");
        try {
            hotelDAO.doSave(hotelToSave);
        }
        catch (SQLException e){
            System.out.println("Errore salvataggio Hotel");
            e.printStackTrace();
        }

        System.out.println("\nSalvo Pacchetto base");
        try {
            pacchettoDAO.doSave(pacchettoToSave);
        }
        catch (SQLException e){
            System.out.println("Errore salvataggio Pacchetto");
            e.printStackTrace();
        }

        System.out.println("\nAggiungo ristorante al Pacchetto");
        try {
            pacchettoDAO.addRestaurant(pacchettoDAO.retrieveAll("","").get(0), restaurantToSave);
        }
        catch (SQLException e){
            System.out.println("Errore aggiunta ristorante al pacchetto");
            e.printStackTrace();
        }

        System.out.println("\nAggiungo Tour al Pacchetto");
        try {
            pacchettoDAO.addTour(pacchettoDAO.retrieveAll("","").get(0), tourToSave);
        }
        catch (SQLException e) {
            System.out.println("Errore aggiunta tour al pacchetto");
            e.printStackTrace();
        }

        System.out.println("\nAggiungo pacchetto a carrello");
        try {
            carrelloDAO.addPacchetto(carrelloDAO.retrieveByKey("g.cardaropoli99@gmail.com"), pacchettoDAO.retrieveAll("","").get(0));
        }
        catch (SQLException e) {
            System.out.println("Errore aggiunta pacchetto a carrello");
            e.printStackTrace();
        }


        System.out.println("\nCreazione fattura");
        try {
            FatturaBean fatturaToSave = new FatturaBean("g.cardaropoli99@gmail.com", carrelloDAO.retrieveByKey("g.cardaropoli99@gmail.com").getId(), carrelloDAO.retrieveByKey("g.cardaropoli99@gmail.com").getTotale());
            fatturaDAO.doSave(fatturaToSave);
        }
        catch (SQLException e) {
            System.out.println("Errore creazione fattura");
            e.printStackTrace();
        }

        System.out.println("\nTest RetrieveAll Fattura");
        try {
            System.out.println(fatturaDAO.retrieveByKey("g.cardaropoli99@gmail.com"));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
