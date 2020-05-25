package control;

import model.hotel.HotelBean;
import model.hotel.HotelDAO;
import model.restaurant.RestaurantBean;
import model.restaurant.RestaurantDAO;
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

@WebServlet("/ComponentsPackServlet")
public class ComponentsPackServlet extends HttpServlet {
    static HotelDAO hotelDAO = new HotelDAO();
    static RestaurantDAO restaurantDAO = new RestaurantDAO();
    static TourDAO tourDAO = new TourDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String componentPack = request.getParameter("component");
        if(componentPack.equals("hotel") || componentPack.equals("ristoranti") || componentPack.equals("tour")){
            doGet(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String componentPack = request.getParameter("component");

        //Andiamo a prelevare dal DB gli hotel, ristoranti o hotel
        switch (componentPack){
            case "hotel":
                try {
                    ArrayList<HotelBean> hotel = new ArrayList<>(hotelDAO.retrieveAll("",""));
                    request.setAttribute("componentPack", hotel);
                }
                catch (SQLException e) {
                    request.setAttribute("error", e.toString());
                    System.out.println("Errore RetrieveAll Hotel");
                    e.printStackTrace();
                }
                break;

            case "ristoranti":
                try {
                    ArrayList<RestaurantBean> ristoranti = new ArrayList<>(restaurantDAO.retrieveAll("",""));
                    request.setAttribute("componentPack", ristoranti);
                }
                catch (SQLException e) {
                    request.setAttribute("error", e.toString());
                    System.out.println("Errore RetriveAll Ristoranti");
                    e.printStackTrace();
                }
                break;

            case "tour":
                try {
                    ArrayList<TourBean> tour = new ArrayList<>(tourDAO.retrieveAll("",""));
                    request.setAttribute("componentPack", tour);
                }
                catch (SQLException e) {
                    request.setAttribute("error", e.toString());
                    System.out.println("Errore RetrieveAll Tour");
                    e.printStackTrace();
                }

                break;
            default:
                request.setAttribute("invalidComponent", true); //Nel caso l'utente modifichi l'url, inserendo un component che non esiste
                break;
        }

        RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/ComponentsPack.jsp"); //ComponentsPack.jsp si occuperà della visualizzazione
        requestDispatcher.forward(request, response);
    }
}
