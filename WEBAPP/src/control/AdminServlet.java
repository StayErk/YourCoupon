package control;

import model.hotel.HotelDAO;
import model.restaurant.RestaurantDAO;
import model.tour.LuogoDAO;
import model.tour.TourDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet("/user/AdminServlet")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String componente = request.getParameter("component");
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        HotelDAO hotelDao = new HotelDAO();
        RestaurantDAO restaurantDao = new RestaurantDAO();
        TourDAO tourDao = new TourDAO();
        LuogoDAO luogodao = new LuogoDAO();

        if(componente != null && action != null && id!= null){
            switch(action) {
                case "edit":
                    if(componente.equals("hotel")){
                        request.setAttribute("tipo", "hotel");
                        try {
                            request.setAttribute("damodificare", hotelDao.retrieveByKey(UUID.fromString(id)));
                            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/edithotel.jsp");
                            requestDispatcher.forward(request, response);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(componente.equals("ristorante")){
                        request.setAttribute("tipo", "ristorante");
                        try {
                            request.setAttribute("damodificare", restaurantDao.retrieveByKey(UUID.fromString(id)));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(componente.equals("tour")){
                        request.setAttribute("tipo", "tour");
                        try{
                            request.setAttribute("tourdamodoficare", tourDao.retrieveByKey(UUID.fromString(id)));
                            request.setAttribute("luogodamodificare", luogodao.retrieveByKey(tourDao.retrieveByKey(UUID.fromString(id)).getId_luogo()));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                    break;
            }
        }
    }
}
