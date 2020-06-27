package control;

import model.BackendValidation;
import model.hotel.HotelBean;
import model.hotel.HotelDAO;
import model.pacchetto.PacchettoDAO;
import model.restaurant.RestaurantBean;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static model.BackendValidation.*;

@WebServlet("/admin/AdminServlet")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tipo = request.getParameter("tipo");
        String id = request.getParameter("id");
        String action = request.getParameter("action");
        HotelDAO hotelDAO = new HotelDAO();
        RestaurantDAO restaurantDAO = new RestaurantDAO();
        if(tipo != null && action != null) {
            if(tipo.equals("hotel")) {
                switch (action) {
                    case "edit":
                        String nuovoCostoNotte = request.getParameter("costonotte");
                        String nuovoNumeroTelefono = request.getParameter("numeroTelefono");
                        String nuoveStelle = request.getParameter("stelle");
                        if ((checkID(id) && checkStelle(Integer.parseInt(nuoveStelle)) && checkCosto(Double.parseDouble(nuovoCostoNotte)) && checkNumeroTelefono(nuovoNumeroTelefono))) {
                            try {
                                System.out.println("nel try");
                                HotelBean daModificare = hotelDAO.retrieveByKey(UUID.fromString(id));
                                daModificare.setCostoNotte(Double.parseDouble(nuovoCostoNotte));
                                daModificare.setStelle(Integer.parseInt(nuoveStelle));
                                daModificare.setNumeroTelefono(nuovoNumeroTelefono);
                                hotelDAO.doUpdate(daModificare);
                                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/admin/managehotel.jsp");
                                requestDispatcher.forward(request, response);
                            } catch (SQLException throwables) {
                                request.setAttribute("errore", true);
                                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/admin/managehotel.jsp");
                                requestDispatcher.forward(request, response);
                            }
                        } else {
                            request.setAttribute("errore", true);
                            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/admin/managehotel.jsp");
                            requestDispatcher.forward(request, response);
                        }
                        break;
                    case "new":
                        String nome = request.getParameter("nome");
                        String indirizzo = request.getParameter("indirizzo");
                        String citta = request.getParameter("citta");
                        Double costoNotte = Double.parseDouble(request.getParameter("costonotte"));
                        String numeroTelefono = request.getParameter("numeroTelefono");
                        String email = request.getParameter("email");
                        Integer stelle = Integer.parseInt(request.getParameter("stelle"));
                        if (checkStelle(stelle) && checkTesto(nome, 20) && checkTesto(indirizzo, 50) && checkTesto(citta, 20) && checkCosto(costoNotte)  && checkNumeroTelefono(numeroTelefono) && checkEmail(email)) {
                            try {
                                citta = citta.substring(0, 1).toUpperCase() + citta.substring(1);
                                HotelBean daInserire = new HotelBean(UUID.randomUUID(), nome, indirizzo, citta, costoNotte, stelle, "", email, numeroTelefono);
                                hotelDAO.doSave(daInserire);
                                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/admin/managehotel.jsp");
                                requestDispatcher.forward(request, response);
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                                request.setAttribute("errore", true);
                                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/admin/managehotel.jsp");
                                requestDispatcher.forward(request, response);
                            }
                        } else {
                            System.out.println(nome + indirizzo + citta + costoNotte + numeroTelefono + email + stelle);
                            request.setAttribute("errore", true);
                            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/admin/managehotel.jsp");
                            requestDispatcher.forward(request, response);
                        }
                        break;
                }
            }else if (tipo.equals("ristoranti")) {
                switch (action) {
                    case "edit":
                        Double nuovoCosto = Double.parseDouble(request.getParameter("costo"));
                        String nuovonumeroTelefono = request.getParameter("numeroTelefono");
                        String nuovaEmail = request.getParameter("email");
                        if(checkID(id) && checkCosto(nuovoCosto) && nuovonumeroTelefono != null && nuovaEmail != null && checkNumeroTelefono(nuovonumeroTelefono) && checkEmail(nuovaEmail)){
                            try {
                                System.out.println("nel try");
                                RestaurantBean daModificare = restaurantDAO.retrieveByKey(UUID.fromString(id));
                                daModificare.setCosto(nuovoCosto);
                                daModificare.setNumeroTelefono(nuovonumeroTelefono);
                                daModificare.setEmail(nuovaEmail);
                                restaurantDAO.doUpdate(daModificare);
                                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/admin/manageristorante.jsp");
                                requestDispatcher.forward(request, response);
                            } catch (SQLException throwables) {
                                request.setAttribute("errore", true);
                                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/admin/manageristorante.jsp");
                                requestDispatcher.forward(request, response);
                            }
                        }
                        break;
                    case "new":
                        String nome = request.getParameter("nome");
                        String indirizzo = request.getParameter("indirizzo");
                        String citta = request.getParameter("citta");
                        Double costo = Double.parseDouble(request.getParameter("costo"));
                        String numeroTelefono = request.getParameter("numeroTelefono");
                        String email = request.getParameter("email");
                        if (checkTesto(nome, 20) && checkTesto(indirizzo, 50) && checkTesto(citta, 20) && checkCosto(costo) && checkNumeroTelefono(numeroTelefono) && checkEmail(email) && checkNumeroTelefono(numeroTelefono)) {
                            try {
                                citta = citta.substring(0, 1).toUpperCase() + citta.substring(1);
                                RestaurantBean daInserire = new RestaurantBean(UUID.randomUUID(), indirizzo, citta, nome, costo, "", numeroTelefono, email);
                                restaurantDAO.doSave(daInserire);
                                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/admin/manageristorante.jsp");
                                requestDispatcher.forward(request, response);
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                                request.setAttribute("errore", true);
                                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/admin/manageristorante.jsp");
                                requestDispatcher.forward(request, response);
                            }
                        } else {
                            System.out.println("" + checkTesto(nome, 20) +  checkTesto(indirizzo, 50)  + checkTesto(citta, 20) +  checkCosto(costo) +  checkNumeroTelefono(numeroTelefono) +  checkEmail(email) +  checkNumeroTelefono(numeroTelefono));
                            request.setAttribute("errore", true);
                            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/admin/manageristorante.jsp");
                            requestDispatcher.forward(request, response);
                        }
                        break;
                }
                } else {
                request.setAttribute("errore", true);
                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/admin/manageristorante.jsp");
                requestDispatcher.forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String componente = request.getParameter("component");
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        HotelDAO hotelDao = new HotelDAO();
        RestaurantDAO restaurantDao = new RestaurantDAO();
        TourDAO tourDao = new TourDAO();
        LuogoDAO luogodao = new LuogoDAO();

        if(componente != null && action != null){
            switch(action) {
                case "edit":
                    if(checkID(id)) {
                        if (componente.equals("hotel")) {
                            request.setAttribute("tipo", "hotel");
                            try {
                                request.setAttribute("damodificare", hotelDao.retrieveByKey(UUID.fromString(id)));
                                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/admin/editcomponent.jsp");
                                requestDispatcher.forward(request, response);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else if (componente.equals("ristoranti")) {
                            request.setAttribute("tipo", "ristoranti");
                            try {
                                request.setAttribute("damodificare", restaurantDao.retrieveByKey(UUID.fromString(id)));
                                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/admin/editcomponent.jsp");
                                requestDispatcher.forward(request, response);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } else if (componente.equals("tour")) {
                            request.setAttribute("tipo", "tour");
                            try {
                                request.setAttribute("tourdamodoficare", tourDao.retrieveByKey(UUID.fromString(id)));
                                request.setAttribute("luogodamodificare", luogodao.retrieveByKey(tourDao.retrieveByKey(UUID.fromString(id)).getId_luogo()));
                                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/admin/editcomponent.jsp");
                                requestDispatcher.forward(request, response);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    }
                case "new":
                    if(componente.equals("hotel")) {
                        request.setAttribute("tipo", componente);
                        RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/admin/addcomponent.jsp");
                        requestDispatcher.forward(request, response);
                    } else if(componente.equals("ristoranti")) {
                        request.setAttribute("tipo", componente);
                        RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/admin/addcomponent.jsp");
                        requestDispatcher.forward(request, response);
                    }
                    break;
                case "elimina":
                    if (componente.equals("pacchetto")) {
                        PacchettoDAO pacchettoDAO = new PacchettoDAO();
                        try {
                            pacchettoDAO.doDelete(pacchettoDAO.retrieveByKey(UUID.fromString(id)));
                            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/admin/managepacchetti.jsp");
                            requestDispatcher.forward(request, response);
                        } catch (SQLException throwables) {
                            request.setAttribute("errore", true);
                            response.setStatus(500);
                            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/admin/managepacchetti.jsp");
                            requestDispatcher.forward(request, response);
                            throwables.printStackTrace();
                        }
                    } else if(componente.equals("hotel")){
                        HotelDAO hotelDAO = new HotelDAO();
                        try {
                            hotelDAO.doDelete(hotelDAO.retrieveByKey(UUID.fromString(id)));
                            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/admin/managehotel.jsp");
                            requestDispatcher.forward(request, response);
                        } catch (SQLException throwables) {
                            request.setAttribute("errore", true);
                            response.setStatus(500);
                            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/admin/managehotel.jsp");
                            requestDispatcher.forward(request, response);
                            throwables.printStackTrace();
                            throwables.printStackTrace();
                        }
                    } else if(componente.equals("ristoranti")){
                        RestaurantDAO restaurantDAO = new RestaurantDAO();
                        try {
                            restaurantDAO.doDelete(restaurantDAO.retrieveByKey(UUID.fromString(id)));
                            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/admin/manageristorante.jsp");
                            requestDispatcher.forward(request, response);
                        } catch (SQLException throwables) {
                            request.setAttribute("errore", true);
                            response.setStatus(500);
                            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/admin/manageristorante.jsp");
                            requestDispatcher.forward(request, response);
                            throwables.printStackTrace();
                        }
                    }
                    else {
                        request.setAttribute("errore", true);
                        response.setStatus(500);
                        RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/admin/managepacchetti.jsp");
                        requestDispatcher.forward(request, response);
                    }
                    break;
            }
        }
    }


}

