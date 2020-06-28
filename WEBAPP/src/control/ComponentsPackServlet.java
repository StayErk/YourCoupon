package control;

import com.google.gson.Gson;
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
        if (componentPack == null || componentPack.equals("") || (!componentPack.equals("hotel") && !componentPack.equals("tour") && !componentPack.equals("ristoranti") && !componentPack.equals("citta") && !componentPack.equals("luogo"))) {
            response.sendRedirect("./index.jsp");
        } else {
            //Andiamo a prelevare dal DB gli hotel, ristoranti o tour
            switch (componentPack) {
                case "citta":
                    try{
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        String filter = request.getParameter("filter");
                        String order = request.getParameter("order");
                        ArrayList<String> citta = new ArrayList<>();
                        ArrayList<HotelBean> hotel = new ArrayList<>();
                        if(filter != null && order != null) {
                            hotel = new ArrayList<>(hotelDAO.retrieveAll(filter, order));
                        } else {
                            hotel = new ArrayList<>(hotelDAO.retrieveAll("", ""));
                        }
                        Gson gson = new Gson();
                        for(HotelBean bean : hotel) {
                            if(!citta.contains(bean.getCitta()))
                                citta.add(bean.getCitta());
                            System.out.println(bean.getCitta() + "\n\n" + citta);
                        }

                        String cittadatornare = gson.toJson(citta);
                        response.setStatus(200);
                        response.getWriter().write(cittadatornare);
                        System.out.println(cittadatornare);
                    } catch (SQLException e) {
                        request.setAttribute("error", e.toString());
                        System.out.println("Errore RetrieveAll Hotel");
                        e.printStackTrace();
                    }
                    break;
                case "luogo":
                    try{
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        ArrayList<LuogoBean> luoghi = new ArrayList<>();
                        HashMap<UUID, String> daritornare = new HashMap<>();
                        LuogoDAO luogoDAO = new LuogoDAO();
                        luoghi = new ArrayList<>(luogoDAO.retrieveAll("",""));
                        Gson gson = new Gson();
                        for(LuogoBean bean : luoghi) {
                            daritornare.putIfAbsent(bean.getId(), bean.getNome());
                            System.out.println(bean);
                        }

                        String risposta = gson.toJson(daritornare);
                        response.setStatus(200);
                        response.getWriter().write(risposta);
                        System.out.println(risposta);
                    } catch (SQLException e) {
                        request.setAttribute("error", e.toString());
                        System.out.println("Errore RetrieveAll Luoghi");
                        e.printStackTrace();
                    }
                    break;
                case "hotel":
                    try {
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        String filter = request.getParameter("filter");
                        String order = request.getParameter("order");
                        ArrayList<HotelBean> hotel = new ArrayList<>();
                        if(filter != null && order != null) {
                            hotel = new ArrayList<>(hotelDAO.retrieveAll(filter, order));
                        } else {
                            hotel = new ArrayList<>(hotelDAO.retrieveAll("", ""));
                        }
                       Gson gson = new Gson();
                       String hoteldaritornare = gson.toJson(hotel);
                       response.setStatus(200);

                       response.getWriter().write(hoteldaritornare);
                    } catch (SQLException e) {
                        request.setAttribute("error", e.toString());
                        System.out.println("Errore RetrieveAll Hotel");
                        e.printStackTrace();
                    }
                    break;

                case "ristoranti":
                    try {
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        String filter = request.getParameter("filter");
                        String order = request.getParameter("order");
                        ArrayList<RestaurantBean> ristoranti = new ArrayList<>();
                        if(filter != null && order != null) {
                           ristoranti = new ArrayList<>(restaurantDAO.retrieveAll(filter, order));
                            System.out.println(filter + order);
                        } else {
                            ristoranti = new ArrayList<>(restaurantDAO.retrieveAll("", ""));
                        }

                        Gson gson = new Gson();
                        String ristorantidaRitornare = gson.toJson(ristoranti);
                        response.setStatus(200);
                        response.getWriter().write(ristorantidaRitornare);
                    } catch (SQLException e) {
                        request.setAttribute("error", e.toString());
                        System.out.println("Errore RetriveAll Ristoranti");
                        e.printStackTrace();
                    }
                    break;

                case "tour":
                    try {
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        HashMap<UUID, ArrayList<Bean>> hashTour = new HashMap<>();
                        ArrayList<Bean> beans = new ArrayList<>();
                        String filter = request.getParameter("filter");
                        String order = request.getParameter("order");
                        ArrayList<TourBean> tour = new ArrayList<>();
                        if(filter != null && order != null) {
                           tour = new ArrayList<>(tourDAO.retrieveAll(filter,order));
                           System.out.println(filter + order);
                        } else {
                          tour = new ArrayList<>(tourDAO.retrieveAll("",""));
                        }
                        for(TourBean t : tour){
                            beans.add(t);
                            beans.add(luogoDAO.retrieveByKey(t.getId_luogo()));
                            hashTour.put(t.getId(), (ArrayList<Bean>) beans.clone());
                            beans.clear();
                        }
                        Gson gson = new Gson();
                        String hashmap = gson.toJson(hashTour);
                        response.setStatus(200);

                        System.out.println("JSON: " + hashmap);
                        response.getWriter().write(hashmap);


                    } catch (SQLException e) {
                        request.setAttribute("error", e.toString());
                        System.out.println("Errore RetrieveAll Tour");
                        e.printStackTrace();
                    }

                    break;
            }
        }
    }
}
