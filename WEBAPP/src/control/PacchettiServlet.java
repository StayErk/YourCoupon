package control;

import com.google.gson.Gson;
import model.Bean;
import model.hotel.HotelDAO;
import model.pacchetto.PacchettoBean;
import model.pacchetto.PacchettoDAO;
import model.restaurant.RestaurantBean;
import model.tour.LuogoDAO;
import model.tour.TourBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@WebServlet("/PacchettiServlet")
public class PacchettiServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

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
                    System.out.println(persone);
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
                    System.out.println("JSON: " + hashmap);
                    response.getWriter().write(hashmap);

                } catch (SQLException e) {
                    request.setAttribute("errore", e.toString());
                    System.out.println("Errore retrieve");
                    e.printStackTrace();
                }
                break;
            case "bykey":
                String key = request.getParameter("key");
                System.out.println(key);
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
                        System.out.println(extraRestaurants);

                        //Recupero i possibili extra tour
                        extraTour = modelDAO.retrieveExtraTour(UUID.fromString(key));
                        for(TourBean tourBean : extraTour){
                            componenti.add(tourBean);
                            componenti.add(luogoDAO.retrieveByKey(tourBean.getId_luogo()));
                            extraTourLuoghi.put(tourBean.getId_luogo(), (ArrayList<Bean>) componenti.clone());
                            componenti.clear();
                        }

                        request.setAttribute("extratourluoghi", extraTourLuoghi);
                        System.out.println(extraTour);




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

                break;
        }

    }
}
