package control;

import com.google.gson.Gson;
import model.Bean;
import model.carrello.CarrelloBean;
import model.carrello.CarrelloDAO;
import model.cliente.ClienteBean;
import model.hotel.HotelDAO;
import model.pacchetto.PacchettoBean;
import model.pacchetto.PacchettoDAO;
import model.pacchetto.PacchettoRaw;
import model.restaurant.RestaurantBean;
import model.tour.LuogoDAO;
import model.tour.TourBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@WebServlet("/PacchettiServlet")
public class PacchettiServlet extends javax.servlet.http.HttpServlet {
    protected synchronized void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        HttpSession session = request.getSession(true);
        String idCliente = "";
        CarrelloBean carrelloBean = null;
        Boolean predefinito = false;
        CarrelloDAO carrelloDAO = new CarrelloDAO();
        if(session != null) {
            ClienteBean bean = (ClienteBean) session.getAttribute("user");

            if(bean != null) {
                idCliente = bean.getEmail();
                if(bean.isAdmin()) predefinito = true;
                try {
                    carrelloBean = carrelloDAO.retrieveByKey(idCliente);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        Gson gson = new Gson();
        PacchettoRaw pacchettoRaw = gson.fromJson(request.getParameter("pacchetto"), PacchettoRaw.class);

        PacchettoDAO pacchettoDAO = new PacchettoDAO();
        PacchettoBean toInsert = new PacchettoBean();

        toInsert.setDurata(pacchettoRaw.getDurata());
        toInsert.setCosto(pacchettoRaw.getCosto());
        toInsert.setPersone(pacchettoRaw.getPersone());
        toInsert.setId_cliente(idCliente);
        toInsert.setId_struttura(pacchettoRaw.getHotel().getId());
        toInsert.setPredefinito(predefinito);
        System.out.println("Prima di ristoranti");
        try {
            pacchettoDAO.doSave(toInsert);
            for(RestaurantBean restaurant : pacchettoRaw.getRistoranti()) {
                pacchettoDAO.addRestaurant(toInsert, restaurant);
            }
            System.out.println("Dopo di ristoranti");
            for (TourBean tour : pacchettoRaw.getTour()) {
                pacchettoDAO.addTour(toInsert, tour);
            }
            System.out.println("Dopo tour");
            carrelloDAO.addPacchetto(carrelloBean, toInsert);
            System.out.println("Inserito nel carrello");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Prima di forward");

        response.sendRedirect(response.encodeURL("./user/chart.jsp"));
        /*
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/chart.jsp");
        dispatcher.forward(request, response);*/

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PacchettoDAO modelDAO = new PacchettoDAO();
        HotelDAO hotelDAO = new HotelDAO();
        String action = request.getParameter("action");
        String path;
        switch (action){
            case "retrieve":
                try {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    HashMap<UUID, ArrayList<Bean>> hashPacchetti = new HashMap<>();
                    ArrayList<Bean> componenti = new ArrayList<>();
                    String persone = request.getParameter("persone");
                    ArrayList<PacchettoBean> pacchetti = new ArrayList<>(modelDAO.retrieveAll("",""));
                    for(PacchettoBean p: pacchetti){

                        componenti.add(p);
                        componenti.add(hotelDAO.retrieveByKey(p.getId_struttura()));
                        hashPacchetti.put(p.getId(), (ArrayList<Bean>) componenti.clone());
                        System.out.println(componenti);
                        componenti.clear();
                    }

                    Gson gson = new Gson();
                    String hashmap = gson.toJson(hashPacchetti);
                    response.setStatus(200);

                    response.getWriter().write(hashmap);

                } catch (SQLException e) {
                    request.setAttribute("errore", e.toString());
                    e.printStackTrace();
                }
                break;
            case "bykey":
                String key = request.getParameter("key");
                if(key != null && !key.equals("")){
                    try {
                        ArrayList<Bean> beans = new ArrayList<>();
                        ArrayList<RestaurantBean> extraRestaurants = new ArrayList<>();
                        ArrayList<TourBean> extraTour = new ArrayList<>();
                        ArrayList<Bean> componenti = new ArrayList<>();
                        HashMap<UUID, ArrayList<Bean>> extraTourLuoghi = new HashMap<>();
                        LuogoDAO luogoDAO = new LuogoDAO();

                        //Recupero il pacchetto e la struttura alberghiera relativa a tale pacchetto
                        PacchettoBean pacchettoBean = modelDAO.retrieveByKey(UUID.fromString(key));
                        beans.add(pacchettoBean);
                        beans.add(hotelDAO.retrieveByKey(pacchettoBean.getId_struttura()));
                        request.setAttribute("arraybeans", beans);

                        //Recupero i possibili extra ristorativi
                        extraRestaurants = modelDAO.retrieveExtraRistoranti(UUID.fromString(key));
                        request.setAttribute("extraristoranti", extraRestaurants);

                        //Recupero i possibili extra tour
                        extraTour = modelDAO.retrieveExtraTour(UUID.fromString(key));
                        for(TourBean tourBean : extraTour){
                            componenti.add(tourBean);
                            componenti.add(luogoDAO.retrieveByKey(tourBean.getId_luogo()));
                            extraTourLuoghi.put(tourBean.getId_luogo(), (ArrayList<Bean>) componenti.clone());
                            componenti.clear();
                        }

                        request.setAttribute("extratourluoghi", extraTourLuoghi);


                        RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/details.jsp");
                        requestDispatcher.forward(request, response);


                    } catch (SQLException e) {
                        System.out.println("Errore byKey");
                        e.printStackTrace();
                        request.setAttribute("errore", e.toString());
                    }
                }
                break;
            case "create":
                doPost(request, response);
                break;
        }

    }
}
