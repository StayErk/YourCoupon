package control;

import model.Bean;
import model.hotel.HotelBean;
import model.hotel.HotelDAO;
import model.pacchetto.PacchettoBean;
import model.restaurant.RestaurantBean;
import model.restaurant.RestaurantDAO;
import model.tour.LuogoBean;
import model.tour.LuogoDAO;
import model.tour.TourBean;
import model.tour.TourDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@WebServlet("/ComponentsPackServlet")
public class ComponentsPackServlet extends HttpServlet {
    static HotelDAO hotelDAO = new HotelDAO();
    static RestaurantDAO restaurantDAO = new RestaurantDAO();
    static TourDAO tourDAO = new TourDAO();
    static LuogoDAO luogoDAO = new LuogoDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String componentPack = request.getParameter("component");
        if(componentPack.equals("hotel") || componentPack.equals("ristoranti") || componentPack.equals("tour")){
            doGet(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = null;
        String componentPack = request.getParameter("component");
        System.out.println(componentPack);
        //Se l'utente ha modificato il parametro component, verrà demandato alla home
        if (componentPack == null || componentPack.equals("") || (!componentPack.equals("hotel") && !componentPack.equals("tour") && !componentPack.equals("ristoranti"))) {
            response.sendRedirect("./index.jsp");
        } else {
            //Andiamo a prelevare dal DB gli hotel, ristoranti o tour
            switch (componentPack) {
                case "hotel":
                    try {
                        ArrayList<HotelBean> hotel = new ArrayList<>(hotelDAO.retrieveAll("", ""));
                        request.setAttribute("componentPack", hotel);
                        request.setAttribute("type", "hotel");
                        System.out.println(hotel);
                        requestDispatcher = this.getServletContext().getRequestDispatcher("/hotel.jsp");
                    } catch (SQLException e) {
                        request.setAttribute("error", e.toString());
                        System.out.println("Errore RetrieveAll Hotel");
                        e.printStackTrace();
                    }
                    break;

                case "ristoranti":
                    try {
                        ArrayList<RestaurantBean> ristoranti = new ArrayList<>(restaurantDAO.retrieveAll("", ""));
                        request.setAttribute("componentPack", ristoranti);
                        request.setAttribute("type", "ristoranti");
                        requestDispatcher = this.getServletContext().getRequestDispatcher("ristoranti.jsp");
                    } catch (SQLException e) {
                        request.setAttribute("error", e.toString());
                        System.out.println("Errore RetriveAll Ristoranti");
                        e.printStackTrace();
                    }
                    break;

                case "tour":
                    try {
                        HashMap<UUID, ArrayList<Bean>> hashTour = new HashMap<>();
                        ArrayList<Bean> beans = new ArrayList<>();
                        ArrayList<TourBean> tour = new ArrayList<>(tourDAO.retrieveAll("",""));
                        for(TourBean t : tour){
                            beans.add(t);
                            beans.add(luogoDAO.retrieveByKey(t.getId_luogo()));
                            hashTour.put(t.getId(), (ArrayList<Bean>) beans.clone());
                            beans.clear();
                        }

                        request.setAttribute("componentPack", hashTour);
                        request.setAttribute("type", "tour");
                        requestDispatcher = this.getServletContext().getRequestDispatcher("tour.jsp");
                    } catch (SQLException e) {
                        request.setAttribute("error", e.toString());
                        System.out.println("Errore RetrieveAll Tour");
                        e.printStackTrace();
                    }

                    break;
            }
            requestDispatcher.forward(request, response);
        }
    }
}
