package control;

import model.BackendValidation;
import model.fattura.FatturaBean;
import model.fattura.FatturaDAO;
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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
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
                        String immagine = request.getParameter("immagine");
                        if (checkStelle(stelle) && checkTesto(nome, 20) && checkTesto(indirizzo, 50) && checkTesto(citta, 20) && checkCosto(costoNotte)  && checkNumeroTelefono(numeroTelefono) && checkEmail(email) && checkImmagine(immagine)) {
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
                        String immagine = request.getParameter("immagine");
                        if (checkTesto(nome, 20) && checkTesto(indirizzo, 50) && checkTesto(citta, 20) && checkCosto(costo) && checkNumeroTelefono(numeroTelefono) && checkEmail(email) && checkNumeroTelefono(numeroTelefono) && checkImmagine(immagine)) {
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
        } else if(action != null) {
            if(action.equals("retrievedati")){
                HashMap<String, Object> stats = new HashMap<>();
                statFatture(stats, request);
                statHotel(stats, request);
                statExtra("ristoranti", stats, request);
                statExtra("tour", stats, request);
                request.setAttribute("statistiche", stats);
                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/admin/index.jsp");
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute("errore", true);
                RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/admin/index.jsp");
                requestDispatcher.forward(request, response);
            }
        }
    }

    private void statFatture(HashMap<String, Object> stats, HttpServletRequest request) {
        try {
            FatturaDAO fatturaDAO = new FatturaDAO();
            ArrayList<FatturaBean> fatture = new ArrayList<>(fatturaDAO.retrieveAll("", ""));
            double guadagnoTotale = 0;
            for (FatturaBean fattura : fatture) {
                guadagnoTotale += fattura.getTotale();
            }
            stats.put("guadagni", guadagnoTotale);
            stats.put("numeroFatture", fatture.size());
        } catch (SQLException throwables) {
            request.setAttribute("erroreFatture", true);
            throwables.printStackTrace();
        }
    }

    private void statHotel(HashMap<String, Object> stats, HttpServletRequest request) {
        PacchettoDAO pacchettoDAO = new PacchettoDAO();
        HotelDAO hotelDAO = new HotelDAO();
        ArrayList<PacchettoBean> pacchetti= null;
        try {
            pacchetti = new ArrayList<>(pacchettoDAO.retrieveAll("", ""));
            HashMap<UUID, Integer> contatore = new HashMap<>();
            for(PacchettoBean pacchettoBean : pacchetti) {
                contatore.computeIfPresent(pacchettoBean.getId_struttura(), (id, tot) -> tot+1);
                contatore.putIfAbsent(pacchettoBean.getId_struttura(), 1);
            }
            stats.put("numeroHotel", hotelDAO.retrieveAll("", "").size());
            stats.put("migliorHotel", hotelDAO.retrieveByKey(Collections.max(contatore.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey()));
        } catch (SQLException throwables) {
            request.setAttribute("erroreHotel", true);
            throwables.printStackTrace();
        }

    }

    private void statExtra(String tipo, HashMap<String, Object> stats, HttpServletRequest request) {
        PacchettoDAO pacchettoDAO = new PacchettoDAO();
        if(tipo.equals("ristoranti")) {
            RestaurantDAO restaurantDAO = new RestaurantDAO();
            try {
                HashMap<UUID, Integer> contatore = new HashMap<>();
                ArrayList<PacchettoBean> pacchettoBeans = new ArrayList<>(pacchettoDAO.retrieveAll("", ""));
                for(PacchettoBean pacchettoBean : pacchettoBeans) {
                    ArrayList<RestaurantBean> ristoranti = new ArrayList<>(pacchettoDAO.retrieveExtraRistoranti(pacchettoBean.getId()));
                    System.out.println(ristoranti);
                    for (RestaurantBean ristorante : ristoranti) {
                        contatore.computeIfPresent(ristorante.getId(), (id, tot) -> tot+1);
                        contatore.putIfAbsent(ristorante.getId(), 1);
                    }
                }
                stats.put("numeroRistoranti", restaurantDAO.retrieveAll("", "").size());
                RestaurantBean migliorRistorante;
                if(!contatore.isEmpty()) {
                    migliorRistorante = restaurantDAO.retrieveByKey(Collections.max(contatore.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey());
                }else{
                    migliorRistorante = new RestaurantBean();
                }
                stats.put("migliorRistorante", migliorRistorante);
            } catch (SQLException throwables) {
                request.setAttribute("erroreRistoranti", true);
                throwables.printStackTrace();
            }
        } else if(tipo.equals("tour")) {
            TourDAO tourDAO = new TourDAO();
            LuogoDAO luogoDAO = new LuogoDAO();
            HashMap<UUID, Integer> contatore = new HashMap<>();
            try {
                ArrayList<PacchettoBean> pacchettoBeans = new ArrayList<>(pacchettoDAO.retrieveAll("", ""));
                for(PacchettoBean pacchettoBean : pacchettoBeans) {
                    ArrayList<TourBean> tours = new ArrayList<>(pacchettoDAO.retrieveExtraTour(pacchettoBean.getId()));
                    for (TourBean tour : tours) {
                        contatore.computeIfPresent(tour.getId(), (id, tot) -> tot+1);
                        contatore.putIfAbsent(tour.getId(), 1);
                    }
                }
                LuogoBean migliorTour;
                if(!contatore.isEmpty()) {
                    migliorTour = luogoDAO.retrieveByKey((tourDAO.retrieveByKey(Collections.max(contatore.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey())).getId_luogo());
                } else {
                    migliorTour = new LuogoBean();
                }
                stats.put("numeroTour", tourDAO.retrieveAll("", "").size());
                stats.put("migliorTour", migliorTour);
            } catch (SQLException throwables) {
                request.setAttribute("erroreTour", true);
                throwables.printStackTrace();
            }
        }
    }



}

